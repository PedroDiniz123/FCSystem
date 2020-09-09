// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.entities;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerTeleport
{
    private Player player;
    private int time;
    private Location location;
    private Location toLoc;
    private int invincibility;
    private boolean teleported;
    
    public PlayerTeleport(final int time, final Location location, final Location toLoc) {
        this.time = time;
        this.location = location;
        this.toLoc = toLoc;
        this.invincibility = -1;
        this.teleported = false;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void setPlayer(final Player player) {
        this.player = player;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public void setTime(final int time) {
        this.time = time;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(final Location location) {
        this.location = location;
    }
    
    public Location getToLoc() {
        return this.toLoc;
    }
    
    public void setToLoc(final Location toLoc) {
        this.toLoc = toLoc;
    }
    
    public int getInvincibility() {
        return this.invincibility;
    }
    
    public boolean isTeleported() {
        return this.teleported;
    }
    
    public void setTeleported(final boolean teleported) {
        this.teleported = teleported;
    }
    
    public void setInvincibility(final int invincibility) {
        this.invincibility = invincibility;
    }
}
