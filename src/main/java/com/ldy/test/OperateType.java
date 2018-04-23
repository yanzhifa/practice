package com.ldy.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yanz3 on 8/23/16.
 */
public enum OperateType {
    NO_OPERATION("no operation"),
    HOST_SHUTDOWN("host shutdown"),
    DISK_ADDITION("disk addition"),
    DISK_REPLACEMENT("disk replacement"),
    CLUSTER_SHUTDOWN("cluster shutdown"),
    UPGRADE("upgrade");

    private final String value;

    OperateType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args) {
        Set<OperateType> typeSet = new HashSet<>();
        typeSet.add(NO_OPERATION);
        typeSet.add(UPGRADE);
        typeSet.add(DISK_REPLACEMENT);
        List<String> operateList = typeSet.parallelStream()
                .filter(type -> type != null)
                .map(OperateType::getValue)
                .collect(Collectors.toList());
        System.out.println(String.valueOf(operateList));
    }
}
