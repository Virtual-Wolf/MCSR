package com.beastmc.MCSR.main.Listeners;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.beastmc.MCSR.main.MCSR;
import com.beastmc.MCSR.main.Utils.ParticleEffects;

public class TrailListener implements Listener {
	public static ArrayList<String> trailOn = new ArrayList<String>();
	
	public TrailListener(MCSR plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void move(PlayerMoveEvent e) {
		if(trailOn.contains(e.getPlayer().getName())) {
			ParticleEffects.sendToLocation(ParticleEffects.GREEN_SPARKLE, e.getFrom(), 1.0F, 1.0F, 1.0F, 3, 20);
		}
	}
}
