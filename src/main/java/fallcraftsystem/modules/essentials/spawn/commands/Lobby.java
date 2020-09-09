// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.spawn.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.PlayerTeleport;
import fallcraftsystem.modules.essentials.spawn.utils.SpawnFile;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lobby implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public Lobby(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("lobby").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("For only players");
            return false;
        }
        Player p = (Player)sender;
        if (args.length == 1) {
            if (!p.hasPermission("fallcraft.module.essentials.spawn_admin")) {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cUse /spawn."));
                return true;
            }
            if (this.plugin.getServer().getPlayer(args[0]) == null) {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player n\u00e3o est\u00e1 online!"));
                return true;
            }
            p = this.plugin.getServer().getPlayer(args[0]);
        }
        final double X = SpawnFile.getSpawnFile().getDouble("LOCALTION.X");
        final double Y = SpawnFile.getSpawnFile().getDouble("LOCALTION.Y");
        final double Z = SpawnFile.getSpawnFile().getDouble("LOCALTION.Z");
        final String world = SpawnFile.getSpawnFile().getString("LOCALTION.WORLD");
        final Location l = new Location(this.plugin.getServer().getWorld(world), X, Y, Z);
        p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Teleportado aguarde! &c(NAO SE MEXA)"));
        if (args.length == 1 && sender.hasPermission("fallcraft.teleport.bypass")) {
            ServerUtils.teleportMap.put(p, new PlayerTeleport(0, p.getLocation(), l));
            return true;
        }
        ServerUtils.teleportMap.put(p, new PlayerTeleport(3, p.getLocation(), l));
        return true;
    }
}
