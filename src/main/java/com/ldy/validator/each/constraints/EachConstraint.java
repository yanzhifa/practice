
package com.ldy.validator.each.constraints;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A meta annotation for a collection pseudo constraint validated by EachValidator
 * It's used to specify the actual constraint annotation which validator
 * should be used to validate the target.
 */
@Documented
@Retention(RUNTIME)
@Target(ANNOTATION_TYPE)
public @interface EachConstraint {

    /**
     * Class of the actual constraint annotation. The target collection's items
     * will be validated with the validator of this constraint.
     */
    Class<? extends Annotation> validateAs();
}
