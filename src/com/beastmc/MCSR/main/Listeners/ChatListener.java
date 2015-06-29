package com.beastmc.MCSR.main.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import com.beastmc.MCSR.main.MCSR;
import com.beastmc.MCSR.main.Punishments;
import com.beastmc.MCSR.main.Rank;
import com.beastmc.MCSR.main.Utils.Mute;
import com.beastmc.MCSR.main.Utils.TempBan;

public class ChatListener implements Listener {
	
	public ChatListener(MCSR plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void chat(AsyncPlayerChatEvent e) {
		e.setFormat(Rank.getPlayersName(e.getPlayer().getName()) + " " + e.getPlayer().getName() + ": " + e.getMessage());
		if(Rank.getPlayersRank(e.getPlayer().getName()).permLevel > 7) {
			e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
		}
		if(Punishments.getMute(e.getPlayer().getName()) != null) {
			Mute m = Punishments.getMute(e.getPlayer().getName());
			long diff = m.end - System.currentTimeMillis();
			if(diff<=0){
				Punishments.endMute(e.getPlayer().getName());
			}else{
				e.setCancelled(true);
				e.getPlayer().sendMessage("-¤6¤lMCSR¤r-\n¤cYou are muted!");
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(Rank.getPlayersRank(p.getName()).permLevel >= 7 || p == e.getPlayer()) {
						p.sendMessage("¤c¤lMUTE>¤r " + e.getFormat());
					}
				}
			}
		}
	}
	
}
