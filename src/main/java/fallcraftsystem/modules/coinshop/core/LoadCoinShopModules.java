// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coinshop.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.coinshop.commands.CoinShop;
import fallcraftsystem.modules.coinshop.events.InventoryClick;
import fallcraftsystem.modules.coinshop.utils.CoinShopItems;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadCoinShopModules
{
    public LoadCoinShopModules(final FallCraftSystem plugin) {
        try {
            CoinShopItems.setupCoinShopItems(plugin);
            new CoinShop(plugin);
            new InventoryClick(plugin);
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aCoinShop load"));
        }
        catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cCoinShop not load"));
        }
    }
}
