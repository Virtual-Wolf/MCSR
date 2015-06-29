package com.beastmc.MCSR.main;

import java.io.File;  
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.beastmc.MCSR.main.Commands.Commands;
import com.beastmc.MCSR.main.Listeners.Listeners;
import com.beastmc.MCSR.main.Utils.Scroller;

public class MCSR extends JavaPlugin {
	
	public static FileConfiguration config;
	
	public static Logger logger;
	
	public static MCSR inst;
	public static ScoreboardManager SMan;
	
	public void onEnable() {
		inst = this;
		SMan = Bukkit.getScoreboardManager();
		logger = this.getLogger();
		new Commands(this);
		new Listeners(this);
		Rank.setUp();
		this.getConfig();
		config = this.getConfig();
		load();
		ScoreboardInit();
	}
	
	public static Scoreboard board;
	
	public static void ScoreboardInit() {
		board = SMan.getNewScoreboard();
		final Objective objective = board.registerNewObjective("bar", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("- ¤6¤lMCSR¤r -");
		Score score =  objective.getScore(ChatColor.DARK_GREEN + "¤lServer");
		score.setScore(16);
		Score s2 = null;
		boolean scrolled = false;
		try{
			if(!(MCSR.getString("servername").length() > 16)) {
				s2 =objective.getScore(MCSR.getString("servername"));
			} else {
				final Scroller scroller = new Scroller(MCSR.getString("servername"), 16, 1, '¤');
				Score s4 = objective.getScore(scroller.next());
				s4.setScore(15);
				
				scrolled = true;
				
				new BukkitRunnable()
				{
				    
				    public void run()
				    {
				    	final String current = scroller.next();
				        final Score sr = objective.getScore(current);
				        sr.setScore(15);
				        
				        Bukkit.getScheduler().runTaskLater(MCSR.inst, new Runnable() {
				        	public void run() {
				        		sr.setScore(-1);
				        		board.resetScores(current);
				        	}
				        }, 3L);
				    }
				}.runTaskTimer(MCSR.inst, 0L, 3L);
			}
		} catch(Exception e) {
			s2 =objective.getScore("¤4Not found...");
		}
		Score sp1 = objective.getScore(" ");
		sp1.setScore(14);
	;if(!scrolled)
			s2.setScore(15);
		Score s3 = objective.getScore(ChatColor.YELLOW + "¤lPlayers");
		s3.setScore(13);
		String s = "";
		for(Player p : Bukkit.getOnlinePlayers()) {
			s += p.getName() + ", ";
		}
		final Scroller scroller = new Scroller(s, 16, 1, '¤');
		Score s4 = objective.getScore(scroller.next());
		s4.setScore(12);
		
		
		
		new BukkitRunnable()
		{
		    
		    public void run()
		    {
		    	final String current = scroller.next();
		        final Score sr = objective.getScore(current);
		        sr.setScore(12);
		        
		        Bukkit.getScheduler().runTaskLater(MCSR.inst, new Runnable() {
		        	public void run() {
		        		sr.setScore(-1);
		        		board.resetScores(current);
		        	}
		        }, 3L);
		    }
		}.runTaskTimer(MCSR.inst, 0L, 3L); // runs every 3 ticks
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(board);
		}
	}
	
	public void onDisable() {
		save();
	}
	
	public static void MessageToPermLevel(int i, String msg) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Rank.getPlayersRank(p.getName()).permLevel >= i) {
				p.sendMessage(msg);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String getPlayersUUID(String s) {
		if(Bukkit.getPlayer(s) != null) {
			return Bukkit.getPlayer(s).getUniqueId().toString();
		} else if(Bukkit.getOfflinePlayer(s) != null){
			return Bukkit.getOfflinePlayer(s).getUniqueId().toString();
		}
		return null;
	}
	
	public static String getUUIDsPlayer(String s) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getUniqueId().toString().equals(s)) {
				return p.getName();
			}
		}
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if(p.getUniqueId().toString().equals(s)) {
				return p.getName();
			}
		}
		return null;
	}
	
	public static boolean save() {
		try {
		config.save(new File("./plugins/MCSR/config.yml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		}
	
	public static void load() {
		try {
			config.load(new File("./plugins/MCSR/config.yml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveLocation(String path, Location loc) {
			config.set("Locations." + path + ".world", loc.getWorld().getName());
		   config.set("Locations." + path + ".x", loc.getX());
		   config.set("Locations." + path + ".y", loc.getY());
		   config.set("Locations." + path + ".z", loc.getZ());
		   save();
	}
	
	public static Location getLocation(String path) { 
		World w = Bukkit.getWorld(config.getString("Locations." + path + ".world"));
		int x = config.getInt("Locations." + path + ".x");
		int y = config.getInt("Locations." + path + ".y");
		int z = config.getInt("Locations." + path + ".z");
		return new Location(w, x, y, z);
	}
	
	public static void saveString(String path, String s) {
		config.set("Strings." + path, s);
		save();
	}
	public static String getString(String path) {
		return config.getString("Strings." + path);
	}
	
	public static void saveInt(String path, int i) {
		config.set("Integers." + path, i);
		save(); 
	}
	
	public static int getInt(String path) {
		return config.getInt("Integers." + path);
	}
	
	public static void saveDouble(String path, double d) {
		config.set("Doubles." + path, d);
		save();
	}
	public static double getDouble(String path) {
		return config.getDouble("Doubles." + path);
	}
}
