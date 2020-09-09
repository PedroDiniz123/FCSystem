// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.utils.generalevents;

import com.massivecraft.factions.entity.MPlayer;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.association.RegionAssociable;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.NoFallSTatus;
import fallcraftsystem.entities.enums.PvpStatus;
import fallcraftsystem.utils.SendTitle;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.ChatVault;
import fallcraftsystem.utils.dependencies.WG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  GeneralEvents implements Listener
{
    public FallCraftSystem plugin;
    
    public GeneralEvents(final FallCraftSystem pl) {
        this.plugin = pl;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this.plugin);
    }
    
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final GamePlayer gm = new GamePlayer(event.getPlayer());
        final Player p = event.getPlayer();
        Ultilities.getUUIDFromNick(event.getPlayer().getName());
        for (final Player ol : ServerUtils.vanishList) {
            if (!p.hasPermission("fc.vanish")) {
                p.hidePlayer(ol);
            }
        }
        ServerUtils.players.put(event.getPlayer(), gm);
        event.setJoinMessage(Ultilities.formater("&a+&f &8" + event.getPlayer().getName()));
        final MPlayer mplayer = MPlayer.get((Object)event.getPlayer());
        Ultilities.sendHeaderAndFooter(event.getPlayer(), Ultilities.formater("§d♦ §f§lFall§b§lCraft §d♦\n§aViva Uma Aventura!\n"), Ultilities.formater("\n§bDiscord: §fhttps://discord.gg/zXYsjUp\n\n§b§lAdquira VIP e Cash acessando: §ffallcraft.buycraft.net"));
        String facName = "";
        if (!mplayer.getFactionTag().equals("") && !mplayer.getFactionTag().equals(" ")) {
            facName = " &e&l" + mplayer.getFactionTag() + "";
        }
        final String perm = ChatVault.getChat().getPlayerPrefix(event.getPlayer()).replace("[", "").replace("]", "");
        event.getPlayer().setPlayerListName(Ultilities.formater(perm + "&7" + event.getPlayer().getName() + facName));
    }
    
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        GamePlayer m = ServerUtils.players.get(event.getPlayer());
        m = null;
        if (event.getPlayer().hasPermission("fallcraft.module.essentials.inv")) {
            event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
        }
        ServerUtils.vanishList.remove(event.getPlayer());
        ServerUtils.players.remove(event.getPlayer());
        event.setQuitMessage(Ultilities.formater("&c-&f &8" + event.getPlayer().getName()));
    }
    
    @EventHandler
    public void updatePlayerOnMove(final PlayerMoveEvent event) {
        final Player p = event.getPlayer();
        if (!ServerUtils.players.containsKey(p)) {
            final GamePlayer gm = new GamePlayer(event.getPlayer());
            ServerUtils.players.put(event.getPlayer(), gm);
        }
        final GamePlayer gp = ServerUtils.players.get(p);
        final Vector vector = new Vector();
        if (gp.getNoFallSTatus().equals(NoFallSTatus.ON)) {
            p.setVelocity(vector.zero());
            return;
        }
        final LocalPlayer localPlayer = WG.getWorldGuardPlugin(this.plugin).wrapPlayer(p);
        final com.sk89q.worldedit.Vector playerVector = localPlayer.getPosition();
        final RegionManager regionManager = WG.getWorldGuardPlugin(this.plugin).getRegionManager(p.getWorld());
        final ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(playerVector);
        if (applicableRegionSet.queryState((RegionAssociable)null, new StateFlag[] { DefaultFlag.PVP }) == StateFlag.State.DENY) {
            if (ServerUtils.players.get(p).getPvpStatus().equals(PvpStatus.ON)) {
                ServerUtils.players.get(p).setPvpStatus(PvpStatus.OFF);
                SendTitle.send(p, Ultilities.formater("&aPVP OFF"), "", 5, 5, 5);
            }
        }
        else if (ServerUtils.players.get(p).getPvpStatus().equals(PvpStatus.OFF)) {
            SendTitle.send(p, Ultilities.formater("&cPVP ON"), "", 5, 5, 5);
            ServerUtils.players.get(p).setPvpStatus(PvpStatus.ON);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerKill(final PlayerDeathEvent event) {
        event.setDeathMessage("");
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final List<AntiLabyMod.LabyMod> mods = new ArrayList<AntiLabyMod.LabyMod>();
        Collections.addAll(mods, AntiLabyMod.LabyMod.values());
        AntiLabyMod.disableMod(e.getPlayer(), mods);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void removeEntityDamage(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player p = (Player)event.getEntity();
            if (ServerUtils.teleportMap.containsKey(p) && ServerUtils.teleportMap.get(p).getInvincibility() > 0) {
                event.setCancelled(true);
            }
        }
    }
}
