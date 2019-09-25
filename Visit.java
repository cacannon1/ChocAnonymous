package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Creates a new visit with fields for the date, time, service, member, and provider.
 * @author Edward Liccardo
 */
public class Visit {
	
	String date; 														// MM-DD-YYYY (Date of visit)
	String time; 														// MM-DD-YYYY 00:00:00 (Date and time received by computer)
	Service service;
	User member;
	User provider;
	String comments = "comments";
	
	/**
	 * Creates an instance of the Visit class.
	 * @param memNum The member's ID number.
	 * @param provNum The provider's ID number.
	 */
	public Visit(int memNum, int provNum) {
		createVisit(memNum, provNum);
	}
	
	public Visit(String date, String time, int code, int mId, int pId, String comments) {
		this.date = date;
		this.time = time;
		for(int i=0; i<Database.services.size(); i++) {					//Find service using code
			if(Database.services.get(i).getCode() == code)
				service = Database.services.get(i);
		}
		for(int i=0; i<Database.members.size(); i++) {					//Find member using ID
			if(Database.members.get(i).getId() == mId)
				member = Database.members.get(i);
		}
		for(int i=0; i<Database.providers.size(); i++) {				//Find provider using ID
			if(Database.providers.get(i).getId() == pId) {
				provider = Database.providers.get(i);
				provider.addVisit();
				provider.addFee(service.getFee());	
			}
		}
		this.comments = comments;
	}
	
	/**
	 * Prompts the user for the date, time, service ID, member ID, and provider ID for the visit.
	 * @param memNum The member's ID number.
	 * @param provNum The provider's ID number.
	 */
	public void createVisit(int memNum, int provNum) {
		String d = JOptionPane.showInputDialog(null, "What is the date of the visit?(MM-DD-YYYY)", "Create Visit", JOptionPane.INFORMATION_MESSAGE);
		Date dat = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		String t = formatter.format(dat);
		
		boolean found = false;
		boolean found1;
		while(!found) {
			found1 = false;
			String se = JOptionPane.showInputDialog(null, "What is the service ID?", "Create Visit", JOptionPane.INFORMATION_MESSAGE);
			int s = Integer.parseInt(se);
			for(int i=0; i<Database.services.size(); i++) {
				if(Database.services.get(i).getCode() == s) {
					found1 = true;
					String name = Database.services.get(i).getName();
					int input = JOptionPane.showConfirmDialog(null, "Is " +name+" the service you want to enter?");
					if(input == 0) {
						service = Database.services.get(i);
						found = true;
					}
				}
			}
			if(found==false && found1==false)
				JOptionPane.showMessageDialog(null, "Invalid service code!");
		}
		int m = memNum;
		int p = provNum;
		String c = JOptionPane.showInputDialog(null, "What are your comments on the session?", "Create Visit", JOptionPane.INFORMATION_MESSAGE);
		comments = c;
		
		date = d;
		time = t;
		for(int i=0; i<Database.members.size(); i++) {
			if(Database.members.get(i).getId() == m)
				member = Database.members.get(i);
		}
		for(int i=0; i<Database.providers.size(); i++) {
			if(Database.providers.get(i).getId() == p) {
				provider = Database.providers.get(i);
				provider.addVisit();
				provider.addFee(service.getFee());
			}
		}
	}
	
	/**
	 * Returns the date of the visit.
	 * @return The visit's date.
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Returns the service provided at the visit.
	 * @return The visit's service.
	 */
	public Service getService() {
		return service;
	}
	
	/**
	 * Returns the time of the visit.
	 * @return The visit's time.
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * Returns the member at the visit.
	 * @return The visit's member.
	 */
	public User getMember() {
		return member;
	}
	
	/**
	 * Returns the provider of the visit.
	 * @return The visit's provider.
	 */
	public User getProvider() {
		return provider;
	}
	
	/**
	 * Returns the comments of the visit.
	 * @return The visit's comments.
	 */
	public String getComments() {
		return comments;
	}
	
	public void setComments(String c) {
		comments = c;
	}
	
	public void setDate(String c) {
		if(c.charAt(0) == '-'){
			throw new IllegalArgumentException();
		}
		date = c;
	}
}
