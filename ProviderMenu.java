package main;

import java.awt.*; 
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.*;   

/**
 * Opened through a button in the Main Menu, the Provider Menu allows the provider to
 * view the provider directory and create a new visit.
 * @author Kinsey Callans
 */
public class ProviderMenu {
	
	static JFrame f;
	static int memNum;
	
	/**
	 * Opens the provider menu with options to view the provider directory and create a new visit.
	 */
	public ProviderMenu() {
		createGUI();
		addButtons();
	}
	
	/**
	 * Creates the GUI for the provider menu.
	 */
	public void createGUI() {
		f = new JFrame("Provider Menu");
		f.setSize(1500, 2000);
		f.setLayout(new GridLayout(2,1));
		f.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 
	        	 try {
					Close.save();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					System.out.println("Sad sad");
					e.printStackTrace();
				}
				
	        	 System.exit(0);
	         }        
	      });
	}
	
	/**
	 * Adds buttons the provider menu's GUI.
	 */
	public void addButtons() {
		JButton a = new JButton("Open Provider Directory");
		JButton b = new JButton("Create Visit");
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProviderMenuControl.printProviderDirectory();
			}
		});
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				boolean valid = ProviderMenuControl.swipeCard(memNum);
				if(valid) {
					Visit v = new Visit(memNum, MainMenu.provNum);
					Database.visits.add(v);
				}
			}
		});
		f.add(a);
		f.add(b);
		f.setVisible(true);
	}
	
}