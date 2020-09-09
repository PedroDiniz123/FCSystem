// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.commands.admin;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.VanishStatus;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public Vanish(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("vanish").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }
        final Player player = (Player)sender;
        final GamePlayer gm = ServerUtils.players.get(player);
        if (gm.getVanishStatus().equals(VanishStatus.VISIBLE)) {
            for (final Player playerTarget : this.plugin.getServer().getOnlinePlayers()) {
                if (!playerTarget.hasPermission("fallcraft.module.essentials.v")) {
                    playerTarget.hidePlayer(player);
                }
            }
            player.setPlayerListName(Ultilities.formater("&4" + player.getName()));
            Bukkit.broadcastMessage(Ultilities.formater("&c-&f &8" + player.getPlayer().getName()));
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce est\u00e1 invis\u00edvel"));
            ServerUtils.vanishList.add(player);
            ServerUtils.players.get(player).setVanishStatus(VanishStatus.INVISIBLE);
        }
        else {
            for (final Player playerTarget : this.plugin.getServer().getOnlinePlayers()) {
                playerTarget.showPlayer(player);
            }
            player.setPlayerListName(Ultilities.formater("&f" + player.getName()));
            ServerUtils.vanishList.remove(player);
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aVoce est\u00e1 vis\u00edvel"));
            Bukkit.broadcastMessage(Ultilities.formater("&a+&f &8" + player.getPlayer().getName()));
            ServerUtils.players.get(player).setVanishStatus(VanishStatus.VISIBLE);
        }
        return true;
    }
}
