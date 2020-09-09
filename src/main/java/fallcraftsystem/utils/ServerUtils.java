// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.utils;

import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.PlayerTeleport;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerUtils
{
    public static String PLUGIN_NAME;
    public static int MENU_SIZE;
    public static String SERVER_NAME;
    public static Map<Player, GamePlayer> players;
    public static List<Player> vanishList;
    public static Map<Player, PlayerTeleport> teleportMap;
    public static Map<Player, Boolean> noFallList;
    
    static {
        ServerUtils.PLUGIN_NAME = "&FFallPL &9&l>>&f ";
        ServerUtils.MENU_SIZE = 54;
        ServerUtils.SERVER_NAME = "&FFall&bCraft &9&l>>&f ";
        ServerUtils.players = new HashMap<Player, GamePlayer>();
        ServerUtils.vanishList = new ArrayList<Player>();
        ServerUtils.teleportMap = new HashMap<Player, PlayerTeleport>();
        ServerUtils.noFallList = new HashMap<Player, Boolean>();
    }
}
