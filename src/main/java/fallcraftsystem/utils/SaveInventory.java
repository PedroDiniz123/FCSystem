// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.utils;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.Map;

public class SaveInventory
{
    public static void save(final FallCraftSystem plugin, final String a, final Player player) {
        try {
            final File fl = new File(WarpFile.plugin.getDataFolder() + "/inventorys/");
            if (!fl.exists()) {
                fl.mkdir();
            }
            final FileOutputStream f = new FileOutputStream(plugin.getDataFolder() + "/inventorys/" + player.getUniqueId() + ".txt");
            final ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(a);
            o.close();
            f.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static String recovery(final FallCraftSystem plugin, final Player player) {
        String pr1 = null;
        try {
            final FileInputStream fi = new FileInputStream(plugin.getDataFolder() + "/inventorys/" + player.getUniqueId() + ".txt");
            final ObjectInputStream oi = new ObjectInputStream(fi);
            pr1 = (String)oi.readObject();
            oi.close();
            fi.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return pr1;
    }
    
    public static void save(final FallCraftSystem plugin, final String a, final String title) {
        try {
            final File fl = new File(WarpFile.plugin.getDataFolder() + "/kits/");
            if (!fl.exists()) {
                fl.mkdir();
            }
            final FileOutputStream f = new FileOutputStream(plugin.getDataFolder() + "/kits/" + title + ".txt");
            final ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(a);
            o.close();
            f.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static String recovery(final FallCraftSystem plugin, final String title) {
        String pr1 = null;
        try {
            final FileInputStream fi = new FileInputStream(plugin.getDataFolder() + "/kits/" + title + ".txt");
            final ObjectInputStream oi = new ObjectInputStream(fi);
            pr1 = (String)oi.readObject();
            oi.close();
            fi.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return pr1;
    }
    
    public static String InventoryToString(final Inventory invInventory) {
        String serialization = invInventory.getSize() + ";";
        for (int i = 0; i < invInventory.getSize(); ++i) {
            final ItemStack is = invInventory.getItem(i);
            if (is != null) {
                String serializedItemStack = "";
                final String isType = String.valueOf(is.getType().getId());
                serializedItemStack = serializedItemStack + "t@" + isType;
                if (is.getDurability() != 0) {
                    final String isDurability = String.valueOf(is.getDurability());
                    serializedItemStack = serializedItemStack + ":d@" + isDurability;
                }
                if (is.getAmount() != 1) {
                    final String isAmount = String.valueOf(is.getAmount());
                    serializedItemStack = serializedItemStack + ":a@" + isAmount;
                }
                final Map<Enchantment, Integer> isEnch = (Map<Enchantment, Integer>)is.getEnchantments();
                if (isEnch.size() > 0) {
                    for (final Map.Entry<Enchantment, Integer> ench : isEnch.entrySet()) {
                        serializedItemStack = serializedItemStack + ":e@" + ench.getKey().getId() + "@" + ench.getValue();
                    }
                }
                serialization = serialization + i + "#" + serializedItemStack + ";";
            }
        }
        return serialization;
    }
    
    public static Inventory StringToInventory(final String invString) {
        final String[] serializedBlocks = invString.split(";");
        final String invInfo = serializedBlocks[0];
        final Inventory deserializedInventory = Bukkit.getServer().createInventory((InventoryHolder)null, Integer.parseInt(invInfo));
        for (int i = 1; i < serializedBlocks.length; ++i) {
            final String[] serializedBlock = serializedBlocks[i].split("#");
            final int stackPosition = Integer.parseInt(serializedBlock[0]);
            if (stackPosition < deserializedInventory.getSize()) {
                ItemStack is = null;
                boolean createdItemStack = false;
                final String[] split;
                final String[] serializedItemStack = split = serializedBlock[1].split(":");
                for (final String itemInfo : split) {
                    final String[] itemAttribute = itemInfo.split("@");
                    if (itemAttribute[0].equals("t")) {
                        is = new ItemStack(Material.getMaterial(Integer.parseInt(itemAttribute[1])));
                        createdItemStack = true;
                    }
                    else if (itemAttribute[0].equals("d") && createdItemStack) {
                        is.setDurability(Short.parseShort(itemAttribute[1]));
                    }
                    else if (itemAttribute[0].equals("a") && createdItemStack) {
                        is.setAmount(Integer.parseInt(itemAttribute[1]));
                    }
                    else if (itemAttribute[0].equals("e") && createdItemStack) {
                        is.addEnchantment(Enchantment.getById(Integer.parseInt(itemAttribute[1])), Integer.parseInt(itemAttribute[2]));
                    }
                }
                deserializedInventory.setItem(stackPosition, is);
            }
        }
        return deserializedInventory;
    }
}
