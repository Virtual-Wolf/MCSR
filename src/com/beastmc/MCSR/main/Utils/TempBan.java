package com.beastmc.MCSR.main.Utils;

public class TempBan extends Object {
	public TempBan(long end, String banned, String banner, String reason) {
		this.end = end;
		this.banned = banned;
		this.banner = banner;
		this.reason = reason;
	}
	public long end;
	
	public String banned;
	public String banner;
	
	public String reason;
}
