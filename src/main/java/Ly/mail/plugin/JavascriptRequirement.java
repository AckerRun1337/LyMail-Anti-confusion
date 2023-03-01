/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 */
package Ly.mail.plugin;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.bukkit.Bukkit;

public class JavascriptRequirement {
    private static ScriptEngine engine = null;
    private final String expression;

    public static ScriptEngine getEngine() {
        return engine;
    }

    public boolean evaluate() {
        try {
            JavascriptRequirement IiiIiIiiII;
            Object object = engine.eval(IiiIiIiiII.expression);
            if (!(object instanceof Boolean)) {
                Bukkit.getConsoleSender().sendMessage(new StringBuilder().insert(0, "\u8bf7\u6c42JavaScript <").append(IiiIiIiiII.expression).append("> \u65e0\u6548, \u5e76\u4e14\u4e0d\u8fd4\u56deBoolean!").toString());
                return false;
            }
            return (Boolean)object;
        }
        catch (ScriptException scriptException) {
            scriptException.printStackTrace();
            return false;
        }
    }

    public JavascriptRequirement(String IiiIiIiiII) {
        JavascriptRequirement IiiIiIiiII2;
        IiiIiIiiII2.expression = IiiIiIiiII;
        if (engine == null) {
            engine = new ScriptEngineManager().getEngineByName("javascript");
            engine.put("BukkitServer", Bukkit.getServer());
        }
    }

    public static void setEngine(ScriptEngine IiiIiIiiII) {
        engine = IiiIiIiiII;
    }
}

