package com.ldy.collection;

import com.ldy.test.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanz3 on 3/14/17.
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        map1.put("192.168.1.101", "host1");
        map1.put("192.168.1.102", "host2");
        map1.put("192.168.1.103", "host3");
        System.out.println(map1);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("192.168.1.101", "host1");
        map.put("192.168.1.102", "host2");
        map.put("192.168.1.103", "host3");
        System.out.println(map);

        System.out.println(File.separator);


        List<Person> hostsManagementAccountList = Collections.emptyList();
        List<Person> managementAccountList = new ArrayList<>();
        managementAccountList.add(new Person("1",1));
        managementAccountList.add(new Person("2",2));
        managementAccountList.forEach(hostsManagementAccount -> {
            Person managementAccount = hostsManagementAccount;
            managementAccount.setAge(hostsManagementAccount.getAge()+1);
            hostsManagementAccountList.add(managementAccount);
        });
        System.out.println(hostsManagementAccountList);
    }
}
