// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coinshop.utils;

import fallcraftsystem.modules.chest.utils.ToBase64;
import fallcraftsystem.modules.coin.database.CoinData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Utilities
{
    public static Inventory createShop(final Player player) {
        final Inventory coinshop = Bukkit.createInventory((InventoryHolder)null, 27, "§fFall§bCraft §6Coin Shop");
        coinshop.setItem(10, getBuyItem().getItemStack());
        coinshop.setItem(16, getSellItem().getItemStack());
        coinshop.setItem(4, getTutorialItem().getItemStack());
        return setCoinItem(player, coinshop);
    }
    
    public static Item getBuyItem() {
        final List<String> desc = new ArrayList<String>(Collections.emptyList());
        desc.add("§7Clique para ver itens a venda");
        return new Item("§b§lComprar", desc, Material.DIAMOND, (short)0);
    }
    
    public static Item getSellItem() {
        final List<String> desc = new ArrayList<String>(Collections.emptyList());
        desc.add("§7Clique para ver itens que");
        desc.add("§7voc\u00ea pode vender");
        return new Item("§c§lVender", desc, Material.EMERALD, (short)0);
    }
    
    public static Item getTutorialItem() {
        final List<String> desc = new ArrayList<String>(Collections.emptyList());
        desc.add("§7Bot\u00e3o Esquerdo: §eCompra 1 / Vende 1");
        desc.add("§7Bot\u00e3o Direito: §eCompra pack / Vende 1");
        return new Item("§9§lAjuda", desc, Material.PAPER, (short)0);
    }
    
    public static Item getCoinItem(final double coins) {
        final List<String> desc = new ArrayList<String>(Collections.emptyList());
        desc.add(String.valueOf("§a¢" + coins));
        return new Item("§a§lSuas Coins:", desc, Material.GOLD_INGOT, (short)0);
    }
    
    public static Item getAlreadyItem(final String comprarVender) {
        final List<String> desc = new ArrayList<String>(Collections.emptyList());
        desc.add(String.valueOf("§7Voc\u00ea j\u00e1 est\u00e1 aqui."));
        return new Item("§7§l" + comprarVender, desc, Material.IRON_INGOT, (short)0);
    }
    
    public static Inventory createBuyShop(final Player player) {
        final Inventory coinshop = Bukkit.createInventory((InventoryHolder)null, 27, "§fFall§bCraft §aComprar");
        if (CoinShopItems.getCoinShopItemsFile().contains("items.buy")) {
            try {
                final ItemStack[] contents = ToBase64.itemStackArrayFromBase64(CoinShopItems.getCoinShopItemsFile().getString("items.buy"));
                coinshop.setContents(contents);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            CoinShopItems.getCoinShopItemsFile().createSection("items.buy");
            CoinShopItems.getCoinShopItemsFile().set("items.buy", (Object)ToBase64.itemStackArrayToBase64(coinshop.getContents()));
        }
        coinshop.setItem(26, getSellItem().getItemStack());
        coinshop.setItem(18, getAlreadyItem("Comprar").getItemStack());
        return setCoinItem(player, coinshop);
    }
    
    public static Inventory createSellShop(final Player player) {
        final Inventory coinshop = Bukkit.createInventory((InventoryHolder)null, 27, "§fFall§bCraft §cVender");
        if (CoinShopItems.getCoinShopItemsFile().contains("items.sell")) {
            try {
                final ItemStack[] contents = ToBase64.itemStackArrayFromBase64(CoinShopItems.getCoinShopItemsFile().getString("items.sell"));
                coinshop.setContents(contents);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            CoinShopItems.getCoinShopItemsFile().createSection("items.sell");
            CoinShopItems.getCoinShopItemsFile().set("items.sell", (Object)ToBase64.itemStackArrayToBase64(coinshop.getContents()));
            CoinShopItems.save();
        }
        coinshop.setItem(18, getBuyItem().getItemStack());
        coinshop.setItem(26, getAlreadyItem("Vender").getItemStack());
        return setCoinItem(player, coinshop);
    }
    
    public static void removeItem(int index, final boolean buy, final Player player) {
        Inventory shop = buy ? createBuyShop(player) : createSellShop(player);
        --index;
        if (shop.getItem(index) != null) {
            boolean erase = true;
            for (int j = 0; j <= 26; j++) {
                if (j >= 18) {
                    shop.setItem(j, new ItemStack(Material.AIR));
                }

            }
            for (int i = 0; i < shop.getContents().length; ++i) {
                if (shop.getItem(index + 1) == null && erase && shop.getItem(index) != null) {
                    final ItemStack item = shop.getItem(index);
                    item.setType(Material.AIR);
                    shop.setItem(index, item);
                    break;
                }
                if (i > index) {
                    final ItemStack itemStack = shop.getItem(i);
                    shop.setItem(i - 1, itemStack);
                    erase = false;
                    if (shop.getItem(index).getType().equals(Material.AIR)) {
                        break;
                    }
                    if (shop.getItem(index) == null) {
                        break;
                    }
                }
            }
            final String buysell = buy ? "buy" : "sell";
            CoinShopItems.getCoinShopItemsFile().set("items." + buysell, (Object)ToBase64.itemStackArrayToBase64(shop.getContents()));
            CoinShopItems.save();
        }
    }
    
    public static double charArrayToDouble(final char[] charArray) {
        String string = "";
        for (final char charactere : charArray) {
            if (Character.isDigit(charactere) || String.valueOf(charactere).equalsIgnoreCase(".")) {
                string += charactere;
            }
        }
        return Double.parseDouble(string);
    }
    
    public static ItemStack getActualItem(final ItemStack itemStack, final int quantidade) {
        final Material type = itemStack.getType();
        final short id = itemStack.getDurability();
        final ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> desc = itemMeta.getLore();
        desc.remove(desc.size()-1);
        desc.remove(desc.size()-1);

        itemMeta.setLore(desc);

        final ItemStack actualItem = new ItemStack(type, quantidade, id);
        actualItem.setItemMeta(itemMeta);
        return actualItem;
    }
    
    public static boolean isValid(final ItemStack[] contents, final ItemStack clickedItem, final int quantidadeClicada) {
        final Material type = clickedItem.getType();
        int quantidade = 0;
        for (final ItemStack itemStack : contents) {
            if (itemStack != null && itemStack.getType().equals((Object)type) && itemStack.getDurability() == clickedItem.getDurability() && itemStack.getEnchantments().equals(clickedItem.getEnchantments())) {
                quantidade += itemStack.getAmount();
            }
        }
        return quantidade >= quantidadeClicada;
    }
    
    public static void removeSoldItems(final Player player, final ItemStack[] contents, final ItemStack clickedItem) {
        Material type = clickedItem.getType();
        int length = contents.length;
        int i = 0;
        while (i < length) {
            ItemStack itemStack = contents[i];
            if (itemStack != null
                    && itemStack.getType().equals(type)
                    && itemStack.getDurability() == clickedItem.getDurability()
                    && itemStack.getEnchantments().equals(clickedItem.getEnchantments())) {
                if (itemStack.getAmount() != 1) {
                    itemStack.setAmount(itemStack.getAmount() - 1);
                    break;
                }
                if (itemStack.getAmount() == 1) {
                    player.getInventory().removeItem(itemStack);
                    break;
                }
            }
            else {
                ++i;
            }
        }
    }
    
    public static Inventory setCoinItem(final Player player, final Inventory coinshop) {
        double coins = 0.0;
        try {
            coins = CoinData.getCoins(player);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        coinshop.setItem(coinshop.getSize() - 5, getCoinItem(coins).getItemStack());
        return coinshop;
    }
}
