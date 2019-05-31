package com.ldy.validator;

import lombok.Data;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;

@Data
public class Dog implements Animal {
    private String name;
    private String ownerName;

    @NotEmpty(message = "type of the dog may be empty")
    private String type;


    public static void main(String[] args) {
        Dog dog = new Dog();
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Dog>> set = validator.validate(dog);
        for (ConstraintViolation<Dog> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }
    }

}
