/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerLoginEvent
 *  org.bukkit.event.player.PlayerLoginEvent$Result
 */
package Ly.mail.plugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener
implements Listener {
    public JoinListener() {
        JoinListener IiiIiIiiII;
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent IiiIiIiiII) {
        Player player = IiiIiIiiII.getPlayer();
        if (!ShitLyMail .licence) {
            IiiIiIiiII.disallow(PlayerLoginEvent.Result.KICK_OTHER, "\u63d2\u4ef6\u521d\u59cb\u5316\u672a\u5b8c\u6210, \u8bf7\u7a0d\u540e\u518d\u8fdb\u5165\u670d\u52a1\u5668.");
        }
    }
}

