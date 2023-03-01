/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
 *  com.google.gson.stream.JsonWriter
 *  org.bukkit.Achievement
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.Statistic
 *  org.bukkit.Statistic$Type
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.serialization.ConfigurationSerializable
 *  org.bukkit.configuration.serialization.ConfigurationSerialization
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package Ly.mail.plugin.fanciful;

import Ly.mail.plugin.fanciful.JsonRepresentedObject;
import Ly.mail.plugin.fanciful.JsonString;
import Ly.mail.plugin.fanciful.MessagePart;
import Ly.mail.plugin.fanciful.TextualComponent;
import Ly.mail.plugin.util.ArrayWrapper;
import Ly.mail.plugin.util.Reflection;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FancyMessage
implements JsonRepresentedObject,
Cloneable,
Iterable<MessagePart>,
ConfigurationSerializable {
    private static Constructor<?> nmsPacketPlayOutChatConstructor;
    private String jsonString;
    private static JsonParser _stringParser;
    private static Object nmsChatSerializerGsonInstance;
    private boolean dirty;
    private List<MessagePart> messageParts = new ArrayList<MessagePart>();
    private static Method fromJsonMethod;

    public FancyMessage() {
        IiiIiIiiII((TextualComponent)null);
        FancyMessage IiiIiIiiII;
    }

    public FancyMessage formattedTooltip(FancyMessage ... IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        if (IiiIiIiiII.length < 1) {
            IiiIiIiiII2.onHover(null, null);
            return IiiIiIiiII2;
        }
        FancyMessage fancyMessage = new FancyMessage();
        fancyMessage.messageParts.clear();
        for (int i = 0; i < IiiIiIiiII.length; ++i) {
            try {
                for (MessagePart messagePart : IiiIiIiiII[i]) {
                    if (messagePart.clickActionData != null && messagePart.clickActionName != null) {
                        throw new IllegalArgumentException("The tooltip text cannot have click data.");
                    }
                    if (messagePart.hoverActionData != null && messagePart.hoverActionName != null) {
                        throw new IllegalArgumentException("The tooltip text cannot have a tooltip.");
                    }
                    if (!messagePart.hasText()) continue;
                    fancyMessage.messageParts.add((MessagePart)messagePart.clone());
                }
            }
            catch (CloneNotSupportedException cloneNotSupportedException) {
                Bukkit.getLogger().log(Level.WARNING, "Failed to clone object", cloneNotSupportedException);
                return IiiIiIiiII2;
            }
            if (i == IiiIiIiiII.length - 1) continue;
            fancyMessage.messageParts.add(new MessagePart(TextualComponent.rawText("\n")));
            continue;
        }
        return IiiIiIiiII2.formattedTooltip(fancyMessage.messageParts.isEmpty() ? null : fancyMessage);
    }

    public FancyMessage formattedTooltip(Iterable<FancyMessage> IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        return IiiIiIiiII2.formattedTooltip(ArrayWrapper.toArray(IiiIiIiiII, FancyMessage.class));
    }

    public FancyMessage translationReplacements(String ... IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        for (String string : IiiIiIiiII) {
            IiiIiIiiII2.latest().translationReplacements.add(new JsonString(string));
        }
        IiiIiIiiII2.dirty = true;
        return IiiIiIiiII2;
    }

    public FancyMessage style(ChatColor ... IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        for (ChatColor chatColor : IiiIiIiiII) {
            if (chatColor.isFormat()) continue;
            throw new IllegalArgumentException(new StringBuilder().insert(0, chatColor.name()).append(" is not a style").toString());
        }
        IiiIiIiiII2.latest().styles.addAll(Arrays.asList(IiiIiIiiII));
        IiiIiIiiII2.dirty = true;
        return IiiIiIiiII2;
    }

    @Override
    public Iterator<MessagePart> iterator() {
        FancyMessage IiiIiIiiII;
        return IiiIiIiiII.messageParts.iterator();
    }

    public FancyMessage text(TextualComponent IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.latest().text = IiiIiIiiII;
        IiiIiIiiII2.dirty = true;
        return IiiIiIiiII2;
    }

    public FancyMessage then(String IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        return IiiIiIiiII2.then(TextualComponent.rawText(IiiIiIiiII));
    }

    public FancyMessage translationReplacements(FancyMessage ... IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        for (FancyMessage fancyMessage : IiiIiIiiII) {
            IiiIiIiiII2.latest().translationReplacements.add(fancyMessage);
        }
        IiiIiIiiII2.dirty = true;
        return IiiIiIiiII2;
    }

    static {
        ConfigurationSerialization.registerClass(FancyMessage.class);
        _stringParser = new JsonParser();
    }

    public FancyMessage insert(String IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.latest().insertionData = IiiIiIiiII;
        IiiIiIiiII2.dirty = true;
        return IiiIiIiiII2;
    }

    public FancyMessage then(TextualComponent IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        if (!IiiIiIiiII2.latest().hasText()) {
            throw new IllegalStateException("previous message part has no text");
        }
        IiiIiIiiII2.messageParts.add(new MessagePart(IiiIiIiiII));
        IiiIiIiiII2.dirty = true;
        return IiiIiIiiII2;
    }

    public FancyMessage statisticTooltip(Statistic IiiIiIiiII4, EntityType IiiIiIiiII2) {
        FancyMessage IiiIiIiiII3;
        Statistic.Type type = IiiIiIiiII4.getType();
        if (type == Statistic.Type.UNTYPED) {
            throw new IllegalArgumentException("That statistic needs no additional parameter!");
        }
        if (type != Statistic.Type.ENTITY) {
            throw new IllegalArgumentException(new StringBuilder().insert(0, "Wrong parameter type for that statistic - needs ").append(type).append("!").toString());
        }
        try {
            IiiIiIiiII4 = Reflection.getMethod(Reflection.getOBCClass("CraftStatistic"), "getEntityStatistic", Statistic.class, EntityType.class).invoke(null, IiiIiIiiII4, IiiIiIiiII2);
            return IiiIiIiiII3.achievementTooltip((String)Reflection.getField(Reflection.getNMSClass("Statistic"), "name").get(IiiIiIiiII4));
        }
        catch (IllegalAccessException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "Could not access method.", IiiIiIiiII4);
            return IiiIiIiiII3;
        }
        catch (IllegalArgumentException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "Argument could not be passed.", IiiIiIiiII4);
            return IiiIiIiiII3;
        }
        catch (InvocationTargetException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "A error has occured durring invoking of method.", IiiIiIiiII4);
            return IiiIiIiiII3;
        }
    }

    public String toOldMessageFormat() {
        FancyMessage IiiIiIiiII;
        StringBuilder stringBuilder = new StringBuilder();
        for (MessagePart messagePart : IiiIiIiiII) {
            stringBuilder.append((Object)(messagePart.color == null ? "" : messagePart.color));
            for (ChatColor chatColor : messagePart.styles) {
                stringBuilder.append(chatColor);
            }
            stringBuilder.append(messagePart.text);
        }
        return stringBuilder.toString();
    }

    public FancyMessage tooltip(String IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.onHover("show_text", new JsonString(IiiIiIiiII));
        return IiiIiIiiII2;
    }

    public FancyMessage(String IiiIiIiiII) {
        IiiIiIiiII2(TextualComponent.rawText(IiiIiIiiII));
        FancyMessage IiiIiIiiII2;
    }

    public FancyMessage text(String IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.latest().text = TextualComponent.rawText(IiiIiIiiII);
        IiiIiIiiII2.dirty = true;
        return IiiIiIiiII2;
    }

    public FancyMessage itemTooltip(ItemStack IiiIiIiiII3) {
        FancyMessage IiiIiIiiII2;
        try {
            IiiIiIiiII3 = Reflection.getMethod(Reflection.getOBCClass("inventory.CraftItemStack"), "asNMSCopy", ItemStack.class).invoke(null, IiiIiIiiII3);
            return IiiIiIiiII2.itemTooltip(Reflection.getMethod(Reflection.getNMSClass("ItemStack"), "save", Reflection.getNMSClass("NBTTagCompound")).invoke(IiiIiIiiII3, Reflection.getNMSClass("NBTTagCompound").newInstance()).toString());
        }
        catch (Exception IiiIiIiiII3) {
            IiiIiIiiII3.printStackTrace();
            return IiiIiIiiII2;
        }
    }

    public FancyMessage achievementTooltip(Achievement IiiIiIiiII3) {
        FancyMessage IiiIiIiiII2;
        try {
            IiiIiIiiII3 = Reflection.getMethod(Reflection.getOBCClass("CraftStatistic"), "getNMSAchievement", Achievement.class).invoke(null, IiiIiIiiII3);
            return IiiIiIiiII2.achievementTooltip((String)Reflection.getField(Reflection.getNMSClass("Achievement"), "name").get(IiiIiIiiII3));
        }
        catch (IllegalAccessException IiiIiIiiII3) {
            Bukkit.getLogger().log(Level.WARNING, "Could not access method.", IiiIiIiiII3);
            return IiiIiIiiII2;
        }
        catch (IllegalArgumentException IiiIiIiiII3) {
            Bukkit.getLogger().log(Level.WARNING, "Argument could not be passed.", IiiIiIiiII3);
            return IiiIiIiiII2;
        }
        catch (InvocationTargetException IiiIiIiiII3) {
            Bukkit.getLogger().log(Level.WARNING, "A error has occured durring invoking of method.", IiiIiIiiII3);
            return IiiIiIiiII2;
        }
    }

    public FancyMessage itemTooltip(String IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.onHover("show_item", new JsonString(IiiIiIiiII));
        return IiiIiIiiII2;
    }

    public void send(Player IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.send((CommandSender)IiiIiIiiII, IiiIiIiiII2.toJSONString());
    }

    public FancyMessage achievementTooltip(String IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.onHover("show_achievement", new JsonString(new StringBuilder().insert(0, "achievement.").append(IiiIiIiiII).toString()));
        return IiiIiIiiII2;
    }

    public void send(CommandSender IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.send(IiiIiIiiII, IiiIiIiiII2.toJSONString());
    }

    public FancyMessage statisticTooltip(Statistic IiiIiIiiII4, Material IiiIiIiiII2) {
        FancyMessage IiiIiIiiII3;
        Statistic.Type type = IiiIiIiiII4.getType();
        if (type == Statistic.Type.UNTYPED) {
            throw new IllegalArgumentException("That statistic needs no additional parameter!");
        }
        if (type == Statistic.Type.BLOCK && IiiIiIiiII2.isBlock() || type == Statistic.Type.ENTITY) {
            throw new IllegalArgumentException(new StringBuilder().insert(0, "Wrong parameter type for that statistic - needs ").append(type).append("!").toString());
        }
        try {
            IiiIiIiiII4 = Reflection.getMethod(Reflection.getOBCClass("CraftStatistic"), "getMaterialStatistic", Statistic.class, Material.class).invoke(null, IiiIiIiiII4, IiiIiIiiII2);
            return IiiIiIiiII3.achievementTooltip((String)Reflection.getField(Reflection.getNMSClass("Statistic"), "name").get(IiiIiIiiII4));
        }
        catch (IllegalAccessException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "Could not access method.", IiiIiIiiII4);
            return IiiIiIiiII3;
        }
        catch (IllegalArgumentException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "Argument could not be passed.", IiiIiIiiII4);
            return IiiIiIiiII3;
        }
        catch (InvocationTargetException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "A error has occured durring invoking of method.", IiiIiIiiII4);
            return IiiIiIiiII3;
        }
    }

    public FancyMessage translationReplacements(Iterable<FancyMessage> IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        return IiiIiIiiII2.translationReplacements(ArrayWrapper.toArray(IiiIiIiiII, FancyMessage.class));
    }

    public FancyMessage statisticTooltip(Statistic IiiIiIiiII3) {
        FancyMessage IiiIiIiiII2;
        Statistic.Type type = IiiIiIiiII3.getType();
        if (type != Statistic.Type.UNTYPED) {
            throw new IllegalArgumentException(new StringBuilder().insert(0, "That statistic requires an additional ").append(type).append(" parameter!").toString());
        }
        try {
            IiiIiIiiII3 = Reflection.getMethod(Reflection.getOBCClass("CraftStatistic"), "getNMSStatistic", Statistic.class).invoke(null, IiiIiIiiII3);
            return IiiIiIiiII2.achievementTooltip((String)Reflection.getField(Reflection.getNMSClass("Statistic"), "name").get(IiiIiIiiII3));
        }
        catch (IllegalAccessException IiiIiIiiII3) {
            Bukkit.getLogger().log(Level.WARNING, "Could not access method.", IiiIiIiiII3);
            return IiiIiIiiII2;
        }
        catch (IllegalArgumentException IiiIiIiiII3) {
            Bukkit.getLogger().log(Level.WARNING, "Argument could not be passed.", IiiIiIiiII3);
            return IiiIiIiiII2;
        }
        catch (InvocationTargetException IiiIiIiiII3) {
            Bukkit.getLogger().log(Level.WARNING, "A error has occured durring invoking of method.", IiiIiIiiII3);
            return IiiIiIiiII2;
        }
    }

    public FancyMessage clone() throws CloneNotSupportedException {
        FancyMessage IiiIiIiiII;
        FancyMessage fancyMessage = (FancyMessage)super.clone();
        fancyMessage.messageParts = new ArrayList<MessagePart>(IiiIiIiiII.messageParts.size());
        for (int i = 0; i < IiiIiIiiII.messageParts.size(); ++i) {
            fancyMessage.messageParts.add(i, (MessagePart)IiiIiIiiII.messageParts.get(i).clone());
        }
        fancyMessage.dirty = false;
        fancyMessage.jsonString = null;
        return fancyMessage;
    }

    private /* synthetic */ MessagePart latest() {
        FancyMessage IiiIiIiiII;
        return IiiIiIiiII.messageParts.get(IiiIiIiiII.messageParts.size() - 1);
    }

    public FancyMessage formattedTooltip(FancyMessage IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        for (MessagePart messagePart : IiiIiIiiII.messageParts) {
            if (messagePart.clickActionData != null && messagePart.clickActionName != null) {
                throw new IllegalArgumentException("The tooltip text cannot have click data.");
            }
            if (messagePart.hoverActionData == null || messagePart.hoverActionName == null) continue;
            throw new IllegalArgumentException("The tooltip text cannot have a tooltip.");
        }
        IiiIiIiiII2.onHover("show_text", IiiIiIiiII);
        return IiiIiIiiII2;
    }

    public Map<String, Object> serialize() {
        FancyMessage IiiIiIiiII;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("messageParts", IiiIiIiiII.messageParts);
        return hashMap;
    }

    public static FancyMessage deserialize(String IiiIiIiiII) {
        Object object = _stringParser.parse(IiiIiIiiII).getAsJsonObject().getAsJsonArray("extra");
        FancyMessage fancyMessage = new FancyMessage();
        fancyMessage.messageParts.clear();
        object = object.iterator();
        while (object.hasNext()) {
            JsonElement jsonElement = (JsonElement)object.next();
            MessagePart messagePart = new MessagePart();
            for (Map.Entry entry : jsonElement.getAsJsonObject().entrySet()) {
                Object object2;
                if (TextualComponent.isTextKey((String)entry.getKey())) {
                    object2 = new HashMap();
                    object2.put("key", entry.getKey());
                    if (((JsonElement)entry.getValue()).isJsonPrimitive()) {
                        object2.put("value", ((JsonElement)entry.getValue()).getAsString());
                    } else {
                        for (Map.Entry entry2 : ((JsonElement)entry.getValue()).getAsJsonObject().entrySet()) {
                            object2.put("value." + (String)entry2.getKey(), ((JsonElement)entry2.getValue()).getAsString());
                        }
                    }
                    messagePart.text = TextualComponent.deserialize((Map<String, Object>)object2);
                    continue;
                }
                if (MessagePart.stylesToNames.inverse().containsKey(entry.getKey())) {
                    if (!((JsonElement)entry.getValue()).getAsBoolean()) continue;
                    messagePart.styles.add((ChatColor)MessagePart.stylesToNames.inverse().get(entry.getKey()));
                    continue;
                }
                if (((String)entry.getKey()).equals("color")) {
                    messagePart.color = ChatColor.valueOf((String)((JsonElement)entry.getValue()).getAsString().toUpperCase());
                    continue;
                }
                if (((String)entry.getKey()).equals("clickEvent")) {
                    object2 = ((JsonElement)entry.getValue()).getAsJsonObject();
                    messagePart.clickActionName = object2.get("action").getAsString();
                    messagePart.clickActionData = object2.get("value").getAsString();
                    continue;
                }
                if (((String)entry.getKey()).equals("hoverEvent")) {
                    object2 = ((JsonElement)entry.getValue()).getAsJsonObject();
                    messagePart.hoverActionName = object2.get("action").getAsString();
                    if (object2.get("value").isJsonPrimitive()) {
                        messagePart.hoverActionData = new JsonString(object2.get("value").getAsString());
                        continue;
                    }
                    messagePart.hoverActionData = FancyMessage.deserialize(object2.get("value").toString());
                    continue;
                }
                if (((String)entry.getKey()).equals("insertion")) {
                    messagePart.insertionData = ((JsonElement)entry.getValue()).getAsString();
                    continue;
                }
                if (!((String)entry.getKey()).equals("with")) continue;
                for (JsonElement jsonElement2 : ((JsonElement)entry.getValue()).getAsJsonArray()) {
                    if (jsonElement2.isJsonPrimitive()) {
                        messagePart.translationReplacements.add(new JsonString(jsonElement2.getAsString()));
                        continue;
                    }
                    messagePart.translationReplacements.add(FancyMessage.deserialize(jsonElement2.toString()));
                }
            }
            fancyMessage.messageParts.add(messagePart);
        }
        return fancyMessage;
    }

    @Override
    public void writeJson(JsonWriter IiiIiIiiII) throws IOException {
        FancyMessage IiiIiIiiII2;
        if (IiiIiIiiII2.messageParts.size() == 1) {
            IiiIiIiiII2.latest().writeJson(IiiIiIiiII);
            return;
        }
        IiiIiIiiII.beginObject().name("text").value("").name("extra").beginArray();
        Iterator<MessagePart> iterator = IiiIiIiiII2.iterator();
        while (iterator.hasNext()) {
            iterator.next().writeJson(IiiIiIiiII);
        }
        IiiIiIiiII.endArray().endObject();
    }

    private /* synthetic */ void onHover(String IiiIiIiiII, JsonRepresentedObject IiiIiIiiII2) {
        FancyMessage IiiIiIiiII3;
        MessagePart messagePart = IiiIiIiiII3.latest();
        messagePart.hoverActionName = IiiIiIiiII;
        messagePart.hoverActionData = IiiIiIiiII2;
        IiiIiIiiII3.dirty = true;
    }

    public FancyMessage link(String IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.onClick("open_url", IiiIiIiiII);
        return IiiIiIiiII2;
    }

    private /* synthetic */ void send(CommandSender IiiIiIiiII4, String IiiIiIiiII2) {
        FancyMessage IiiIiIiiII3;
        if (!(IiiIiIiiII4 instanceof Player)) {
            IiiIiIiiII4.sendMessage(IiiIiIiiII3.toOldMessageFormat());
            return;
        }
        IiiIiIiiII4 = (Player)IiiIiIiiII4;
        try {
            IiiIiIiiII4 = Reflection.getHandle(IiiIiIiiII4);
            Object object = Reflection.getField(IiiIiIiiII4.getClass(), "playerConnection").get(IiiIiIiiII4);
            Reflection.getMethod(object.getClass(), "sendPacket", Reflection.getNMSClass("Packet")).invoke(object, IiiIiIiiII3.createChatPacket(IiiIiIiiII2));
            return;
        }
        catch (IllegalArgumentException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "Argument could not be passed.", IiiIiIiiII4);
            return;
        }
        catch (IllegalAccessException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "Could not access method.", IiiIiIiiII4);
            return;
        }
        catch (InstantiationException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "Underlying class is abstract.", IiiIiIiiII4);
            return;
        }
        catch (InvocationTargetException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "A error has occured durring invoking of method.", IiiIiIiiII4);
            return;
        }
        catch (NoSuchMethodException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "Could not find method.", IiiIiIiiII4);
            return;
        }
        catch (ClassNotFoundException IiiIiIiiII4) {
            Bukkit.getLogger().log(Level.WARNING, "Could not find class.", IiiIiIiiII4);
            return;
        }
    }

    public FancyMessage command(String IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.onClick("run_command", IiiIiIiiII);
        return IiiIiIiiII2;
    }

    public FancyMessage tooltip(String ... IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < IiiIiIiiII.length; ++i) {
            stringBuilder.append(IiiIiIiiII[i]);
            if (i == IiiIiIiiII.length - 1) continue;
            stringBuilder.append('\n');
        }
        IiiIiIiiII2.tooltip(stringBuilder.toString());
        return IiiIiIiiII2;
    }

    public FancyMessage file(String IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.onClick("open_file", IiiIiIiiII);
        return IiiIiIiiII2;
    }

    public static FancyMessage deserialize(Map<String, Object> IiiIiIiiII) {
        FancyMessage fancyMessage = new FancyMessage();
        fancyMessage.messageParts = (List)IiiIiIiiII.get("messageParts");
        fancyMessage.jsonString = IiiIiIiiII.containsKey("JSON") ? IiiIiIiiII.get("JSON").toString() : null;
        fancyMessage.dirty = !IiiIiIiiII.containsKey("JSON");
        return fancyMessage;
    }

    public String toJSONString() {
        FancyMessage IiiIiIiiII;
        if (!IiiIiIiiII.dirty && IiiIiIiiII.jsonString != null) {
            return IiiIiIiiII.jsonString;
        }
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter((Writer)stringWriter);
        try {
            IiiIiIiiII.writeJson(jsonWriter);
            jsonWriter.close();
        }
        catch (IOException iOException) {
            throw new RuntimeException("invalid message");
        }
        IiiIiIiiII.jsonString = stringWriter.toString();
        IiiIiIiiII.dirty = false;
        return IiiIiIiiII.jsonString;
    }

    public FancyMessage suggest(String IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.onClick("suggest_command", IiiIiIiiII);
        return IiiIiIiiII2;
    }

    public FancyMessage tooltip(Iterable<String> IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.tooltip(ArrayWrapper.toArray(IiiIiIiiII, String.class));
        return IiiIiIiiII2;
    }

    public FancyMessage color(ChatColor IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        if (!IiiIiIiiII.isColor()) {
            throw new IllegalArgumentException(new StringBuilder().insert(0, IiiIiIiiII.name()).append(" is not a color").toString());
        }
        IiiIiIiiII2.latest().color = IiiIiIiiII;
        IiiIiIiiII2.dirty = true;
        return IiiIiIiiII2;
    }

    private /* synthetic */ Object createChatPacket(String IiiIiIiiII) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz;
        if (nmsChatSerializerGsonInstance == null) {
            clazz = Integer.parseInt(Reflection.getVersion().replace('_', '.').substring(3, 5).replaceAll("[^0-9]", "")) < 8 ? Reflection.getNMSClass("ChatSerializer") : Reflection.getNMSClass("IChatBaseComponent$ChatSerializer");
            if (clazz == null) {
                throw new ClassNotFoundException("Can't find the ChatSerializer class");
            }
            for (Field field : clazz.getDeclaredFields()) {
                if (!Modifier.isFinal(field.getModifiers()) || !Modifier.isStatic(field.getModifiers()) || !field.getType().getName().endsWith("Gson")) continue;
                field.setAccessible(true);
                nmsChatSerializerGsonInstance = field.get(null);
                fromJsonMethod = nmsChatSerializerGsonInstance.getClass().getMethod("fromJson", String.class, Class.class);
                break;
            }
        }
        clazz = fromJsonMethod.invoke(nmsChatSerializerGsonInstance, IiiIiIiiII, Reflection.getNMSClass("IChatBaseComponent"));
        return nmsPacketPlayOutChatConstructor.newInstance(clazz);
    }

    private /* synthetic */ void onClick(String IiiIiIiiII, String IiiIiIiiII2) {
        FancyMessage IiiIiIiiII3;
        MessagePart messagePart = IiiIiIiiII3.latest();
        messagePart.clickActionName = IiiIiIiiII;
        messagePart.clickActionData = IiiIiIiiII2;
        IiiIiIiiII3.dirty = true;
    }

    public void send(Iterable<? extends CommandSender> IiiIiIiiII) {
        FancyMessage IiiIiIiiII2;
        String string = IiiIiIiiII2.toJSONString();
        IiiIiIiiII = IiiIiIiiII.iterator();
        while (IiiIiIiiII.hasNext()) {
            CommandSender commandSender = (CommandSender)IiiIiIiiII.next();
            IiiIiIiiII2.send(commandSender, string);
        }
    }

    public FancyMessage(TextualComponent IiiIiIiiII3) {
        FancyMessage IiiIiIiiII2;
        IiiIiIiiII2.messageParts.add(new MessagePart(IiiIiIiiII3));
        IiiIiIiiII2.jsonString = null;
        IiiIiIiiII2.dirty = false;
        if (nmsPacketPlayOutChatConstructor == null) {
            try {
                nmsPacketPlayOutChatConstructor = Reflection.getNMSClass("PacketPlayOutChat").getDeclaredConstructor(Reflection.getNMSClass("IChatBaseComponent"));
                nmsPacketPlayOutChatConstructor.setAccessible(true);
                return;
            }
            catch (NoSuchMethodException IiiIiIiiII3) {
                Bukkit.getLogger().log(Level.SEVERE, "Could not find Minecraft method or constructor.", IiiIiIiiII3);
                return;
            }
            catch (SecurityException IiiIiIiiII3) {
                Bukkit.getLogger().log(Level.WARNING, "Could not access constructor.", IiiIiIiiII3);
            }
        }
    }

    public FancyMessage then() {
        FancyMessage IiiIiIiiII;
        if (!IiiIiIiiII.latest().hasText()) {
            throw new IllegalStateException("previous message part has no text");
        }
        IiiIiIiiII.messageParts.add(new MessagePart());
        IiiIiIiiII.dirty = true;
        return IiiIiIiiII;
    }
}

