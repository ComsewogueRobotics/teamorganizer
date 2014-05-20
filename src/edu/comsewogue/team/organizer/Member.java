package edu.comsewogue.team.organizer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Member implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Time time;
	private int id;
	private Time clockedIn;
	/**
	 * 
	 * @param n the name of the team member
	 * @param id the id number of the team member
	 */
	public Member(String n, int id){
		name = n;
		time = new Time();
		this.id = id;
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
		try{
			FileOutputStream fileOut = new FileOutputStream("/data/"+getID()+".dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
