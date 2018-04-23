package com.ldy.test;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class MainTest {
    public static void main(String[] args) {
        Map<Integer, List<Integer>>  map = DGTopology.QUANTA.getCacheCapacityMapping();
        System.out.println(map);
        
        Map<String, String> m = new HashMap<>();
        m.put("a", "aaa");
        
        m.put("a", "bbb");
        System.out.println(m.get("b"));
        
        String[] str = "asdf".split("\\.");
        System.out.println(str);
        String st = "asdf";
        System.out.println(st.toLowerCase());

        String s = null;
        System.out.println(1==1?0:s.toString());

        List<Integer> list = new ArrayList<>();
        list.add(null);
        System.out.println(list.contains(null));



        int[]a={3,1,2,4};
        int[]b={1,2,3,4};
        System.out.println(Arrays.equals(a, b));

        List<Pair<String, String>> listPair = new ArrayList<>();
        listPair.add(new ImmutablePair<>("1","11"));
        listPair.add(new ImmutablePair<>("2","22"));
        listPair.add(new ImmutablePair<>("3","33"));
        listPair.forEach(pair -> System.out.println(pair.getLeft()));
        listPair = new ArrayList<>();
        long l1 = listPair.parallelStream().filter(pair -> {
           if(pair.getLeft().equals("1")){
               return true;
           } else
               return false;
        }).count();
        System.out.println(l1);

        Person person = null;
        int result = buildPerson(person);
        System.out.println(result + " : "+person);
    }


    private static int buildPerson(Person person) {
        Person p = new Person("test", 12);
        person = p;
        return 0;
    }
}
