// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.invsee.events;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class InventoryClose implements Listener
{
    public FallCraftSystem plugin;
    
    public InventoryClose(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void inventoryCloseEvent(final InventoryCloseEvent event) {
        if (event.getInventory().getName().contains("§fF§bC (§fInvent\u00e1rio de §b")) {
            final Inventory inventory = event.getInventory();
            final Player owner = (Player)inventory.getHolder();
            owner.getInventory().setContents(inventory.getContents());
        }
    }
}
