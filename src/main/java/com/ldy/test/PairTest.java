package com.ldy.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by yanz3 on 8/17/16.
 */
public class PairTest {

    private boolean isShared;

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public static void main(String[] args) throws Exception {
//        Pair<Boolean, String> pair = new ImmutablePair<>(true, "hello");
//        System.out.println(pair.getLeft() + ":"+ pair.getRight());
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("1",1);
//        map.put("2",2);
//        map.put("3",3);
//        int i = map.get("4");
//        System.out.println(i);


        String nm = "management";
        if (nm!=null) {
//            nm = nm.substring(0, nm.indexOf("@"));
            nm = StringUtils.substringBefore(nm, "@");
        }
        System.out.println(nm);

        String sdf = null;
        if(sdf != null && sdf.contains("a")) {
            System.out.println(sdf);
        }

//        Optional<String> optional = Optional.of(sdf);
//        System.out.println(optional.isPresent());

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        Optional<String> optional1 = list.stream().filter(s -> s.equalsIgnoreCase("c")).findFirst();
        System.out.println(optional1.isPresent());

        String str = null;
        Assert.notNull(str, "not null");
        if(str !=null && str.equals("a")){
            System.out.println(str.getClass().getSimpleName());
        }

        try {
            int i = 10/0;
        } catch (Exception e) {
            new Thread(()-> hello()).start();
            System.out.println(e);
        }

    }

    private static void hello() {
        for(int i=0;i<100;i++) {

        }
        System.out.println("end..");
    }
}
