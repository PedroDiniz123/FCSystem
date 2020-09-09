// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.kits.core;

public class KitInv
{
    private String kitName;
    private String kitPerm;
    private boolean enchant;
    private String icon;
    private short type;
    private int pos;
    private int time;
    
    public KitInv(final String kitName, final String kitPerm, final boolean enchant, final String icon, final short type, final int pos, final int time) {
        this.kitName = kitName;
        this.kitPerm = kitPerm;
        this.enchant = enchant;
        this.icon = icon;
        this.type = type;
        this.pos = pos;
        this.time = time;
    }
    
    public String getKitName() {
        return this.kitName;
    }
    
    public void setKitName(final String kitName) {
        this.kitName = kitName;
    }
    
    public String getKitPerm() {
        return this.kitPerm;
    }
    
    public void setKitPerm(final String kitPerm) {
        this.kitPerm = kitPerm;
    }
    
    public boolean isEnchant() {
        return this.enchant;
    }
    
    public void setEnchant(final boolean enchant) {
        this.enchant = enchant;
    }
    
    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(final String icon) {
        this.icon = icon;
    }
    
    public short getType() {
        return this.type;
    }
    
    public void setType(final short type) {
        this.type = type;
    }
    
    public int getPos() {
        return this.pos;
    }
    
    public void setPos(final int pos) {
        this.pos = pos;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public void setTime(final int time) {
        this.time = time;
    }
}
