/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package Ly.mail.plugin;

import Ly.mail.plugin.sql.MySQLManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {
    public static Object getData(UUID IiiIiIiiII, String IiiIiIiiII2, OfflinePlayer IiiIiIiiII3) {
        if (ShitLyMail.sql) {
            return MySQLManager.get(IiiIiIiiII.toString(), IiiIiIiiII2);
        }
        IiiIiIiiII3 = YamlConfiguration.loadConfiguration((File)new File(new StringBuilder().insert(0, "plugins/").append(ShitLyMail .getInstance().getName()).append("/data/").append(IiiIiIiiII3.getName()).append(".yml").toString()));
        if (IiiIiIiiII3.get(IiiIiIiiII.toString() + "." + IiiIiIiiII2) != null) {
            return IiiIiIiiII3.get(new StringBuilder().insert(0, IiiIiIiiII.toString()).append(".").append(IiiIiIiiII2).toString());
        }
        return "";
    }

    public static ItemStack itemSetName(ItemStack IiiIiIiiII, String IiiIiIiiII2) {
        ItemMeta itemMeta = IiiIiIiiII.getItemMeta();
        itemMeta.setDisplayName(IiiIiIiiII2);
        IiiIiIiiII.setItemMeta(itemMeta);
        return IiiIiIiiII;
    }

    public static List<String> ListReplace(List<String> IiiIiIiiII, String IiiIiIiiII2, String IiiIiIiiII3) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < IiiIiIiiII.size(); ++i) {
            arrayList.add(IiiIiIiiII.get(i).replace(IiiIiIiiII2, IiiIiIiiII3));
        }
        return arrayList;
    }

    public static List<String> getAllData(OfflinePlayer IiiIiIiiII) {
        if (ShitLyMail .sql) {
            ArrayList<String> arrayList = new ArrayList<String>();
            List<Object> list = MySQLManager.getAllMail();
            for (Object object : list) {
                String string = object.toString();
                if (!MySQLManager.get(string, "owner").equalsIgnoreCase(IiiIiIiiII.getName())) continue;
                arrayList.add(string);
            }
            return arrayList;
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        File file = new File(new StringBuilder().insert(0, "plugins/").append(ShitLyMail .getInstance().getName()).append("/data/").append(IiiIiIiiII.getName()).append(".yml").toString());
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration((File)file);
        for (String string : yamlConfiguration.getKeys(false)) {
            arrayList.add(string);
        }
        return arrayList;
    }

    public static ItemStack getItem(String IiiIiIiiII) {
        int n = 0;
        int n2 = 0;
        if (IiiIiIiiII.indexOf(":") > -1) {
            n = Integer.parseInt(IiiIiIiiII.split(":")[0]);
            n2 = Integer.parseInt(IiiIiIiiII.split(":")[1]);
        } else {
            n = Integer.parseInt(IiiIiIiiII);
        }
        if (n != 0) {
            return new ItemStack(Material.getMaterial((int)n), 1, (short)n2);
        }
        return null;
    }

    public static void setData(UUID IiiIiIiiII, String IiiIiIiiII22, String IiiIiIiiII3, OfflinePlayer IiiIiIiiII4) {
        if (ShitLyMail .sql) {
            MySQLManager.set(IiiIiIiiII.toString(), IiiIiIiiII22, IiiIiIiiII3);
            return;
        }
        IiiIiIiiII4 = new File(new StringBuilder().insert(0, "plugins/").append(ShitLyMail .getInstance().getName()).append("/data/").append(IiiIiIiiII4.getName()).append(".yml").toString());
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration((File)IiiIiIiiII4);
        yamlConfiguration.set(IiiIiIiiII.toString() + "." + IiiIiIiiII22, (Object)IiiIiIiiII3);
        try {
            yamlConfiguration.save((File)IiiIiIiiII4);
            return;
        }
        catch (IOException IiiIiIiiII22) {
            IiiIiIiiII22.printStackTrace();
            return;
        }
    }

    public static ItemStack itemSetLore(ItemStack IiiIiIiiII, List<String> IiiIiIiiII2) {
        ItemMeta itemMeta = IiiIiIiiII.getItemMeta();
        itemMeta.setLore(IiiIiIiiII2);
        IiiIiIiiII.setItemMeta(itemMeta);
        return IiiIiIiiII;
    }

    public static List<File> getFileList(String IiiIiIiiII) {
        ArrayList<File> arrayList = new ArrayList<File>();
        File[] fileArray = new File(IiiIiIiiII).listFiles();
        if (fileArray != null) {
            for (int i = 0; i < fileArray.length; ++i) {
                String string = fileArray[i].getName();
                if (fileArray[i].isDirectory()) {
                    arrayList.addAll(Utils.getFileList(fileArray[i].getAbsolutePath()));
                    continue;
                }
                if (!string.toLowerCase().endsWith(".yml")) continue;
                arrayList.add(new File(fileArray[i].getPath()));
            }
        }
        return arrayList;
    }

    public static void saveLog(Player IiiIiIiiII, String IiiIiIiiII22, String IiiIiIiiII3) {
        if (!ShitLyMail .config.getBoolean("log")) {
            return;
        }
        File file = new File(new StringBuilder().insert(0, "plugins/").append(ShitLyMail .getInstance().getName()).append("/logs.yml").toString());
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration((File)file);
        yamlConfiguration.set(IiiIiIiiII.getName() + "." + IiiIiIiiII3, (Object)IiiIiIiiII22);
        try {
            yamlConfiguration.save(file);
            return;
        }
        catch (IOException IiiIiIiiII22) {
            IiiIiIiiII22.printStackTrace();
            return;
        }
    }

    public static void deleteData(UUID IiiIiIiiII, OfflinePlayer IiiIiIiiII22) {
        if (ShitLyMail .sql) {
            MySQLManager.delete(IiiIiIiiII22.getName());
            return;
        }
        IiiIiIiiII22 = new File(new StringBuilder().insert(0, "plugins/").append(ShitLyMail .getInstance().getName()).append("/data/").append(IiiIiIiiII22.getName()).append(".yml").toString());
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration((File)IiiIiIiiII22);
        yamlConfiguration.set(IiiIiIiiII.toString(), null);
        try {
            yamlConfiguration.save((File)IiiIiIiiII22);
            return;
        }
        catch (IOException IiiIiIiiII22) {
            IiiIiIiiII22.printStackTrace();
            return;
        }
    }

    public Utils() {
        Utils IiiIiIiiII;
    }

    public static int getInvEmptySlot(Player IiiIiIiiII) {
        Player player = IiiIiIiiII;
        int n = 0;
        player = player.getInventory();
        for (int i = 0; i < 36; ++i) {
            if (player.getItem(i) != null) continue;
            ++n;
        }
        return n;
    }
}

