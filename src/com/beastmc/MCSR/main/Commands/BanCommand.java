package com.beastmc.MCSR.main.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.beastmc.MCSR.main.MCSR;
import com.beastmc.MCSR.main.Punishments;
import com.beastmc.MCSR.main.Rank;

public class BanCommand implements CommandExecutor {
	public BanCommand(MCSR plugin) {
		plugin.getCommand("tempban").setExecutor(this);
		plugin.getCommand("mute").setExecutor(this);
		plugin.getCommand("pardon").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(Rank.getPlayersRank(p.getName()).permLevel >=7 && args.length >= 4) {
				String arg = "";
				for(int i = 3; i<args.length; i++) {
					arg = arg + args[i] + " ";
				}
				if(cmd.getName().equalsIgnoreCase("tempban")) {
					Bukkit.broadcastMessage("-¤6¤lMCSR¤r-    " + args[0] + " has been temp-banned by "  + p.getName() + " for "  + args[1] + " " + args[2] + ". This was for " + arg);
					Punishments.TempBan(args[0], arg, p.getName(), Integer.valueOf(args[1]), args[2]);
				}
				if(cmd.getName().equalsIgnoreCase("mute")) {
					Bukkit.broadcastMessage("-¤6¤lMCSR¤r-    " + args[0] + " has been muted for "  + args[1] + " " + args[2] + ". This was for " + arg);
					Punishments.Mute(args[0], arg, p.getName(), Integer.valueOf(args[1]), args[2]);
				}
			} else {
				return false;
			}
			if(cmd.getName().equalsIgnoreCase("pardon") && Rank.getPlayersRank(p.getName()).permLevel >= 8) {
				Punishments.endMute(args[0]);
				Punishments.endTempBan(args[0]);
			}
		}
		return false;
	}
	
}
