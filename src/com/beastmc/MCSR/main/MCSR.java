package com.beastmc.MCSR.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.beastmc.MCSR.main.Commands.Commands;
import com.beastmc.MCSR.main.Listeners.Listeners;

public class MCSR extends JavaPlugin {
	
	public static FileConfiguration config;
	
	public void onEnable() {
		new Commands(this);
		new Listeners(this);
		Rank.setUp();
		this.getConfig();
		config = this.getConfig();
		load();
	}
	
	public void onDisable() {
		save();
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
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
