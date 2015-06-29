package com.beastmc.MCSR.main.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.beastmc.MCSR.main.MCSR;
import com.beastmc.MCSR.main.Rank;

public class MCSRCommand implements CommandExecutor {
	
	public MCSRCommand(MCSR plugin) {
		plugin.getCommand("MCSR").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args[0].equalsIgnoreCase("setservername") && args.length > 1 && Rank.getPlayersRank(sender.getName()).permLevel >= 10) {
			String name = "";
			for (int i = 0; i < args.length; i++) {
				String s = args[i];
				if(i !=0) 
					name += s + " ";
			}
			MCSR.saveString("servername", name);
			MCSR.ScoreboardInit();
		}
		return false;
	}

}
