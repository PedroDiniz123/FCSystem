// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.warp.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.PlayerTeleport;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor
{
    public FallCraftSystem plugin;
    private FileConfiguration locationFile;
    
    public Warp(final FallCraftSystem plugin) {
        this.locationFile = WarpFile.getWarpFile();
        this.plugin = plugin;
        plugin.getCommand("warp").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commands for only players!");
            return true;
        }
        final Player player = (Player)sender;
        if (args.length == 0) {
            Warps.openInv(player, this.locationFile);
            return true;
        }
        args[0] = args[0].toUpperCase();
        if (!this.locationFile.contains("warps." + args[0])) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lThis warp not exists!"));
            return true;
        }
        final Double X = Double.parseDouble(this.locationFile.getString("warps." + args[0] + ".X"));
        final Double Y = Double.parseDouble(this.locationFile.getString("warps." + args[0] + ".Y"));
        final Double Z = Double.parseDouble(this.locationFile.getString("warps." + args[0] + ".Z"));
        final String world = this.locationFile.getString("warps." + args[0] + ".WOLRD");
        final String permission = this.locationFile.getString("warps." + args[0] + ".PERMISSION");
        if (!player.hasPermission(permission)) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lYou don`t have permission for this warp!"));
            return true;
        }
        final Location loc = new Location(Bukkit.getWorld(world), (double)X, (double)Y, (double)Z);
        ServerUtils.teleportMap.put(player, new PlayerTeleport(3, player.getLocation(), loc));
        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Teleportado aguarde! &c(NAO SE MEXA)"));
        return true;
    }
}
