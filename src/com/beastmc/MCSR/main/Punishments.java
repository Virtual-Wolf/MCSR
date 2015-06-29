package com.beastmc.MCSR.main;

import org.bukkit.Bukkit;

import com.beastmc.MCSR.main.Utils.TempBan;
import com.beastmc.MCSR.main.Utils.PunishmentUnit;
import com.beastmc.MCSR.main.Utils.Mute;

public class Punishments {

	public static void Ban(String s, String reason, String banner) {
		
	}
	
	public static void TempBan(final String s, String reason, String banner, int amount, String unit) {
		long now = System.currentTimeMillis();
		long endOfBan = now + PunishmentUnit.getTicks(unit, amount);
		MCSR.config.set("TempBans." + s + ".reason", reason);
		MCSR.config.set("TempBans." + s + ".banner", banner);
		MCSR.config.set("TempBans." + s + ".endOfBan", endOfBan);
		
		MCSR.save();	
		Bukkit.getScheduler().runTaskLater(MCSR.inst, new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				TempBan tb = Punishments.getTempBan(s);
				long diff = tb.end - System.currentTimeMillis();
				if(diff<=0){
					Punishments.endTempBan(s);
				}else{
					if(Bukkit.getPlayer(s) != null) {
						Bukkit.getPlayer(s).kickPlayer("-¤6¤lMCSR¤r-\n You have been ¤btemporarily banned¤r by " + tb.banner + "\n For: " + tb.reason + "\n This ban will last for:\n" + Punishments.getMSGTime(tb.end));
					}
				}
			}
		}, 5);
	}
	
	public static void Mute(String s, String reason, String banner, int amount, String unit) {
		long now = System.currentTimeMillis();
		long endOfBan = now + PunishmentUnit.getTicks(unit, amount);
		MCSR.config.set("Mutes." + s + ".reason", reason);
		MCSR.config.set("Mutes." + s + ".banner", banner);
		MCSR.config.set("Mutes." + s + ".endOfBan", endOfBan);
		MCSR.config.set("History." + s + ".Mutes." + MCSR.config.getInt("History." + s + ".TempBans.amount") + 1, "¤cMute¤r: ¤eMuter¤r: " + banner + ", ¤eReason¤r: " + reason + ", ¤eamount¤r: " + amount + " " + unit);
		MCSR.save();	
	}
	
	public static TempBan getTempBan(String player) {
		String reason = MCSR.config.getString("TempBans." + player + ".reason");
		if(reason != null) {
			String banner = MCSR.config.getString("TempBans." + player + ".banner");
			long endOfBan = MCSR.config.getLong("TempBans." + player + ".endOfBan");
			return new TempBan(endOfBan, player, banner, reason);
		}else {
			return null;
		} 
	}
	
	public static void endTempBan(String player) {
		MCSR.config.set("TempBan." + player + ". reason", null);
		MCSR.save();
	}
	
	public static Mute getMute(String player) {
		String reason = MCSR.config.getString("Mutes." + player + ".reason");
		if(reason != null) {
			String banner = MCSR.config.getString("Mutes." + player + ".banner");
			long endOfBan = MCSR.config.getLong("Mutes." + player + ".endOfBan");
			return new Mute(endOfBan, player, banner, reason);
		}else {
			return null;
		} 
	}
	public static void endMute(String player) {
		MCSR.config.set("Mutes." + player + ". reason", null);
		MCSR.save();
	}
	
	
	
	public static String getMSGTime(long endOfBan){
		String message = "";

		long now = System.currentTimeMillis();
		long diff = endOfBan - now;
		int seconds = (int) (diff / 1000);				

		if(seconds >= 60*60*24){
			int days = seconds / (60*60*24);
			seconds = seconds % (60*60*24);

			message += days + " ¤eDay(s)\n";
		}
		if(seconds >= 60*60){
			int hours = seconds / (60*60);
			seconds = seconds % (60*60);

			message += hours + " ¤eHour(s)\n";
		}	
		if(seconds >= 60){
			int min = seconds / 60;
			seconds = seconds % 60;

			message += min + " ¤eMinute(s)¤r\n";
		}	
		if(seconds >= 0){
			message += seconds + " ¤eSecond(s)\n";
		}	

		return message;
	}
	
	public static String getHistory(String s) {
		String i = "01";
		int i2 = 1;
		boolean cancel = false;
		String Message = "[¤2History¤r] " + s +"¤8:¤r";
		while(!cancel) {
			if(MCSR.config.get("History." + s + ".TempBans." + i) == null && MCSR.config.get("History." + s + ".Mutes." + i) == null) { 
				cancel = true;
			} else {
				Message += MCSR.config.get("History." + s + ".TempBans." + i) + "\n";
				Message += MCSR.config.get("History." + s + ".Mutes." + i) + "\n";
				i2++;
				i = "0" + String.valueOf(i2);
			}
		}
		return Message;
	}

}
