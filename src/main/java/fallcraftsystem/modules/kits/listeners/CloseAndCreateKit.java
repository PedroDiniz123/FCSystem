// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.kits.listeners;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.commands.CreateKit;
import fallcraftsystem.modules.kits.core.KitInv;
import fallcraftsystem.modules.kits.utils.KitConfig;
import fallcraftsystem.utils.SaveInventory;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class CloseAndCreateKit implements Listener
{
    public FallCraftSystem plugin;
    
    public CloseAndCreateKit(final FallCraftSystem pl) {
        this.plugin = pl;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)pl);
    }
    
    @EventHandler
    public void oncloseCreate(final InventoryCloseEvent event) {
        final Player player = (Player)event.getPlayer();
        if (!event.getInventory().getName().contains("KIT")) {
            return;
        }
        final String[] names = event.getInventory().getName().split("\\.");
        final String name = names[1];
        final KitInv kit = CreateKit.listInvsKits.get(name);
        final Inventory inventory = event.getInventory();
        final String invent = SaveInventory.InventoryToString(inventory);
        SaveInventory.save(this.plugin, invent, kit.getKitName());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".name", (Object)kit.getKitName());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".icon", (Object)kit.getIcon());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".type", (Object)kit.getType());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".perm", (Object)kit.getKitPerm());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".effect", (Object)kit.isEnchant());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".pos", (Object)kit.getPos());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".time", (Object)kit.getTime());
        KitConfig.save();
        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aKit &6" + StringUtils.capitalize(kit.getKitName()) + " &acriado com sucesso!"));
    }
}
