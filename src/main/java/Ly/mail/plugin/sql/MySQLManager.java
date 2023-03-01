/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package Ly.mail.plugin.sql;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class MySQLManager {
    static String database;
    static String Password;
    static int port;
    static BukkitTask task;
    static String Name;
    static String ip;
    static boolean sqlenable;
    private static MySQLConnection mysql;

    public static void clearDataBase() {
        mysql.clearTable(ShitLyMail .getInstance().getName());
    }

    public static void onLogin() {
        MySQLExecutorService.setupExecutorService();
        if (sqlenable) {
            MySQLExecutorService.getThread().execute(() -> {
                mysql = new MySQLConnection();
                if (!mysql.isConnection()) {
                    ShitLyMail .sql = false;
                    return;
                }
                String string = ShitLyMail .getInstance().getName();
                if (!mysql.isExists(string)) {
                    mysql.createTable(string, "uuid", "owner", "template", "endtime");
                    Bukkit.getConsoleSender().sendMessage(new StringBuilder().insert(0, "\u68c0\u6d4b\u5230Mysql\u8868\u4e0d\u5b58\u5728: ").append(string).append(" \u5df2\u81ea\u52a8\u751f\u6210").toString());
                }
                ShitLyMail .sql = true;
            });
            if (task != null) {
                task.cancel();
            }
            task = new BukkitRunnable(){

                public void run() {
                    if (!sqlenable) {
                        1 IiiIiIiiII;
                        IiiIiIiiII.cancel();
                    }
                    if (!mysql.isValid(3)) {
                        mysql.connect();
                    }
                }
                {
                    1 IiiIiIiiII;
                }
            }.runTaskTimerAsynchronously((Plugin)ShitLyMail .getInstance(), 10L, 20L);
        }
    }

    public static void shutdown() {
        mysql.closeConnection();
    }

    public static String get(String IiiIiIiiII, String IiiIiIiiII2) {
        if (!mysql.isConnection()) {
            mysql = new MySQLConnection();
        }
        if (mysql.isExists(ShitLyMail .getInstance().getName(), "uuid", IiiIiIiiII)) {
            return String.valueOf(mysql.getValue(ShitLyMail .getInstance().getName(), "uuid", (Object)IiiIiIiiII, IiiIiIiiII2));
        }
        return "";
    }

    public static MySQLConnection getMysql() {
        return mysql;
    }

    public static List<Object> getAllMail() {
        return mysql.getValues(ShitLyMail .getInstance().getName(), "uuid", -1);
    }

    static {
        sqlenable = ShitLyMail .config.getBoolean("mysql.enable");
        ip = ShitLyMail .config.getString("mysql.ip");
        database = ShitLyMail .config.getString("mysql.databasename");
        Name = ShitLyMail .config.getString("mysql.username");
        Password = ShitLyMail .config.getString("mysql.password");
        port = ShitLyMail .config.getInt("mysql.port");
    }

    public static void set(String IiiIiIiiII, String IiiIiIiiII2, Object IiiIiIiiII3) {
        if (!mysql.isConnection()) {
            mysql = new MySQLConnection();
        }
        if (!mysql.isExists(ShitLyMail .getInstance().getName(), "uuid", IiiIiIiiII)) {
            mysql.intoValue(ShitLyMail .getInstance().getName(), IiiIiIiiII, "", "", "");
            mysql.setValue(ShitLyMail .getInstance().getName(), "uuid", IiiIiIiiII, IiiIiIiiII2, String.valueOf(IiiIiIiiII3));
            return;
        }
        mysql.setValue(ShitLyMail .getInstance().getName(), "uuid", IiiIiIiiII, IiiIiIiiII2, String.valueOf(IiiIiIiiII3));
    }

    public MySQLManager() {
        MySQLManager IiiIiIiiII;
    }

    public static void delete(String IiiIiIiiII) {
        if (!mysql.isConnection()) {
            mysql = new MySQLConnection();
        }
        if (mysql.isExists(ShitLyMail .getInstance().getName(), "owner", IiiIiIiiII)) {
            mysql.deleteValue(ShitLyMail .getInstance().getName(), "owner", IiiIiIiiII);
        }
    }

    public static boolean has(String IiiIiIiiII) {
        if (!mysql.isConnection()) {
            mysql = new MySQLConnection();
        }
        return mysql.isExists(ShitLyMail .getInstance().getName(), "uuid", IiiIiIiiII);
    }
}

