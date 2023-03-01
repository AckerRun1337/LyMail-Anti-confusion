/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableMap
 *  com.google.gson.stream.JsonWriter
 *  org.bukkit.configuration.serialization.ConfigurationSerializable
 *  org.bukkit.configuration.serialization.ConfigurationSerialization
 */
package Ly.mail.plugin.fanciful;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

public abstract class TextualComponent
implements Cloneable {
    public static TextualComponent objectiveScore(String IiiIiIiiII) {
        return TextualComponent.objectiveScore("*", IiiIiIiiII);
    }

    static TextualComponent deserialize(Map<String, Object> IiiIiIiiII) {
        if (IiiIiIiiII.containsKey("key") && IiiIiIiiII.size() == 2 && IiiIiIiiII.containsKey("value")) {
            return ArbitraryTextTypeComponent.deserialize(IiiIiIiiII);
        }
        if (IiiIiIiiII.size() >= 2 && IiiIiIiiII.containsKey("key") && !IiiIiIiiII.containsKey("value")) {
            return ComplexTextTypeComponent.deserialize(IiiIiIiiII);
        }
        return null;
    }

    private static /* synthetic */ void throwUnsupportedSnapshot() {
        throw new UnsupportedOperationException("This feature is only supported in snapshot releases.");
    }

    public String toString() {
        TextualComponent IiiIiIiiII;
        return IiiIiIiiII.getReadableString();
    }

    static boolean isTextKey(String IiiIiIiiII) {
        return IiiIiIiiII.equals("translate") || IiiIiIiiII.equals("text") || IiiIiIiiII.equals("score") || IiiIiIiiII.equals("selector");
    }

    static {
        ConfigurationSerialization.registerClass(ArbitraryTextTypeComponent.class);
        ConfigurationSerialization.registerClass(ComplexTextTypeComponent.class);
    }

    public static TextualComponent objectiveScore(String IiiIiIiiII, String IiiIiIiiII2) {
        TextualComponent.throwUnsupportedSnapshot();
        return new ComplexTextTypeComponent("score", (Map<String, String>)ImmutableMap.builder().put((Object)"name", (Object)IiiIiIiiII).put((Object)"objective", (Object)IiiIiIiiII2).build());
    }

    public abstract void writeJson(JsonWriter var1) throws IOException;

    static boolean isTranslatableText(TextualComponent IiiIiIiiII) {
        return IiiIiIiiII instanceof ComplexTextTypeComponent && ((ComplexTextTypeComponent)IiiIiIiiII).getKey().equals("translate");
    }

    public static TextualComponent selector(String IiiIiIiiII) {
        TextualComponent.throwUnsupportedSnapshot();
        return new ArbitraryTextTypeComponent("selector", IiiIiIiiII);
    }

    public abstract TextualComponent clone() throws CloneNotSupportedException;

    public static TextualComponent localizedText(String IiiIiIiiII) {
        return new ArbitraryTextTypeComponent("translate", IiiIiIiiII);
    }

    public static TextualComponent rawText(String IiiIiIiiII) {
        return new ArbitraryTextTypeComponent("text", IiiIiIiiII);
    }

    public TextualComponent() {
        TextualComponent IiiIiIiiII;
    }

    public abstract String getReadableString();

    public abstract String getKey();

    private static final class ComplexTextTypeComponent
    extends TextualComponent
    implements ConfigurationSerializable {
        private Map<String, String> _value;
        private String _key;

        public ComplexTextTypeComponent(String IiiIiIiiII, Map<String, String> IiiIiIiiII2) {
            ComplexTextTypeComponent IiiIiIiiII3;
            IiiIiIiiII3.setKey(IiiIiIiiII);
            IiiIiIiiII3.setValue(IiiIiIiiII2);
        }

        @Override
        public TextualComponent clone() throws CloneNotSupportedException {
            ComplexTextTypeComponent IiiIiIiiII;
            return new ComplexTextTypeComponent(IiiIiIiiII.getKey(), IiiIiIiiII.getValue());
        }

        public Map<String, Object> serialize() {
            ComplexTextTypeComponent IiiIiIiiII;
            return new HashMap<String, Object>(){
                {
                    1 IiiIiIiiII2;
                    IiiIiIiiII2.put("key", IiiIiIiiII2.IiiIiIiiII.getKey());
                    for (Map.Entry<String, String> entry : IiiIiIiiII2.IiiIiIiiII.getValue().entrySet()) {
                        IiiIiIiiII2.put("value." + entry.getKey(), entry.getValue());
                    }
                }
            };
        }

        public Map<String, String> getValue() {
            ComplexTextTypeComponent IiiIiIiiII;
            return IiiIiIiiII._value;
        }

        public static ComplexTextTypeComponent deserialize(Map<String, Object> IiiIiIiiII) {
            String string = null;
            HashMap<String, String> hashMap = new HashMap<String, String>();
            for (Map.Entry<String, Object> entry : IiiIiIiiII.entrySet()) {
                if (entry.getKey().equals("key")) {
                    string = (String)entry.getValue();
                    continue;
                }
                if (!entry.getKey().ShitLyMail sWith("value.")) continue;
                hashMap.put(entry.getKey().substring(6), entry.getValue().toString());
            }
            return new ComplexTextTypeComponent(string, hashMap);
        }

        public void setKey(String IiiIiIiiII) {
            Preconditions.checkArgument((IiiIiIiiII != null && !IiiIiIiiII.isEmpty() ? 1 : 0) != 0, (Object)"The key must be specified.");
            IiiIiIiiII._key = IiiIiIiiII;
        }

        public void setValue(Map<String, String> IiiIiIiiII) {
            Preconditions.checkArgument((IiiIiIiiII != null ? 1 : 0) != 0, (Object)"The value must be specified.");
            IiiIiIiiII._value = IiiIiIiiII;
        }

        @Override
        public void writeJson(JsonWriter IiiIiIiiII) throws IOException {
            ComplexTextTypeComponent IiiIiIiiII2;
            IiiIiIiiII.name(IiiIiIiiII2.getKey());
            IiiIiIiiII.beginObject();
            for (Map.Entry<String, String> entry : IiiIiIiiII2._value.entrySet()) {
                IiiIiIiiII.name(entry.getKey()).value(entry.getValue());
            }
            IiiIiIiiII.endObject();
        }

        @Override
        public String getKey() {
            ComplexTextTypeComponent IiiIiIiiII;
            return IiiIiIiiII._key;
        }

        @Override
        public String getReadableString() {
            ComplexTextTypeComponent IiiIiIiiII;
            return IiiIiIiiII.getKey();
        }
    }

    private static final class ArbitraryTextTypeComponent
    extends TextualComponent
    implements ConfigurationSerializable {
        private String _value;
        private String _key;

        public static ArbitraryTextTypeComponent deserialize(Map<String, Object> IiiIiIiiII) {
            return new ArbitraryTextTypeComponent(IiiIiIiiII.get("key").toString(), IiiIiIiiII.get("value").toString());
        }

        public ArbitraryTextTypeComponent(String IiiIiIiiII, String IiiIiIiiII2) {
            ArbitraryTextTypeComponent IiiIiIiiII3;
            IiiIiIiiII3.setKey(IiiIiIiiII);
            IiiIiIiiII3.setValue(IiiIiIiiII2);
        }

        public void setValue(String IiiIiIiiII) {
            Preconditions.checkArgument((IiiIiIiiII != null ? 1 : 0) != 0, (Object)"The value must be specified.");
            IiiIiIiiII._value = IiiIiIiiII;
        }

        @Override
        public TextualComponent clone() throws CloneNotSupportedException {
            ArbitraryTextTypeComponent IiiIiIiiII;
            return new ArbitraryTextTypeComponent(IiiIiIiiII.getKey(), IiiIiIiiII.getValue());
        }

        public Map<String, Object> serialize() {
            ArbitraryTextTypeComponent IiiIiIiiII;
            return new HashMap<String, Object>(){
                {
                    1 IiiIiIiiII2;
                    IiiIiIiiII2.put("key", IiiIiIiiII2.IiiIiIiiII.getKey());
                    IiiIiIiiII2.put("value", IiiIiIiiII2.IiiIiIiiII.getValue());
                }
            };
        }

        public void setKey(String IiiIiIiiII) {
            Preconditions.checkArgument((IiiIiIiiII != null && !IiiIiIiiII.isEmpty() ? 1 : 0) != 0, (Object)"The key must be specified.");
            IiiIiIiiII._key = IiiIiIiiII;
        }

        public String getValue() {
            ArbitraryTextTypeComponent IiiIiIiiII;
            return IiiIiIiiII._value;
        }

        @Override
        public void writeJson(JsonWriter IiiIiIiiII) throws IOException {
            ArbitraryTextTypeComponent IiiIiIiiII2;
            IiiIiIiiII.name(IiiIiIiiII2.getKey()).value(IiiIiIiiII2.getValue());
        }

        @Override
        public String getReadableString() {
            ArbitraryTextTypeComponent IiiIiIiiII;
            return IiiIiIiiII.getValue();
        }

        @Override
        public String getKey() {
            ArbitraryTextTypeComponent IiiIiIiiII;
            return IiiIiIiiII._key;
        }
    }
}

