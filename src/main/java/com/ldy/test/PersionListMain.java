package com.ldy.test;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanz3 on 8/12/16.
 */
public class PersionListMain {

    List<Person> persons;
    Map<Integer, List<Integer>> map;
    List<String> strings;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Map<Integer, List<Integer>> getMap() {
        return map;
    }

    public void setMap(Map<Integer, List<Integer>> map) {
        this.map = map;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public String toString() {
        return "PersionListMain{" +
                "persons=" + persons +
                ", map=" + map +
                ", strings=" + strings +
                '}';
    }

    public static void main(String[] args) {
        PersionListMain plist = new PersionListMain();
        Person p1 = new Person("1111", 1);
        p1.setAge(1);
        p1.setName("1111");
        Person p2 = new Person("2222", 2);
        p2.setAge(2);
        p2.setName("2222");

        Person[] people = new Person[2];
        people[0] = p1;
        people[1] = p2;
        System.out.println("People[]:" + Arrays.deepToString(people));

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        plist.setPersons(list);

        List<Integer> in = new ArrayList<>();
        in.add(11);
        in.add(22);
        Map<Integer, List<Integer>> m = new HashMap<>();
        m.put(0, in);
        plist.setMap(m);

        System.out.println(plist);

        String path = "/mnt/lcm-bundle-repo/vxrack/vxmanager-store-repo/";
//        String path = "/mnt/lcm-bundle-repo/asdf/";

        System.out.println(StringUtils.substring(path, 0, StringUtils.ordinalIndexOf(path, "/", 3)));
        System.out.println(StringUtils.ordinalIndexOf(path, "/", 3));

    }
}
