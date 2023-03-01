package Ly.mail.origin;

/**
 * @author YieldRain
 * @date 2023/3/1 13:44
 */
import java.io.File;
import java.io.InputStream;

public interface PluginProxy {
    public File getDataFolder();

    public void info(String var1);

    public void disablePlugin();

    public InputStream getResource(String var1);

    public boolean isPrimaryThread();
}

