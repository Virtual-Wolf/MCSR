package com.beastmc.MCSR.main.Utils;

public class Mute {
	public Mute(long end, String muted, String muter, String reason) {
		this.end = end;
		this.muted = muted;
		this.muter = muter;
		this.reason = reason;
	}
	public long end;
	
	public String muted;
	public String muter;
	
	public String reason;
}
