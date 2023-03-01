/*
 * Decompiled with CFR 0.152.
 */
package Ly.mail.plugin.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MySQLConnection {
    String connectionUrl;
    String user;
    String url;
    String port;
    String password;
    boolean fallReconnection = true;
    String database;
    static boolean mysql = ShitLyMail .config.getBoolean("mysql.enable");
    Connection connection;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object getValue(String IiiIiIiiII62, String IiiIiIiiII2, Object IiiIiIiiII3, String IiiIiIiiII4) {
        MySQLConnection IiiIiIiiII5;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII5.connection.prepareStatement(new StringBuilder().insert(0, "select * from `").append((String)IiiIiIiiII62).append("` where `").append(IiiIiIiiII2).append("` = ? limit 1").toString());
            preparedStatement.setObject(1, IiiIiIiiII3);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                IiiIiIiiII62 = resultSet.getObject(IiiIiIiiII4);
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                return IiiIiIiiII62;
            }
            IiiIiIiiII5.freeResult(resultSet, preparedStatement);
        }
        catch (Exception IiiIiIiiII62) {
            try {
                IiiIiIiiII5.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII5.print("\u9519\u8bef\u7c7b\u578b: getValue(String,String,Object,String);");
                IiiIiIiiII5.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII62.getMessage());
                if (IiiIiIiiII5.fallReconnection && IiiIiIiiII62.getMessage().contains("closed")) {
                    IiiIiIiiII5.connect();
                }
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
            }
            catch (Throwable IiiIiIiiII62) {
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII62;
            }
        }
        return null;
    }

    public boolean editColumn(String IiiIiIiiII, String IiiIiIiiII2, Column IiiIiIiiII3) {
        MySQLConnection IiiIiIiiII4;
        return IiiIiIiiII4.execute("alter table " + IiiIiIiiII + " change `" + IiiIiIiiII2 + "` " + IiiIiIiiII3.toString());
    }

    public String getDatabase() {
        MySQLConnection IiiIiIiiII;
        return IiiIiIiiII.database;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean intoValue(String IiiIiIiiII, Object ... IiiIiIiiII2) {
        MySQLConnection IiiIiIiiII3;
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : IiiIiIiiII2) {
            stringBuilder.append("?, ");
        }
        Object object = null;
        ResultSet resultSet = null;
        try {
            object = IiiIiIiiII3.connection.prepareStatement(new StringBuilder().insert(0, "insert into `").append(IiiIiIiiII).append("` values(null, ").append(stringBuilder.substring(0, stringBuilder.length() - 2)).append(")").toString());
            for (int i = 0; i < IiiIiIiiII2.length; ++i) {
                object.setObject(i + 1, IiiIiIiiII2[i]);
            }
            object.executeUpdate();
        }
        catch (Exception exception) {
            IiiIiIiiII3.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
            IiiIiIiiII3.print("\u9519\u8bef\u7c7b\u578b: intoValue(String,Object...);");
            IiiIiIiiII3.print("\u9519\u8bef\u539f\u56e0: " + exception.getMessage());
            if (IiiIiIiiII3.fallReconnection && exception.getMessage().contains("closed")) {
                IiiIiIiiII3.connect();
            }
        }
        finally {
            IiiIiIiiII3.freeResult(resultSet, (PreparedStatement)object);
        }
        return false;
    }

    public boolean isValid(int IiiIiIiiII3) {
        try {
            MySQLConnection IiiIiIiiII2;
            return IiiIiIiiII2.connection.isValid(3);
        }
        catch (SQLException IiiIiIiiII3) {
            return false;
        }
    }

    public Connection getConnection() {
        MySQLConnection IiiIiIiiII;
        return IiiIiIiiII.connection;
    }

    public void print(String IiiIiIiiII) {
        System.out.println(new StringBuilder().insert(0, "[").append(ShitLyMail .getInstance().getName()).append("] ").append(IiiIiIiiII).toString());
    }

    public boolean deleteColumn(String IiiIiIiiII, String IiiIiIiiII2) {
        MySQLConnection IiiIiIiiII3;
        return IiiIiIiiII3.execute("alter table `" + IiiIiIiiII + "` drop `" + IiiIiIiiII2 + "`");
    }

    public void closeConnection() {
        try {
            MySQLConnection IiiIiIiiII;
            IiiIiIiiII.connection.close();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public MySQLConnection() {
        IiiIiIiiII(ShitLyMail .config.getString("mysql.ip"), ShitLyMail .config.getString("mysql.username"), String.valueOf(ShitLyMail .config.getInt("mysql.port")), ShitLyMail .config.getString("mysql.password"), ShitLyMail .config.getString("mysql.databasename"));
        MySQLConnection IiiIiIiiII;
    }

    public LinkedList<Map<String, Object>> getValues(String IiiIiIiiII, String IiiIiIiiII2, int IiiIiIiiII3, String ... IiiIiIiiII4) {
        MySQLConnection IiiIiIiiII5;
        return IiiIiIiiII5.getValues(IiiIiIiiII, IiiIiIiiII2, IiiIiIiiII3, false, IiiIiIiiII4);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, Object> getValue(String IiiIiIiiII6222, String IiiIiIiiII2, Object IiiIiIiiII3, String ... IiiIiIiiII422) {
        MySQLConnection IiiIiIiiII5;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII5.connection.prepareStatement(new StringBuilder().insert(0, "select * from `").append((String)IiiIiIiiII6222).append("` where `").append(IiiIiIiiII2).append("` = ? limit 1").toString());
            preparedStatement.setObject(1, IiiIiIiiII3);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                for (String IiiIiIiiII422 : IiiIiIiiII422) {
                    hashMap.put(IiiIiIiiII422, resultSet.getObject(IiiIiIiiII422));
                }
            }
            IiiIiIiiII5.freeResult(resultSet, preparedStatement);
            return hashMap;
        }
        catch (Exception IiiIiIiiII6222) {
            try {
                IiiIiIiiII5.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII5.print("\u9519\u8bef\u7c7b\u578b: getValue(String,String,Object,String...);");
                IiiIiIiiII5.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII6222.getMessage());
                if (IiiIiIiiII5.fallReconnection && IiiIiIiiII6222.getMessage().contains("closed")) {
                    IiiIiIiiII5.connect();
                }
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                return hashMap;
            }
            catch (Throwable IiiIiIiiII6222) {
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII6222;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isExists(String IiiIiIiiII52, String IiiIiIiiII2, Object IiiIiIiiII3) {
        MySQLConnection IiiIiIiiII4;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII4.connection.prepareStatement(new StringBuilder().insert(0, "select * from `").append(IiiIiIiiII52).append("` where `").append(IiiIiIiiII2).append("` = ?").toString());
            preparedStatement.setObject(1, IiiIiIiiII3);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                boolean IiiIiIiiII52 = true;
                IiiIiIiiII4.freeResult(resultSet, preparedStatement);
                return IiiIiIiiII52;
            }
            IiiIiIiiII4.freeResult(resultSet, preparedStatement);
        }
        catch (Exception IiiIiIiiII52) {
            try {
                IiiIiIiiII4.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII4.print("\u9519\u8bef\u7c7b\u578b: isExists(String,String,Object);");
                IiiIiIiiII4.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII52.getMessage());
                if (IiiIiIiiII4.fallReconnection && IiiIiIiiII52.getMessage().contains("closed")) {
                    IiiIiIiiII4.connect();
                }
                IiiIiIiiII4.freeResult(resultSet, preparedStatement);
            }
            catch (Throwable IiiIiIiiII52) {
                IiiIiIiiII4.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII52;
            }
        }
        return false;
    }

    public boolean renameTable(String IiiIiIiiII, String IiiIiIiiII2) {
        MySQLConnection IiiIiIiiII3;
        return IiiIiIiiII3.execute("rename table `" + IiiIiIiiII + "` to `" + IiiIiIiiII2 + "`");
    }

    public boolean clearTable(String IiiIiIiiII) {
        MySQLConnection IiiIiIiiII2;
        return IiiIiIiiII2.execute("delete from " + IiiIiIiiII);
    }

    public MySQLConnection(String IiiIiIiiII, String IiiIiIiiII2, String IiiIiIiiII3, String IiiIiIiiII4, String IiiIiIiiII5) {
        MySQLConnection IiiIiIiiII6;
        if (!IiiIiIiiII6.loadDriverMySQL()) {
            IiiIiIiiII6.print("\u9a71\u52a8\u5668\u83b7\u53d6\u5931\u8d25, \u65e0\u6cd5\u8fde\u63a5\u5230\u6570\u636e\u5e93");
            return;
        }
        IiiIiIiiII6.url = IiiIiIiiII == null ? "localhost" : IiiIiIiiII;
        IiiIiIiiII6.user = IiiIiIiiII2 == null ? "root" : IiiIiIiiII2;
        IiiIiIiiII6.port = IiiIiIiiII3 == null ? "3306" : IiiIiIiiII3;
        IiiIiIiiII6.password = IiiIiIiiII4 == null ? "" : IiiIiIiiII4;
        IiiIiIiiII6.database = IiiIiIiiII5 == null ? "test" : IiiIiIiiII5;
        IiiIiIiiII6.connectionUrl = "jdbc:mysql://" + IiiIiIiiII6.url + ":" + IiiIiIiiII6.port + "/" + IiiIiIiiII6.database + "?characterEncoding=utf-8&useSSL=false";
        IiiIiIiiII6.connect();
    }

    public void addColumn(String IiiIiIiiII, Column ... IiiIiIiiII2) {
        for (Column column : IiiIiIiiII2) {
            MySQLConnection IiiIiIiiII3;
            IiiIiIiiII3.execute("alter table " + IiiIiIiiII + " add " + column.toString());
        }
    }

    public String getConnectionUrl() {
        MySQLConnection IiiIiIiiII;
        return IiiIiIiiII.connectionUrl;
    }

    public boolean isConnection() {
        try {
            MySQLConnection IiiIiIiiII;
            if (IiiIiIiiII.connection == null || IiiIiIiiII.connection.isClosed()) {
                return false;
            }
        }
        catch (SQLException sQLException) {
            return false;
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean intoValue2(String IiiIiIiiII42, List<String> IiiIiIiiII2) {
        MySQLConnection IiiIiIiiII3;
        String string2;
        StringBuilder stringBuilder = new StringBuilder();
        for (String string2 : IiiIiIiiII2) {
            stringBuilder.append("?, ");
        }
        Object object = null;
        string2 = null;
        object = IiiIiIiiII3.connection.prepareStatement(new StringBuilder().insert(0, "insert into `").append(IiiIiIiiII42).append("` values(null, ").append(stringBuilder.substring(0, stringBuilder.length() - 2)).append(")").toString());
        for (int IiiIiIiiII42 = 0; IiiIiIiiII42 < IiiIiIiiII2.size(); ++IiiIiIiiII42) {
            object.setObject(IiiIiIiiII42 + 1, IiiIiIiiII2.get(IiiIiIiiII42));
        }
        try {
            object.executeUpdate();
            IiiIiIiiII3.freeResult((ResultSet)((Object)string2), (PreparedStatement)object);
        }
        catch (Exception IiiIiIiiII42) {
            try {
                IiiIiIiiII3.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII3.print("\u9519\u8bef\u7c7b\u578b: intoValue(String,Object...);");
                IiiIiIiiII3.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII42.getMessage());
                if (IiiIiIiiII3.fallReconnection && IiiIiIiiII42.getMessage().contains("closed")) {
                    IiiIiIiiII3.connect();
                }
                IiiIiIiiII3.freeResult((ResultSet)((Object)string2), (PreparedStatement)object);
            }
            catch (Throwable IiiIiIiiII42) {
                IiiIiIiiII3.freeResult((ResultSet)((Object)string2), (PreparedStatement)object);
                throw IiiIiIiiII42;
            }
        }
        return false;
    }

    public boolean deleteTable(String IiiIiIiiII) {
        MySQLConnection IiiIiIiiII2;
        return IiiIiIiiII2.execute("drop table if exists " + IiiIiIiiII);
    }

    public String getUser() {
        MySQLConnection IiiIiIiiII;
        return IiiIiIiiII.user;
    }

    public List<String> getColumns(String IiiIiIiiII) {
        MySQLConnection IiiIiIiiII2;
        return IiiIiIiiII2.getColumns(IiiIiIiiII, false);
    }

    public boolean editColumn(String IiiIiIiiII, String IiiIiIiiII2, String IiiIiIiiII3) {
        MySQLConnection IiiIiIiiII4;
        if (!IiiIiIiiII3.contains("/")) {
            return IiiIiIiiII4.execute("alter table " + IiiIiIiiII + " change `" + IiiIiIiiII2 + "` `" + IiiIiIiiII3 + "` text");
        }
        return IiiIiIiiII4.execute(new StringBuilder().insert(0, "alter table ").append(IiiIiIiiII).append(" change `").append(IiiIiIiiII2).append("` `").append(IiiIiIiiII3.split("/")[0]).append("` ").append(IiiIiIiiII3.split("/")[1]).toString());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, Object> getValueLast(String IiiIiIiiII6222, String IiiIiIiiII2, Object IiiIiIiiII3, String ... IiiIiIiiII422) {
        MySQLConnection IiiIiIiiII5;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII5.connection.prepareStatement(new StringBuilder().insert(0, "select * from `").append((String)IiiIiIiiII6222).append("` where `").append(IiiIiIiiII2).append("` = ? order by id desc limit 1").toString());
            preparedStatement.setObject(1, IiiIiIiiII3);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                for (String IiiIiIiiII422 : IiiIiIiiII422) {
                    hashMap.put(IiiIiIiiII422, resultSet.getObject(IiiIiIiiII422));
                }
            }
            IiiIiIiiII5.freeResult(resultSet, preparedStatement);
            return hashMap;
        }
        catch (Exception IiiIiIiiII6222) {
            try {
                IiiIiIiiII5.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII5.print("\u9519\u8bef\u7c7b\u578b: getValueLast(String,String,Object,String...);");
                IiiIiIiiII5.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII6222.getMessage());
                if (IiiIiIiiII5.fallReconnection && IiiIiIiiII6222.getMessage().contains("closed")) {
                    IiiIiIiiII5.connect();
                }
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                return hashMap;
            }
            catch (Throwable IiiIiIiiII6222) {
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII6222;
            }
        }
    }

    public boolean connect() {
        MySQLConnection IiiIiIiiII;
        try {
            IiiIiIiiII.print("\u6b63\u5728\u8fde\u63a5\u6570\u636e\u5e93");
            IiiIiIiiII.print("\u5730\u5740: " + IiiIiIiiII.connectionUrl);
            long l = System.currentTimeMillis();
            IiiIiIiiII.connection = DriverManager.getConnection(IiiIiIiiII.connectionUrl, IiiIiIiiII.user, IiiIiIiiII.password);
            IiiIiIiiII.print(new StringBuilder().insert(0, "\u6570\u636e\u5e93\u8fde\u63a5\u6210\u529f (").append(System.currentTimeMillis() - l).append("ms)").toString());
            return true;
        }
        catch (SQLException sQLException) {
            IiiIiIiiII.print("\u6570\u636e\u5e93\u8fde\u63a5\u5931\u8d25");
            IiiIiIiiII.print(new StringBuilder().insert(0, "\u9519\u8bef\u539f\u56e0: ").append(sQLException.getMessage()).toString());
            IiiIiIiiII.print(new StringBuilder().insert(0, "\u9519\u8bef\u4ee3\u7801: ").append(sQLException.getErrorCode()).toString());
            return false;
        }
    }

    public void setFallReconnection(boolean IiiIiIiiII) {
        IiiIiIiiII.fallReconnection = IiiIiIiiII;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public List<String> getColumns(String IiiIiIiiII42, boolean IiiIiIiiII2) {
        MySQLConnection IiiIiIiiII3;
        ArrayList<String> arrayList = new ArrayList<String>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII3.connection.prepareStatement("select column_name from information_schema.COLUMNS where table_name = ?");
            preparedStatement.setString(1, IiiIiIiiII42);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                arrayList.add(resultSet.getString(1));
            }
            IiiIiIiiII3.freeResult(resultSet, preparedStatement);
        }
        catch (Exception IiiIiIiiII42) {
            try {
                IiiIiIiiII3.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII3.print("\u9519\u8bef\u7c7b\u578b: getColumns(String,boolean);");
                IiiIiIiiII3.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII42.getMessage());
                if (IiiIiIiiII3.fallReconnection && IiiIiIiiII42.getMessage().contains("closed")) {
                    IiiIiIiiII3.connect();
                }
                IiiIiIiiII3.freeResult(resultSet, preparedStatement);
            }
            catch (Throwable IiiIiIiiII42) {
                IiiIiIiiII3.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII42;
            }
        }
        if (!IiiIiIiiII2) {
            arrayList.remove("id");
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean execute(String IiiIiIiiII3) {
        MySQLConnection IiiIiIiiII2;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = IiiIiIiiII2.connection.prepareStatement(IiiIiIiiII3);
            preparedStatement.execute();
            boolean bl = true;
            return bl;
        }
        catch (Exception exception) {
            IiiIiIiiII2.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
            IiiIiIiiII2.print("\u9519\u8bef\u7c7b\u578b: execute(String);");
            IiiIiIiiII2.print("\u9519\u8bef\u539f\u56e0: " + exception.getMessage());
            IiiIiIiiII2.print(new StringBuilder().insert(0, "\u9519\u8bef\u547d\u4ee4: ").append(IiiIiIiiII3).toString());
            if (exception.getMessage().contains("closed")) {
                IiiIiIiiII2.connect();
            }
            boolean bl = false;
            return bl;
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            catch (Exception IiiIiIiiII3) {}
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public List<Object> getValues(String IiiIiIiiII62, String IiiIiIiiII2, int IiiIiIiiII3, boolean IiiIiIiiII4) {
        MySQLConnection IiiIiIiiII5;
        LinkedList<Object> linkedList = new LinkedList<Object>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII4 ? IiiIiIiiII5.connection.prepareStatement(new StringBuilder().insert(0, "select * from `").append(IiiIiIiiII62).append("` order by `").append(IiiIiIiiII2).append("` desc ").append(IiiIiIiiII3 < 0 ? "" : new StringBuilder().insert(0, " limit ").append(IiiIiIiiII3).toString()).toString()) : IiiIiIiiII5.connection.prepareStatement(new StringBuilder().insert(0, "select * from `").append(IiiIiIiiII62).append("` order by `").append(IiiIiIiiII2).append("` ").append(IiiIiIiiII3 < 0 ? "" : new StringBuilder().insert(0, " limit ").append(IiiIiIiiII3).toString()).toString());
            resultSet = preparedStatement.executeQuery();
            while (true) {
                if (!resultSet.next()) {
                    IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                    return linkedList;
                }
                linkedList.add(resultSet.getObject(IiiIiIiiII2));
            }
        }
        catch (Exception IiiIiIiiII62) {
            try {
                IiiIiIiiII5.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII5.print("\u9519\u8bef\u7c7b\u578b: getValues(String,String,int,boolean);");
                IiiIiIiiII5.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII62.getMessage());
                if (IiiIiIiiII5.fallReconnection && IiiIiIiiII62.getMessage().contains("closed")) {
                    IiiIiIiiII5.connect();
                }
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                return linkedList;
            }
            catch (Throwable IiiIiIiiII62) {
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII62;
            }
        }
    }

    public String getUrl() {
        MySQLConnection IiiIiIiiII;
        return IiiIiIiiII.url;
    }

    public boolean createTable(String IiiIiIiiII, Column ... IiiIiIiiII2) {
        MySQLConnection IiiIiIiiII3;
        StringBuilder stringBuilder = new StringBuilder();
        for (Column column : IiiIiIiiII2) {
            stringBuilder.append(column.toString()).append(", ");
        }
        return IiiIiIiiII3.execute(new StringBuilder().insert(0, "create table if not exists ").append(IiiIiIiiII).append(" (id int(1) not null primary key auto_increment, ").append(stringBuilder.substring(0, stringBuilder.length() - 2)).append(")").toString());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isExists(String IiiIiIiiII32) {
        MySQLConnection IiiIiIiiII2;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII2.connection.prepareStatement("select table_name FROM information_schema.TABLES where table_name = ?");
            preparedStatement.setString(1, IiiIiIiiII32);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                boolean IiiIiIiiII32 = true;
                IiiIiIiiII2.freeResult(resultSet, preparedStatement);
                return IiiIiIiiII32;
            }
            IiiIiIiiII2.freeResult(resultSet, preparedStatement);
        }
        catch (Exception IiiIiIiiII32) {
            try {
                IiiIiIiiII2.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII2.print("\u9519\u8bef\u7c7b\u578b: isExists(String);");
                IiiIiIiiII2.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII32.getMessage());
                if (IiiIiIiiII2.fallReconnection && IiiIiIiiII32.getMessage().contains("closed")) {
                    IiiIiIiiII2.connect();
                }
                IiiIiIiiII2.freeResult(resultSet, preparedStatement);
            }
            catch (Throwable IiiIiIiiII32) {
                IiiIiIiiII2.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII32;
            }
        }
        return false;
    }

    private /* synthetic */ boolean loadDriverMySQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
        }
        catch (ClassNotFoundException classNotFoundException) {
            return false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean ALLATORIxDEMO(String IiiIiIiiII82, String IiiIiIiiII2, Object IiiIiIiiII3, String IiiIiIiiII4, Object IiiIiIiiII5, boolean IiiIiIiiII6) {
        MySQLConnection IiiIiIiiII7;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII6 ? IiiIiIiiII7.connection.prepareStatement(new StringBuilder().insert(0, "update `").append(IiiIiIiiII82).append("` set `").append(IiiIiIiiII4).append("` = `").append(IiiIiIiiII4).append("` + ? where `").append(IiiIiIiiII2).append("` = ?").toString()) : IiiIiIiiII7.connection.prepareStatement(new StringBuilder().insert(0, "update `").append(IiiIiIiiII82).append("` set `").append(IiiIiIiiII4).append("` = ? where `").append(IiiIiIiiII2).append("` = ?").toString());
        }
        catch (Exception IiiIiIiiII82) {
            try {
                IiiIiIiiII7.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII7.print("\u9519\u8bef\u7c7b\u578b: setValue(String,String,Object,String,Object,boolean);");
                IiiIiIiiII7.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII82.getMessage());
                if (IiiIiIiiII7.fallReconnection && IiiIiIiiII82.getMessage().contains("closed")) {
                    IiiIiIiiII7.connect();
                }
                IiiIiIiiII7.freeResult(resultSet, preparedStatement);
                return false;
            }
            catch (Throwable IiiIiIiiII82) {
                IiiIiIiiII7.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII82;
            }
        }
        {
            preparedStatement.setObject(1, IiiIiIiiII5);
            preparedStatement.setObject(2, IiiIiIiiII3);
            preparedStatement.executeUpdate();
            IiiIiIiiII7.freeResult(resultSet, preparedStatement);
            return false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object getValueLast(String IiiIiIiiII62, String IiiIiIiiII2, Object IiiIiIiiII3, String IiiIiIiiII4) {
        MySQLConnection IiiIiIiiII5;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII5.connection.prepareStatement(new StringBuilder().insert(0, "select * from `").append((String)IiiIiIiiII62).append("` where `").append(IiiIiIiiII2).append("` = ? order by id desc limit 1").toString());
            preparedStatement.setObject(1, IiiIiIiiII3);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                IiiIiIiiII62 = resultSet.getObject(IiiIiIiiII4);
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                return IiiIiIiiII62;
            }
            IiiIiIiiII5.freeResult(resultSet, preparedStatement);
        }
        catch (Exception IiiIiIiiII62) {
            try {
                IiiIiIiiII5.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII5.print("\u9519\u8bef\u7c7b\u578b: getValueLast(String,String,Object,String);");
                IiiIiIiiII5.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII62.getMessage());
                if (IiiIiIiiII5.fallReconnection && IiiIiIiiII62.getMessage().contains("closed")) {
                    IiiIiIiiII5.connect();
                }
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
            }
            catch (Throwable IiiIiIiiII62) {
                IiiIiIiiII5.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII62;
            }
        }
        return null;
    }

    public boolean isFallReconnection() {
        MySQLConnection IiiIiIiiII;
        return IiiIiIiiII.fallReconnection;
    }

    public boolean addColumn(String IiiIiIiiII, String IiiIiIiiII2) {
        MySQLConnection IiiIiIiiII3;
        if (!IiiIiIiiII2.contains("/")) {
            return IiiIiIiiII3.execute("alter table " + IiiIiIiiII + " add `" + IiiIiIiiII2 + "` text");
        }
        return IiiIiIiiII3.execute(new StringBuilder().insert(0, "alter table ").append(IiiIiIiiII).append(" add `").append(IiiIiIiiII2.split("/")[0]).append("` ").append(IiiIiIiiII2.split("/")[1]).toString());
    }

    public String getPort() {
        MySQLConnection IiiIiIiiII;
        return IiiIiIiiII.port;
    }

    public List<Object> getValues(String IiiIiIiiII, String IiiIiIiiII2, int IiiIiIiiII3) {
        MySQLConnection IiiIiIiiII4;
        return IiiIiIiiII4.getValues(IiiIiIiiII, IiiIiIiiII2, IiiIiIiiII3, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public LinkedList<Map<String, Object>> getValues(String IiiIiIiiII72, String IiiIiIiiII2, int IiiIiIiiII3, boolean IiiIiIiiII4, String ... IiiIiIiiII5) {
        MySQLConnection IiiIiIiiII6;
        LinkedList<Map<String, Object>> linkedList = new LinkedList<Map<String, Object>>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII4 != 0 ? IiiIiIiiII6.connection.prepareStatement(new StringBuilder().insert(0, "select * from `").append((String)((Object)IiiIiIiiII72)).append("` order by `").append((String)IiiIiIiiII2).append("` desc").append(IiiIiIiiII3 < 0 ? "" : new StringBuilder().insert(0, " limit ").append(IiiIiIiiII3).toString()).toString()) : IiiIiIiiII6.connection.prepareStatement(new StringBuilder().insert(0, "select * from `").append((String)((Object)IiiIiIiiII72)).append("` order by `").append((String)IiiIiIiiII2).append("`").append(IiiIiIiiII3 < 0 ? "" : new StringBuilder().insert(0, " limit ").append(IiiIiIiiII3).toString()).toString());
            resultSet = preparedStatement.executeQuery();
            while (true) {
                if (!resultSet.next()) {
                    IiiIiIiiII6.freeResult(resultSet, preparedStatement);
                    return linkedList;
                }
                IiiIiIiiII72 = new HashMap();
                for (String string : IiiIiIiiII5) {
                    IiiIiIiiII72.put(string, resultSet.getObject(string));
                }
                linkedList.add(IiiIiIiiII72);
            }
        }
        catch (Exception IiiIiIiiII72) {
            try {
                IiiIiIiiII6.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII6.print("\u9519\u8bef\u7c7b\u578b: getValues(String,String,int,boolean,String...);");
                IiiIiIiiII6.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII72.getMessage());
                if (IiiIiIiiII6.fallReconnection && IiiIiIiiII72.getMessage().contains("closed")) {
                    IiiIiIiiII6.connect();
                }
                IiiIiIiiII6.freeResult(resultSet, preparedStatement);
                return linkedList;
            }
            catch (Throwable IiiIiIiiII72) {
                IiiIiIiiII6.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII72;
            }
        }
    }

    private /* synthetic */ void freeResult(ResultSet IiiIiIiiII, PreparedStatement IiiIiIiiII2) {
        try {
            if (IiiIiIiiII != null) {
                IiiIiIiiII.close();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        try {
            if (IiiIiIiiII2 != null) {
                IiiIiIiiII2.close();
                return;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public boolean setValue(String IiiIiIiiII, String IiiIiIiiII2, Object IiiIiIiiII3, String IiiIiIiiII4, Object IiiIiIiiII5) {
        MySQLConnection IiiIiIiiII6;
        return IiiIiIiiII6.ALLATORIxDEMO(IiiIiIiiII, IiiIiIiiII2, IiiIiIiiII3, IiiIiIiiII4, IiiIiIiiII5, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean deleteValue(String IiiIiIiiII52, String IiiIiIiiII2, Object IiiIiIiiII3) {
        MySQLConnection IiiIiIiiII4;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = IiiIiIiiII4.connection.prepareStatement(new StringBuilder().insert(0, "delete from `").append(IiiIiIiiII52).append("` where `").append(IiiIiIiiII2).append("` = ?").toString());
            preparedStatement.setObject(1, IiiIiIiiII3);
            preparedStatement.executeUpdate();
            IiiIiIiiII4.freeResult(resultSet, preparedStatement);
        }
        catch (Exception IiiIiIiiII52) {
            try {
                IiiIiIiiII4.print("\u6570\u636e\u5e93\u547d\u4ee4\u6267\u884c\u51fa\u9519");
                IiiIiIiiII4.print("\u9519\u8bef\u7c7b\u578b: deleteValue(String,String,Object);");
                IiiIiIiiII4.print("\u9519\u8bef\u539f\u56e0: " + IiiIiIiiII52.getMessage());
                if (IiiIiIiiII4.fallReconnection && IiiIiIiiII52.getMessage().contains("closed")) {
                    IiiIiIiiII4.connect();
                }
                IiiIiIiiII4.freeResult(resultSet, preparedStatement);
            }
            catch (Throwable IiiIiIiiII52) {
                IiiIiIiiII4.freeResult(resultSet, preparedStatement);
                throw IiiIiIiiII52;
            }
        }
        return false;
    }

    public boolean truncateTable(String IiiIiIiiII) {
        MySQLConnection IiiIiIiiII2;
        return IiiIiIiiII2.execute("truncate table " + IiiIiIiiII);
    }

    public boolean createTable(String IiiIiIiiII, String ... IiiIiIiiII2) {
        MySQLConnection IiiIiIiiII3;
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : IiiIiIiiII2) {
            if (!string.contains("/")) {
                stringBuilder.append("`").append(string).append("` text, ");
                continue;
            }
            stringBuilder.append("`").append(string.split("/")[0]).append("` ").append(string.split("/")[1]).append(", ");
        }
        return IiiIiIiiII3.execute(new StringBuilder().insert(0, "create table if not exists ").append(IiiIiIiiII).append(" (id int(1) not null primary key auto_increment, ").append(stringBuilder.substring(0, stringBuilder.length() - 2)).append(")").toString());
    }

    public String getPassword() {
        MySQLConnection IiiIiIiiII;
        return IiiIiIiiII.password;
    }

    public static class Column {
        private String name;
        private int b;
        private Object type;
        private int a;

        public Column(String IiiIiIiiII) {
            Column IiiIiIiiII2;
            IiiIiIiiII2.name = IiiIiIiiII;
            IiiIiIiiII2.type = ColumnString.TEXT;
        }

        public Column(String IiiIiIiiII, ColumnChar IiiIiIiiII2, int IiiIiIiiII3) {
            IiiIiIiiII4(IiiIiIiiII);
            Column IiiIiIiiII4;
            IiiIiIiiII4.type = IiiIiIiiII2;
            IiiIiIiiII4.a = IiiIiIiiII3;
        }

        public String toString() {
            Column IiiIiIiiII;
            if (IiiIiIiiII.type instanceof ColumnInteger || IiiIiIiiII.type instanceof ColumnChar) {
                return new StringBuilder().insert(0, "`").append(IiiIiIiiII.name).append("` ").append(IiiIiIiiII.type.toString().toLowerCase()).append("(").append(IiiIiIiiII.a).append(")").toString();
            }
            if (IiiIiIiiII.type instanceof ColumnFloat) {
                return new StringBuilder().insert(0, "`").append(IiiIiIiiII.name).append("` ").append(IiiIiIiiII.type.toString().toLowerCase()).append("(").append(IiiIiIiiII.a).append(",").append(IiiIiIiiII.b).append(")").toString();
            }
            return new StringBuilder().insert(0, "`").append(IiiIiIiiII.name).append("` ").append(IiiIiIiiII.type.toString().toLowerCase()).toString();
        }

        public Column(String IiiIiIiiII, ColumnString IiiIiIiiII2) {
            IiiIiIiiII3(IiiIiIiiII);
            Column IiiIiIiiII3;
            IiiIiIiiII3.type = IiiIiIiiII2;
        }

        public Column(String IiiIiIiiII, ColumnInteger IiiIiIiiII2, int IiiIiIiiII3) {
            IiiIiIiiII4(IiiIiIiiII);
            Column IiiIiIiiII4;
            IiiIiIiiII4.type = IiiIiIiiII2;
            IiiIiIiiII4.a = IiiIiIiiII3;
        }

        public Column(String IiiIiIiiII, ColumnFloat IiiIiIiiII2, int IiiIiIiiII3, int IiiIiIiiII4) {
            IiiIiIiiII5(IiiIiIiiII);
            Column IiiIiIiiII5;
            IiiIiIiiII5.type = IiiIiIiiII2;
            IiiIiIiiII5.a = IiiIiIiiII3;
            IiiIiIiiII5.b = IiiIiIiiII4;
        }

        public Column(String IiiIiIiiII, ColumnInteger IiiIiIiiII2) {
            IiiIiIiiII3(IiiIiIiiII);
            Column IiiIiIiiII3;
            IiiIiIiiII3.type = IiiIiIiiII2;
            IiiIiIiiII3.a = 12;
        }
    }

    public static enum ColumnString {
        TINYTEXT,
        TEXT,
        MEDIUMTEXT,
        LONGTEXT;


        /*
         * WARNING - Possible parameter corruption
         * WARNING - void declaration
         */
        private /* synthetic */ ColumnString() {
            ColumnString IiiIiIiiII;
        }
    }

    public static enum ColumnChar {
        CHAR,
        VARCHAR;


        /*
         * WARNING - Possible parameter corruption
         * WARNING - void declaration
         */
        private /* synthetic */ ColumnChar() {
            ColumnChar IiiIiIiiII;
        }
    }

    public static enum ColumnFloat {
        FLOAT,
        DOUBLE;


        /*
         * WARNING - Possible parameter corruption
         * WARNING - void declaration
         */
        private /* synthetic */ ColumnFloat() {
            ColumnFloat IiiIiIiiII;
        }
    }

    public static enum ColumnInteger {
        TINYINT,
        SMALLINT,
        MEDIUMINT,
        INT,
        BIGINT;


        /*
         * WARNING - Possible parameter corruption
         * WARNING - void declaration
         */
        private /* synthetic */ ColumnInteger() {
            ColumnInteger IiiIiIiiII;
        }
    }
}

