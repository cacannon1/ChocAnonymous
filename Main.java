package main;

import java.io.FileNotFoundException;

/**
 * The Main class is run to open the main menu and begin the project/open the system.
 * @author Edward Liccardo
 */

public class Main {

	/**
	 * The main function
	 * @param args the command line arguments
	 * @throws FileNotFoundException The file cannot be found.
	 */
	public static void main(String[] args) throws FileNotFoundException{
		Database.createDatabase();
		new MainMenu();
	}
}
