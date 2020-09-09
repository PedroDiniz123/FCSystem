// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.kits.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.utils.KitConfig;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteKit implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public DeleteKit(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("delkit").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lEsse kit nao existe!"));
            return true;
        }
        KitConfig.getKitFIle().set("kit." + args[0].toLowerCase(), (Object)null);
        KitConfig.save();
        sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lKit deletado!"));
        return true;
    }
}
