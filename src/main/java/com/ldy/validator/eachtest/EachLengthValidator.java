package com.ldy.validator.eachtest;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.hibernate.validator.internal.metadata.core.ConstraintHelper;
import org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;
import static org.apache.commons.lang3.ClassUtils.isAssignable;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class EachLengthValidator implements ConstraintValidator<Annotation, Collection<?>> {

    private Map<Class, Class<? extends ConstraintValidator<?, ?>>> validators;

    public void initialize(Annotation annotation) {
        Class constraintClass = annotation.annotationType().getAnnotation(EachConstraint.class).validateAs();
        try {
            Annotation constraint = createConstraintAndCopyAttributes(constraintClass, annotation);
            ConstraintDescriptor descriptor = newInstance(constraint);
            validators = categorizeValidatorsByType(descriptor.getConstraintValidatorClasses());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    protected <T extends Annotation> T createConstraintAndCopyAttributes(Class<T> constraintType, Annotation source) {
        Map<String, Object> attributes = readAllAttributes(source);

        // if message is not set, keep message from original constraint instead
        if (isEmpty((String) attributes.get("message"))) {
            attributes.remove("message");
        }
        return createAnnotation(constraintType, attributes);
    }
    public static <T extends Annotation> T createAnnotation(Class<T> annotationType, Map<String, Object> attributes) {

        // check if given attributes are defined in annotation
        for (Iterator<String> it = attributes.keySet().iterator(); it.hasNext(); ) {
            String name = it.next();
            Object value = attributes.get(name);

            if (value == null) {
                System.out.println("Attribute's value must not be null; will be ignored");
                it.remove();
                continue;
            }
            if (!hasAttribute(annotationType, name)) {
//                System.out.println("Annotation {} does not define attribute: {}; will be ignored",
//                        annotationType.getName(), name);
                it.remove();
                continue;
            }
            Class<?> attrType = getAttributeType(annotationType, name);

            Validate.isTrue(isAssignable(value.getClass(), attrType),
                    "Attribute '%s' expects %s, but given: %s (%s)",
                    name, attrType.getName(), value, value.getClass().getName());
        }
        // check if required attributes are given
        for (Method m : annotationType.getDeclaredMethods()) {
            Validate.isTrue(attributes.containsKey(m.getName()) || m.getDefaultValue() != null,
                    "Missing required attribute: %s", m.getName());
        }

        AnnotationDescriptor<T> descriptor = AnnotationDescriptor.getInstance(annotationType, attributes);

        return AnnotationFactory.create(descriptor);
    }

    public static boolean hasAttribute(Class<? extends Annotation> annotationType, String attributeName) {

        for (Method m : annotationType.getDeclaredMethods()) {
            if (m.getName().equals(attributeName)) {
                return true;
            }
        }
        return false;
    }

    public static Class<?> getAttributeType(Class<? extends Annotation> annotationType, String attributeName) {
        try {
            return annotationType.getDeclaredMethod(attributeName).getReturnType();

        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException(String.format(
                    "No such attribute %s in %s", attributeName, annotationType.getName()), ex);
        }
    }

    public static Map<String, Object> readAllAttributes(Annotation annotation) {
        Map<String, Object> attributes = new HashMap<>();

        for (Method method : annotation.annotationType().getDeclaredMethods()) {
            try {
                Object value = method.invoke(annotation);
                attributes.put(method.getName(), value);

            } catch (IllegalAccessException | InvocationTargetException ex) {
                throw new IllegalStateException(ex);
            }
        }
        return attributes;
    }
    private ConstraintDescriptorImpl newInstance(Annotation annotation) throws ReflectiveOperationException {
        Constructor<ConstraintDescriptorImpl> constructor = ConstraintDescriptorImpl.class.getConstructor(getConstructorArguments());
        return constructor.newInstance(new ConstraintHelper(), null, annotation, ElementType.LOCAL_VARIABLE);
    }

    private Class[] getConstructorArguments() {
        return new Class[]{ConstraintHelper.class, Member.class, Annotation.class, ElementType.class};
    }

    protected <T extends ConstraintValidator<?, ?>>
    Map<Class, Class<? extends T>> categorizeValidatorsByType(List<Class<? extends T>> validatorClasses) {

        Map<Class, Class<? extends T>> validators = new LinkedHashMap<>(10);

        for (Class<? extends T> validator : validatorClasses) {
            Class<?> type = determineTargetType(validator);
            if (type.isArray()) continue;

            validators.put(type, validator);
        }
        return unmodifiableMap(validators);
    }

    protected Class<?> determineTargetType(Class<? extends ConstraintValidator<?, ?>> validatorClass) {
        TypeVariable<?> typeVar = ConstraintValidator.class.getTypeParameters()[1];
        return TypeUtils.getRawType(typeVar, validatorClass);
    }

    @Override
    public boolean isValid(Collection<?> collection, ConstraintValidatorContext context) {

        if (collection == null || collection.isEmpty()) {
            return true;  //nothing to validate here
        }

        System.out.println(validators.values());

        return false;
    }

}
