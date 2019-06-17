
package com.ldy.validator.each;

import com.ldy.validator.each.constraints.EachConstraint;
import com.ldy.validator.each.internal.AnnotationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Common validator for collection constraints that validates each element of
 * the given collection.
 */
@Slf4j
public class EachValidator implements ConstraintValidator<Annotation, Collection<?>> {

    // set default during initialization
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    // after initialization it's read-only
    private ConstraintDescriptor descriptor;

    // after initialization it's read-only
    private Map<Class, Class<? extends ConstraintValidator<?, ?>>> validators;

    // modifiable after initialization
    private Map<Class, ConstraintValidator> validatorInstances;

    @Override
    public void initialize(Annotation eachAnnotation) {

        Class<? extends Annotation> annotationType = eachAnnotation.annotationType();
        log.trace("Initializing EachValidator for {}", annotationType);

        validatorInstances = new ConcurrentHashMap<>(2);

        if (annotationType.isAnnotationPresent(EachConstraint.class)) {
            Class constraintClass = annotationType.getAnnotation(EachConstraint.class).validateAs();
            Annotation constraint = createConstraintAndCopyAttributes(constraintClass, eachAnnotation);
            descriptor = createConstraintDescriptor(constraint);
        } else {
            throw new IllegalArgumentException(String.format("%s is not annotated with @EachConstraint.", annotationType.getName()));
        }
        validators = categorizeValidatorsByType(descriptor.getConstraintValidatorClasses());
        Validate.notEmpty(validators, "No validator found for constraint: %s", descriptor.getAnnotation().annotationType());
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
                log.debug("Element [{}] = '{}' is invalid according to: {}", index, element, validator.getClass().getName());
                String message = AnnotationUtils.readAttribute(descriptor.getAnnotation(), "message", String.class);
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

        Map<Class, Class<? extends T>> validatorMap = new LinkedHashMap<>(10);

        for (Class<? extends T> validator : validatorClasses) {
            Class<?> type = determineTargetType(validator);
            if (type.isArray()) continue;

            log.trace("Found validator {} for type {}", validator.getName(), type.getName());
            validatorMap.put(type, validator);
        }
        return Collections.unmodifiableMap(validatorMap);
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
        Map<String, Object> attributes = AnnotationUtils.readAllAttributes(source);

        // if message is not set, keep message from original constraint instead
        if (StringUtils.isEmpty((String) attributes.get("message"))) {
            attributes.remove("message");
        }
        return AnnotationUtils.createAnnotation(constraintType, attributes);
    }

}
