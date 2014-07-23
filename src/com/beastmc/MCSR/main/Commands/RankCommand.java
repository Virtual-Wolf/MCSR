package com.beastmc.MCSR.main.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.beastmc.MCSR.main.MCSR;
import com.beastmc.MCSR.main.Rank;

public class RankCommand implements CommandExecutor {
	
	public RankCommand(MCSR plugin) {
		plugin.getCommand("Rank").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(Rank.getPlayersRank(p.getName()).permLevel >= 9) {
				if(args[0].equalsIgnoreCase("set") && args.length == 3) {
					if(Rank.getRank(args[2]) != null) {
						Rank.setPlayersRank(args[1], args[2]);
						p.sendMessage("¤6¤l" + args[1] + "¤8¤l is now a ¤6¤l" + Rank.getRank(args[2]).name + "¤8¤l.");
					} else {
						p.sendMessage("¤8¤lNo such rank exists!");
					}
				}
			}
		} else {
			
				if(args[0].equalsIgnoreCase("set") && args.length == 3) {
					if(Rank.getRank(args[2]) != null) {
						Rank.setPlayersRank(args[1], args[2]);
						System.out.println("¤6¤l" + args[1] + "¤8¤l is now a ¤6¤l" + Rank.getRank(args[2]).name + "¤8¤l.");
					} else {
						System.out.println("¤8¤lNo such rank exists!");
					}
				}
			
		}
		return false;
	}
}
