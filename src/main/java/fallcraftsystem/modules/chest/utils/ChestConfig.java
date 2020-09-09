// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.chest.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ChestConfig
{
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;
    
    public ChestConfig(final FallCraftSystem main) {
        ChestConfig.plugin = main;
    }
    
    public static void setupChestConfig(final FallCraftSystem main) {
        ChestConfig.plugin = main;
        if (!ChestConfig.plugin.getDataFolder().exists()) {
            ChestConfig.plugin.getDataFolder().mkdir();
        }
        ChestConfig.file = new File(ChestConfig.plugin.getDataFolder(), "chests-config.yml");
        if (!ChestConfig.file.exists()) {
            try {
                ChestConfig.plugin.saveResource("chests-config.yml", false);
            }
            catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("§cN\u00e3o foi poss\u00edvel criar o arquivo chests-config.yml!");
                localException1.printStackTrace();
            }
        }
        ChestConfig.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(ChestConfig.file);
    }
    
    public static void reload() {
        ChestConfig.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(ChestConfig.file);
    }
    
    public static FileConfiguration getChestFile() {
        return ChestConfig.userfile;
    }
    
    public static void save() {
        try {
            ChestConfig.userfile.save(ChestConfig.file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
