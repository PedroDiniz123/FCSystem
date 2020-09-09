// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coinshop.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Item
{
    private String name;
    private List<String> desc;
    private ItemMeta itemMeta;
    private ItemStack itemStack;
    private Material material;
    private short id;
    
    public Item(final String name, final List<String> desc, final Material material, final short id) {
        this.name = name;
        this.desc = desc;
        this.material = material;
        this.id = id;
        this.itemStack = new ItemStack(material, 1, id);
        (this.itemMeta = this.itemStack.getItemMeta()).setDisplayName(name);
        this.itemMeta.setLore((List)desc);
        this.itemStack.setItemMeta(this.itemMeta);
    }
    
    public Item(final String name, final List<String> desc, final ItemStack itemStack) {
        this.name = name;
        this.desc = desc;
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }
    
    public Item(final ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }
    
    public Item(final Material material, final short id) {
        this.material = material;
        this.itemStack = new ItemStack(material, 1, id);
    }
    
    public void setItemMeta(final ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
        this.itemStack.setItemMeta(itemMeta);
    }
    
    public ItemMeta getItemMeta() {
        return this.itemMeta;
    }
    
    public void setItemStack(final ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    public void setName(final String name) {
        this.name = name;
        this.itemMeta.setDisplayName(name);
        this.itemStack.setItemMeta(this.itemMeta);
    }
    
    public String getName() {
        this.itemMeta.getDisplayName();
        return this.name;
    }
    
    public void setDesc(final List<String> desc) {
        this.desc = desc;
        this.itemMeta.setLore(desc);
        this.itemStack.setItemMeta(this.itemMeta);
    }
    
    public List<String> getDesc() {
        return this.desc;
    }
    
    public void setMaterial(final Material material) {
        this.material = material;
        this.itemStack.setType(material);
        this.itemMeta = this.itemStack.getItemMeta();
    }
    
    public Material getMaterial() {
        return this.material;
    }
    
    public short getId() {
        return this.id;
    }
}
