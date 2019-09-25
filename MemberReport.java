package main;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

/**
 * Creates a member report with the member's name, id, address, city, state, and zip code.
 * Prints each visit for that member with the date, provider name, and service name.
 * @author Caroline Cannon
 */
public class MemberReport {
	
	static int found = 0;
	
	/**
	 * Generates the member report for the given member to a file named after the member and the report's date in the Reports/Member Reports folder.
	 * @param memNumber The member's ID that the report is for.
	 * @throws IOException Invalid input.
	 */
	public static void produceReport(int memNumber) throws IOException {
		String name = getName(memNumber);
		String date = getDate();
		File providerReport = new File("src/Reports/Member Reports");					//"Provider name - current date(MM-DD-YYYY)"
		providerReport.mkdirs();
		providerReport = new File("src/Reports/Member Reports/"+name + " - "+ date);	
		providerReport.createNewFile();
		for (int i = 0; i < Database.members.size(); i++) {
			if (Database.members.get(i).id == memNumber) {
				PrintWriter writer = new PrintWriter("src/Reports/Member Reports/"+name + " - "+ date, "UTF-8");
				writer.println("Name: "+Database.members.get(i).name);		//print name
				writer.println("Member ID: "+Database.members.get(i).id);			//print id
				writer.println("Address: "+Database.members.get(i).addr);		//print address
				writer.println("City: "+Database.members.get(i).city);		//print city
				writer.println("State: "+Database.members.get(i).state);		//print state
				writer.println("ZIP Code: "+Database.members.get(i).zip);			//print zip
				writer.println("************************************");
				/*
				 * Make a new array list of the visits for that member
				 * Sort that array list by the date of the visit.
				 */
				ArrayList<Visit> v = new ArrayList<Visit>();
				
				for(int a = 0; a < Database.visits.size(); a++) {
					if(Database.visits.get(a).getMember().getId()==memNumber) { //getting services
						v.add(Database.visits.get(a));
					}
				}

				for(int j=0; j<v.size()-1; j++) {
					String y = v.get(j).getDate().substring(6);
					int y1 = Integer.parseInt(y);
					y = v.get(j+1).getDate().substring(6);
					int y2 = Integer.parseInt(y);
					y = v.get(j).getDate().substring(0,2);
					int m1 = Integer.parseInt(y);
					y = v.get(j+1).getDate().substring(0,2);
					int m2 = Integer.parseInt(y);
					y = v.get(j).getDate().substring(3,5);
					int d1 = Integer.parseInt(y);
					y = v.get(j).getDate().substring(3,5);
					int d2 = Integer.parseInt(y);
					if(y1 < y2 ) {
						swap(v, j, j+1);
					}
					else if(y1 == y2) {
						if(m1 < m2) {
							swap(v, j, j+1);
						}
						else if(m1 == m2) {
							if(d1 < d2) {
								swap(v, j, j+1);
							}
						}
					}
				}
				
				for(int j=0; j<v.size(); j++) {
					writer.println("Date of Visit: "+v.get(j).getDate());     //print date
					writer.println("Provider Name: "+v.get(j).getProvider().getName()); //print provider name 
					writer.println("Service: "+v.get(j).getService().getName()); //print service name	
				}
				writer.close();
				i = Database.members.size();
				found = 1;		
			}												//member found
			else if (found == 0 && i == Database.members.size()-1) { 			//member not found
				JOptionPane.showMessageDialog(null, "Error: Member Not Found");
			}
		}		
	}	 
	
	/**
	 * Gets the member's name in order to name the file.
	 * @param id The member's ID number.
	 * @return The member's name.
	 */
	public static String getName(int id) {
		for(int i=0; i<Database.members.size(); i++) {
			if(Database.members.get(i).getId() == id)
				return Database.members.get(i).getName();
		}
		return "Member does not exist";
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
	
	/**
	 * Swaps two elements in an ArrayList of Visits.
	 * @param v The ArrayList of Visits to swap elements in.
	 * @param i The first position to swap.
	 * @param j The second position to swap.
	 */
	public static void swap(ArrayList<Visit> v, int i, int j) {
		Visit v1 = v.get(i);
		Visit v2 = v.get(j);
		v.remove(i);
		v.add(i, v2);
		v.remove(j);
		v.add(j, v1);
	}
}