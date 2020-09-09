// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.chest.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.chest.command.ChestCommand;
import fallcraftsystem.modules.chest.command.EChest;
import fallcraftsystem.modules.chest.events.InventoryClick;
import fallcraftsystem.modules.chest.events.InventoryClose;
import fallcraftsystem.modules.chest.utils.ChestConfig;
import fallcraftsystem.modules.chest.utils.ChestsList;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadChestModules
{
    public LoadChestModules(final FallCraftSystem plugin) {
        try {
            ChestsList.setupChestList(plugin);
            ChestConfig.setupChestConfig(plugin);
            new InventoryClose(plugin);
            new InventoryClick(plugin);
            new ChestCommand(plugin);
            new EChest(plugin);
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aChest load"));
        }
        catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cChest not load"));
        }
    }
}
