package main;

import java.awt.*; 
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.*;   

/**
 * Opened through a button in the Main Menu, the Operator Menu allows the operator
 * to add, edit, and delete members and providers. 
 * @author Edward Liccardo
 */
public class OperatorMenu {
	
	JFrame f = new JFrame("Operator Menu");
	
	/**
	 * Opens the operator menu with options to manage member and manage provider.
	 */
	public OperatorMenu() {
		createGUI(f, 2);
		addButtons();
	}
	
	/**
	 * Creates the GUI for the menu.
	 * @param f The JFrame to use for the GUI.
	 * @param x The number of buttons.
	 */
	public void createGUI(JFrame f, int x) {
		f.setSize(1500, 2000);
		f.setLayout(new GridLayout(x,1));
		f.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 
	        	 try {
					Close.save();
					System.exit(0);
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					System.out.println("Sad sad");
					e.printStackTrace();
				}
	         }        
	      });
	}
	
	/**
	 * Adds the buttons to the operator menu GUI.
	 */
	public void addButtons() {
		JButton a = new JButton("Manage Provider");
		JButton b = new JButton("Manage Member");
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				f.setVisible(false);
				f.remove(a);
				f.remove(b);
				OperatorMenuControl.openManagePMenu(f);
			}
		});
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				f.setVisible(false);
				f.remove(a);
				f.remove(b);
				OperatorMenuControl.openManageMMenu(f);
			}
		});
		f.add(a);
		f.add(b);
		f.setVisible(true);
	}
	
}