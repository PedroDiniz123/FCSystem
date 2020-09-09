// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.reparo.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.reparo.utils.ReparoConfig;
import fallcraftsystem.modules.reparo.utils.Utilities;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reparo implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public Reparo(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("reparo").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para jogadores.");
            return true;
        }
        Player player = (Player)sender;
        if (args.length == 0 || args.length > 2) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cUse /reparo <m\u00e3o/tudo>."));
            return true;
        }
        if (args.length == 2 && player.hasPermission("fc.reparar_admin")) {
            if (this.plugin.getServer().getPlayer(args[1]) == null) {
                player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player n\u00e3o est\u00e1 online!"));
                return true;
            }
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aItens reparados."));
            player = this.plugin.getServer().getPlayer(args[1]);
        }
        final int handall = Utilities.handOrAll(args);
        final boolean bypass = sender.hasPermission("fc.reparar_bypass");
        final int index = Utilities.getPermIndex(sender);
        final double price = ReparoConfig.getRepararFile().getDouble("config.precos." + index + ".mao");
        final double money = VaultEconomy.getVault().getBalance((OfflinePlayer)player);
        if (index == 0 && !bypass) {
            sender.sendMessage("§cSem permiss\u00e3o.");
            return true;
        }
        if (!Utilities.hasEnoughMoney(money, price) && !bypass) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&7Dinheiro insuficiente"));
            return true;
        }
        Utilities.repair(handall, player, bypass, price);
        return true;
    }
}
