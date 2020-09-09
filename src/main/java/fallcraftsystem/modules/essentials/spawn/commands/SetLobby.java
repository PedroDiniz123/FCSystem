// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.spawn.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.spawn.utils.SpawnFile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobby implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public SetLobby(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("setlobby").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("For only players");
            return false;
        }
        final Player p = (Player)sender;
        final Location location = p.getLocation();
        SpawnFile.getSpawnFile().set("LOCALTION.X", (Object)location.getX());
        SpawnFile.getSpawnFile().set("LOCALTION.Y", (Object)location.getY());
        SpawnFile.getSpawnFile().set("LOCALTION.Z", (Object)location.getZ());
        SpawnFile.getSpawnFile().set("LOCALTION.WORLD", (Object)location.getWorld().getName());
        SpawnFile.save();
        sender.sendMessage("Lobby salvo");
        return true;
    }
}
