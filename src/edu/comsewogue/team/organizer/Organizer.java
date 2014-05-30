package edu.comsewogue.team.organizer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Organizer {
	private static final String LIST_PATH = "/data/list.dat";
	private static ArrayList<Integer> members;
	private static ArrayList<Member> loaded = new ArrayList<Member>();

	
	public static void main(String[] args){
		//TODO: GUI stuff
		loadList();
		//new GUI();
	}
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
				fileIn = new FileInputStream("/data/"+i+".dat");
				in = new ObjectInputStream(fileIn);
				loaded.add((Member) in.readObject());
				in.close();
				fileIn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
