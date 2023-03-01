/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package Ly.mail.plugin;

import Ly.mail.plugin.data.MailManager;
import Ly.mail.plugin.fanciful.FancyMessage;
import org.bukkit.entity.Player;

public interface LyMailAPI {
    public static final MailManager MailManager = null;

    public void playSound(Player var1);

    public void sendJson(Player var1, FancyMessage var2);

    public MailManager getMailManager();
}

