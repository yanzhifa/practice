package com.ldy.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class JsonTest {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        JsonTest test = new JsonTest();
        Entity e = test.new Entity();
        e.setId("12345");
        e.setaFloat(1.5f);
//        e.setbFloat(2.6f);
        // e.setType(Type.A);

        Map<String, String> map = new HashMap<>();
        map.put("1", "111");
        map.put("2", "222");
        map.put("3", "333");
        map.put("4", "444");
        e.setMap(map);

        JSONObject json = new JSONObject(map);
        json.put("id", e.getId());
        json.put("type", e.getType());
        System.out.println(json);

        Field[] f = e.getClass().getDeclaredFields();

        for (Field fs : f) {
            fs.setAccessible(true);
            System.out.println(fs.getName() + ":" + fs.getType());
            if(fs.getType().equals(Float.class)) {
                Float aFloat  = (Float)fs.get(e);
                System.out.println(aFloat);
            }

        }
        System.out.println(f);

        Method m1 = e.getClass().getMethod("setType", Class.forName("com.ldy.json.Type"));
        System.out.println(m1.getName());

        m1.invoke(e, Type.A);
        System.out.println("-------------");
        System.out.println(e.getType());
        System.out.println("-------------");

        // System.out.println(m1.getName());
        Method[] m = e.getClass().getDeclaredMethods();
        for (Method ms : m) {
            if (ms.getName().startsWith("get")) {
                System.out.println(ms.getName() + ":" + ms.getReturnType().getName());
                Object obj;
                try {
                    obj = ms.invoke(e);
                    System.out.println(obj);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        }

        System.out.println("----------------");
        System.out.println(e);
        test.add(e);
        System.out.println(e);

    }

    public void add(Entity entity) {
        entity.setType(Type.B);
    }

    class Entity {
        Float aFloat;
//        float bFloat;
        String id;
        Type type;
        Map<String, String> map = new HashMap<>();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Float getaFloat() {
            return aFloat;
        }

        public void setaFloat(Float aFloat) {
            this.aFloat = aFloat;
        }

//        public float getbFloat() {
//            return bFloat;
//        }
//
//        public void setbFloat(float bFloat) {
//            this.bFloat = bFloat;
//        }
    }
}
