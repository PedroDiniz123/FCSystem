// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.chest.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class EnderChest
{
    public static Inventory createVirtEchest(final Player player, final String name) {
        final Inventory echest = Bukkit.createInventory((InventoryHolder)player, 27, "§fF§bC §5Ender Chest  " + name);
        echest.setContents(player.getEnderChest().getContents());
        return echest;
    }
}
