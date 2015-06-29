package com.beastmc.MCSR.main.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import com.beastmc.MCSR.main.MCSR;
import com.beastmc.MCSR.main.Punishments;
import com.beastmc.MCSR.main.Rank;
import com.beastmc.MCSR.main.Utils.*;

public class JoinLeaveListener implements Listener {
	
	public JoinLeaveListener(MCSR plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void Join(PlayerJoinEvent e) {
		e.setJoinMessage("¤a+¤7" + e.getPlayer().getName() + " has joined.");
		MCSR.ScoreboardInit();
		if(Rank.getPlayersRank(e.getPlayer().getName()) == null) {
			Rank.setPlayersRank(e.getPlayer().getName(), "Default");
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void Login(PlayerLoginEvent e) {
		if(Punishments.getTempBan(e.getPlayer().getName()) != null) {
			TempBan tb = Punishments.getTempBan(e.getPlayer().getName());
			long diff = tb.end - System.currentTimeMillis();
			if(diff<=0){
				Punishments.endTempBan(e.getPlayer().getName());
			}else{
				e.disallow(Result.KICK_BANNED, "-¤6¤lMCSR¤r-\n You have been ¤btemporarily banned¤r by " + tb.banner + "\n For: " + tb.reason + "\n This ban will last for:\n" + Punishments.getMSGTime(tb.end));
			}
		}
	}
	
	@EventHandler
	public void leave(PlayerQuitEvent e) {
		Bukkit.getScheduler().runTaskLater(MCSR.inst, new Runnable() {
			public void run() {
				MCSR.ScoreboardInit();
			}
		}
		, 10);
		e.setQuitMessage("¤c-¤7" + e.getPlayer().getName() + "has left");
	}
}
