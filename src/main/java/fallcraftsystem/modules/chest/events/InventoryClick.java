// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.chest.events;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class InventoryClick implements Listener
{
    public FallCraftSystem plugin;
    
    public InventoryClick(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void EchestClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {
            final Inventory inventory = event.getClickedInventory();
            final Player clicker = (Player)event.getWhoClicked();
            if (inventory.getName().contains("§fF§bC §5Ender Chest  ") && clicker.hasPermission("fc.echest_mod") && !clicker.hasPermission("fc.echest_admin")) {
                final Player echestOwner = (Player)inventory.getHolder();
                if (echestOwner != clicker) {
                    event.setCancelled(true);
                }
            }
            if (inventory.getName().contains("§fFall§bCraft §9Ba\u00fa") && clicker.hasPermission("fc.echest_mod") && !clicker.hasPermission("fc.echest_admin")) {
                final Player chestOwner = (Player)inventory.getHolder();
                if (chestOwner != clicker) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
