// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coin.commands.players;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.coin.database.CoinData;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Coin implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public Coin(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("coin").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if (args.length == 0 && !(sender instanceof Player)) {
            sender.sendMessage(Ultilities.formater("&cUse /coin user"));
            return true;
        }
        if (args.length == 0) {
            final Player player = (Player)sender;
            try {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Seus coins: &2¢" + CoinData.getCoins(player)));
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aCoin Help"));
            sender.sendMessage(Ultilities.formater("&9/coin &0- &6Mostrar quantidade de coins"));
        }
        else if (args[0].equalsIgnoreCase("pay")) {
            if (Bukkit.getPlayer(args[1]) != null) {
                try {
                    final Player p = Bukkit.getPlayer(args[1]);
                    final double amount = Double.parseDouble(args[2]);
                    CoinData.addCoins(p, amount);
                    CoinData.takeCoins((Player)sender, amount);
                    sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aPago ¢" + amount + " coins para o player " + p.getName()));
                    p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aRecebidos ¢" + amount + " coins de " + ((Player)sender).getDisplayName()));
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                catch (Exception e2) {
                    System.out.println(e2.getMessage());
                    sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cUse o comando /coin add <Nick> <Quantidade>"));
                }
            }
        }
        else if (args[0].equalsIgnoreCase("add") && sender.hasPermission("fc.coin_admin")) {
            if (Bukkit.getPlayer(args[1]) != null) {
                try {
                    final Player p = Bukkit.getPlayer(args[1]);
                    final double amount = Double.parseDouble(args[2]);
                    CoinData.addCoins(p, amount);
                    sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aAdicionado ¢" + amount + " coins para o player " + p.getName()));
                    p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aRecebidos ¢" + amount + " coins"));
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                catch (Exception e2) {
                    System.out.println(e2.getMessage());
                    sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cUse o comando /coin add <Nick> <Quantidade>"));
                }
            }
        }
        else if (args[0].equalsIgnoreCase("take") && sender.hasPermission("fc.coin_admin")) {
            if (Bukkit.getPlayer(args[1]) != null) {
                try {
                    final Player p = Bukkit.getPlayer(args[1]);
                    final double amount = Double.parseDouble(args[2]);
                    if (CoinData.getCoins(p) < amount) {
                        sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player " + p.getName() + " nao possui a quantidade de ¢" + amount + " coins"));
                        return true;
                    }
                    CoinData.takeCoins(p, amount);
                    sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cRemovido ¢" + amount + " coins do player " + p.getName()));
                    p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cRetirados ¢" + amount + " coins"));
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                catch (Exception e2) {
                    System.out.println(e2.getMessage());
                    sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cUse o comando /coin add <Nick> <Quantidade>"));
                }
            }
        }
        else if (sender.hasPermission("fc.coin_admin") && Bukkit.getPlayer(args[0]) != null) {
            final Player p = Bukkit.getPlayer(args[0]);
            try {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Coins do player " + p.getName() + ": &2¢" + CoinData.getCoins(p)));
            }
            catch (SQLException throwables) {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player pode nao estar online ou nao existir!"));
            }
        }
        else if (sender.hasPermission("fc.coin_admin") && Bukkit.getPlayer(args[0]) != null) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player pode nao estar online ou nao existir!"));
        }
        return true;
    }
}
