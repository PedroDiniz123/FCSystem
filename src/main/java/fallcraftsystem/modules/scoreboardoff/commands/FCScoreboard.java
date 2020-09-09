// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.scoreboardoff.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.FCScoreboardStatus;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FCScoreboard implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public FCScoreboard(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("fcscoreboard").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (args.length != 0) {
            return false;
        }
        final Player player = (Player)sender;
        final GamePlayer gm = ServerUtils.players.get(player);
        if (gm.getFCScoreboardStatus().equals(FCScoreboardStatus.OFF)) {
            gm.setFcScoreboardStatus(FCScoreboardStatus.ON);
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aFCScoreboard Ligada"));
        }
        else {
            gm.setFcScoreboardStatus(FCScoreboardStatus.OFF);
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cFCScoreboard Desligada"));
        }
        return true;
    }
}
