// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.kits.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.commands.CreateKit;
import fallcraftsystem.modules.kits.commands.DeleteKit;
import fallcraftsystem.modules.kits.commands.Kit;
import fallcraftsystem.modules.kits.listeners.ClickAndGetItem;
import fallcraftsystem.modules.kits.listeners.CloseAndCreateKit;
import fallcraftsystem.modules.kits.utils.KitConfig;
import fallcraftsystem.modules.kits.utils.KitDbConfig;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadKitModules
{
    public LoadKitModules(final FallCraftSystem pl) {
        try {
            KitConfig.setupKitFile(pl);
            KitDbConfig.setupDBKit(pl);
            new Kit(pl);
            new CreateKit(pl);
            new DeleteKit(pl);
            new CloseAndCreateKit(pl);
            new ClickAndGetItem(pl);
            pl.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aKits load"));
        }
        catch (Exception e) {
            pl.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cKits not load"));
        }
    }
}
