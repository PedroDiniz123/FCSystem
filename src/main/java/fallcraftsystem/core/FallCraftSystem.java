// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.core;

import fallcraftsystem.modules.chest.core.LoadChestModules;
import fallcraftsystem.modules.coin.core.LoadCoinModule;
import fallcraftsystem.modules.coinshop.core.LoadCoinShopModules;
import fallcraftsystem.modules.essentials.commands.LoadEssentialCommandsModule;
import fallcraftsystem.modules.essentials.spawn.LoadEssentialSpawnModule;
import fallcraftsystem.modules.essentials.warp.LoadEssentialWarpModule;
import fallcraftsystem.modules.invsee.core.LoadInvseeModules;
import fallcraftsystem.modules.kits.core.LoadKitModules;
import fallcraftsystem.modules.reparo.core.LoadReparoModules;
import fallcraftsystem.modules.scoreboard.core.LoadScoreboard;
import fallcraftsystem.modules.scoreboardoff.core.LoadScoreboardOffModules;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.ChatVault;
import fallcraftsystem.utils.dependencies.PEX;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import fallcraftsystem.utils.generalevents.GeneralEvents;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class FallCraftSystem extends JavaPlugin
{
    public static FallCraftSystem plugin;
    
    public void onEnable() {
        FallCraftSystem.plugin = this;
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a======================================================="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     ########### ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     ########    ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     #           #        # ########## ##########    ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a======================================================="));
        this.loadModules();
    }
    
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c======================================================="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     ########### ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     ########    ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     #           #        # ########## ##########    ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c======================================================="));
        HandlerList.unregisterAll((Plugin)this);
    }
    
    private void loadModules() {
        Ultilities.teleportTimer();
        ChatVault.setupChat(this);
        PEX.setupPEX(this);
        VaultEconomy.setupEconomy(this);
        new LoadCoinModule(this);
        new ServerUtils();
        new LoadKitModules(this);
        new LoadEssentialWarpModule(this);
        new LoadEssentialSpawnModule(this);
        new LoadEssentialCommandsModule(this);
        new LoadScoreboard(this);
        new GeneralEvents(this);
        new LoadChestModules(this);
        new LoadScoreboardOffModules(this);
        new LoadInvseeModules(this);
        new LoadReparoModules(this);
        new LoadCoinShopModules(this);
    }
}
