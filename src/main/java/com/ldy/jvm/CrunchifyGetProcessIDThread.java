package com.ldy.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

/**
 * Created by yanz3 on 2/1/17.
 */
public class CrunchifyGetProcessIDThread {
    public static void main(String[] args) {
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();

        String jvmName = runtimeBean.getName();
        System.out.println("JVM Name = " + jvmName);
        long pid = Long.valueOf(jvmName.split("@")[0]);
        System.out.println("JVM PID  = " + pid);

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();

        int peakThreadCount = bean.getPeakThreadCount();
        System.out.println("Peak Thread Count = " + peakThreadCount);
    }
}
