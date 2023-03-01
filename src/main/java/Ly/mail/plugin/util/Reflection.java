/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 */
package Ly.mail.plugin.util;

import Ly.mail.plugin.util.ArrayWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;

import javax.print.attribute.standard.DateTimeAtCompleted;

public final class Reflection {
    private static String _versionString;
    private static final Map<String, Class<?>> _loadedOBCClasses;
    private static final Map<Class<?>, Map<String, Field>> _loadedFields;
    private static final Map<String, Class<?>> _loadedNMSClasses;
    private static final Map<Class<?>, Map<String, Map<ArrayWrapper<Class<?>>, Method>>> _loadedMethods;

    static {
        _loadedNMSClasses = new HashMap();
        _loadedOBCClasses = new HashMap();
        _loadedFields = new HashMap();
        _loadedMethods = new HashMap();
    }

    public static synchronized Field getField(Class<?> IiiIiIiiII, String IiiIiIiiII2) {
        Map<String, Field> map;
        if (!_loadedFields.containsKey(IiiIiIiiII)) {
            map = new HashMap();
            _loadedFields.put(IiiIiIiiII, map);
        } else {
            map = _loadedFields.get(IiiIiIiiII);
        }
        if (map.containsKey(IiiIiIiiII2)) {
            return (Field)map.get(IiiIiIiiII2);
        }
        try {
            Field field = IiiIiIiiII.getDeclaredField(IiiIiIiiII2);
            field.setAccessible(true);
            map.put(IiiIiIiiII2, field);
            return field;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            map.put(IiiIiIiiII2, null);
            return null;
        }
    }

/*    public static synchronized Object getHandle(Object IiiIiIiiII) {
        try {

            return Reflection.getMethod(IiiIiIiiII.getClass(), "getHandle", new Class[0]).invoke(IiiIiIiiII, DateTimeAtCompleted);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }*/

    private static InvocationHandler getMethod(Class<?> aClass, String getHandle, Class[] classes) {
        return null;
    }

    public static synchronized Class<?> getNMSClass(String IiiIiIiiII) {
        if (_loadedNMSClasses.containsKey(IiiIiIiiII)) {
            return _loadedNMSClasses.get(IiiIiIiiII);
        }
        String string = new StringBuilder().insert(0, "net.minecraft.server.").append(Reflection.getVersion()).append(IiiIiIiiII).toString();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(string);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            _loadedNMSClasses.put(IiiIiIiiII, null);
            return null;
        }
        _loadedNMSClasses.put(IiiIiIiiII, clazz);
        return clazz;
    }

    public static synchronized String getVersion() {
        if (_versionString == null) {
            if (Bukkit.getServer() == null) {
                return null;
            }
            String string = Bukkit.getServer().getClass().getPackage().getName();
            _versionString = new StringBuilder().insert(0, string.substring(string.lastIndexOf(46) + 1)).append(".").toString();
        }
        return _versionString;
    }

/*    public static synchronized Method getMethod(Class<?> IiiIiIiiII, String IiiIiIiiII2, Class<?> ... IiiIiIiiII3) {
        ArrayWrapper                                     arrayWrapper;
        Map<String, Map<ArrayWrapper<Class<?>>, Method>> map;
        if (!_loadedMethods.containsKey(IiiIiIiiII)) {
            _loadedMethods.put(IiiIiIiiII, new HashMap());
        }
        if (!(map = _loadedMethods.get(IiiIiIiiII)).containsKey(IiiIiIiiII2)) {
            map.put(IiiIiIiiII2, new HashMap());
        }
        if ((map = (Map<Object, Object>) map.get(IiiIiIiiII2)).containsKey(arrayWrapper = new ArrayWrapper(IiiIiIiiII3))) {
            return (Method)map.get(arrayWrapper);
        }
        for (Method method : IiiIiIiiII.getMethods()) {
            if (!method.getName().equals(IiiIiIiiII2) || !Arrays.equals(IiiIiIiiII3, method.getParameterTypes())) continue;
            method.setAccessible(true);
            map.put(arrayWrapper, method);
            return method;
        }
        map.put(arrayWrapper, null);
        return null;
    }*/

    private /* synthetic */ Reflection() {
        Reflection IiiIiIiiII;
    }

    public static synchronized Class<?> getOBCClass(String IiiIiIiiII) {
        if (_loadedOBCClasses.containsKey(IiiIiIiiII)) {
            return _loadedOBCClasses.get(IiiIiIiiII);
        }
        String string = new StringBuilder().insert(0, "org.bukkit.craftbukkit.").append(Reflection.getVersion()).append(IiiIiIiiII).toString();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(string);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            _loadedOBCClasses.put(IiiIiIiiII, null);
            return null;
        }
        _loadedOBCClasses.put(IiiIiIiiII, clazz);
        return clazz;
    }
}

