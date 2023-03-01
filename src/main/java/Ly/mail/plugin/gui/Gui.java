/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  me.clip.placeholderapi.PlaceholderAPI
 *  org.bukkit.Bukkit
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.ItemStack
 */
package Ly.mail.plugin.gui;

import Ly.mail.plugin.Utils;
import Ly.mail.plugin.data.MailData;
import Ly.mail.plugin.data.MailTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class Gui {
    public static Map<UUID, Integer> status = new ConcurrentHashMap<UUID, Integer>();

    public static void update(Player IiiIiIiiII, int IiiIiIiiII2) {
        int n;
        if (IiiIiIiiII.getOpenInventory() == null) {
            return;
        }
        Inventory inventory = IiiIiIiiII.getOpenInventory().getTopInventory();
        status.put(IiiIiIiiII.getUniqueId(), IiiIiIiiII2);
        Map<UUID, MailData> map = ShitLyMail .MailAPI.getMailManager().getPlayerAllMails((OfflinePlayer)IiiIiIiiII);
        for (n = 0; n < 45; ++n) {
            inventory.setItem(n, null);
        }
        if (map != null && map.size() > 0) {
            n = 0;
            for (Map.Entry entry : map.entrySet()) {
                if (n < 45 * (IiiIiIiiII2 - 1)) {
                    ++n;
                    continue;
                }
                if (n == 45 * IiiIiIiiII2) {
                    return;
                }
                MailData mailData = (MailData)entry.getValue();
                if (!ShitLyMail .MailAPI.getMailManager().hasMailTemplate(mailData.getMailTemplateId())) continue;
                MailTemplate mailTemplate = ShitLyMail .MailAPI.getMailManager().getMailTemplate(mailData.getMailTemplateId());
                List<String> list = new ArrayList<String>(mailTemplate.getInfo());
                List list2 = ShitLyMail .config.getStringList("gui.mail-lore");
                list.addAll(list2);
                list = Utils.ListReplace(list, "{time}", mailData.getExpiredTimeFormat());
                list = Utils.ListReplace(list, "&", "\u00a7");
                list = PlaceholderAPI.setPlaceholders((Player)IiiIiIiiII, list);
                String string = mailTemplate.getTitle().replace("&", "\u00a7");
                mailTemplate = Utils.getItem(ShitLyMail .config.getString("gui.mail-item"));
                mailTemplate = Utils.itemSetName((ItemStack)mailTemplate, string);
                mailTemplate = Utils.itemSetLore((ItemStack)mailTemplate, list);
                inventory.setItem(n++ - 45 * (IiiIiIiiII2 - 1), (ItemStack)mailTemplate);
            }
        }
    }

    public static ItemStack getInfoNextItem1() {
        ItemStack itemStack = Utils.getItem(ShitLyMail .config.getString("gui.next-item1"));
        String string = ShitLyMail .config.getString("gui.next-name1").replace("&", "\u00a7");
        itemStack = Utils.itemSetName(itemStack, string);
        return itemStack;
    }

    public static void open(Player IiiIiIiiII, int IiiIiIiiII2) {
        IiiIiIiiII.closeInventory();
        status.put(IiiIiIiiII.getUniqueId(), IiiIiIiiII2);
        Inventory inventory = Bukkit.createInventory((InventoryHolder)IiiIiIiiII, (int)54, (String)ShitLyMail .config.getString("gui.title").replace("&", "\u00a7"));
        Map<UUID, MailData> map = ShitLyMail .MailAPI.getMailManager().getPlayerAllMails((OfflinePlayer)IiiIiIiiII);
        if (map != null && map.size() > 0) {
            int n = 0;
            for (Map.Entry entry : map.entrySet()) {
                if (n < 45 * (IiiIiIiiII2 - 1)) continue;
                if (n == 45 * IiiIiIiiII2) break;
                MailData mailData = (MailData)entry.getValue();
                if (!ShitLyMail .MailAPI.getMailManager().hasMailTemplate(mailData.getMailTemplateId())) continue;
                MailTemplate mailTemplate = ShitLyMail .MailAPI.getMailManager().getMailTemplate(mailData.getMailTemplateId());
                List<String> list = new ArrayList<String>(mailTemplate.getInfo());
                List list2 = ShitLyMail .config.getStringList("gui.mail-lore");
                list.addAll(list2);
                list = Utils.ListReplace(list, "{time}", mailData.getExpiredTimeFormat());
                list = Utils.ListReplace(list, "&", "\u00a7");
                list = PlaceholderAPI.setPlaceholders((Player)IiiIiIiiII, list);
                String string = mailTemplate.getTitle().replace("&", "\u00a7");
                mailTemplate = Utils.getItem(ShitLyMail .config.getString("gui.mail-item"));
                mailTemplate = Utils.itemSetName((ItemStack)mailTemplate, string);
                mailTemplate = Utils.itemSetLore((ItemStack)mailTemplate, list);
                inventory.setItem(n++ - 45 * (IiiIiIiiII2 - 1), (ItemStack)mailTemplate);
            }
        }
        inventory.setItem(45, Gui.getInfoNextItem1());
        inventory.setItem(46, Gui.getInfoFillItem());
        inventory.setItem(47, Gui.getInfoFillItem());
        inventory.setItem(48, Gui.getInfoFillItem());
        inventory.setItem(49, Gui.getInfoFillItem());
        inventory.setItem(50, Gui.getInfoFillItem());
        inventory.setItem(51, Gui.getInfoFillItem());
        inventory.setItem(52, Gui.getInfoFillItem());
        inventory.setItem(53, Gui.getInfoNextItem2());
        IiiIiIiiII.openInventory(inventory);
    }

    public static ItemStack getInfoFillItem() {
        ItemStack itemStack = Utils.getItem(ShitLyMail .config.getString("gui.fill-item"));
        String string = ShitLyMail .config.getString("gui.fill-name").replace("&", "\u00a7");
        itemStack = Utils.itemSetName(itemStack, string);
        return itemStack;
    }

    public static ItemStack getInfoNextItem2() {
        ItemStack itemStack = Utils.getItem(ShitLyMail .config.getString("gui.next-item2"));
        String string = ShitLyMail .config.getString("gui.next-name2").replace("&", "\u00a7");
        itemStack = Utils.itemSetName(itemStack, string);
        return itemStack;
    }

    public Gui() {
        Gui IiiIiIiiII;
    }
}

