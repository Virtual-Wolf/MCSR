package com.beastmc.MCSR.main.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.beastmc.MCSR.main.MCSR;
import com.beastmc.MCSR.main.Rank;
import com.beastmc.MCSR.main.Listeners.TrailListener;

public class TrailCommand implements CommandExecutor {
	
	public TrailCommand(MCSR plugin) {
		plugin.getCommand("trail").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(Rank.getPlayersRank(p.getName()).permLevel >= 2) {
				if(TrailListener.trailOn.contains(p.getName())) {
					TrailListener.trailOn.remove(p.getName());
					p.sendMessage("¤8¤lTrail effect turned ¤c¤lOFF¤8¤l!");
				} else {
					TrailListener.trailOn.add(p.getName());
					p.sendMessage("¤8¤lTrail effect turned ¤a¤lON¤8¤l!");
				}
			} else {
				p.sendMessage("¤8¤lBuy ¤1¤lDONATOR¤8¤l at ¤6¤lhttp://INSERT WEBSITE ADDRESS ¤8¤lto use this command!");
				return false;
			}
		} else {
			return false;
		}
		return false;
	}
}
