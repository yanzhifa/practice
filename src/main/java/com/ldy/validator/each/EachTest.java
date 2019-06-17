package com.ldy.validator.each;

import com.ldy.validator.each.constraints.CollectionConstraint;
import com.ldy.validator.each.constraints.EachLength;
import com.ldy.validator.each.constraints.EachMax;
import com.ldy.validator.each.constraints.EachMin;
import com.ldy.validator.each.constraints.EachNotEmpty;
import com.ldy.validator.each.constraints.EachSize;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class EachTest {

//    @EachLength(min = 3, max = 10)
    @CollectionConstraint(size = @Size(min = 3, max = 10))
    List<String> strings;




//    @EachSize(min = 4, max = 11)
    List<String> string1s;

//    @EachLength(min = 5, max = 8)
//    @EachSize(min = 3, max = 5)
    List<List<String>> lists;

//    @EachMin(value = 3, message = "it's small than {value}")
//    @EachMax(value = 9)
    List<Integer> integers;

//    @Length(min = 2, max = 5)
//    @Size(min = 2, max = 5)
    String name;

//    @EachNotEmpty
    List<String> testL1;
    static boolean b;
    public static void main(String[] args) {


        System.out.println(b);

        Integer[] aa = new Integer[10];
        System.out.println(aa[2]);

        EachTest eachTest = new EachTest();
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("123");
        strings.add("2");
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
