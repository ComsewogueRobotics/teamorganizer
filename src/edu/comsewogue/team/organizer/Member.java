package edu.comsewogue.team.organizer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



public class Member implements Serializable {
	
	public static enum Subteam{
		CONTROL("Control", 0), BUILD("Build", 1), PNEUMATICS("Pneumatics", 2), SCOUT("Scouting", 3);
		private final int ID;
		private final String name;
		private Subteam(String teamName, int id){
			ID = id;
			name = teamName;
		}
		public int getID(){ return ID; }
		public String getName(){ return name; }
		
	}
	private static final long serialVersionUID = 1L;
	private String name;
	private Time time;
	private int id;
	private Time clockedIn;
	private Subteam subteam;
	/**
	 * 
	 * @param n the name of the team member
	 * @param id the id number of the team member
	 */
	public Member(String n, int id, Subteam team){
		name = n;
		time = new Time();
		this.id = id;
		subteam = team;
	}
	public Subteam getSubteam(){
		return subteam;
	}
	public boolean isClockedIn(){
		return clockedIn != null;
	}
	public int getID(){
		return id;
	}
	public String getName(){
		return name;
	}
	public Time getTime(){
		return time;
	}
	public void clockIn(){
		clockedIn = new Time(System.currentTimeMillis());
	}
	public Time clockOut(){
		Time t = new Time(System.currentTimeMillis()-clockedIn.getTotalMillis());
		time.addTime(t);
		clockedIn = null;
		return t;
	}
	public void save(){
		if(!isClockedIn()){
			try{
				FileOutputStream fileOut = new FileOutputStream(Organizer.DATA_PATH+getID()+".dat");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(this);
				out.close();
				fileOut.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			clockOut();
			save();
		}
	}
	
	
	
}
