/*
 * Decompiled with CFR 0.152.
 */
package Ly.mail.plugin.sql;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MySQLExecutorService {
    private static ExecutorService thread;

    public static void setupExecutorService() {
        thread = Executors.newFixedThreadPool(1);
    }

    public static ExecutorService getThread() {
        return thread;
    }

    public MySQLExecutorService() {
        MySQLExecutorService IiiIiIiiII;
    }
}

