// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.commands.player;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Discord implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public Discord(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("discord").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }
        sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Nosso discord: &9https://discord.gg/Rh63Fjz"));
        return true;
    }
}
