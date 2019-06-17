/*
 * The MIT License
 *
 * Copyright 2013-2016 Jakub Jirutka <jakub@jirutka.cz>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ldy.validator.collection;

import com.ldy.validator.collection.constraints.EachConstraint;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.hibernate.validator.internal.metadata.core.ConstraintHelper;
import org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ldy.validator.collection.internal.AnnotationUtils.createAnnotation;
import static com.ldy.validator.collection.internal.AnnotationUtils.readAllAttributes;
import static com.ldy.validator.collection.internal.AnnotationUtils.readAttribute;
import static java.util.Collections.unmodifiableMap;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Common validator for collection constraints that validates each element of
 * the given collection.
 */
@Slf4j
public class CommonEachValidator implements ConstraintValidator<Annotation, Collection<?>> {

    // injected by container, or set default during initialization
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    // after initialization it's read-only
    private ConstraintDescriptor descriptor;

    // after initialization it's read-only
    private Map<Class, Class<? extends ConstraintValidator<?, ?>>> validators;

    // modifiable after initialization; must be thread-safe!
    private Map<Class, ConstraintValidator> validatorInstances;

    public void initialize(Annotation eachAnnotation) {

        Class<? extends Annotation> eachAType = eachAnnotation.annotationType();
        log.trace("Initializing CommonEachValidator for {}", eachAType);

        validatorInstances = new ConcurrentHashMap<>(2);

        if (eachAType.isAnnotationPresent(EachConstraint.class)) {
            Class constraintClass = eachAType.getAnnotation(EachConstraint.class).validateAs();
            Annotation constraint = createConstraintAndCopyAttributes(constraintClass, eachAnnotation);
            descriptor = createConstraintDescriptor(constraint);
        } else {
            throw new IllegalArgumentException(String.format("%s is not annotated with @EachConstraint", eachAType.getName()));
        }
        validators = categorizeValidatorsByType(descriptor.getConstraintValidatorClasses());
        Validate.notEmpty(validators,
                "No validator found for constraint: %s", descriptor.getAnnotation().annotationType());
    }

    public boolean isValid(Collection<?> collection, ConstraintValidatorContext context) {
        if (collection == null || collection.isEmpty()) {
            return true;  //nothing to validate here
        }
        context.disableDefaultConstraintViolation();  //do not add wrapper's message

        int index = 0;
        for (Iterator<?> it = collection.iterator(); it.hasNext(); index++) {
            Object element = it.next();

            ConstraintValidator validator = element != null
                    ? getValidatorInstance(element.getClass())
                    : getAnyValidatorInstance();
            validator.initialize(descriptor.getAnnotation());
            if (!validator.isValid(element, context)) {
                log.debug("Element [{}] = '{}' is invalid according to: {}",
                        index, element, validator.getClass().getName());

                String message = readAttribute(descriptor.getAnnotation(), "message", String.class);
                context.buildConstraintViolationWithTemplate(message)
                        .addBeanNode()
                        .inIterable()
                        .atIndex(index)
                        .addConstraintViolation();

                return false;
            }
        }
        return true;
    }

    protected ConstraintDescriptor createConstraintDescriptor(Annotation annotation) {
        try {
            Class[] constructorArgs = new Class[]{ConstraintHelper.class, Member.class, Annotation.class, ElementType.class};
            Constructor<ConstraintDescriptorImpl> constructor = ConstraintDescriptorImpl.class.getConstructor(constructorArgs);
            return constructor.newInstance(new ConstraintHelper(), null, annotation, ElementType.LOCAL_VARIABLE);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException(ex);
        }
    }

    protected <T extends ConstraintValidator<?, ?>>
    Map<Class, Class<? extends T>> categorizeValidatorsByType(List<Class<? extends T>> validatorClasses) {

        Map<Class, Class<? extends T>> validators = new LinkedHashMap<>(10);

        for (Class<? extends T> validator : validatorClasses) {
            Class<?> type = determineTargetType(validator);
            if (type.isArray()) continue;

            log.trace("Found validator {} for type {}", validator.getName(), type.getName());
            validators.put(type, validator);
        }
        return unmodifiableMap(validators);
    }

    protected Class<?> determineTargetType(Class<? extends ConstraintValidator<?, ?>> validatorClass) {
        TypeVariable<?> typeVar = ConstraintValidator.class.getTypeParameters()[1];
        return TypeUtils.getRawType(typeVar, validatorClass);
    }

    /**
     * Returns initialized validator instance for the specified object type.
     * Instances are cached.
     *
     * @param type Type of the object to be validated.
     */
    protected ConstraintValidator getValidatorInstance(Class<?> type) {
        ConstraintValidator validator = validatorInstances.get(type);

        if (validator == null) {
            validator = findAndInitializeValidator(type);
            validatorInstances.put(type, validator);
        }
        return validator;
    }

    /**
     * Returns initialized validator instance for any object type. This is used
     * when the object to be validated is <tt>null</tt> so we can't determine
     * it's type. Instances are cached.
     */
    protected ConstraintValidator getAnyValidatorInstance() {

        if (validatorInstances.isEmpty()) {
            Class type = validators.keySet().iterator().next();
            return findAndInitializeValidator(type);

        } else {
            return validatorInstances.values().iterator().next();
        }
    }

    protected ConstraintValidator findAndInitializeValidator(Class<?> type) {
        log.trace("Looking for validator for type: {}", type.getName());

        for (Class<?> clazz : validators.keySet()) {
            if (clazz.isAssignableFrom(type)) {

                Class validatorClass = validators.get(clazz);

                log.trace("Initializing validator: {}", validatorClass.getName());
                return factory.getConstraintValidatorFactory().getInstance(validatorClass);
            }
        }
        throw new IllegalArgumentException("No validator found for type: " + type.getName());
    }


    /**
     * Instantiates constraint of the specified type and copies values of all
     * the common attributes from the given source constraint (of any type)
     * to it.
     *
     * <p>If the source constraint's {@code message} is empty, then it will
     * <b>not</b> copy it (so the default {@code message} of the target
     * constraint will be preserved).</p>
     *
     * @param constraintType Type of the constraint to create.
     * @param source         Any annotation to copy attribute values from.
     * @return An instance of the specified constraint.
     */
    protected <T extends Annotation> T createConstraintAndCopyAttributes(Class<T> constraintType, Annotation source) {
        Map<String, Object> attributes = readAllAttributes(source);

        // if message is not set, keep message from original constraint instead
        if (isEmpty((String) attributes.get("message"))) {
            attributes.remove("message");
        }
        return createAnnotation(constraintType, attributes);
    }
}
