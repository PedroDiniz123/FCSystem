// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.invsee.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class Invsee implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public Invsee(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("invsee").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cUse /invsee <jogador>."));
            return true;
        }
        if (this.plugin.getServer().getPlayer(args[0]) == null) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player n\u00e3o est\u00e1 online!"));
            return true;
        }
        final Player admin = (Player)sender;
        final Player player = this.plugin.getServer().getPlayer(args[0]);
        final Inventory inv = this.createInv(player);
        admin.openInventory(inv);
        return true;
    }
    
    public Inventory createInv(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)player, 36, "§fF§bC (§fInvent\u00e1rio de §b" + player.getDisplayName() + ")");
        inventory.setContents(player.getInventory().getContents());
        return inventory;
    }
}
