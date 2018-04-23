package com.ldy.cache;

import java.util.LinkedHashMap;

public class ECacheTest {

    public static void main(String[] args) {

        LinkedHashMap<String, Object> linkedMap = new LinkedHashMap<String, Object>();
        
        linkedMap.put("1", "The first obj");
        linkedMap.put("1", "The second obj");
        System.out.println(linkedMap);

    }
}
