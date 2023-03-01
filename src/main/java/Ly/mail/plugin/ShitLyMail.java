package Ly.mail.plugin;

import Ly.mail.origin.PluginProxy;
import Ly.mail.plugin.data.MailTemplate;
import Ly.mail.plugin.gui.Gui;
import Ly.mail.plugin.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author YieldRain
 * @date 2023/3/1 13:53
 */
public class ShitLyMail extends JavaPlugin
        implements PluginProxy {

    //别说话那么嚣张,一口一个傻逼的,看看你写的弱智代码,看了都想吐,真不知道Magiclicense把你厉害的
    //等一会就给你其他全家桶推上来 )))))))
    public static YamlConfiguration config;
    public static LyMailAPI MailAPI;
    public static boolean sql;
    public Help help = new ClientHelp();
    public static File configFile;
    private static ShitLyMail  instance;

    @Override
    public void disablePlugin() {
        ShitLyMail  LyMailloser1 = new ShitLyMail ();
        LyMailloser1.help.disablePlugin();
        Bukkit.getPluginManager().disablePlugin((Plugin) LyMailloser1);
    }

    @Override
    public void info(String LyMailloser1) {
        Bukkit.getConsoleSender().sendMessage(LyMailloser1);
    }

    static {
        MailAPI = new LyMailAPIClient();
    }

    public boolean onCommand(CommandSender LyMailloser1, Command LyMailloser, String LyMail, String[] LyMailloser4) {
        if (((String) LyMail).equalsIgnoreCase("lmail")) {
            if (LyMailloser4.length == 0 && LyMailloser1.isOp()) {
                LyMailloser1.sendMessage("§7/" + (String) LyMail + " reload §3重载插件");
                LyMailloser1.sendMessage(new StringBuilder().insert(0, "§7/").append((String) LyMail).append(" send [玩家] [模板id] §3发送指定模板的邮件 (输入{online}为全服在线玩家{offline}为全服所有玩家)").toString());
                LyMailloser1.sendMessage(new StringBuilder().insert(0, "§7/").append((String) LyMail).append(" open §3打开邮箱").toString());
            } else if (LyMailloser4.length == 1 && LyMailloser4[0].equalsIgnoreCase("reload") && LyMailloser1.isOp()) {
                ShitLyMail  LyMailloser15 = new ShitLyMail ();
                LyMailloser15.help.reload();
                LyMailloser1.sendMessage("重载成功");
            } else if (LyMailloser4.length == 1 && LyMailloser4[0].equalsIgnoreCase("open") && LyMailloser1 instanceof Player) {
                LyMailloser = (Command) LyMailloser1;
                Gui.open((Player) LyMailloser, 1);
            } else if (LyMailloser4.length == 3 && LyMailloser4[0].equalsIgnoreCase("send") && LyMailloser1.isOp()) {
                if (!MailAPI.getMailManager().hasMailTemplate(LyMailloser4[2])) {
                    LyMailloser1.sendMessage("模板不存在!");
                    return true;
                }
                if (LyMailloser4[1].equalsIgnoreCase("{online}")) {
                    for (Object OMG : Bukkit.getOnlinePlayers()) {
                        MailTemplate mailTemplate = MailAPI.getMailManager().getMailTemplate(LyMailloser4[2]);
                        mailTemplate.sendMail((OfflinePlayer) OMG, mailTemplate.getMailData((OfflinePlayer) OMG));
                    }
                } else if (LyMailloser4[1].equalsIgnoreCase("{offline}")) {
                    for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                        MailTemplate mailTemplate = MailAPI.getMailManager().getMailTemplate(LyMailloser4[2]);
                        mailTemplate.sendMail(offlinePlayer, mailTemplate.getMailData(offlinePlayer));
                    }
                } else {
                    LyMailloser = (Command) Bukkit.getOfflinePlayer((String) LyMailloser4[1]);
                    if (LyMailloser == null) {
                        LyMailloser1.sendMessage("\u73a9\u5bb6\u4e0d\u5b58\u5728!");
                        return true;
                    }
                }
            }
        }
        return true;
    }


    @Override
    public boolean isPrimaryThread() {
        return Bukkit.isPrimaryThread();
    }

    public void ShitLyMail () {
        ShitLyMail  LyMailloser1;
    }

    public void onEnable() {
        ShitLyMail  LyMailloser1 = new ShitLyMail ();
        instance = LyMailloser1;
        LyMailloser1.saveDefaultConfig();
        LyMailloser1.reloadConfig();
        configFile = new File(new StringBuilder().insert(0, "plugins/").append(LyMailloser1.getName()).append("/config.yml").toString());
        config = YamlConfiguration.loadConfiguration((File)configFile);
        Bukkit.getPluginManager().registerEvents((Listener)new JoinListener(), (Plugin)ShitLyMail .getInstance());
    }

    public static ShitLyMail  getInstance() {
        return instance;
    }
}


