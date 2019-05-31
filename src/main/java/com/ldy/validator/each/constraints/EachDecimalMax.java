
package com.ldy.validator.each.constraints;


import com.ldy.validator.each.EachValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMax;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @see DecimalMax
 * @see EachValidator
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@EachNotNull
@EachConstraint(validateAs = DecimalMax.class)
@Constraint(validatedBy = EachValidator.class)
public @interface EachDecimalMax {

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * The {@code String} representation of the max value according to the
     * {@code BigDecimal} string representation.
     *
     * @return value the element must be lower or equal to
     */
    String value();

    /**
     * Specifies whether the specified maximum is inclusive or exclusive.
     * By default, it is inclusive.
     *
     * @return {@code true} if the value must be lower or equal to the specified maximum,
     *         {@code false} if the value must be lower
     *
     * @since Hibernate Validator 5.0.0
     */
    boolean inclusive() default true;
}
