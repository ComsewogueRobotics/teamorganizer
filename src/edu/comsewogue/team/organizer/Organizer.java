package edu.comsewogue.team.organizer;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import edu.comsewogue.team.organizer.Member.Subteam;

public class Organizer {
	private static final String LIST_PATH = getDir()+"/data/list.dat";
	public static final String DATA_PATH = getDir()+"/data/";
	private static ArrayList<Integer> members;
	private static ArrayList<Member> loaded = new ArrayList<Member>();
	private static String teamName;
	private static String backgroundPath;
	private static GUI gui;

	
	public static void main(String[] args){
		try{
			FileInputStream fileIn = new FileInputStream("props.txt");
			Properties props = new Properties();
			props.load(fileIn);
			fileIn.close();
			teamName = props.getProperty("teamName");
			backgroundPath = props.getProperty("backgroundPath");
		}catch(FileNotFoundException e){
			System.out.println("Creating default properties file...");
			Properties props = new Properties();
			props.setProperty("backgroundPath", "/data/defaultBackground.jpg");
			props.setProperty("teamName", "Default Name");
			try{
				File newFile = new File("props.txt");
				newFile.createNewFile();
				FileOutputStream fileOut = new FileOutputStream("props.txt");
				props.store(fileOut, "Default properties file for Team Organizer");
				fileOut.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		gui = new GUI();
		loadList();
		gui.feed("Started");
	}
	public static String getTeamName(){ return teamName; }
	public static String getBackgroundPath(){ return getDir()+backgroundPath; }
	public static String getDir(){ return System.getProperty("user.dir"); }
	public static void saveAllAndClose(){
		for(Member m: loaded){
			m.save();
		}
		saveList();
	}
	@SuppressWarnings("unchecked")
	public static void loadList(){
		try{
			FileInputStream fileIn = new FileInputStream(LIST_PATH);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			members = (ArrayList<Integer>) in.readObject();
			in.close();
			fileIn.close();
		} catch(EOFException e){
			members = new ArrayList<Integer>();
			saveList();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void saveList(){
		try{
			FileOutputStream fileOut = new FileOutputStream(LIST_PATH);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(members);
			out.close();
			fileOut.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void loadAll(){
		try{
			FileInputStream fileIn;
			ObjectInputStream in;
			for(Integer i: members){
				fileIn = new FileInputStream(DATA_PATH+i+".dat");
				in = new ObjectInputStream(fileIn);
				loaded.add((Member) in.readObject());
				in.close();
				fileIn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public static void addMember(String name, int id, Subteam team){
		Member m = new Member(name, id, team);
		File newMember = new File(DATA_PATH+id+".dat");
		try{
			newMember.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
		m.save();
		members.add(m.getID());
		gui.feed("Member "+m.getName()+" added.");
	}
	public static Member getMember(int id){
		for(Member m: loaded){
			if(m.getID()==id)
				return m;
		}
		FileInputStream fileIn;
		ObjectInputStream in;
		try{
			fileIn = new FileInputStream(DATA_PATH+id+".dat");
			in = new ObjectInputStream(fileIn);
			Member m = (Member) in.readObject();
			in.close();
			fileIn.close();
			return m;
		}catch(Exception e){
			return null;
		}
		
	}
	public static void clockInMember(int id){
		Member m = getMember(id);
		if(m==null){
			GUI.error("Member not found. Have you added him/her?");
			return;
		}
		if(!loaded.contains(m))
			loaded.add(m);
		m.clockIn();
		gui.feed(m.getName()+" clocked in.");
	}
	public static void clockOutMember(int id){
		Member m = getMember(id);
		if(!loaded.contains(m)){
			GUI.error(m.getName()+" is not clocked in!");
			return;
		}
		loaded.remove(m);
		m.clockOut();
		m.save();
		gui.feed("Member "+m.getName()+" clocked out.");
	}
	public static void saveAllNotClockedIn(){
		for(int i = 0; i<loaded.size(); i++){
			Member m = loaded.get(i);
			if(!m.isClockedIn()){
				m.save();
				loaded.remove(i);
			}
		}
	}
	
}
