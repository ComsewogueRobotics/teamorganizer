package edu.comsewogue.team.organizer;
/*
*	 Copyright 2014 Andrew Jaffie
*	 This file is part of Team Organizer.
*
*    Team Organizer is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    Team Organizer is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with Team Organizer.  If not, see <http://www.gnu.org/licenses/>.
*/
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
