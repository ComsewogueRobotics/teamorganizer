package edu.comsewogue.team.organizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JLabel idLabel;
	private JTextField txt;
	private Image background;
	
	public static void main(String [] args)
	{
		new GUI();
	}
	
	public GUI ()
	{
		gui();
	}
	
	@SuppressWarnings("serial")
	public void gui()
	{
		f = new JFrame("Team Organizer: "+Organizer.getTeamName());
		f.setVisible(true);
		f.setSize(1000,800);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				Organizer.saveAllAndClose();
				System.exit(0);
			}
		});
		
		
		try{
			background = ImageIO.read(new File(Organizer.getBackgroundPath()));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		p = new JPanel(new GridBagLayout()){
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(background, 0, 0, null);
			}
			@Override
			public Dimension getPreferredSize(){
				return new Dimension(1000,800);
			}
		};
		p.setBackground(Color.BLUE);//can be changed
		
		idLabel = new JLabel("Enter ID number here:");
		bIN = new JButton("Sign In");
		bOUT = new JButton("Sign Out");
		bAdd = new JButton("Add Member");
		txt = new JTextField("", 5);//parameter is size
		txt.setToolTipText("Student ID");
		//GridBagConstraints c = new GridBagConstraints();
		p.add(idLabel);
		p.add(txt);
		p.add(bIN);
		p.add(bOUT);
		bIN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Organizer.clockInMember(Integer.parseInt(txt.getText()));
				txt.setText("");
			}
		});
		bOUT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Organizer.clockOutMember(Integer.parseInt(txt.getText()));
				txt.setText("");
			}
		});
		f.add(p);
		f.pack();
		f.setVisible(true);
		
	}
	
}