package com.beastmc.MCSR.main.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.beastmc.MCSR.main.MCSR;

public class MCSRCommand implements CommandExecutor {
	
	public MCSRCommand(MCSR plugin) {
		plugin.getCommand("MCSR").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		return false;
	}

}
