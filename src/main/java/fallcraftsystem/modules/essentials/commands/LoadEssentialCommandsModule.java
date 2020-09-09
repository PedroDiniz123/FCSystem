// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.commands.admin.*;
import fallcraftsystem.modules.essentials.commands.player.Discord;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadEssentialCommandsModule
{
    public LoadEssentialCommandsModule(final FallCraftSystem fallCraftSystem) {
        try {
            new Reloadall(fallCraftSystem);
            new Discord(fallCraftSystem);
            new Fly(fallCraftSystem);
            new Vanish(fallCraftSystem);
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aCommands load"));
        }
        catch (Exception e) {
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aCommands not load"));
        }
    }
}
