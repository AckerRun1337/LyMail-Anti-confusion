/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.stream.JsonWriter
 *  org.bukkit.configuration.serialization.ConfigurationSerializable
 */
package Ly.mail.plugin.fanciful;

import Ly.mail.plugin.fanciful.JsonRepresentedObject;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

final class JsonString
implements JsonRepresentedObject,
ConfigurationSerializable {
    private String _value;

    @Override
    public void writeJson(JsonWriter IiiIiIiiII) throws IOException {
        JsonString IiiIiIiiII2;
        IiiIiIiiII.value(IiiIiIiiII2.getValue());
    }

    public static JsonString deserialize(Map<String, Object> IiiIiIiiII) {
        return new JsonString(IiiIiIiiII.get("stringValue").toString());
    }

    public Map<String, Object> serialize() {
        JsonString IiiIiIiiII;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("stringValue", IiiIiIiiII._value);
        return hashMap;
    }

    public String toString() {
        JsonString IiiIiIiiII;
        return IiiIiIiiII._value;
    }

    public String getValue() {
        JsonString IiiIiIiiII;
        return IiiIiIiiII._value;
    }

    public JsonString(CharSequence IiiIiIiiII) {
        JsonString IiiIiIiiII2;
        IiiIiIiiII2._value = IiiIiIiiII == null ? null : IiiIiIiiII.toString();
    }
}

