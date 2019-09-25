package main;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;  

import javax.swing.*;

/**
 * Creates a provider report with the provider's name, id, address, city, state, and zip code.
 * Prints each visit for that provider with the date, time, member name, member number, service code, and fee.
 * Finally, it prints the total number of visits and the total fee for the week.
 * @author Caroline Cannon
 */
public class ProviderReport {
	
	static int found = 0;
	
	/**
	 * Generates the provider report for the given provider to a file named after the provider and the report's date and in the Reports/Provider Reports folder.
	 * @param provNumber The provider's ID that the report is for.
	 * @throws IOException Invalid input.
	 */
	public static void produceReport(int provNumber) throws IOException {
		String name = getName(provNumber);
		String date = getDate();
		File providerReport = new File("src/Reports/Provider Reports");					//"Provider name - current date(MM-DD-YYYY)"
		providerReport.mkdirs();
		providerReport = new File("src/Reports/Provider Reports/"+name + " - "+ date);	
		providerReport.createNewFile();
		for (int i = 0; i < Database.providers.size(); i++) {
			if (Database.providers.get(i).getId() == provNumber) {
				PrintWriter writer = new PrintWriter("src/Reports/Provider Reports/"+name + " - "+ date, "UTF-8");
				writer.println("Name: "+Database.providers.get(i).getName());		//print name
				writer.println("Provider ID: "+Database.providers.get(i).getId());		//print id
				writer.println("Address: "+Database.providers.get(i).getAddr());		//print address
				writer.println("City: "+Database.providers.get(i).getCity());		//print city
				writer.println("State: "+Database.providers.get(i).getState());		//print state
				writer.println("ZIP Code: "+Database.providers.get(i).getZip());		//print zip
				writer.println("************************************");
				for(int a = 0; a < Database.visits.size(); a++) {
					if(Database.visits.get(a).getProvider().getId()==provNumber) { //getting services
						writer.println("Date of Visit: "+Database.visits.get(a).getDate());     //print date
						writer.println("Time Received by Computer: "+Database.visits.get(a).getTime());     //print time
						writer.println("Member Name: "+Database.visits.get(a).getMember().getName()); //print member name 
						writer.println("Member ID: "+Database.visits.get(a).getMember().getId());  //print member id
						writer.println("Service Code: "+Database.visits.get(a).getService().getCode()); //print service code
						writer.println("Service Fee: "+Database.visits.get(a).getService().getFee());  //print service fee
					}
				}
				writer.println("************************************");
				writer.println("Total number of consultations with members: "+Database.providers.get(i).getNumVisits()); //Total number of consultations with members (3 digits)
				writer.println("Total fee: "+Database.providers.get(i).getTotalFee()); //Total fee for the week
				i = Database.providers.size();
				writer.close();
				found = 1;													//member found
			}
			else if (found == 0 && i == Database.providers.size()-1) {		//member not found
				JOptionPane.showMessageDialog(null, "Error: Member Not Found");
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
		return "Provider does not exist";
	}
	
	/**
	 * Gets the current date in order to name the file.
	 * @return The current date in the format MM-DD-YYYY
	 */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		String dat = formatter.format(date);
		return dat;
	}
}