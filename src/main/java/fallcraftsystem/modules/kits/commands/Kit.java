// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.kits.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.utils.KitConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kit implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public Kit(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("kit").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }
        final Player player = (Player)sender;
        try {
            KitMenu.openInv(player, KitConfig.getKitFIle());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
