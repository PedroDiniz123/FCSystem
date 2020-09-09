// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coin.listener;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.coin.database.CoinData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.sql.SQLException;

public class AddPlayer implements Listener
{
    public FallCraftSystem pl;
    
    public AddPlayer(final FallCraftSystem pl) {
        this.pl = pl;
        pl.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)pl);
    }
    
    @EventHandler
    public void OnJoin(final PlayerJoinEvent event) {
        try {
            CoinData.insert(event.getPlayer());
        }
        catch (SQLException ex) {}
    }
}
