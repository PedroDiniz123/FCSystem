// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.kits.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class KitDbConfig
{
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;
    
    public KitDbConfig(final FallCraftSystem main) {
        KitDbConfig.plugin = main;
    }
    
    public static void setupDBKit(final FallCraftSystem main) {
        KitDbConfig.plugin = main;
        if (!KitDbConfig.plugin.getDataFolder().exists()) {
            KitDbConfig.plugin.getDataFolder().mkdir();
        }
        KitDbConfig.file = new File(KitDbConfig.plugin.getDataFolder(), "kit-db.yml");
        if (!KitDbConfig.file.exists()) {
            try {
                KitDbConfig.plugin.saveResource("kit-db.yml", false);
            }
            catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("\ufffdcN\ufffdo foi poss\ufffdvel criar o arquivo kit-db.yml!");
                localException1.printStackTrace();
            }
        }
        KitDbConfig.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(KitDbConfig.file);
    }
    
    public static void reload() {
        KitDbConfig.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(KitDbConfig.file);
    }
    
    public static FileConfiguration getDBKItFile() {
        return KitDbConfig.userfile;
    }
    
    public static void save() {
        try {
            KitDbConfig.userfile.save(KitDbConfig.file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
