/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package Ly.mail.plugin.util;

import Ly.mail.plugin.util.JsonBuilder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class ItemSerialize {
    static ItemSerialize itemSerialize;

    public abstract String getName();

    public ItemSerialize() {
        ItemSerialize IiiIiIiiII;
    }

    public abstract String parse(ItemStack var1);

    public static String $(ItemStack IiiIiIiiII) {
        return itemSerialize.parse(IiiIiIiiII);
    }

    static {
        try {
            itemSerialize = new Automatic();
        }
        catch (IllegalStateException illegalStateException) {
            itemSerialize = new Manual();
        }
    }

    static class Manual
    extends ItemSerialize {
        @Override
        public String parse(ItemStack IiiIiIiiII) {
            Manual IiiIiIiiII2 = null;
            return IiiIiIiiII2.serialize(IiiIiIiiII);
        }

        Manual() {
            Manual IiiIiIiiII;
        }

        private /* synthetic */ String getTag(ItemMeta IiiIiIiiII) {
            Manual IiiIiIiiII2          = null;
            StringBuilder stringBuilder = new StringBuilder("{");
            if (IiiIiIiiII.hasEnchants()) {
                stringBuilder.append(String.format("ench:[%s],", IiiIiIiiII2.getEnch(IiiIiIiiII.getEnchants().entrySet())));
            }
            if (IiiIiIiiII.hasDisplayName() || IiiIiIiiII.hasLore()) {
                stringBuilder.append(String.format("display:%s,", IiiIiIiiII2.getDisplay(IiiIiIiiII)));
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }

        @Override
        public String getName() {
            return "Manual";
        }

        private /* synthetic */ String getDisplay(ItemMeta IiiIiIiiII) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            if (IiiIiIiiII.hasDisplayName()) {
                stringBuilder.append(String.format("Name:\"%s\",", IiiIiIiiII.getDisplayName()));
            }
            if (IiiIiIiiII.hasLore()) {
                stringBuilder.append("Lore:[");
                int n = 0;
                for (String string : IiiIiIiiII.getLore()) {
                    Object[] objectArray = new Object[2];
                    objectArray[0] = n;
                    ++n;
                    objectArray[1] = new JsonBuilder(string).toString();
                    stringBuilder.append(String.format("%s:\"%s\",", objectArray));
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append("],");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }

        private /* synthetic */ String serialize(ItemStack IiiIiIiiII) {
            StringBuilder stringBuilder = new StringBuilder("{");
            stringBuilder.append(String.format("id:\"%s\",Damage:\"%s\"",  IiiIiIiiII.getDurability()));
            if (IiiIiIiiII.getAmount() > 1) {
                stringBuilder.append(String.format(",Count:%s", IiiIiIiiII.getAmount()));
            }
            if (IiiIiIiiII.hasItemMeta()) {
                Manual IiiIiIiiII2 = null;
                stringBuilder.append(String.format(",tag:%s", IiiIiIiiII2.getTag(IiiIiIiiII.getItemMeta())));
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }

        private /* synthetic */ String getEnch(Set<Map.Entry<Enchantment, Integer>> IiiIiIiiII) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<Enchantment, Integer> entry : IiiIiIiiII) {
                stringBuilder.append(String.format("{id:%s,lvl:%s},",entry.getValue()));
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
    }

    static class Automatic
    extends ItemSerialize {
        private static Class<?>[] nmsNBTTagCompound;
        private static String ver;
        private static boolean inited;
        private static Method nmsSaveNBTMethod;
        private static Method asNMSCopyMethod;



        @Override
        public String getName() {
            return "Automatic";
        }

        public Automatic() {
            Automatic IiiIiIiiII;
            if (!inited) {
                throw new IllegalStateException("无法初始化自动处理类!");
            }
        }

        @Override
        public String parse(ItemStack var1) {
            return null;
        }

        static {
            Class<?>[] classArray;
            inited = false;
            ver = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
            Class clazz = null;
            try {
                clazz = Automatic.getOBCClass("inventory.CraftItemStack");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                asNMSCopyMethod = clazz.getMethod("asNMSCopy", ItemStack.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            Class<?> clazz2 = asNMSCopyMethod.getReturnType();
            for (Method method : clazz2.getMethods()) {
                classArray = new Class[]{method.getReturnType()};
/*                if (method.getParameterTypes().length != 0 || !"NBTTagCompound".equals(classArray.getSimpleName())) continue;*/
                nmsNBTTagCompound = classArray;
            }
            for (Method method : clazz2.getMethods()) {
                classArray = method.getParameterTypes();
                Class<?> clazz3 = method.getReturnType();
                if (classArray.length != 1 || !"NBTTagCompound".equals(classArray[0].getSimpleName()) || !"NBTTagCompound".equals(clazz3.getSimpleName())) continue;
                nmsSaveNBTMethod = method;
            }
            inited = true;

        }

        private static /* synthetic */ Class getOBCClass(String IiiIiIiiII) throws ClassNotFoundException {
            return Class.forName(new StringBuilder().insert(0, "org.bukkit.craftbukkit.").append(ver).append(".").append(IiiIiIiiII).toString());
        }
    }
}

