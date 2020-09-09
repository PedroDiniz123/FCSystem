// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coinshop.events;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.coin.database.CoinData;
import fallcraftsystem.modules.coinshop.utils.Utilities;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.sql.SQLException;
import java.util.List;

public class InventoryClick implements Listener
{
    public FallCraftSystem plugin;
    Player player;
    
    public InventoryClick(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void inventoryClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {
            final ClickType clickType = event.getClick();
            final Inventory clicked = event.getClickedInventory();
            this.player = (Player) event.getWhoClicked();
            final ItemStack clickedItem = event.getCurrentItem();
            if (clicked.getName().equalsIgnoreCase(Utilities.createShop(this.player).getName())) {
                if (!event.getWhoClicked().getItemOnCursor().getType().equals((Object)Material.AIR)) {
                    event.setCancelled(true);
                    return;
                }
                event.setCancelled(true);
                if (clickedItem.equals((Object)Utilities.getBuyItem().getItemStack())) {
                    this.player.closeInventory();
                    this.player.openInventory(Utilities.createBuyShop(this.player));
                }
                if (clickedItem.equals((Object)Utilities.getSellItem().getItemStack())) {
                    this.player.closeInventory();
                    this.player.openInventory(Utilities.createSellShop(this.player));
                }
            }
            else {
                int quantidade;
                if (clickType.isLeftClick()) {
                    quantidade = 1;
                }
                else {
                    if (!clickType.isRightClick()) {
                        return;
                    }
                    quantidade = clickedItem.getMaxStackSize();
                }
                double price = 0.0;
                if (clicked.getName().equalsIgnoreCase(Utilities.createBuyShop(this.player).getName()) || clicked.getName().equalsIgnoreCase(Utilities.createSellShop(this.player).getName())) {
                    if (clickedItem.getType().equals(Material.AIR)) {
                        return;
                    }

                    List<String> desc = clickedItem.getItemMeta().getLore();

                    String priceStrg = desc.get(desc.size() - 1);
                    char[] priceArray = priceStrg.toCharArray();
                    price = Utilities.charArrayToDouble(priceArray) * quantidade;
                }
                if (clicked.getName().equalsIgnoreCase(Utilities.createBuyShop(this.player).getName())) {
                    event.setCancelled(true);
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(Utilities.getSellItem().getName())) {
                        player.closeInventory();
                        this.player.openInventory(Utilities.createSellShop(this.player));
                        return;
                    }
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(Utilities.getAlreadyItem("Comprar").getName())) {
                        return;
                    }
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(Utilities.getCoinItem(0.0).getName())) {
                        return;
                    }
                    try {
                        double balance = CoinData.getCoins(this.player);
                        if (price > balance) {
                            this.player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cCoins insuficientes"));
                            return;
                        }
                        CoinData.takeCoins(this.player, price);
                        final ItemStack actualItem = Utilities.getActualItem(clickedItem, quantidade);
                        this.player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§aRemovidos ¢" + price + "§a da sua conta."));
                        this.player.getInventory().addItem(actualItem);
                    }
                    catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    this.player.openInventory(Utilities.createBuyShop(this.player));
                }
                else if (clicked.getName().equalsIgnoreCase(Utilities.createSellShop(this.player).getName())) {
                    event.setCancelled(true);
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(Utilities.getBuyItem().getName())) {
                        player.closeInventory();
                        this.player.openInventory(Utilities.createBuyShop(this.player));
                        return;
                    }
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(Utilities.getCoinItem(0.0).getName())) {
                        return;
                    }
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(Utilities.getAlreadyItem("Vender").getName())) {
                        return;
                    }
                    price /= quantidade;
                    quantidade = 1;
                    if (!Utilities.isValid(this.player.getInventory().getContents(), clickedItem, quantidade)) {
                        this.player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cQuantidade insuficiente."));
                        return;
                    }
                    try {
                        Utilities.removeSoldItems(this.player, this.player.getInventory().getContents(), clickedItem);
                        this.player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§aAdicionados ¢" + price + "§a \u00e0 sua conta."));
                        CoinData.addCoins(this.player, price);
                    }
                    catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    this.player.openInventory(Utilities.createSellShop(this.player));
                }
            }
        }
    }
}
