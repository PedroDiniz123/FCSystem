// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.scoreboard.listener;

import com.massivecraft.factions.entity.MPlayer;
import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.*;
import fallcraftsystem.modules.coin.database.CoinData;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.ChatVault;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.sql.SQLException;

public class GameScoreboard implements Listener
{
    public FallCraftSystem plugin;
    
    public GameScoreboard(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    private static void adminPlayerScoreboard(final Player player) throws SQLException {
        final String[] colors = { "&a", "&b", "&c", "&d", "&e", "&f", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9" };
        final ScoreboardManager scoreboardManager = FallCraftSystem.plugin.getServer().getScoreboardManager();
        final Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective(Ultilities.formater("&5&l" + player.getName()), "MENU");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        final MPlayer mplayer = MPlayer.get((Object)player);
        final double x = Math.random() * (colors.length - 1 + 1);
        final int ale = (int)x;
        String facName = "";
        final Score score1 = objective.getScore(ChatColor.RESET + Ultilities.formater(colors[ale] + "&m&l----------  -----------------"));
        Score score2 = objective.getScore(Ultilities.formater("&f Fac\u00e7\u00e3o: &8Sem fac\u00e7\u00e3o"));
        if (!mplayer.getFactionTag().equals("") && !mplayer.getFactionTag().equals(" ")) {
            score2 = objective.getScore(Ultilities.formater("&f Fac\u00e7\u00e3o: &e" + mplayer.getFactionName()));
        }
        final Score score3 = objective.getScore(Ultilities.formater("&f Power: &c" + String.format("%.1f", mplayer.getPower())));
        final Score score4 = objective.getScore("  ");
        final Score score5 = objective.getScore(Ultilities.formater("&f Money: &a$" + VaultEconomy.getVault().getBalance((OfflinePlayer)player)));
        final Score score6 = objective.getScore(Ultilities.formater("&f Coins: &2¢" + String.format("%.1f", CoinData.getCoins(player))));
        final Score score7 = objective.getScore("    ");
        final Score score8 = objective.getScore(Ultilities.formater("&f PVP: " + Ultilities.formater("&6" + ServerUtils.players.get(player).getPvpStatus().toString())));
        final Score score9 = objective.getScore(ChatColor.RESET + Ultilities.formater(colors[ale] + "&m&l-----------------------------"));
        final Score score10 = objective.getScore(Ultilities.formater("&6 === STAFF === "));
        final Score score11 = objective.getScore("       ");
        final Score score12 = objective.getScore(ChatColor.RESET + Ultilities.formater(Ultilities.formater(colors[ale] + "&m&l------------   ---------------")));
        score1.setScore(20);
        score2.setScore(19);
        score3.setScore(18);
        score4.setScore(17);
        score5.setScore(16);
        score6.setScore(15);
        score7.setScore(14);
        score8.setScore(13);
        score9.setScore(12);
        score10.setScore(11);
        score11.setScore(10);
        score12.setScore(9);


        final String perm = ChatVault.getChat().getPlayerPrefix(player).replace("[", "").replace("]", "");
        if (!mplayer.getFactionTag().equals("") && !mplayer.getFactionTag().equals(" ")) {
            facName = " &e&l" + mplayer.getFactionTag() + "";
        }
        player.setPlayerListName(Ultilities.formater(perm + "&7" + player.getName() + facName));
        player.setScoreboard(scoreboard);
    }
    
    private static void normalPlayerScoreboard(final Player player) throws SQLException {
        final String[] colors = { "&a", "&b", "&c", "&d", "&e", "&f", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9" };
        final ScoreboardManager scoreboardManager = FallCraftSystem.plugin.getServer().getScoreboardManager();
        final Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective(Ultilities.formater("&5&l" + player.getName()), "MENU");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        final MPlayer mplayer = MPlayer.get((Object)player);
        String facName = "";
        final double x = Math.random() * (colors.length - 1 + 1);
        final int ale = (int)x;
        final Score score1 = objective.getScore(Ultilities.formater(colors[ale] + "&m&l---------------------------"));
        Score score2 = objective.getScore(Ultilities.formater("&f Fac\u00e7\u00e3o: &8Sem fac\u00e7\u00e3o"));
        if (!mplayer.getFactionTag().equals("") && !mplayer.getFactionTag().equals(" ")) {
            score2 = objective.getScore(Ultilities.formater("&f Fac\u00e7\u00e3o: &e" + mplayer.getFactionName()));
        }
        final Score score3 = objective.getScore(Ultilities.formater("&f Power: &c" + String.format("%.1f", mplayer.getPower())));
        final Score score4 = objective.getScore("  ");
        final Score score5 = objective.getScore(Ultilities.formater("&f Money: &a$" + VaultEconomy.getVault().getBalance((OfflinePlayer)player)));
        final Score score6 = objective.getScore(Ultilities.formater("&f Coins: &2¢" + String.format("%.1f", CoinData.getCoins(player))));
        final Score score7 = objective.getScore("    ");
        final Score score8 = objective.getScore(Ultilities.formater("&f PVP: " + Ultilities.formater("&6" + ServerUtils.players.get(player).getPvpStatus().toString())));
        final Score score9 = objective.getScore(Ultilities.formater(colors[ale] + "&m&l--------------------------- "));
        score1.setScore(10);
        score2.setScore(9);
        score3.setScore(8);
        score4.setScore(7);
        score5.setScore(6);
        score6.setScore(5);
        score7.setScore(4);
        score8.setScore(3);
        score9.setScore(2);
        if (!mplayer.getFactionTag().equals("") && !mplayer.getFactionTag().equals(" ")) {
            facName = " &e&l" + mplayer.getFactionTag() + "";
        }
        final String perm = ChatVault.getChat().getPlayerPrefix(player).replace("[", "").replace("]", "");
        player.setPlayerListName(Ultilities.formater(perm + "&7" + player.getName() + facName));
        player.setScoreboard(scoreboard);
    }
    
    private static void emptyScoreboard(final Player player) throws SQLException {
        final ScoreboardManager scoreboardManager = FallCraftSystem.plugin.getServer().getScoreboardManager();
        final Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("", "");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        final Score score1 = objective.getScore(Ultilities.formater(""));
        score1.setScore(0);
        player.setScoreboard(scoreboard);
    }
    
    @EventHandler
    public void onJoinPlayer(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        new BukkitRunnable() {
            public void run() {
                final GamePlayer gm = ServerUtils.players.get(player);
                try {
                    if (gm.getFCScoreboardStatus().equals(FCScoreboardStatus.OFF)) {
                        emptyScoreboard(player);
                    }
                    else if (player.hasPermission("admin.scoreboard")) {
                        adminPlayerScoreboard(player);
                    }
                    else {
                        normalPlayerScoreboard(player);
                    }
                }
                catch (Exception e) {
                    System.err.println("Player is null, canceling the timer");
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)this.plugin, 0L, 7L);
    }
}
