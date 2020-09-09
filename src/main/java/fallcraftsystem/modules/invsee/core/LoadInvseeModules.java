// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.invsee.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.invsee.commands.Invsee;
import fallcraftsystem.modules.invsee.events.InventoryClick;
import fallcraftsystem.modules.invsee.events.InventoryClose;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadInvseeModules
{
    public LoadInvseeModules(final FallCraftSystem plugin) {
        try {
            new Invsee(plugin);
            new InventoryClick(plugin);
            new InventoryClose(plugin);
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aInvsee load"));
        }
        catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cInvsee not load"));
        }
    }
}
