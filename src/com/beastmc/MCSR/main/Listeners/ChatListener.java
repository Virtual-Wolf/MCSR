package com.beastmc.MCSR.main.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.beastmc.MCSR.main.MCSR;
import com.beastmc.MCSR.main.Rank;

public class ChatListener implements Listener {
	public static String[] colourCodes = new String[] {"&1", "&2", "&3","&4", "&5","&6", "&7", "&8", "&9", "&0", "&a", "&b", "&c", "&d","&e", "&f","&k", "&m", "&n", "&o", "&l", "&r"};
	
	public ChatListener(MCSR plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void chat(AsyncPlayerChatEvent e) {
		e.setFormat(Rank.getPlayersName(e.getPlayer().getName()) + e.getPlayer().getName() + ": " + e.getMessage());
		if(Rank.getPlayersRank(e.getPlayer().getName()).permLevel > 7) {
			for(String s : colourCodes) {
				if(e.getMessage().contains(s)) {
					e.setMessage(e.getMessage().replace(s, s.replace('&', '¤')));
				}
			}
		}
	}
}
