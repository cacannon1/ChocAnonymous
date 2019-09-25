package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

public class ManagerMenuControl {
	
	/**
	 * Prompts for the member's ID and creates a report for that member.
	 * @throws FileNotFoundException If the given file cannot be found.
	 * @throws UnsupportedEncodingException If the encoding is unsupported.
	 * @throws IOException Invalid input.
	 */
	public static void generateMemberReport() throws IOException {
		boolean found = false;
		while(!found) {
			String s = JOptionPane.showInputDialog(null, "What is the member's ID?", "Generate Member Report", JOptionPane.INFORMATION_MESSAGE);
			int id = Integer.parseInt(s);
			if (Verify.verifyMember(id) == 0)	{							 //verify member
				MemberReport.produceReport(id);	 //produce report
				found = true;
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid Member ID!");
		}
	}
	
	/**
	 * Prompts for the provider's ID and creates a report for that provider.
	 * @throws IOException Invalid input.
	 */
	public static void generateProviderReport() throws IOException {
		boolean found = false;
		while(!found) {
			String s = JOptionPane.showInputDialog(null, "What is the provider's ID?", "Generate Provider Report", JOptionPane.INFORMATION_MESSAGE);
			int id = Integer.parseInt(s);
			if (Verify.verifyProvider(id) == 0) {							 	//verify provider
				ProviderReport.produceReport(id); //produce report
				found = true;
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid Provider ID!");
		}
	}
	
	/**
	 * Creates the summary report.
	 * @throws IOException Invalid input.
	 */
	public static void generateSummaryReport() throws IOException {
		SummaryReport.produceReport();    //produce report
	}
	
	/**
	 * Prompts the user for the provider's ID and then creates the EFT Report for that provider.
	 * @throws IOException Invalid input.
	 */
	public static void generateEFTReport() throws IOException {
		boolean found = false;
		while(!found) {
			String s = JOptionPane.showInputDialog(null, "What is the provider's ID?", "Generate EFT Report", JOptionPane.INFORMATION_MESSAGE);
			int id = Integer.parseInt(s);
			if(Verify.verifyProvider(id) == 0) {
				EFTReport.produceReport(id);
				found = true;
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid Provider ID!");
		}
	}
	
	/**
	 * Creates reports for every member and provider as well as a summary report.
	 * @throws IOException Invalid input.
	 */
	public static void runMainAccProc() throws IOException {
		generateSummaryReport();
		JOptionPane.showMessageDialog(null, "Summary Report Created!");
		for(int i=0; i<Database.providers.size(); i++) {
			int id = Database.providers.get(i).getId();
			ProviderReport.produceReport(id);
		}
		JOptionPane.showMessageDialog(null, "Provider Reports Created!");
		for(int i=0; i<Database.members.size(); i++) {
			int id = Database.members.get(i).getId();
			MemberReport.produceReport(id);
		}
		JOptionPane.showMessageDialog(null,"Member Reports Created!");
	}
}
