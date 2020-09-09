// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.commands.admin;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class Reloadall implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public Reloadall(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("reloadall").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final Player PlayerSender = (Player)sender;
        Bukkit.broadcastMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aReiniciando o servidor em em:"));
        for (int i = 3; i >= 0; --i) {
            try {
                TimeUnit.SECONDS.sleep(1L);
                Bukkit.broadcastMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c" + i));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (final Player pl : Bukkit.getOnlinePlayers()) {
            pl.kickPlayer(Ultilities.formater("&cServer is restarting..."));
        }
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "reload");
        return true;
    }
}
