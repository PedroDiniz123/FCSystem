// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.chest.events;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.chest.utils.ChestsList;
import fallcraftsystem.modules.chest.utils.ToBase64;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class InventoryClose implements Listener
{
    FallCraftSystem plugin;
    
    public InventoryClose(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onClose(final InventoryCloseEvent event) {
        if (event.getInventory().getName().contains("§fF§bC §5Ender Chest")) {
            final Player player = (Player)event.getInventory().getHolder();
            player.getEnderChest().setContents(event.getInventory().getContents());
            return;
        }
        if (event.getInventory().getName().contains("§fFall§bCraft §9Ba\u00fa")) {
            final Player player = (Player)event.getInventory().getHolder();
            final int size = event.getInventory().getSize();
            boolean empty = true;
            for (int i = 0; i < size; ++i) {
                if (event.getInventory().getItem(i) != null) {
                    empty = false;
                    break;
                }
            }
            ItemStack[] contents = event.getInventory().getContents();
            if (empty) {
                final Inventory inv = Bukkit.createInventory((InventoryHolder)player, 9);
                contents = inv.getContents();
            }
            final String base64 = ToBase64.itemStackArrayToBase64(contents);
            if (!ChestsList.getChestListFile().contains("chests.players." + player.getName() + ".chest_items")) {
                ChestsList.getChestListFile().createSection("chests.players." + player.getName() + ".chest_items");
            }
            ChestsList.getChestListFile().set("chests.players." + player.getName() + ".chest_items", (Object)base64);
            ChestsList.save();
        }
    }
}
