// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.chest.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ChestsList
{
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;
    
    public ChestsList(final FallCraftSystem main) {
        ChestsList.plugin = main;
    }
    
    public static void setupChestList(final FallCraftSystem main) {
        ChestsList.plugin = main;
        if (!ChestsList.plugin.getDataFolder().exists()) {
            ChestsList.plugin.getDataFolder().mkdir();
        }
        ChestsList.file = new File(ChestsList.plugin.getDataFolder(), "chestlist.yml");
        if (!ChestsList.file.exists()) {
            try {
                ChestsList.plugin.saveResource("chestlist.yml", false);
            }
            catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("§cN\u00e3o foi poss\u00edvel criar o arquivo chestlist.yml!");
                localException1.printStackTrace();
            }
        }
        ChestsList.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(ChestsList.file);
    }
    
    public static void reload() {
        ChestsList.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(ChestsList.file);
    }
    
    public static FileConfiguration getChestListFile() {
        return ChestsList.userfile;
    }
    
    public static void save() {
        try {
            ChestsList.userfile.save(ChestsList.file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
