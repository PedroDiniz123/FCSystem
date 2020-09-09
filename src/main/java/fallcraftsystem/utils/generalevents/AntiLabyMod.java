// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.utils.generalevents;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

public class AntiLabyMod
{
    public static void disableMod(final Player p, final List<LabyMod> mods) {
        try {
            final HashMap<String, Boolean> dList = new HashMap<String, Boolean>();
            for (final LabyMod mod : mods) {
                dList.put(mod.toString(), false);
            }
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject(dList);
            final ByteBuf bb = Unpooled.copiedBuffer(out.toByteArray());
            final PacketDataSerializer serializer = new PacketDataSerializer(bb);
            final PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("LABYMOD", serializer);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public enum LabyMod
    {
        FOOD, 
        GUI, 
        NICK, 
        BLOCKBUILD, 
        CHAT, 
        EXTRAS, 
        ANIMATIONS, 
        DAMAGEINDICATOR, 
        MINIMAP_RADAR, 
        IMPROVED_LAVA, 
        CROSSHAIR_SYNC, 
        REFILL_FIX, 
        GUI_ALL, 
        GUI_POTION_EFFECTS, 
        GUI_ARMOR_HUD, 
        GUI_ITEM_HUD, 
        TAGS, 
        SATURATION_BAR, 
        POTIONS, 
        ARMOR;
    }
}
