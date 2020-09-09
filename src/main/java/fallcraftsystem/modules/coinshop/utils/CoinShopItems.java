// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coinshop.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class CoinShopItems
{
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;
    
    public CoinShopItems(final FallCraftSystem main) {
        CoinShopItems.plugin = main;
    }
    
    public static void setupCoinShopItems(final FallCraftSystem main) {
        CoinShopItems.plugin = main;
        if (!CoinShopItems.plugin.getDataFolder().exists()) {
            CoinShopItems.plugin.getDataFolder().mkdir();
        }
        CoinShopItems.file = new File(CoinShopItems.plugin.getDataFolder(), "coin-shop-items.yml");
        if (!CoinShopItems.file.exists()) {
            try {
                CoinShopItems.plugin.saveResource("coin-shop-items.yml", false);
            }
            catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("§cN\u00e3o foi poss\u00edvel criar o arquivo coin-shop-items.yml!");
                localException1.printStackTrace();
            }
        }
        CoinShopItems.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(CoinShopItems.file);
    }
    
    public static void reload() {
        CoinShopItems.userfile = (FileConfiguration)YamlConfiguration.loadConfiguration(CoinShopItems.file);
    }
    
    public static FileConfiguration getCoinShopItemsFile() {
        return CoinShopItems.userfile;
    }
    
    public static void save() {
        try {
            CoinShopItems.userfile.save(CoinShopItems.file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
