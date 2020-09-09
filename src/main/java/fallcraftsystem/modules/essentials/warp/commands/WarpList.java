// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.warp.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.warp.utils.STATIC;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class WarpList implements CommandExecutor
{
    private FileConfiguration locationFile;
    public FallCraftSystem plugin;
    
    public WarpList(final FallCraftSystem plugin) {
        this.locationFile = WarpFile.getWarpFile();
        this.plugin = plugin;
        plugin.getCommand("warplist").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commands for only players!");
            return true;
        }
        final Player player = (Player)sender;
        final StringBuilder list = new StringBuilder();
        final ConfigurationSection sec = this.locationFile.getConfigurationSection("warps");
        for (final String key : sec.getKeys(false)) {
            if (player.hasPermission(this.locationFile.getString("warps." + key + ".PERMISSION"))) {
                list.append("&f&l>> &6&l" + key + "\n");
            }
        }
        sender.sendMessage(Ultilities.formater(String.valueOf(STATIC.PREFIX) + "&5&lList from warps"));
        sender.sendMessage(Ultilities.formater(list.toString()));
        return true;
    }
}
