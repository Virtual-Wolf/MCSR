package com.beastmc.MCSR.main.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.beastmc.MCSR.main.Rank;

public class JoinLeaveListener implements Listener {
	
	@EventHandler
	public void Join(PlayerJoinEvent e) {
		if(Rank.getPlayersRank(e.getPlayer().getName()) == null) {
			Rank.setPlayersRank(e.getPlayer().getName(), "Default");
		}
	}
}
