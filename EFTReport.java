package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The EFT Report for a provider contains the provider's name, number and their total fees for the week.
 * @author Kinsey Callans
 */
public class EFTReport {
	
	/**
	 * Generates the EFT Report for the given provider.
	 * @param id The provider's ID.
	 * @throws IOException Invalid input.
	 */
	public static void produceReport(int id) throws IOException {
		String name = getName(id);
		File providerReport = new File("src/Reports/EFT Reports");					//"Provider name - current date(MM-DD-YYYY)"
		providerReport.mkdirs();
		providerReport = new File("src/Reports/EFT Reports/"+name);	
		providerReport.createNewFile();
		for(int i=0; i<Database.providers.size(); i++) {
			if(Database.providers.get(i).getId() == id) {
				User p = Database.providers.get(i);
				PrintWriter writer = new PrintWriter("src/Reports/EFT Reports/"+name, "UTF-8");
				writer.println("Provider Name: "+ p.getName());
				writer.println("Provider ID: "+ p.getId());
				writer.println("Total Fees: "+ p.getTotalFee());
				writer.close();
			}
		}
	}
	
	/**
	 * Gets the provider's name in order to name the file.
	 * @param id The provider's ID number.
	 * @return The provider's name.
	 */
	public static String getName(int id) {
		for(int i=0; i<Database.providers.size(); i++) {
			if(Database.providers.get(i).getId() == id)
				return Database.providers.get(i).getName();
		}
		return "Does not exist";
	}
}
