package main;

import javax.swing.JOptionPane;

public class ProviderMenuControl {
	
	/**
	 * Uses a GUI to print the provider directory to the screen.
	 */
	public static void printProviderDirectory() {
		int i = 0;
		Service currServ;			//holder service
		String printStr = "";

		printStr += "Services:\n";
		printStr += "name - code - fee\n\n";
		for(i = 0; i < Database.services.size(); ++i) {	//loops through services
			currServ = Database.services.get(i);				//service to be printed
			printStr += currServ.getName();			//adds name to string
			printStr += " - ";
			printStr += currServ.getCode();			//adds code to string
			printStr += " - ";
			printStr += currServ.getFee();				//adds fee to sting		
			printStr += "\n";								//adds new line to printStr
		}
		JOptionPane.showMessageDialog(null, printStr);				//sends string to UserInterface to be printed to screen
		return;
	}
	
	/**
	 * Simulates swiping a member's card by prompting for the member's ID and then returning
	 * whether the member is active, suspended/nonexistent.
	 * @param memNum The member's ID number.
	 * @return true=active, false=suspended/nonexistent
	 */
	public static boolean swipeCard(int memNum) {
		String s = JOptionPane.showInputDialog(null,"Enter member ID: ", "Swipe Card", JOptionPane.INFORMATION_MESSAGE);
		int x = Integer.parseInt(s);
		boolean success = false;
		boolean suspended = false;
		for (int i = 0; i < Database.members.size(); i++) {      //checks if member id exists
			if (Database.members.get(i).getId() == x) {
				success = true;
				if (Database.members.get(i).getStatus() == false) {
					suspended = true;
				}
				break;
			}
		}
		if (success == true && suspended == false) {                                      //member number exists
			memNum = x;
			JOptionPane.showMessageDialog(null, "Verified!");
			return true;
		} 
		else if (success == true && suspended == true){
			JOptionPane.showMessageDialog(null,"Member number is suspended");						//member number is suspended
			return false;
		}
		else {
			JOptionPane.showMessageDialog(null, "Member number does not exist");     //member number does not exist
			return false;
		}
	}
}
