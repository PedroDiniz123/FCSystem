// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.utils;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.NoFallSTatus;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.*;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.UUID;

public class Ultilities
{
    public static String formater(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public static void send(final CommandSender sender, final String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
    
    public static void send(final Player player, final String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
    
    public static String getUUIDFromNick(final String name) {
        final FallCraftSystem pl = FallCraftSystem.plugin;
        if (pl.getServer().getOnlineMode()) {
            return returnFromWebSite(name);
        }
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)).toString();
    }
    
    private static String returnFromWebSite(final String name) {
        final String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        String uuidFormated = "";
        String uuid = "";
        try {
            final String UUIDJson = IOUtils.toString(new URL(url));
            if (UUIDJson.isEmpty()) {
                return "invalid name";
            }
            final JSONObject UUIDObject = (JSONObject)JSONValue.parseWithException(UUIDJson);
            uuid = UUIDObject.get((Object)"id").toString();
        }
        catch (IOException | ParseException ex2) {
            final Exception ex = null;
            final Exception e = ex;
            e.printStackTrace();
        }
        uuidFormated = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
        return uuidFormated;
    }
    
    public static void sendHeaderAndFooter(final Player p, final String head, final String foot) {
        final PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        final IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{'color':'', 'text':'" + head + "'}");
        final IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{'color':'', 'text':'" + foot + "'}");
        final PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try {
            final Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());
            final Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        connection.sendPacket((Packet)packet);
    }
    
    public static void teleportTimer() {
        new BukkitRunnable() {
            public void run() {
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    try {
                        if (!ServerUtils.teleportMap.containsKey(p)) {
                            continue;
                        }
                        if (!p.hasPermission("fallcraft.teleport.bypass")) {
                            final GamePlayer gp = ServerUtils.players.get(p);
                            if (ServerUtils.teleportMap.get(p).getLocation().getY() == p.getLocation().getY() || ServerUtils.teleportMap.get(p).isTeleported()) {
                                if (ServerUtils.teleportMap.get(p).getTime() > 0) {
                                    p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Teleportando em &c" + ServerUtils.teleportMap.get(p).getTime()));
                                    ServerUtils.teleportMap.get(p).setTime(ServerUtils.teleportMap.get(p).getTime() - 1);
                                }
                                else {
                                    if (ServerUtils.teleportMap.get(p).getTime() != 0) {
                                        continue;
                                    }
                                    if (!ServerUtils.teleportMap.get(p).isTeleported()) {
                                        if (gp.getNoFallSTatus().equals(NoFallSTatus.OFF)) {
                                            gp.setNoFallSTatus(NoFallSTatus.ON);
                                            p.setAllowFlight(true);
                                        }
                                        p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cTeleportando..."));
                                        p.playSound(ServerUtils.teleportMap.get(p).getToLoc(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                                        p.teleport(ServerUtils.teleportMap.get(p).getToLoc());
                                    }
                                    gp.setTeleports(gp.getTeleports() + 1);
                                    if (gp.getTeleports() == 4) {
                                        p.teleport(ServerUtils.teleportMap.get(p).getToLoc());
                                        gp.setNoFallSTatus(NoFallSTatus.OFF);
                                        p.setAllowFlight(false);
                                        gp.setTeleports(0);
                                    }
                                    ServerUtils.teleportMap.get(p).setTeleported(true);
                                    if (ServerUtils.teleportMap.get(p).getInvincibility() < 0) {
                                        ServerUtils.teleportMap.get(p).setTeleported(true);
                                        ServerUtils.teleportMap.get(p).setInvincibility(2);
                                    }
                                    else if (ServerUtils.teleportMap.get(p).getInvincibility() > 0) {
                                        ServerUtils.teleportMap.get(p).setInvincibility(ServerUtils.teleportMap.get(p).getInvincibility() - 1);
                                    }
                                    else {
                                        if (ServerUtils.teleportMap.get(p).getInvincibility() != 0) {
                                            continue;
                                        }
                                        ServerUtils.teleportMap.remove(p);
                                    }
                                }
                            }
                            else {
                                p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Voc\u00ea se mexeu, teleporte &ccancelado&6."));
                                ServerUtils.teleportMap.remove(p);
                            }
                        }
                        else {
                            p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cTeleportando..."));
                            p.playSound(ServerUtils.teleportMap.get(p).getToLoc(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                            p.teleport(ServerUtils.teleportMap.get(p).getToLoc());
                            ServerUtils.teleportMap.remove(p);
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                        ServerUtils.teleportMap.remove(p);
                    }
                }
            }
        }.runTaskTimer((Plugin)FallCraftSystem.plugin, 20L, 20L);
    }
    
    public static ItemStack newBook(final String title, final String author, final String... pages) {
        ItemStack is = new ItemStack(Material.WRITTEN_BOOK, 1);
        final net.minecraft.server.v1_8_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
        final NBTTagCompound bd = new NBTTagCompound();
        bd.setString("title", title);
        bd.setString("author", author);
        final NBTTagList bp = new NBTTagList();
        for (final String text : pages) {
            bp.add((NBTBase)new NBTTagString(text));
        }
        bd.set("pages", (NBTBase)bp);
        nmsis.setTag(bd);
        is = CraftItemStack.asBukkitCopy(nmsis);
        return is;
    }
    
    public static void openBook(final ItemStack book, final Player p) {
        final int slot = p.getInventory().getHeldItemSlot();
        final ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, book);
        final PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(Unpooled.EMPTY_BUFFER));
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
        p.getInventory().setItem(slot, old);
    }
}
