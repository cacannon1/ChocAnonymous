package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainMenuControl {
	
	/**
	 * Prompts the provider for an ID and sends that to be checked by the database.
	 * If correct, opens the provider menu. Otherwise, shows an error.
	 * @param provNum The provider's ID number.
	 * @param f The JFrame window to use.
	 */
	public static void login(int provNum, JFrame f) {
		String s = JOptionPane.showInputDialog(null, "Provider ID: ", "Login", JOptionPane.INFORMATION_MESSAGE);
		int userId = Integer.parseInt(s);
		boolean a = providerLogin(userId);
		if(a) {
			JOptionPane.showMessageDialog(null,"Verified!");
			provNum = userId;
			openProviderMenu(f);
		}
		else
			JOptionPane.showMessageDialog(null, "Incorrect User ID");
	}
	
	/**
	 * 
	 * @param id The provider's ID number.
	 * @return true = verified, false = ID not found.
	 */
	public static boolean providerLogin(int id) {
		for(int i=0; i<Database.providers.size(); i++) {
			if(Database.providers.get(i).getId() == id)
				return true;
		}
		return false;
	}
	
	/**
	 * Opens the provider menu by creating a new instance of ProviderMenu().
	 * @param f The JFrame window to use.
	 */
	public static void openProviderMenu(JFrame f) {
		f.dispose();
		new ProviderMenu();
	}
	
	/**
	 * Opens the manager menu by creating a new instance of ManagerMenu().
	 * @param f The JFrame window to use.
	 */
	public static void openManagerMenu(JFrame f) {
		f.dispose();
		new ManagerMenu();
	}
	
	/**
	 * Opens the operator menu by creating a new instance of OperatorMenu().
	 * @param f The JFrame window to use.
	 */
	public static void openOperatorMenu(JFrame f) {
		f.dispose();
		new OperatorMenu();
	}
}
