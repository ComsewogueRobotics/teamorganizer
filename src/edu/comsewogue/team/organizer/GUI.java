package edu.comsewogue.team.organizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.comsewogue.team.organizer.Member.Subteam;


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
	
	private JPanel addMemPanel;
	
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
		p.add(bAdd);
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
		bAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField name = new JTextField();
				JTextField id = new JTextField();
				addMemPanel = new JPanel(new GridLayout(3,3));
				addMemPanel.add(new JLabel("Name: "));
				addMemPanel.add(name);
				addMemPanel.add(Box.createHorizontalStrut(15)); //Spacer
				addMemPanel.add(new JLabel("ID Number: "));
				addMemPanel.add(id);
				addMemPanel.add(Box.createHorizontalStrut(15)); //Spacer
				addMemPanel.add(new JLabel("Subteam: "));
				Subteam[] subteams = {Subteam.CONTROL, Subteam.BUILD, Subteam.PNEUMATICS, Subteam.SCOUT};
				JComboBox<Subteam> subteamSelector = new JComboBox<Subteam>(subteams);
				addMemPanel.add(subteamSelector);
				 int result = JOptionPane.showConfirmDialog(null, addMemPanel, 
			               "Please Enter Name, ID, and Subteam", JOptionPane.OK_CANCEL_OPTION);
				 if(result==JOptionPane.OK_OPTION){
					 Subteam team = (Subteam)subteamSelector.getSelectedItem();
					 Organizer.addMember(name.getText(), Integer.parseInt(id.getText()), team);
				 }
			}
		});
		f.add(p);
		f.pack();
		f.setVisible(true);
		
	}
	public static void message(String m){
		JOptionPane.showMessageDialog(null, m, "Information", JOptionPane.PLAIN_MESSAGE);
	}
	public static void error(String m){
		JOptionPane.showMessageDialog(null, m, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
}


