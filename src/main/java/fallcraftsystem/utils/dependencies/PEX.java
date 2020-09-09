// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.utils.dependencies;

import fallcraftsystem.core.FallCraftSystem;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class PEX
{
    private static Permission perms;
    
    public static boolean setupPEX(final FallCraftSystem plugin) {
        final RegisteredServiceProvider<Permission> rsp = (RegisteredServiceProvider<Permission>)Bukkit.getServer().getServicesManager().getRegistration((Class)Permission.class);
        PEX.perms = (Permission)rsp.getProvider();
        return PEX.perms != null;
    }
    
    public static Permission getPEX() {
        return PEX.perms;
    }
}
