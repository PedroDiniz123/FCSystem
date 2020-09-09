// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coinshop.utils;

import fallcraftsystem.modules.chest.utils.ToBase64;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Product
{
    private double price;
    private ItemStack itemStack;
    private String nome;
    private List<String> desc;
    private Map<Enchantment, Integer> enchantments;
    
    public Product(final double price, final ItemStack itemStack, final boolean buy) {
        this.price = price;
        this.itemStack = itemStack;

        nome = itemStack.getItemMeta().getDisplayName();
        if (itemStack.getEnchantments() != null) {
            enchantments = itemStack.getEnchantments();
        }

        if (itemStack.getItemMeta().hasLore()) {
            desc = itemStack.getItemMeta().getLore();
        } else {
            desc = Collections.emptyList();
        }

        desc.add("");
        desc.add("§a¢" + String.valueOf(price));

        final Item item = new Item(itemStack);
        item.setItemMeta(itemStack.getItemMeta());
        item.setDesc(desc);

        if (buy) {
            ItemStack[] contents = null;
            if (CoinShopItems.getCoinShopItemsFile().contains("items.buy")) {
                try {
                    contents = ToBase64.itemStackArrayFromBase64(CoinShopItems.getCoinShopItemsFile().getString("items.buy"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                CoinShopItems.getCoinShopItemsFile().createSection("items.buy");
            }
            final Inventory emptyInv = Bukkit.createInventory((InventoryHolder)null, 27, "Empty Inventory");
            if (contents != null) {
                emptyInv.setContents(contents);
            }
            emptyInv.addItem(itemStack);
            CoinShopItems.getCoinShopItemsFile().set("items.buy", (Object)ToBase64.itemStackArrayToBase64(emptyInv.getContents()));
            CoinShopItems.save();
            return;
        }
        ItemStack[] contents = null;
        if (CoinShopItems.getCoinShopItemsFile().contains("items.sell")) {
            try {
                contents = ToBase64.itemStackArrayFromBase64(CoinShopItems.getCoinShopItemsFile().getString("items.sell"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            CoinShopItems.getCoinShopItemsFile().createSection("items.sell");
        }
        final Inventory emptyInv = Bukkit.createInventory((InventoryHolder)null, 27, "Empty Inventory");
        if (contents != null) {
            emptyInv.setContents(contents);
        }
        emptyInv.addItem(itemStack);
        CoinShopItems.getCoinShopItemsFile().set("items.sell", (Object)ToBase64.itemStackArrayToBase64(emptyInv.getContents()));
        CoinShopItems.save();
    }
}
