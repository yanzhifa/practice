/*
 * The MIT License
 *
 * Copyright 2013-2014 Jakub Jirutka <jakub@jirutka.cz>.
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
package com.ldy.validator.collection.constraints;


import com.ldy.validator.collection.CommonEachValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @see DecimalMin
 * @see CommonEachValidator
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@EachConstraint(validateAs = DecimalMin.class)
@Constraint(validatedBy = CommonEachValidator.class)
public @interface EachDecimalMin {

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * The {@code String} representation of the min value according to the
     * {@code BigDecimal} string representation.
     *
     * @return value the element must be higher or equal to
     */
    String value();

    /**
     * Specifies whether the specified minimum is inclusive or exclusive.
     * By default, it is inclusive.
     *
     * @return {@code true} if the value must be higher or equal to the specified minimum,
     *         {@code false} if the value must be higher
     *
     * @since Hibernate Validator 5.0.0
     */
    boolean inclusive() default true;
}
