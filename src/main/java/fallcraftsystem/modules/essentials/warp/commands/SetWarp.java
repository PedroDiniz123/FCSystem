// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.warp.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.warp.utils.STATIC;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor
{
    public FallCraftSystem plugin;
    private FileConfiguration locationFile;
    
    public SetWarp(final FallCraftSystem plugin) {
        this.locationFile = WarpFile.getWarpFile();
        this.plugin = plugin;
        plugin.getCommand("setwarp").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commands for only players!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lUse /setwarp <Name> <Position> [Permission] to set the new warp!"));
            return true;
        }
        if (args.length == 1) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lVoce precisa informar a posicao do item!"));
            return true;
        }
        final Player player = (Player)sender;
        int iconPos = 0;
        final String warpName = ChatColor.stripColor(Ultilities.formater(args[0])).toUpperCase();
        try {
            iconPos = Integer.parseInt(args[1]);
        }
        catch (Exception e) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lVoce precisa informar a posicao do item!"));
            return true;
        }
        if (iconPos > STATIC.MENU_SIZE) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lVoce precisa informar um numero menor que " + STATIC.MENU_SIZE));
            return true;
        }
        final String title = args[0];
        Material item = player.getInventory().getItemInHand().getType();
        final Boolean enchant = player.getInventory().getItemInHand().getEnchantments().size() > 0;
        String itemName = player.getInventory().getItemInHand().getType().toString();
        if (item.equals((Object)Material.AIR)) {
            item = Material.GRASS;
            itemName = "GRASS";
        }
        this.locationFile.set("warps." + warpName + ".TITLE", (Object)title);
        this.locationFile.set("warps." + warpName + ".X", (Object)player.getLocation().getX());
        this.locationFile.set("warps." + warpName + ".Y", (Object)player.getLocation().getY());
        this.locationFile.set("warps." + warpName + ".Z", (Object)player.getLocation().getZ());
        this.locationFile.set("warps." + warpName + ".POS", (Object)(iconPos - 1));
        this.locationFile.set("warps." + warpName + ".ICON", (Object)itemName);
        this.locationFile.set("warps." + warpName + ".TYPE", (Object)player.getInventory().getItemInHand().getDurability());
        this.locationFile.set("warps." + warpName + ".EFFECT", (Object)enchant);
        this.locationFile.set("warps." + warpName + ".WOLRD", (Object)player.getLocation().getWorld().getName());
        if (args.length == 2) {
            this.locationFile.set("warps." + warpName + ".PERMISSION", (Object)"fallcraft.warp.default");
        }
        else {
            this.locationFile.set("warps." + warpName + ".PERMISSION", (Object)("fallcraft.warp." + args[2]));
        }
        WarpFile.save();
        sender.sendMessage(Ultilities.formater("&A&lWarp setted"));
        return true;
    }
}
