// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.warp;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.warp.commands.*;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class LoadEssentialWarpModule
{
    public LoadEssentialWarpModule(final FallCraftSystem fallCraftSystem) {
        try {
            WarpFile.setupWarpFile(fallCraftSystem);
            new DelWarp(fallCraftSystem);
            new SetWarp(fallCraftSystem);
            new Warp(fallCraftSystem);
            new WarpList(fallCraftSystem);
            fallCraftSystem.getServer().getPluginManager().registerEvents((Listener)new ClickItemTeleport(), (Plugin)fallCraftSystem);
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aWarp load"));
        }
        catch (Exception e) {
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cWarp not load"));
        }
    }
}
