// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.utils.dependencies;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.plugin.Plugin;

public class WG
{
    public static WorldGuardPlugin getWorldGuardPlugin(final FallCraftSystem plugin) {
        final Plugin wg = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
        if (wg == null || !(wg instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin)wg;
    }
}
