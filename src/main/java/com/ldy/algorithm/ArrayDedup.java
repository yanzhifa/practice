package com.ldy.algorithm;

import java.util.Arrays;

/**
 * Created by yanz3 on 3/8/18.
 */
public class ArrayDedup {

    public static void main(String[] args) {
        int[] arrays = {2, 4, 6, 8};

        System.out.println(arrays.length);

        for (int i = 0; i < arrays.length; i++) {
            System.out.println(arrays[i]);
        }

        Arrays.stream(arrays).forEach(value -> {
            System.out.println(value);
        });


        String resetRootPasswordCommand = "(echo Testesx123! ; echo Testesx123!) | passwd";
        String command = "echo 'Testvxrail' | su root -c \"" + resetRootPasswordCommand + "\"";
        System.out.println(command);
    }
}
