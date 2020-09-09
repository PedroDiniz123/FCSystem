// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.warp.commands;

import fallcraftsystem.modules.essentials.warp.utils.STATIC;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Warps
{
    public static void openInv(final Player player, final FileConfiguration locationFile) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)player, STATIC.MENU_SIZE, Ultilities.formater("&9&lWARPS"));
        if (!locationFile.contains("warps")) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lNot warp setted"));
            return;
        }
        final ConfigurationSection sec = locationFile.getConfigurationSection("warps");
        final ArrayList<ItemStack> itensList = new ArrayList<ItemStack>();
        for (final String key : sec.getKeys(false)) {
            if (player.hasPermission(locationFile.getString("warps." + key + ".PERMISSION")) || player.hasPermission("fc.warp_all")) {
                final ItemStack item = new ItemStack(Material.getMaterial(locationFile.getString("warps." + key + ".ICON")));
                item.setDurability((short)locationFile.getInt("warps." + key + ".TYPE"));
                new ItemStack(Material.MONSTER_EGG, 1, (short)4);
                if (locationFile.getBoolean("warps." + key + ".EFFECT")) {
                    item.addEnchantment(Enchantment.DURABILITY, 1);
                }
                final ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(Ultilities.formater(locationFile.getString("warps." + key + ".TITLE")));
                item.setItemMeta(itemMeta);
                inventory.setItem(locationFile.getInt("warps." + key + ".POS"), item);
            }
        }
        player.openInventory(inventory);
    }
}
