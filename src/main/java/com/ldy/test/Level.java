package com.ldy.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yanz3 on 8/22/16.
 */
public enum Level {
    HIGH(3),  //calls constructor with value 3
    MEDIUM(2),  //calls constructor with value 2
    LOW(1)   //calls constructor with value 1
    ; // semicolon needed when fields / methods follow


    private final int levelCode;

    private Level(int levelCode) {
        this.levelCode = levelCode;
    }

    public static List<Pair<Level, Level>> getCoexist() {
        List<Pair<Level, Level>> coexistPair = new ArrayList<>();
        coexistPair.add(new ImmutablePair<>(Level.HIGH, Level.LOW));
        return coexistPair;
    }

    public int getLevelCode() {
        return levelCode;
    }

    public static void main(String[] args) {

        for (Level l : Level.values()) {

            System.out.println(l.name());
        }

        System.out.println(Level.getCoexist());

        Set<Level> set = new HashSet<>();
        set.add(Level.HIGH);
        set.add(Level.MEDIUM);
        set.stream().filter(s-> s.levelCode > 0).map(Level::getLevelCode).collect(Collectors.toList());
        System.out.println(set.stream().filter(s-> s.levelCode > 0).map(Level::getLevelCode).collect(Collectors.toList()));
    }
}
