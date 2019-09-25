package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * The summary report lists every provider to be paid that week, the number of consultations each had, and his or her total fee for that week.
 * Finally, the total number of providers who provided services, the total number of consultations, and the overall fee total are printed.
 * @author Caroline Cannon
 */
 
public class SummaryReport {

	static int numProv = 0;
	static int numConsult = 0;
	static int totalFee = 0;
	
	/**
	 * Generates the summary report for the manager in a file named "Summary Report" in the Reports folder.
	 * @throws IOException Invalid input.
	 */
	public static void produceReport() throws IOException {
		File providerReport = new File("src/Reports");					//"Provider name - current date(MM-DD-YYYY)"
		providerReport.mkdir();
		providerReport = new File("src/Reports/Summary Report");	
		providerReport.createNewFile();
		PrintWriter writer = new PrintWriter("src/Reports/Summary Report", "UTF-8");
		for (int i = 0; i < Database.providers.size(); i++) {
			if (Database.providers.get(i).getNumVisits() > 0) {
				numProv++;
				numConsult += Database.providers.get(i).getNumVisits();
				totalFee += Database.providers.get(i).getTotalFee();
				writer.println("Provider Name: "+Database.providers.get(i).getName());      //every provider to be paid
				writer.println("Number of Visits: " +Database.providers.get(i).getNumVisits()); //number of consultations
				writer.println("Total Fee: "+Database.providers.get(i).getTotalFee());  //total fee for week
			}
		}
		writer.println("Total number of providers: "+numProv);								     //total number of providers
		writer.println("Total number of consultations: "+numConsult); 							     //total consultations
		writer.println("Overall total fee: "+totalFee); 							     //overall fee total
		writer.close();
	}

}
