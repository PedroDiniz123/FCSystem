// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.entities;

import fallcraftsystem.entities.enums.*;
import org.bukkit.entity.Player;

public class GamePlayer
{
    private Player player;
    private PlayerStatus playerStatus;
    private PvpStatus pvpStatus;
    private FCScoreboardStatus fcScoreboardStatus;
    private NoFallSTatus noFallSTatus;
    private FlyStatus flyStatus;
    private VanishStatus vanishStatus;
    private int teleports;
    
    public GamePlayer(final Player player, final PlayerStatus playerStatus, final PvpStatus pvpStatus, final FCScoreboardStatus fcScoreboardStatus, final NoFallSTatus noFallSTatus, final int teleports, FlyStatus flyStatus, VanishStatus vanishStatus) {
        this.player = player;
        this.playerStatus = playerStatus;
        this.pvpStatus = pvpStatus;
        this.fcScoreboardStatus = fcScoreboardStatus;
        this.noFallSTatus = noFallSTatus;
        this.teleports = teleports;
        this.flyStatus = flyStatus;
        this.vanishStatus = vanishStatus;
    }
    
    public GamePlayer(final Player player) {
        this.player = player;
        this.playerStatus = PlayerStatus.FREE;
        this.pvpStatus = PvpStatus.OFF;
        this.fcScoreboardStatus = FCScoreboardStatus.ON;
        this.noFallSTatus = NoFallSTatus.OFF;
        this.teleports = 0;
        this.flyStatus = FlyStatus.NOT_FLYING;
        this.vanishStatus = VanishStatus.VISIBLE;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void setPlayer(final Player player) {
        this.player = player;
    }
    
    public PlayerStatus getPlayerStatus() {
        return this.playerStatus;
    }
    
    public void setPlayerStatus(final PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }
    
    public PvpStatus getPvpStatus() {
        return this.pvpStatus;
    }
    
    public void setPvpStatus(final PvpStatus pvpStatus) {
        this.pvpStatus = pvpStatus;
    }
    
    public FCScoreboardStatus getFCScoreboardStatus() {
        return this.fcScoreboardStatus;
    }
    
    public void setFcScoreboardStatus(final FCScoreboardStatus fcScoreboardStatus) {
        this.fcScoreboardStatus = fcScoreboardStatus;
    }
    
    public NoFallSTatus getNoFallSTatus() {
        return this.noFallSTatus;
    }
    
    public void setNoFallSTatus(final NoFallSTatus noFallSTatus) {
        this.noFallSTatus = noFallSTatus;
    }
    
    public int getTeleports() {
        return this.teleports;
    }
    
    public void setTeleports(final int teleports) {
        this.teleports = teleports;
    }

    public FlyStatus getFlyStatus() {
        return flyStatus;
    }

    public void setFlyStatus(FlyStatus flyStatus) {
        this.flyStatus = flyStatus;
    }

    public VanishStatus getVanishStatus() {
        return vanishStatus;
    }

    public void setVanishStatus(VanishStatus vanishStatus) {
        this.vanishStatus = vanishStatus;
    }

    @Override
    public String toString() {
        return "GamePlayer{"
                + ", player=" + this.player
                + ", playerStatus=" + this.playerStatus
                + ", pvpStatus=" + this.pvpStatus
                + ", fcScoreboardStatus= " + this.fcScoreboardStatus
                + ", noFallStatus= " + this.noFallSTatus + '}';
    }
}
