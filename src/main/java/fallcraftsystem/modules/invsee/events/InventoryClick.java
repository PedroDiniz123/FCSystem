// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.invsee.events;

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
    public void inventoryClickEvent(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        final Inventory inventory = event.getInventory();
        if (inventory.getName().contains("§fF§bC (§fInvent\u00e1rio de §b")) {
            if (event.getClick().isShiftClick()) {
                event.setCancelled(true);
                return;
            }
            final Player admin = (Player)event.getWhoClicked();
            final Player owner = (Player)inventory.getHolder();
            if (!admin.hasPermission("fc.invsee_admin")) {
                event.setCancelled(true);
            }
            owner.getInventory().setContents(inventory.getContents());
        }
    }
}
