// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.spawn;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.spawn.commands.Lobby;
import fallcraftsystem.modules.essentials.spawn.commands.SetLobby;
import fallcraftsystem.modules.essentials.spawn.utils.SpawnFile;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadEssentialSpawnModule
{
    public LoadEssentialSpawnModule(final FallCraftSystem fallCraftSystem) {
        try {
            SpawnFile.setupSpawnFile(fallCraftSystem);
            new Lobby(fallCraftSystem);
            new SetLobby(fallCraftSystem);
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aSpawn load"));
        }
        catch (Exception e) {
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aSpawn not load"));
        }
    }
}
