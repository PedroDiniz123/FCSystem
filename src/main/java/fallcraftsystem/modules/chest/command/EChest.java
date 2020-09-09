// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.chest.command;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.chest.utils.EnderChest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EChest implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public EChest(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("echest").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players");
            return true;
        }
        Player player = (Player)sender;
        if (args.length == 0) {
            final Inventory echest = EnderChest.createVirtEchest(player, " ");
            player.openInventory(echest);
            return true;
        }
        if (!player.hasPermission("fc.echest_mod")) {
            return false;
        }
        if (this.plugin.getServer().getPlayer(args[0]) != null) {
            final Player staff = player;
            player = this.plugin.getServer().getPlayer(args[0]);
            final Inventory echest = EnderChest.createVirtEchest(player, "§8(" + player.getDisplayName() + ")");
            staff.openInventory(echest);
            return true;
        }
        return false;
    }
}
