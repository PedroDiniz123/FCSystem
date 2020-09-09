// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.chest.command;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.chest.utils.ChestCreator;
import fallcraftsystem.modules.chest.utils.ChestsList;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ChestCommand implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public ChestCommand(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("chest").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas para players");
            return true;
        }
        Player player = (Player)sender;
        final Player adm = (Player)sender;
        boolean staff = false;
        if (args.length == 1 && player.hasPermission("fc.chest_admin")) {
            if (this.plugin.getServer().getPlayer(args[0]) == null) {
                adm.sendMessage("§cPlayer n\u00e3o encontrado");
                return true;
            }
            player = this.plugin.getServer().getPlayer(args[0]);
            staff = true;
        }
        final ChestCreator chest = new ChestCreator(player);
        if (!chest.createChest()) {
            adm.sendMessage("§cO jogador informado n\u00e3o possui chest");
            return true;
        }
        Inventory chestVirtual = chest.getChest();
        if (ChestsList.getChestListFile().contains("chests.players." + player.getName())) {
            chestVirtual = chest.loadChest();
        }
        else {
            ChestsList.getChestListFile().createSection("chests.players." + player.getName() + ".uuid");
            ChestsList.getChestListFile().set("chests.players." + player.getName() + ".uuid", (Object)String.valueOf(Ultilities.getUUIDFromNick(player.getName())));
        }
        if (staff) {
            adm.openInventory(chestVirtual);
            return true;
        }
        player.openInventory(chestVirtual);
        return true;
    }
}
