package com.beastmc.MCSR.main.Listeners;

import com.beastmc.MCSR.main.MCSR;

public class Listeners {
	public Listeners(MCSR plugin) {
		new JoinLeaveListener(plugin);
		new ChatListener(plugin);
		new TrailListener(plugin);
	}
}
