package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

/**
 * Saves all the information in the Database to text files.
 * @author Edward Liccardo
 */
public class Close {
	
	/**
	 * Saves the information from the program into files.
	 * @throws FileNotFoundException The file does not exist.
	 * @throws UnsupportedEncodingException The encoding is unsupported.
	 */
	public static void save() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("src/members.txt", "UTF-8");			//Write members.txt
		for(int i=0; i<Database.members.size(); i++) {
			User mem = Database.members.get(i);
			writer.println(mem.getName());
			writer.println(mem.getAddr());
			writer.println(mem.getCity());
			writer.println(mem.getState());
			writer.println(mem.getZip());
			writer.println(mem.getId());
		}
		writer.close();
		writer = new PrintWriter("src/providers.txt", "UTF-8");						//Write providers.txt
		for(int i=0; i<Database.providers.size(); i++) {
			User prov = Database.providers.get(i);
			writer.println(prov.getName());
			writer.println(prov.getAddr());
			writer.println(prov.getCity());
			writer.println(prov.getState());
			writer.println(prov.getZip());
			writer.println(prov.getId());
		}
		writer.close();
		writer = new PrintWriter("src/visits.txt", "UTF-8");						//Write visits.txt
		for(int i=0; i<Database.visits.size(); i++) {
			Visit vis = Database.visits.get(i);
			writer.println(vis.getComments());
			writer.println(vis.getDate());
			writer.println(vis.getTime());
			writer.println(vis.getService().getCode());
			writer.println(vis.getMember().getId());
			writer.println(vis.getProvider().getId());
		}
		writer.close();
		JOptionPane.showMessageDialog(null, "Saved!");
	}
}
