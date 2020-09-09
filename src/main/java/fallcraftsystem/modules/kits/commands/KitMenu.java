// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.kits.commands;

import fallcraftsystem.modules.kits.utils.KitDbConfig;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.TimeCalculator;
import fallcraftsystem.utils.Ultilities;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KitMenu
{
    public static <TimeStamp> void openInv(final Player player, final FileConfiguration locationFile) throws Exception {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)player, 45, Ultilities.formater("&9&lKits"));
        if (!locationFile.contains("kit")) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lNenhum kit definido"));
            return;
        }
        final ItemStack background = new ItemStack(Material.STAINED_GLASS_PANE);
        background.setDurability((short)9);
        final ItemMeta backMeta = background.getItemMeta();
        backMeta.setDisplayName(Ultilities.formater(" "));
        background.setItemMeta(backMeta);
        for (int i = 0; i < 45; ++i) {
            inventory.setItem(i, background);
        }
        final ConfigurationSection sec = locationFile.getConfigurationSection("kit");
        final ArrayList<ItemStack> itensList = new ArrayList<ItemStack>();
        for (final String key : sec.getKeys(false)) {
            final ItemStack item = new ItemStack(Material.getMaterial(locationFile.getString("kit." + key + ".icon")));
            item.setDurability((short)locationFile.getInt("kit." + key + ".type"));
            if (locationFile.getBoolean("kit." + key + ".effect")) {
                item.addEnchantment(Enchantment.DURABILITY, 1);
            }
            final ItemMeta itemMeta = item.getItemMeta();
            final String itemName = "&b&o" + StringUtils.capitalize(locationFile.getString("kit." + key + ".name"));
            if (KitDbConfig.getDBKItFile().contains(player.getUniqueId() + "." + key)) {
                final String hora = KitDbConfig.getDBKItFile().getString(player.getUniqueId() + "." + key + ".time");
                final Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hora);
                final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                final Date date2 = new Date();
                final int difEmMin = (int)TimeCalculator.diferencaEmMinutos(date1, date2);
                final int horaTotal = locationFile.getInt("kit." + key + ".time");
                final int minutosTotal = horaTotal * 60;
                final int minutosRestantes = minutosTotal - difEmMin;
                final int inputs = difEmMin * 60 - horaTotal * 3600;
                final Duration duracao = Duration.ofMinutes(minutosRestantes);
                int horas = minutosRestantes / 60;
                if (minutosRestantes / 60 > 24) {
                    horas = ((horaTotal - (horaTotal - minutosRestantes / 1440)) * 60 - minutosRestantes % 60) / 60;
                }
                final long diasRestantesFinal = duracao.toDays();
                final long horasRestantesFianl = duracao.toHours() % 24L;
                final long minutosRestantesFinal = duracao.toMinutes() % 60L;
                if (minutosRestantes <= 0 && horas <= 0 && minutosRestantes <= 0) {
                    final List<String> lore = new ArrayList<String>();
                    lore.add(Ultilities.formater("&aItem disponivel"));
                    itemMeta.setLore((List)lore);
                }
                else {
                    final List<String> lore = new ArrayList<String>();
                    lore.add(Ultilities.formater("&cKit nao disponivel"));
                    lore.add(Ultilities.formater("&ADisponivel nos proximos"));
                    lore.add(Ultilities.formater("&6" + diasRestantesFinal + " Dias " + horasRestantesFianl + " Horas e " + minutosRestantesFinal + " minutos"));
                    itemMeta.setLore((List)lore);
                }
            }
            else {
                final List<String> lore2 = new ArrayList<String>();
                lore2.add(Ultilities.formater("&aItem disponivel"));
                itemMeta.setLore((List)lore2);
            }
            if (!player.hasPermission(locationFile.getString("kit." + key + ".perm"))) {
                final List<String> lore2 = new ArrayList<String>();
                lore2.add(Ultilities.formater("&cVoce nao tem permissao para pegar esse kit!"));
                itemMeta.setLore((List)lore2);
            }
            itemMeta.setDisplayName(Ultilities.formater(itemName));
            item.setItemMeta(itemMeta);
            inventory.setItem(locationFile.getInt("kit." + key + ".pos"), item);
        }
        player.openInventory(inventory);
    }
}
