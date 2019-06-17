
package com.ldy.validator.each.constraints;


import com.ldy.validator.each.CollectionValidator;
import org.hibernate.validator.constraints.Length;
import sun.reflect.annotation.AnnotationType;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A meta annotation for a constraints pseudo constraints validated by EachValidator.
 * It's used to specify the actual constraints annotation which validator
 * should be used to validate the target.
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = CollectionValidator.class)
public @interface CollectionConstraint {

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };



    Size size() default @Size;
    Length length() default @Length;
    NotNull notNull() default @NotNull;
    NotEmpty notEmpty() default @NotEmpty;
    Max max() default @Max(value = Long.MAX_VALUE);
    Min min() default @Min(value = 0);

}
