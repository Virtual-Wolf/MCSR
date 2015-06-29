package com.beastmc.MCSR.main;

import java.util.ArrayList;

public class Rank extends Object{
	
	public static ArrayList<Rank> Ranks = new ArrayList<Rank>();
	public Rank(String name, String prefix, String tag, int permLevel) {
		this.tag = tag;
		this.name = name;
		this.prefix = prefix;
		this.permLevel = permLevel;
	}
	public String prefix;
	public String name;
	
	public String tag;
	
	public int permLevel;
	
	public static Rank getPlayersRank(String s) {
		
		return getRank(MCSR.getString("Ranks." + MCSR.getPlayersUUID(s)));
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
		Ranks.add(new Rank("Owner", "¤4O¤6w¤en¤ae¤br ¤r", "¤4", 10));
		Ranks.add(new Rank("HeadDev", "¤cHead Dev ¤r", "¤c", 10));
		Ranks.add(new Rank("Admin", "¤1Admin ¤r", "¤1", 10));
		Ranks.add(new Rank("SrMod", "¤2Sr. Moderator ¤r", "¤2", 9));
		Ranks.add(new Rank("Developer", "¤cDeveloper ¤r", "¤c", 8));
		Ranks.add(new Rank("Moderator", "¤3Moderator ¤r","¤3", 8));
		Ranks.add(new Rank("JrMod", "¤5Jr. Moderator ¤r", "¤5", 7));
		Ranks.add(new Rank("Builder", "¤8Builder ¤r", "¤8", 6));
		Ranks.add(new Rank("Ghast", "¤rGhast ¤r", "¤r", 1));
		Ranks.add(new Rank("Pigman", "¤dPigman ¤r", "¤2", 4));
		Ranks.add(new Rank("Creeper", "¤aCreeper ¤r", "¤a", 3));
		Ranks.add(new Rank("Skeleton", "¤7Skeleton ¤r", "¤7", 2));
		Ranks.add(new Rank("Zombie", "¤2Zombie ¤r", "¤2", 1));
		Ranks.add(new Rank("Default", "¤r","", 0));
	}
}
