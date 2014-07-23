package com.beastmc.MCSR.main;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Rank extends Object{
	
	public static ArrayList<Rank> Ranks = new ArrayList<Rank>();
	public Rank(String name, String prefix, int permLevel) {
		this.name = name;
		this.prefix = prefix;
		this.permLevel = permLevel;
	}
	public String prefix;
	public String name;
	
	public int permLevel;
	
	public static Rank getPlayersRank(String s) {
		return getRank(MCSR.getString("Ranks." + s));
	}
	
	public static Rank getRank(String s) {
		for(Rank r : Ranks) {
			if(r.name.equalsIgnoreCase(s))
				return r;
		}
		return null;
	}
	
	public static void setPlayersRank(String s, String rank) {
		MCSR.saveString("Ranks." + s, rank);
		MCSR.save();
	}
	
	public static void setPlayersPrefix(String s, String prefix) {
		MCSR.saveString("Prefixes." + s, prefix);
		MCSR.save();
	}
	
	public static String getPlayersPrefix(String s) {
		return MCSR.getString("Prefixes." + s);
	}
	
	public static String getPlayersName(String s) {
		String Name = "";
		if(getPlayersRank(s) != null && getPlayersPrefix(s) == null) {
			Name += getPlayersRank(s).prefix;
		}
		if(getPlayersPrefix(s) != null) {
			if(getPlayersRank(s) != null) {
				Name += getPlayersRank(s).prefix + " ";
			}
			Name += getPlayersPrefix(s);
		}
		return Name;
	}
	
	public static void setUp() {
		Ranks.clear();
		Ranks.add(new Rank("Owner", "¤4O¤6w¤en¤ae¤br ¤r", 10));
		Ranks.add(new Rank("Developer", "¤cDeveloper ¤r", 10));
		Ranks.add(new Rank("Admin", "¤1Admin ¤r", 10));
		Ranks.add(new Rank("SrMod", "¤2Sr. Moderator ¤r", 9));
		Ranks.add(new Rank("Moderator", "¤3Moderator ¤r", 8));
		Ranks.add(new Rank("JrMod", "¤5Jr. Moderator", 7));
		Ranks.add(new Rank("Builder", "¤aBuilder ¤r", 6));
		Ranks.add(new Rank("Donator", "¤9Donator ¤r", 2));
		Ranks.add(new Rank("Default", "", 1));
	}
}
