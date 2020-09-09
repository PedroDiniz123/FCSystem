// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.utils.dependencies;

import fallcraftsystem.core.FallCraftSystem;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.plugin.RegisteredServiceProvider;

public class ChatVault
{
    public static Chat chat;
    
    public static boolean setupChat(final FallCraftSystem plugin) {
        final RegisteredServiceProvider<Chat> rsp = (RegisteredServiceProvider<Chat>)plugin.getServer().getServicesManager().getRegistration((Class)Chat.class);
        ChatVault.chat = (Chat)rsp.getProvider();
        return ChatVault.chat != null;
    }
    
    public static Chat getChat() {
        return ChatVault.chat;
    }
}
