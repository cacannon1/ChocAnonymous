package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Opened through a button in the Main Menu, the Manager Menu allows the manager to
 * generate the member, provider, summary, and EFT reports.
 * @author Caroline Cannon
 */
public class ManagerMenu {
	JFrame f = new JFrame();
	
	/**
	 * Opens the manager menu with options to generate member, provider, and summary reports.
	 */
	public ManagerMenu() {
		createGUI();
		addButtons();
	}
	
	/**
	 * Creates the GUI for the manager menu.
	 */
	public void createGUI() {
		f = new JFrame("Manager Menu");
		f.setSize(1500, 2000);
		f.setLayout(new GridLayout(3,2));
		f.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 
	        	 try {
					Close.save();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					JOptionPane.showMessageDialog(null, "File not found!");
				}
	        	 System.exit(0);
	         }        
	      });
	}
	
	/**
	 * Adds buttons the manager menu's GUI.
	 */
	public void addButtons() {
		JButton a = new JButton("Generate Member Report");
		JButton b = new JButton("Generate Provider Report");
		JButton c = new JButton("Generate Summary Report");
		JButton d = new JButton("Generate EFT Report");
		JButton e = new JButton("Run Main Accounting Procedure");
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ManagerMenuControl.generateMemberReport();
					JOptionPane.showMessageDialog(null, "Member Report Created!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error! Bad Input!");
				}
			}
		});
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ManagerMenuControl.generateProviderReport();
					JOptionPane.showMessageDialog(null, "Provider Report Created!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error! Bad Input!");
				}
			}
		});
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ManagerMenuControl.generateSummaryReport();
					JOptionPane.showMessageDialog(null, "Summary Report Created!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error! Bad input!");
				}
			}
		});
		d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ManagerMenuControl.generateEFTReport();
					JOptionPane.showMessageDialog(null, "EFT Report Created!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error! Bad input!");
				}
			}
		});
		e.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ManagerMenuControl.runMainAccProc();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error! Bad input!");
				}
			}
		});
		f.add(a);
		f.add(b);
		f.add(c);
		f.add(d);
		f.add(e);
		f.setVisible(true);
	}
}