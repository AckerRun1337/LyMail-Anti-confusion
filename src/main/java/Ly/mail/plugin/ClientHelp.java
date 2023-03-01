/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package Ly.mail.plugin;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

public class ClientHelp
implements Help {
    @Override
    public void disablePlugin() {
    }

    @Override
    public void reload() {
        ShitLyMail .getInstance().saveDefaultConfig();
        ShitLyMail .getInstance().reloadConfig();
        ShitLyMail .configFile = new File(new StringBuilder().insert(0, "plugins/").append(ShitLyMail .getInstance().getName()).append("/config.yml").toString());
        ShitLyMail .config = YamlConfiguration.loadConfiguration((File)ShitLyMail .configFile);
    }

    @Override
    public void loadMailTemplate() {
    }

    public ClientHelp() {
        ClientHelp IiiIiIiiII;
    }
}

