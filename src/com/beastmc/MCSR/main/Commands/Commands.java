package com.beastmc.MCSR.main.Commands;

import com.beastmc.MCSR.main.MCSR;

public class Commands {
	public Commands(MCSR plugin) {
		new MCSRCommand(plugin);
		new TrailCommand(plugin);
		new RankCommand(plugin);
	}
}
