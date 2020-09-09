// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.scoreboard.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.scoreboard.listener.GameScoreboard;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadScoreboard
{
    public LoadScoreboard(final FallCraftSystem plugin) {
        try {
            new GameScoreboard(plugin);
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aScoreboard load"));
        }
        catch (Exception e) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cScoreboard not load"));
        }
    }
}
