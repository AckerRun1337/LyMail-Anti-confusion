/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.OfflinePlayer
 */
package Ly.mail.plugin.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class MailData {
    private UUID uuid;
    private UUID owner;
    private long endTime;
    private String templateId;

    public UUID getMailUUID() {
        MailData IiiIiIiiII = null;
        return IiiIiIiiII.uuid;
    }

    public String getExpiredTimeFormat() {
        MailData IiiIiIiiII               = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ShitLyMail .config.getString("time-format").replace("&", "§"));
        return simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(IiiIiIiiII.endTime)))).toString();
    }

    public long getExpiredTime() {
        MailData IiiIiIiiII = null;
        return IiiIiIiiII.endTime;
    }

    public OfflinePlayer getOwner() {
        MailData IiiIiIiiII = null;
        return Bukkit.getOfflinePlayer((UUID)IiiIiIiiII.owner);
    }

    public boolean isExpired() {
        MailData IiiIiIiiII = null;
        return new Date().getTime() >= IiiIiIiiII.endTime;
    }

    public String getMailTemplateId() {
        MailData IiiIiIiiII = null;
        return IiiIiIiiII.templateId;
    }

    public MailData(UUID IiiIiIiiII, UUID IiiIiIiiII2, String IiiIiIiiII3, long IiiIiIiiII4) {
        MailData IiiIiIiiII5 = null;
        IiiIiIiiII5.uuid = IiiIiIiiII;
        IiiIiIiiII5.owner = IiiIiIiiII2;
        IiiIiIiiII5.templateId = IiiIiIiiII3;
        IiiIiIiiII5.endTime = IiiIiIiiII4;
    }
}

