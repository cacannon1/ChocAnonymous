package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OperatorMenuControl {
	
	/**
	 * Opens the manage provider menu with options to add, edit, or delete the provider.
	 * @param f The JFrame to use for the GUI.
	 */
	public static void openManagePMenu(JFrame f) {
		f.setLayout(new GridLayout(3,1));
		f.setTitle("Manage Provider");
		JButton a = new JButton("Add Provider");
		JButton b = new JButton("Edit Provider");
		JButton c = new JButton("Delete Provider");
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				addProvider();
			}
		});
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				editProvider();
			}
		});
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				deleteProvider();
			}
		});
		f.add(a);
		f.add(b);
		f.add(c);
		f.setVisible(true);
	}
	
	/**
	 * Opens the manage member menu with options to add, edit, and delete the member.
	 * @param f The JFrame to use for the GUI.
	 */
	public static void openManageMMenu(JFrame f) {
		f.setLayout(new GridLayout(3,1));
		f.setTitle("Manage Member");
		JButton a = new JButton("Add Member");
		JButton b = new JButton("Edit Member");
		JButton c = new JButton("Delete Member");
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMember();
			}
		});
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editMember();
			}
		});
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMember();
			}
		});
		f.add(a);
		f.add(b);
		f.add(c);
		f.setVisible(true);
	}
	
	/**
	 * Prompts the user for the new member's information and then adds them to the database.
	 */
	public static void addMember() {
		String name = JOptionPane.showInputDialog(null, "Member Name: ", "Add Member", JOptionPane.INFORMATION_MESSAGE);
		String addr = JOptionPane.showInputDialog(null, "Address: ", "Add Member", JOptionPane.INFORMATION_MESSAGE);
		String city = JOptionPane.showInputDialog(null, "City: ", "Add Member", JOptionPane.INFORMATION_MESSAGE);
		String state = JOptionPane.showInputDialog(null, "State: ", "Add Member", JOptionPane.INFORMATION_MESSAGE);
		String zip = JOptionPane.showInputDialog(null, "ZIP Code: ", "Add Member", JOptionPane.INFORMATION_MESSAGE);
		User newU = new User(name, addr, city, state, zip, true);
		Database.members.add(newU);
		JOptionPane.showMessageDialog(null, "Member Added!");
	}
	
	/**
	 * Prompts the user for the member's ID and the information to be changed and then changes the member's info in the database.
	 */
	public static void editMember() {
		User mem = new User();
		boolean found = false;
		while(!found) {
			String x = JOptionPane.showInputDialog(null, "What is the member ID that you would like to edit?", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
			int y = Integer.parseInt(x);
			for(int i=0; i<Database.members.size(); i++) {
				if(Database.members.get(i).id == y) {
					mem = Database.members.get(i);
					found = true;
				}
			}
			if(found == false) {
				JOptionPane.showMessageDialog(null,"Error! Member not found!");
			}
		}
		
		boolean done = false;
		boolean err = false;
		while(!done) {
			String s = JOptionPane.showInputDialog(null, "What would you like to edit? (name, addr, city, zip, st, status)", "Edit Member", JOptionPane.INFORMATION_MESSAGE);

			if(s.equals("name")) {
				String n = JOptionPane.showInputDialog(null, "What is the new name?", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
				mem.setName(n);
			}
			else if(s.equals("addr")) {
				String n = JOptionPane.showInputDialog(null, "What is the new address?", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
				mem.setAddr(n);
			}
			else if(s.equals("city")) {
				String n = JOptionPane.showInputDialog(null, "What is the new city?", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
				mem.setCity(n);
			}
			else if(s.equals("zip")) {
				String n = JOptionPane.showInputDialog(null, "What is the new zip code?", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
				mem.setZip(n);
			}
			else if(s.equals("st")) {
				String n = JOptionPane.showInputDialog(null, "What is the new state?", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
				mem.setState(n);
			}
			else if(s.equals("status")) {
				boolean x = true;
				while(x) {
					String n = JOptionPane.showInputDialog(null, "What is the new status? (active or suspended)", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
					if(n.equals("active")) {
						mem.setStatus(true);
						x = false;
						done = true;
					}
					else if(n.equals("suspended")) {
						mem.setStatus(false);
						x = false;
						done = true;
					}
					else {
						JOptionPane.showMessageDialog(null, "Error! Invalid input!");
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Error! Invalid input!");
				err = true;
			}
			if(err == false) {
				int input = JOptionPane.showConfirmDialog(null, "Are you done editing "+mem.getName()+"?", "Are you done?", JOptionPane.YES_NO_OPTION);
				if(input == 0)
					done = true;
			}
		}
		
	}
	
	/**
	 * Prompts the user for the member to be deleted and prompts with an "Are you sure?". 
	 * If yes, removes the member from the database.
	 */
	public static void deleteMember() {
		User mem = new User();
		boolean found = false;
		while(!found) {
			String x = JOptionPane.showInputDialog(null, "What is the member ID that you would like to delete?", "Delete Member", JOptionPane.INFORMATION_MESSAGE);
			int y = Integer.parseInt(x);
			for(int i=0; i<Database.members.size(); i++) {
				if(Database.members.get(i).id == y) {
					mem = Database.members.get(i);
					found = true;
				}
			}
			if(found == false) {
				JOptionPane.showMessageDialog(null,"Error! Member not found!");
			}
		}
		
		int c = JOptionPane.showConfirmDialog(null, "Member Name: " + mem.name+"\n"+"Member ID: "+mem.id+"\n"+"Are you sure you want to delete this member?");
		if(c == 0) {
			Database.members.remove(mem);
		}
	}
	
	/**
	 * Prompts the user for the new provider's information and then adds them to the database.
	 */
	public static void addProvider() {
		String name = JOptionPane.showInputDialog(null, "Provider Name: ", "Add Provider", JOptionPane.INFORMATION_MESSAGE);
		String addr = JOptionPane.showInputDialog(null, "Address: ", "Add Provider", JOptionPane.INFORMATION_MESSAGE);
		String city = JOptionPane.showInputDialog(null, "City: ", "Add Provider", JOptionPane.INFORMATION_MESSAGE);
		String state = JOptionPane.showInputDialog(null, "State: ", "Add Provider", JOptionPane.INFORMATION_MESSAGE);
		String zip = JOptionPane.showInputDialog(null, "ZIP Code: ", "Add Provider", JOptionPane.INFORMATION_MESSAGE);
		User newP = new User(name, addr, city, state, zip, false);
		Database.providers.add(newP);
		JOptionPane.showMessageDialog(null, "Provider Added!");
	}
	
	/**
	 * Prompts the user for the provider's ID and the information to be changed and then changes the provider's info in the database.
	 */
	public static void editProvider() {
		User prov = new User();
		boolean found = false;
		while(!found) {
			String x = JOptionPane.showInputDialog(null, "What is the provider ID that you would like to edit?", "Edit Provider", JOptionPane.INFORMATION_MESSAGE);
			int y = Integer.parseInt(x);
			for(int i=0; i<Database.providers.size(); i++) {
				if(Database.providers.get(i).getId() == y) {
					prov = Database.providers.get(i);
					found = true;
				}
			}
			if(found == false) {
				JOptionPane.showMessageDialog(null,"Error! Provider not found!");
			}
		}
		
		boolean done = false;
		boolean err = false;
		while(!done) {
			String s = JOptionPane.showInputDialog(null, "What would you like to edit? (name, addr, city, zip, st)", "Edit Provider", JOptionPane.INFORMATION_MESSAGE);

			if(s.equals("name")) {
				String n = JOptionPane.showInputDialog(null, "What is the new name?", "Edit Provider", JOptionPane.INFORMATION_MESSAGE);
				prov.setName(n);
			}
			else if(s.equals("addr")) {
				String n = JOptionPane.showInputDialog(null, "What is the new address?", "Edit Provider", JOptionPane.INFORMATION_MESSAGE);
				prov.setAddr(n);
			}
			else if(s.equals("city")) {
				String n = JOptionPane.showInputDialog(null, "What is the new city?", "Edit Provider", JOptionPane.INFORMATION_MESSAGE);
				prov.setCity(n);
			}
			else if(s.equals("zip")) {
				String n = JOptionPane.showInputDialog(null, "What is the new zip code?", "Edit Provider", JOptionPane.INFORMATION_MESSAGE);
				prov.setZip(n);
			}
			else if(s.equals("st")) {
				String n = JOptionPane.showInputDialog(null, "What is the new state?", "Edit Provider", JOptionPane.INFORMATION_MESSAGE);
				prov.setState(n);
			}
			else {
				JOptionPane.showMessageDialog(null, "Error! Invalid input!");
				err = true;
			}
			if(err == false) {
				int input = JOptionPane.showConfirmDialog(null, "Are you done editing "+prov.getName()+"?", "Are you done?", JOptionPane.YES_NO_OPTION);
				if(input == 0)
					done = true;
			}
		}
	}
	
	/**
	 * Prompts the user for the ID of the provider to be deleted and then prompts with an "Are you sure?". 
	 * If yes, removes the provider from the database.
	 */
	public static void deleteProvider() {
		User prov = new User();
		boolean found = false;
		while(!found) {
			String x = JOptionPane.showInputDialog(null, "What is the provider ID that you would like to delete?", "Delete Provider", JOptionPane.INFORMATION_MESSAGE);
			int y = Integer.parseInt(x);
			for(int i=0; i<Database.providers.size(); i++) {
				if(Database.providers.get(i).getId() == y) {
					prov = Database.providers.get(i);
					found = true;
				}
			}
			if(found == false) {
				JOptionPane.showMessageDialog(null,"Error! Provider not found!");
			}
		}
		
		int c = JOptionPane.showConfirmDialog(null, "Provider Name: " + prov.name+"\n"+"Member ID: "+prov.id+"\n"+"Are you sure you want to delete this provider?");
		if(c == 0) {
			Database.providers.remove(prov);
		}
	}
}
