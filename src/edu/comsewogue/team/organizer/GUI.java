package edu.comsewogue.team.organizer;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI extends JFrame /*basic windows features like minimize*/{
	private static final long serialVersionUID = 1L;
	private JFrame f;
	private JPanel p;
	private JButton bIN;
	private JButton bOUT;
	//private JButton bViewHours;
	private JButton bAdd;
	private JButton bRemove;
	//private JLabel lab;
	private JTextField txt;
	
	public static void main(String [] args)
	{
		new GUI();
	}
	
	public GUI ()
	{
		gui();
	}
	
	public void gui()
	{
		f = new JFrame("Team Organizer");
		f.setVisible(true);
		f.setSize(1000,800);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				Organizer.saveAllAndClose();
				System.exit(0);
			}
		});
		p = new JPanel(new GridBagLayout());
		p.setBackground(Color.BLUE);//can be changed
		
		bIN = new JButton("Sign In");
		bOUT = new JButton("Sign Out");//setsize
		bAdd = new JButton("Add Member");
		txt = new JTextField("Enter ID # here", 5);//parameter is size
		txt.setToolTipText("Student ID");
		GridBagConstraints c = new GridBagConstraints();
		p.add(txt);
		p.add(bIN);
		p.add(bOUT);
		bIN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Organizer.clockMemberIn(Integer.parseInt(txt.getText()));
				txt.setText("");
			}
		});
		bOUT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Organizer.clockMemberOut(Integer.parseInt(txt.getText()));
				txt.setText("");
			}
		});
		f.add(p);
		
		
	}
	
}

//import javax.swing.JTextField;
//JTextField numEnter = new JTextField("Enter ID # here", 20); 
//p.add(numEnter);
//private JTextField numEnter;
