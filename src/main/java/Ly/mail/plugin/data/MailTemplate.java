/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  me.clip.placeholderapi.PlaceholderAPI
 *  org.bukkit.Bukkit
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.entity.Player
 */
package Ly.mail.plugin.data;

import Ly.mail.plugin.fanciful.FancyMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MailTemplate {
    private boolean enable = true;
    private long time;
    private List<String> commands;
    private List<String> info;
    private List<String> conditions;
    private String id;
    private String title;
    private int need_slot;

    public String getTitle() {
        MailTemplate IiiIiIiiII = null;
        return IiiIiIiiII.title;
    }

    public MailTemplate(YamlConfiguration IiiIiIiiII, String IiiIiIiiII2) {
        MailTemplate IiiIiIiiII3 = null;
        String string            = IiiIiIiiII3.id = IiiIiIiiII.contains("id") ? IiiIiIiiII.getString("id") : null;
        if (IiiIiIiiII3.id == null) {
            IiiIiIiiII3.enable = false;
            Bukkit.getConsoleSender().sendMessage(new StringBuilder().insert(0, "\u00a77").append(IiiIiIiiII2).append(" \u7684 id \u4e0d\u5b58\u5728\uff0c\u5df2\u53d6\u6d88\u672c\u6b21\u8f7d\u5165\u3002").toString());
            return;
        }
        String string2 = IiiIiIiiII3.title = IiiIiIiiII.contains("title") ? IiiIiIiiII.getString("title") : null;
        if (IiiIiIiiII3.title == null) {
            IiiIiIiiII3.enable = false;
            Bukkit.getConsoleSender().sendMessage(new StringBuilder().insert(0, "\u00a77").append(IiiIiIiiII2).append(" \u7684 title \u4e0d\u5b58\u5728\uff0c\u5df2\u53d6\u6d88\u672c\u6b21\u8f7d\u5165\u3002").toString());
            return;
        }
        List<String> list = IiiIiIiiII3.info = IiiIiIiiII.contains("info") ? IiiIiIiiII.getStringList("info") : null;
        if (IiiIiIiiII3.info == null) {
            IiiIiIiiII3.enable = false;
            Bukkit.getConsoleSender().sendMessage(new StringBuilder().insert(0, "\u00a77").append(IiiIiIiiII2).append(" \u7684 info \u4e0d\u5b58\u5728\uff0c\u5df2\u53d6\u6d88\u672c\u6b21\u8f7d\u5165\u3002").toString());
            return;
        }
        long l = IiiIiIiiII3.time = IiiIiIiiII.contains("expiration-time") ? IiiIiIiiII.getLong("expiration-time") : new Long(0L).longValue();
        if (IiiIiIiiII3.time == 0L) {
            IiiIiIiiII3.enable = false;
            Bukkit.getConsoleSender().sendMessage(new StringBuilder().insert(0, "\u00a77").append(IiiIiIiiII2).append(" \u7684 expiration-time \u4e0d\u5b58\u5728\uff0c\u5df2\u53d6\u6d88\u672c\u6b21\u8f7d\u5165\u3002").toString());
            return;
        }
        IiiIiIiiII3.need_slot = IiiIiIiiII.contains("need-slot") ? IiiIiIiiII.getInt("need-slot") : 0;
        IiiIiIiiII3.conditions = IiiIiIiiII.contains("receive-conditions") ? IiiIiIiiII.getStringList("receive-conditions") : new ArrayList();
        IiiIiIiiII3.commands = IiiIiIiiII.contains("receive-commands") ? IiiIiIiiII.getStringList("receive-commands") : new ArrayList();
    }

/*    public boolean checkConditions(Player IiiIiIiiII) {
        MailTemplate IiiIiIiiII2 = null;
        for (String string : IiiIiIiiII2.conditions) {
            Object PlaceholderAPI = null;
            if (new JavascriptRequirement(string = PlaceholderAPI.setPlaceholders((Player)IiiIiIiiII, (String)string)).evaluate()) continue;
            return false;
        }
        return true;
    }*/

    public List<String> getInfo() {
        MailTemplate IiiIiIiiII = null;
        return IiiIiIiiII.info;
    }

    public long getTime() {
        MailTemplate IiiIiIiiII = null;
        return IiiIiIiiII.time;
    }

    public boolean sendMail(OfflinePlayer IiiIiIiiII, MailData IiiIiIiiII2) {
        if (IiiIiIiiII == null) {
            return false;
        }
        ShitLyMail .MailAPI.getMailManager().addMail(IiiIiIiiII, ((MailData)IiiIiIiiII2).getMailUUID(), ((MailData)IiiIiIiiII2).getMailTemplateId(), ((MailData)IiiIiIiiII2).getExpiredTime());
        IiiIiIiiII2 = ShitLyMail .config.getString("message.receive-click", "").replace("&", "\u00a7");
        if (IiiIiIiiII.isOnline()) {
            if (!((String)IiiIiIiiII2).equals("")) {
                IiiIiIiiII2 = new FancyMessage(ShitLyMail .config.getString("message.receive-mail").replace("&", "\u00a7"));
                ((FancyMessage)IiiIiIiiII2).then(ShitLyMail .config.getString("message.receive-click", " &7&n\u70b9\u51fb\u67e5\u770b"));
                ((FancyMessage)IiiIiIiiII2).command("/lmail open");
                ShitLyMail .MailAPI.sendJson(IiiIiIiiII.getPlayer(), (FancyMessage)IiiIiIiiII2);
                ShitLyMail .MailAPI.playSound(IiiIiIiiII.getPlayer());
            } else {
                IiiIiIiiII.getPlayer().sendMessage(ShitLyMail .config.getString("message.receive-mail").replace("&", "\u00a7"));
                ShitLyMail .MailAPI.playSound(IiiIiIiiII.getPlayer());
            }
        }
        return true;
    }

    public String getId() {
        MailTemplate IiiIiIiiII;
        return IiiIiIiiII.id;
    }

    private /* synthetic */ void playerCommand(Player IiiIiIiiII, String IiiIiIiiII2, boolean IiiIiIiiII3) {
        boolean bl = IiiIiIiiII.isOp();
        if (IiiIiIiiII3 && !bl) {
            IiiIiIiiII.setOp(true);
        }
        Bukkit.dispatchCommand((CommandSender)IiiIiIiiII, (String)IiiIiIiiII2);
        if (IiiIiIiiII3 && !bl) {
            IiiIiIiiII.setOp(false);
        }
    }

    public boolean isEnable() {
        MailTemplate IiiIiIiiII;
        return IiiIiIiiII.enable;
    }

    public void useCommands(Player IiiIiIiiII) {
        MailTemplate IiiIiIiiII2 = null;
        for (String string : IiiIiIiiII2.commands) {
            string = string.replace("%p", IiiIiIiiII.getName());
            if ((string = PlaceholderAPI.setPlaceholders((Player)IiiIiIiiII, (String)string)).contains("[op]")) {
                IiiIiIiiII2.playerCommand(IiiIiIiiII, string.replace("[op]", ""), true);
                continue;
            }
            if (string.contains("[console]")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)string.replace("[console]", ""));
                continue;
            }
            if (!string.contains("[player]")) continue;
            IiiIiIiiII2.playerCommand(IiiIiIiiII, string.replace("[player]", ""), false);
        }
    }

    public int getNeedSlot() {
        MailTemplate IiiIiIiiII = null;
        return IiiIiIiiII.need_slot;
    }

    public MailData getMailData(OfflinePlayer IiiIiIiiII) {
        MailTemplate IiiIiIiiII2;
        return new MailData(UUID.randomUUID(), IiiIiIiiII.getUniqueId(), IiiIiIiiII2.id, new Date().getTime() + IiiIiIiiII2.time * 1000L);
    }
}

