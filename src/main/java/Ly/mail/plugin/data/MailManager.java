/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.OfflinePlayer
 */
package Ly.mail.plugin.data;

import Ly.mail.plugin.data.MailData;
import Ly.mail.plugin.data.MailTemplate;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.OfflinePlayer;

public class MailManager {
    private static Map<UUID, Map<UUID, MailData>> datas = new ConcurrentHashMap<UUID, Map<UUID, MailData>>();
    private static Map<String, MailTemplate> templates = new ConcurrentHashMap<String, MailTemplate>();

    public void setMailTemplate(String IiiIiIiiII, MailTemplate IiiIiIiiII2) {
        templates.put(IiiIiIiiII, IiiIiIiiII2);
    }

    public boolean hasMailTemplate(String IiiIiIiiII) {
        return templates.containsKey(IiiIiIiiII);
    }

    public Map<UUID, Map<UUID, MailData>> getAllMails() {
        return datas;
    }

    public MailManager() {
        MailManager IiiIiIiiII;
    }

    public void setAllMails(Map<UUID, Map<UUID, MailData>> IiiIiIiiII) {
        datas = IiiIiIiiII;
    }

    public MailTemplate getMailTemplate(String IiiIiIiiII) {
        return templates.get(IiiIiIiiII);
    }

    public boolean removeMail(OfflinePlayer IiiIiIiiII, UUID IiiIiIiiII2) {
        Map<UUID, MailData> map;
        if (datas.containsKey(IiiIiIiiII.getUniqueId()) && (map = datas.get(IiiIiIiiII.getUniqueId())).containsKey(IiiIiIiiII2)) {
            map.remove(IiiIiIiiII2);
            datas.put(IiiIiIiiII.getUniqueId(), map);
            return true;
        }
        return false;
    }
}

/*    public void addMail(OfflinePlayer IiiIiIiiII, UUID IiiIiIiiII2, String IiiIiIiiII3, long IiiIiIiiII42) {
        IiiIiIiiII3 = String.valueOf(new MailData(IiiIiIiiII2, IiiIiIiiII.getUniqueId(), (String)IiiIiIiiII3, IiiIiIiiII42));
        if (datas.containsKey(IiiIiIiiII.getUniqueId())) {
            Map<UUID, MailData> IiiIiIiiIIa = datas.get(IiiIiIiiII.getUniqueId());
            IiiIiIiiII42.put(IiiIiIiiII2, (MailData)IiiIiIiiII3);
            datas.put(IiiIiIiiII.getUniqueId(), IiiIiIiiII42);
            return;
        }
        ConcurrentHashMap<UUID, Object> IiiIiIiiII42 = new ConcurrentHashMap<UUID, Object>();
        IiiIiIiiII42.put(IiiIiIiiII2, IiiIiIiiII3);
        datas.put(IiiIiIiiII.getUniqueId(), IiiIiIiiII42);
    }

    public Map<UUID, MailData> getPlayerAllMails(OfflinePlayer IiiIiIiiII) {
        return datas.get(IiiIiIiiII.getUniqueId());
    }
}*/

