package com.ldy.reflact;

import java.lang.reflect.Field;

/**
 * Created by yanz3 on 1/31/17.
 */
public class ReflectPointTest {
    public static void main(String[] args) throws Exception {
        ReflectPoint rp = new ReflectPoint();
        System.out.println(rp);
//        setSomeFields(rp);
//        System.out.println(rp);
    }

    private static void setSomeFields(ReflectPoint rp) throws Exception {
        // TODO Auto-generated method stub
        //获得ReflectPoint类中的一个属性str1

        Field field = rp.getClass().getDeclaredField("str1");
        //强制获取属性中的值（私有属性不能轻易获取其值）
        field.setAccessible(true);
        System.out.println(field.get(rp));
        //修改属性的值
        field.set(rp, "123456789");
    }

    private static void getAllFields(ReflectPoint rp) throws Exception {
        // TODO Auto-generated method stub
        Field[] fields = rp.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.get(rp));
        }
    }
}
