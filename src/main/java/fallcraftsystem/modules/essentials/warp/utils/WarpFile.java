// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.warp.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class WarpFile
{
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;
    
    public WarpFile(final FallCraftSystem main) {
        WarpFile.plugin = main;
    }
    
    public static void setupWarpFile(final FallCraftSystem main) {
        WarpFile.plugin = main;
        if (!WarpFile.plugin.getDataFolder().exists()) {
            WarpFile.plugin.getDataFolder().mkdir();
        }
        WarpFile.file = new File(WarpFile.plugin.getDataFolder() + "/warps/", "warps.yml");
        if (!WarpFile.file.exists()) {
            try {
                WarpFile.plugin.saveResource("warps\\warps.yml", false);
            }
            catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("\ufffdcN\ufffdo foi poss\ufffdvel criar o arquivo warps.yml!");
                localException1.printStackTrace();
            }
        }
        WarpFile.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(WarpFile.file);
    }
    
    public static void reload() {
        WarpFile.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(WarpFile.file);
    }
    
    public static FileConfiguration getWarpFile() {
        return WarpFile.userfile;
    }
    
    public static void save() {
        try {
            WarpFile.userfile.save(WarpFile.file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
