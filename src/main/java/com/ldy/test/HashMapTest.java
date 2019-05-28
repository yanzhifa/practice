package com.ldy.test;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yanz3 on 12/27/16.
 */
public class HashMapTest {
    private static Map<String, String> map = new HashMap<String, String>() {{
        put("a", "AAA");
        put("b", "BBB");
        put("c", "CCC");
    }};

    public static void main(String[] args) {

        String s= map.get("d");
        System.out.println(s);

    }

}
