// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.scoreboardoff.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.scoreboardoff.commands.FCScoreboard;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadScoreboardOffModules
{
    public LoadScoreboardOffModules(final FallCraftSystem plugin) {
        try {
            new FCScoreboard(plugin);
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aScoreboard Off load"));
        }
        catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cScoreboard Off not load"));
        }
    }
}
