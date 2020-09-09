// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.spawn.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class SpawnFile
{
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;
    
    public SpawnFile(final FallCraftSystem main) {
        SpawnFile.plugin = main;
    }
    
    public static void setupSpawnFile(final FallCraftSystem main) {
        SpawnFile.plugin = main;
        if (!SpawnFile.plugin.getDataFolder().exists()) {
            SpawnFile.plugin.getDataFolder().mkdir();
        }
        SpawnFile.file = new File(SpawnFile.plugin.getDataFolder(), "spawn.yml");
        if (!SpawnFile.file.exists()) {
            try {
                SpawnFile.plugin.saveResource("spawn.yml", false);
            }
            catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("\ufffdcN\ufffdo foi poss\ufffdvel criar o arquivo spawn.yml!");
                localException1.printStackTrace();
            }
        }
        SpawnFile.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(SpawnFile.file);
    }
    
    public static void reload() {
        SpawnFile.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(SpawnFile.file);
    }
    
    public static FileConfiguration getSpawnFile() {
        return SpawnFile.userfile;
    }
    
    public static void save() {
        try {
            SpawnFile.userfile.save(SpawnFile.file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
