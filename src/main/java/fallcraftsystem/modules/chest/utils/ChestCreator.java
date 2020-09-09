// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.chest.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class ChestCreator
{
    public Player player;
    public Inventory chest;
    
    public ChestCreator(final Player player) {
        this.player = player;
        this.createChest();
    }
    
    public boolean createChest() {
        int size = 0;
        String name = "          Sem Rank";
        if (this.player.hasPermission("fc.chest_safira")) {
            size = ChestConfig.getChestFile().getInt("config.tamanhos.safira");
            name = "§d§l           Safira";
            this.chest = Bukkit.createInventory((InventoryHolder)this.player, 9 * size, "§fFall§bCraft §9Ba\u00fa" + name);
            return true;
        }
        if (this.player.hasPermission("fc.chest_dima")) {
            size = ChestConfig.getChestFile().getInt("config.tamanhos.dima");
            name = "§b§l             Dima";
            this.chest = Bukkit.createInventory((InventoryHolder)this.player, 9 * size, "§fFall§bCraft §9Ba\u00fa" + name);
            return true;
        }
        if (this.player.hasPermission("fc.chest_ouro")) {
            size = ChestConfig.getChestFile().getInt("config.tamanhos.ouro");
            name = "§6§l             Ouro";
            this.chest = Bukkit.createInventory((InventoryHolder)this.player, 9 * size, "§fFall§bCraft §9Ba\u00fa" + name);
            return true;
        }
        return false;
    }
    
    public Inventory getChest() {
        return this.chest;
    }
    
    public Inventory loadChest() {
        try {
            final String contentsStrg = ChestsList.getChestListFile().getString("chests.players." + this.player.getName() + ".chest_items");
            final ItemStack[] contents = ToBase64.itemStackArrayFromBase64(contentsStrg);
            this.chest.setContents(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return this.chest;
    }
}
