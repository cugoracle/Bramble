package com.bramble;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class BrambleConsole {
    public static ThreadMXBean TMX = ManagementFactory.getThreadMXBean();
    public static int maxDepth;
    public static long interverl;
    public static boolean isRunning = true;
    public static String path;
    public static int times;
    public static long time;
    public static String logtime;
}
