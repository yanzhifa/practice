package com.ldy.validator.collection;

import com.ldy.validator.collection.constraints.EachLength;
import com.ldy.validator.collection.constraints.EachMax;
import com.ldy.validator.collection.constraints.EachMin;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EachTest {

    @EachLength(min = 3, max = 10)
    List<String> strings;

//    @EachSize(min = 4, max = 11)
    List<String> string1s;

//    @EachLength(min = 5, max = 8)
//    @EachSize(min = 3, max = 5)
    List<List<String>> lists;

    @EachMin(value = 3, message = "it's small than {value}")
//    @EachMax(value = 9)
    List<Integer> integers;

//    @Length(min = 2, max = 5)
//    @Size(min = 2, max = 5)
    String name;

//    @EachNotEmpty
    List<String> testL1;

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public List<String> getString1s() {
        return string1s;
    }

    public void setString1s(List<String> string1s) {
        this.string1s = string1s;
    }

    public List<List<String>> getLists() {
        return lists;
    }

    public void setLists(List<List<String>> lists) {
        this.lists = lists;
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTestL1() {
        return testL1;
    }

    public void setTestL1(List<String> testL1) {
        this.testL1 = testL1;
    }

    public static void main(String[] args) {
        EachTest eachTest = new EachTest();
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("123");
        strings.add(null);
        eachTest.setStrings(strings);
        eachTest.setName("a");

        List<List<String>> lists = new ArrayList<>();
        lists.add(strings);
        lists.add(strings);
        eachTest.setLists(lists);

        List<String> string1s = new ArrayList<>();
        string1s.add(null);
        string1s.add("31111111");
        string1s.add(null);
        eachTest.setString1s(string1s);

        List<String> l1 = new ArrayList<>();
        l1.add(null);
        l1.add("1");
        eachTest.setTestL1(l1);

        List<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(2);
        integers.add(2);
        integers.add(10);
        eachTest.setIntegers(integers);

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<EachTest>> set = validator.validate(eachTest);
        for (ConstraintViolation<EachTest> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }

        System.out.println(set.size());
    }

}
