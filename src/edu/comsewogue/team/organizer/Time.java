package edu.comsewogue.team.organizer;

import java.io.Serializable;

public class Time implements Serializable {
	private static final long serialVersionUID = 1L;
	private long millis;
	public Time(){
		millis = 0;
	}
	public Time(long timeInMillis){
		millis = timeInMillis;
	}

	public long getTotalMillis(){
		return millis;
	}
	
	public String toString(){
		return "Days: "+getDays()+"; Hours: "+getHours()+"; Minutes: "+getMinutes()
				+"; Seconds: "+getSeconds()+"; Milliseconds: "+getMillis();
	}
	public double getTotalHours(){
		return millis/3600000.0;
	}
	public int getDays(){
		return (int)(millis/86400000);
	}
	public int getHours(){
		return (int)(millis/3600000)-(getDays()*24);
	}
	public int getMinutes(){
		return (int)(millis/60000)-(getDays()*1440)-(getHours()*60);
	}
	public int getSeconds(){
		return (int)(millis/1000)-(getDays()*86400)-(getHours()*3600)-(getMinutes()*60); 
	}
	public long getMillis(){
		return millis-(getDays()*86400000)-(getHours()*3600000)-(getMinutes()*60000)-(getSeconds()*1000);
	}
	public void addTime(long m){
		millis+=m;
	}
	public void addTime(Time t){
		millis+=t.getTotalMillis();
	}
}