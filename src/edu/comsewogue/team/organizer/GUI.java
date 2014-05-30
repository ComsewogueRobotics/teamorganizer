package edu.comsewogue.team.organizer;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame /*basic windows features like minimize*/{
	private static final long serialVersionUID = 1L;
	private JFrame f;
	private JPanel p;
	private JButton b1;
	private JLabel lab;
	
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
		p = new JPanel();
		p.setBackground(Color.BLUE);//can be changed
		b1 = new JButton("TestButton");
		lab = new JLabel("TestLabel");
		p.add(b1);
		p.add(lab);
		f.add(p);
	}
}
