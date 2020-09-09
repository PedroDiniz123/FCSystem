// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.kits.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.core.KitInv;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public class CreateKit implements CommandExecutor
{
    public static Map<String, KitInv> listInvsKits;
    public FallCraftSystem plugin;
    
    public CreateKit(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("createkit").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }
        if (args.length < 2) {
            return false;
        }
        final Player player = (Player)sender;
        final String kitName = args[0].toLowerCase();
        String kitPerm = "fallcraft.module.kit.default";
        Material item = player.getInventory().getItemInHand().getType();
        final boolean enchant = player.getInventory().getItemInHand().getEnchantments().size() > 0;
        String itemName = player.getInventory().getItemInHand().getType().toString();
        if (item.equals((Object)Material.AIR)) {
            item = Material.GRASS;
            itemName = "GRASS";
        }
        final short type = player.getInventory().getItemInHand().getDurability();
        if (args.length == 4) {
            kitPerm = "fallcraft.module.kit." + args[3].toLowerCase();
        }
        int iconPos = 0;
        int time = 1;
        try {
            iconPos = Integer.parseInt(args[1]);
            time = Integer.parseInt(args[2]);
        }
        catch (Exception e) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lVoce precisa informar a posicao do item!"));
            return true;
        }
        CreateKit.listInvsKits.put(kitName, new KitInv(kitName, kitPerm, enchant, itemName, type, iconPos, time));
        final Inventory inv = Bukkit.createInventory((InventoryHolder)player, 27, Ultilities.formater("KIT." + kitName));
        player.openInventory(inv);
        return true;
    }
    
    static {
        CreateKit.listInvsKits = new HashMap<String, KitInv>();
    }
}
