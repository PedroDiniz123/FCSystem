// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coinshop.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.coinshop.utils.Product;
import fallcraftsystem.modules.coinshop.utils.Utilities;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CoinShop implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public CoinShop(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("coinshop").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players");
            return true;
        }
        final Player player = (Player)sender;
        if (args.length == 0) {
            player.openInventory(Utilities.createShop(player));
            return true;
        }
        if (!player.hasPermission("fc.coinshop_admin")) {
            return false;
        }
        if (args[0].equalsIgnoreCase("ajuda")) {
            player.sendMessage("§cCoinShop Ajuda:\n");
            player.sendMessage("§e/coinshop add <buy/sell> <pre\u00e7o> <nome>: §9Adiciona o item na aba de compras ou vendas da CoinShop");
            player.sendMessage("§e/coinshop remove <buy/sell> <n\u00famero do slot>: §9Remove o item da aba de compras ou vendas da CoinShop");
            return true;
        }
        if (args.length < 3) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cDigite /coinshop ajuda."));
            return true;
        }
        if (!args[0].equalsIgnoreCase("add")) {
            if (args[0].equalsIgnoreCase("remove")) {
                if (this.isNumeric(args[2])) {
                    if (Integer.parseInt(args[2]) > 54) {
                        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cN\u00famero alto demais."));
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("buy")) {
                        Utilities.removeItem(Integer.parseInt(args[2]), true, player);
                    }
                    if (args[1].equalsIgnoreCase("sell")) {
                        Utilities.removeItem(Integer.parseInt(args[2]), false, player);
                    }
                    player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§aItem removido."));
                    return true;
                }
                else {
                    player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cSlot inv\u00e1lido."));
                }
            }
            return true;
        }
        if (!this.isNumeric(args[2])) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cPre\u00e7o inv\u00e1lido."));
            this.ajuda();
            return true;
        }
        if (player.getItemInHand().getType().equals((Object)Material.AIR)) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cVoc\u00ea precisa ter um item na m\u00e3o"));
            return true;
        }
        if (args[1].equalsIgnoreCase("buy")) {
            new Product(Integer.parseInt(args[2]), player.getItemInHand(), true);
            player.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
        }
        else {
            if (!args[1].equalsIgnoreCase("sell")) {
                player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cDigite /coinshop ajuda para ver os comandos."));
                return true;
            }
            new Product(Integer.parseInt(args[2]), player.getItemInHand(), false);
            player.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
        }
        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§aItem adicionado!"));
        return true;
    }
    
    public boolean isNumeric(final String arg) {
        final char[] charArray;
        final char[] argChar = charArray = arg.toCharArray();
        for (final char charactere : charArray) {
            if (!Character.isDigit(charactere)) {
                return false;
            }
        }
        return true;
    }
    
    public void ajuda() {
    }
}
