// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.core;

import fallcraftsystem.modules.chest.utils.ChestsList;
import fallcraftsystem.modules.essentials.spawn.utils.SpawnFile;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import fallcraftsystem.modules.kits.utils.KitConfig;
import fallcraftsystem.modules.kits.utils.KitDbConfig;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadFall implements CommandExecutor
{
    public FallCraftSystem plugin;
    
    public ReloadFall(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("reloadfall").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        SpawnFile.reload();
        WarpFile.reload();
        KitDbConfig.reload();
        KitConfig.reload();
        WarpFile.reload();
        ChestsList.reload();
        commandSender.sendMessage(Ultilities.formater("&aArquivos de configuracao recarregado!"));
        return true;
    }
}
