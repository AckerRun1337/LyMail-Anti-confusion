/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.ImmutableBiMap
 *  com.google.common.collect.ImmutableBiMap$Builder
 *  com.google.gson.stream.JsonWriter
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.configuration.serialization.ConfigurationSerializable
 *  org.bukkit.configuration.serialization.ConfigurationSerialization
 */
package Ly.mail.plugin.fanciful;

import Ly.mail.plugin.fanciful.FancyMessage;
import Ly.mail.plugin.fanciful.JsonRepresentedObject;
import Ly.mail.plugin.fanciful.JsonString;
import Ly.mail.plugin.fanciful.TextualComponent;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

final class MessagePart
implements JsonRepresentedObject,
ConfigurationSerializable,
Cloneable {
    JsonRepresentedObject hoverActionData;
    TextualComponent text;
    ArrayList<ChatColor> styles;
    String hoverActionName;
    String clickActionData;
    static final BiMap<ChatColor, String> stylesToNames;
    String insertionData;
    String clickActionName;
    ArrayList<JsonRepresentedObject> translationReplacements;
    ChatColor color;

    MessagePart(TextualComponent IiiIiIiiII) {
        MessagePart IiiIiIiiII2;
        IiiIiIiiII2.color = ChatColor.WHITE;
        IiiIiIiiII2.styles = new ArrayList();
        IiiIiIiiII2.clickActionName = null;
        IiiIiIiiII2.clickActionData = null;
        IiiIiIiiII2.hoverActionName = null;
        IiiIiIiiII2.hoverActionData = null;
        IiiIiIiiII2.text = null;
        IiiIiIiiII2.insertionData = null;
        IiiIiIiiII2.translationReplacements = new ArrayList();
        IiiIiIiiII2.text = IiiIiIiiII;
    }

    public static MessagePart deserialize(Map<String, Object> IiiIiIiiII) {
        MessagePart messagePart = new MessagePart((TextualComponent)IiiIiIiiII.get("text"));
        messagePart.styles = (ArrayList)IiiIiIiiII.get("styles");
        messagePart.color = ChatColor.getByChar((String)IiiIiIiiII.get("color").toString());
        messagePart.hoverActionName = (String)IiiIiIiiII.get("hoverActionName");
        messagePart.hoverActionData = (JsonRepresentedObject)IiiIiIiiII.get("hoverActionData");
        messagePart.clickActionName = (String)IiiIiIiiII.get("clickActionName");
        messagePart.clickActionData = (String)IiiIiIiiII.get("clickActionData");
        messagePart.insertionData = (String)IiiIiIiiII.get("insertion");
        messagePart.translationReplacements = (ArrayList)IiiIiIiiII.get("translationReplacements");
        return messagePart;
    }

    static {
        ImmutableBiMap.Builder builder = ImmutableBiMap.builder();
        for (ChatColor chatColor : ChatColor.values()) {
            String string;
            if (!chatColor.isFormat()) continue;
            switch (chatColor) {
                case MAGIC: {
                    string = "obfuscated";
                    break;
                }
                case UNDERLINE: {
                    string = "underlined";
                    break;
                }
                default: {
                    string = chatColor.name().toLowerCase();
                }
            }
            builder.put((Object)chatColor, (Object)string);
        }
        stylesToNames = builder.build();
        ConfigurationSerialization.registerClass(MessagePart.class);
    }

    MessagePart() {
        MessagePart IiiIiIiiII;
        IiiIiIiiII.color = ChatColor.WHITE;
        IiiIiIiiII.styles = new ArrayList();
        IiiIiIiiII.clickActionName = null;
        IiiIiIiiII.clickActionData = null;
        IiiIiIiiII.hoverActionName = null;
        IiiIiIiiII.hoverActionData = null;
        IiiIiIiiII.text = null;
        IiiIiIiiII.insertionData = null;
        IiiIiIiiII.translationReplacements = new ArrayList();
        IiiIiIiiII.text = null;
    }

    boolean hasText() {
        MessagePart IiiIiIiiII;
        return IiiIiIiiII.text != null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void writeJson(JsonWriter IiiIiIiiII) {
        try {
            MessagePart IiiIiIiiII2;
            IiiIiIiiII.beginObject();
            IiiIiIiiII2.text.writeJson(IiiIiIiiII);
            IiiIiIiiII.name("color").value(IiiIiIiiII2.color.name().toLowerCase());
            for (ChatColor chatColor : IiiIiIiiII2.styles) {
                IiiIiIiiII.name((String)stylesToNames.get((Object)chatColor)).value(true);
            }
            if (IiiIiIiiII2.clickActionName != null && IiiIiIiiII2.clickActionData != null) {
                IiiIiIiiII.name("clickEvent").beginObject().name("action").value(IiiIiIiiII2.clickActionName).name("value").value(IiiIiIiiII2.clickActionData).endObject();
            }
            if (IiiIiIiiII2.hoverActionName != null && IiiIiIiiII2.hoverActionData != null) {
                IiiIiIiiII.name("hoverEvent").beginObject().name("action").value(IiiIiIiiII2.hoverActionName).name("value");
                IiiIiIiiII2.hoverActionData.writeJson(IiiIiIiiII);
                IiiIiIiiII.endObject();
            }
            if (IiiIiIiiII2.insertionData != null) {
                IiiIiIiiII.name("insertion").value(IiiIiIiiII2.insertionData);
            }
            if (IiiIiIiiII2.translationReplacements.size() > 0 && IiiIiIiiII2.text != null && TextualComponent.isTranslatableText(IiiIiIiiII2.text)) {
                IiiIiIiiII.name("with").beginArray();
                for (JsonRepresentedObject jsonRepresentedObject : IiiIiIiiII2.translationReplacements) {
                    jsonRepresentedObject.writeJson(IiiIiIiiII);
                }
                IiiIiIiiII.endArray();
            }
            IiiIiIiiII.endObject();
            return;
        }
        catch (IOException iOException) {
            Bukkit.getLogger().log(Level.WARNING, "A problem occured during writing of JSON string", iOException);
            return;
        }
    }

    public Map<String, Object> serialize() {
        MessagePart IiiIiIiiII;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("text", IiiIiIiiII.text);
        hashMap.put("styles", IiiIiIiiII.styles);
        hashMap.put("color", Character.valueOf(IiiIiIiiII.color.getChar()));
        hashMap.put("hoverActionName", IiiIiIiiII.hoverActionName);
        hashMap.put("hoverActionData", IiiIiIiiII.hoverActionData);
        hashMap.put("clickActionName", IiiIiIiiII.clickActionName);
        hashMap.put("clickActionData", IiiIiIiiII.clickActionData);
        hashMap.put("insertion", IiiIiIiiII.insertionData);
        hashMap.put("translationReplacements", IiiIiIiiII.translationReplacements);
        return hashMap;
    }

    public MessagePart clone() throws CloneNotSupportedException {
        MessagePart IiiIiIiiII;
        MessagePart messagePart = (MessagePart)super.clone();
        messagePart.styles = (ArrayList)IiiIiIiiII.styles.clone();
        if (IiiIiIiiII.hoverActionData instanceof JsonString) {
            messagePart.hoverActionData = new JsonString(((JsonString)IiiIiIiiII.hoverActionData).getValue());
        } else if (IiiIiIiiII.hoverActionData instanceof FancyMessage) {
            messagePart.hoverActionData = ((FancyMessage)IiiIiIiiII.hoverActionData).clone();
        }
        messagePart.translationReplacements = (ArrayList)IiiIiIiiII.translationReplacements.clone();
        return messagePart;
    }
}

