package com.ldy.ip;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanz3 on 6/19/17.
 */
public class NetMask {

    public static void main(String[] args) {


        Map<String, String> netmaskMappings = new HashMap<String, String>();
        Integer[] parts = {255, 255, 255, 255};
        Integer m = 32;
        for (int i = 3; i >= 0; --i) {
            for (int j = 1; j <= 128; j = j * 2) {
                String ip = parts[0].toString() + "." + parts[1].toString()
                        + "." + parts[2].toString() + "." + parts[3].toString();
                String mask = m.toString();
                netmaskMappings.put(ip, mask);
                netmaskMappings.put(mask, ip);
                parts[i] -= j;
                --m;
            }
        }
        System.out.println(netmaskMappings);
    }
}
