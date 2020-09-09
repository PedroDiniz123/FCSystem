// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.kits.listeners;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.utils.KitConfig;
import fallcraftsystem.modules.kits.utils.KitDbConfig;
import fallcraftsystem.utils.SaveInventory;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.TimeCalculator;
import fallcraftsystem.utils.Ultilities;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class ClickAndGetItem implements Listener
{
    public FallCraftSystem plugin;
    
    public ClickAndGetItem(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void selectItem(final InventoryClickEvent event) {

        if (event.getClickedInventory() == null) {
            return;
        }

        try {
            if (event.getCurrentItem().getType().equals((Object)Material.AIR)) {
                return;
            }
        }
        catch (Exception e2) {
            return;
        }
        if (!ChatColor.stripColor(event.getView().getTitle()).equals("Kits")) {
            return;
        }
        final String kit = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase().trim();
        event.setCancelled(true);
        if (kit.equals("")) {
            return;
        }
        final String kitName = KitConfig.getKitFIle().getString("kit." + kit + ".name");
        final String invString = SaveInventory.recovery(this.plugin, kitName);
        final Inventory inventory = SaveInventory.StringToInventory(invString);
        final Player player = (Player)event.getWhoClicked();
        if (KitDbConfig.getDBKItFile().contains(player.getUniqueId() + "." + kit)) {
            try {
                final String hora = KitDbConfig.getDBKItFile().getString(player.getUniqueId() + "." + kit + ".time");
                final Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hora);
                final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                final Date date2 = new Date();
                final int difEmMin = (int)TimeCalculator.diferencaEmMinutos(date1, date2);
                final int horaTotal = KitConfig.getKitFIle().getInt("kit." + kit + ".time");
                final int minutosTotal = horaTotal * 60;
                final int minutosRestantes = minutosTotal - difEmMin;
                int horas = minutosRestantes / 60;
                if (minutosRestantes / 60 > 24) {
                    horas = ((horaTotal - (horaTotal - minutosRestantes / 1440)) * 60 - minutosRestantes % 60) / 60;
                }
                final Duration duracao = Duration.ofMinutes(minutosRestantes);
                final long diasRestantesFinal = duracao.toDays();
                final long horasRestantesFianl = duracao.toHours() % 24L;
                final long minutosRestantesFinal = duracao.toMinutes() % 60L;
                if (!player.isOp() && !player.hasPermission("fallcraft.kit.bypass") && (diasRestantesFinal > 0L || horasRestantesFianl > 0L || minutosRestantesFinal > 0L)) {
                    player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce ja pegou esse item!"));
                    event.setCancelled(true);
                    return;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!player.hasPermission(KitConfig.getKitFIle().getString("kit." + kit + ".perm"))) {
            player.sendMessage(Ultilities.formater("&cVoce nao tem permissao para pegar esse kit!"));
            event.setCancelled(true);
            return;
        }
        event.setCancelled(true);
        for (final ItemStack content : inventory.getContents()) {
            if (content != null) {
                player.getInventory().addItem(new ItemStack[] { content });
            }
        }
        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aVoce pegou o kit &6" + StringUtils.capitalize(kitName)));
        final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date3 = new Date();
        final String frmtdDate = dateFormat2.format(date3);
        KitDbConfig.getDBKItFile().set(player.getUniqueId() + "." + kitName + ".time", (Object)frmtdDate);
        KitDbConfig.save();
        player.closeInventory();
    }
}
