package main;

import java.util.Random;

/**
 * Members and providers are both created using the User class. 
 * Creates a new User with a name, address, city, state, zip code, and ID.
 * Members also have a membership status and providers have total visits and total fee for the week.
 * @author Tabitha Williams
 */
public class User {
	
	String name;
	String addr;
	String city;
	String state;
	String zip;
	boolean status;      												//true = active, false = suspended
	int id;
	int numVisits = 0;
	int totalFee = 0;
	
	public User() {
		this.name = "";
		this.addr = "";
		this.city = "";
		this.state = "";
		this.zip = "";
		this.status = false;
		this.id = generateId();
	}
	
	/**
	 * Creates a new User and randomly assigns them a 9-digit ID number.
	 * @param name The user's name.
	 * @param addr The user's address.
	 * @param city The user's city.
	 * @param state The user's state.
	 * @param zip The user's ZIP code.
	 * @param x true = member, false = provider.
	 */
	public User(String name, String addr, String city, String state, String zip, boolean x) {
		this.name = name;
		this.addr = addr;
		this.city = city;
		this.state = state;
		this.zip = zip;
		status = true;
		
		
		
		if(x) {
			id = generateId();
			while(Verify.verifyMember(id) != 2) {
				id = generateId();
			}
		}
		else {
			id = generateId();
			while(Verify.verifyProvider(id) != 2)
				id = generateId();
		}
	}
	
	/**
	 * Creates a new user with a previously assigned ID number.
	 * @param name The user's name.
	 * @param addr The user's address.
	 * @param city The user's city.
	 * @param state The user's state.
	 * @param zip The user's ZIP code.
	 * @param x true = member, false = provider.
	 * @param i The user's previously assigned ID number.
	 */
	public User(String name, String addr, String city, String state, String zip, int i, boolean x) {
		this.name = name;
		this.addr = addr;
		this.city = city;
		this.state = state;
		this.zip = zip;
		id = i;
		status = true;
	}
	
	
	/**
	 * Creates a random 9-digit ID number.
	 * @return A random ID number.
	 */
	public int generateId() {
		Random random = new Random();
		int x = random.nextInt(888888889);
		x += 111111111;
		return x;
	}
	
	/**
	 * Changes the user's name.
	 * @param x The user's new name.
	 */
	public void setName(String x) {
		name = x;
	}
	
	/**
	 * Changes the user's address.
	 * @param x The user's new address.
	 */
	public void setAddr(String x) {
		addr = x;
	}
	
	/**
	 * Changes the user's city.
	 * @param x The user's new city.
	 */
	public void setCity(String x) {
		city = x;
	}
	
	/**
	 * Changes the user's state.
	 * @param x The user's new state.
	 */
	public void setState(String x) {
		state = x;
	}
	
	/**
	 * Changes the user's ZIP code.
	 * @param x The user's new ZIP code.
	 */
	public void setZip(String x) {
		if(x.charAt(0) == '-'){
			throw new IllegalArgumentException();
		}
		zip = x;
	}
	
	/**
	 * Changes the user's membership status.
	 * @param x The user's new status. true=active, false=suspended.
	 */
	public void setStatus(boolean x) {
		status = x;
	}
	
	/**
	 * Accesses and returns the user's name.
	 * @return The user's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Accesses and returns the user's address.
	 * @return The user's address.
	 */
	public String getAddr() {
		return addr;
	}
	
	/**
	 * Accesses and returns the user's city.
	 * @return The user's city.
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Accesses and returns the user's state.
	 * @return The user's state.
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Accesses and returns the user's ZIP code.
	 * @return The user's ZIP code.
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * Accesses and returns the user's membership status.
	 * @return The user's status.
	 */
	public boolean getStatus() {
		return status;
	}
	
	/**
	 * Accesses and returns the user's ID number.
	 * @return The user's ID number.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Increases the provider's visit count for the week by 1.
	 */
	public void addVisit() {
		numVisits++;
	}
	
	/**
	 * Accesses and returns the provider's total number of visits for the week.
	 * @return The provider's total number of visits for the week.
	 */
	public int getNumVisits() {
		return numVisits;
	}
	
	/**
	 * Adds a specific service fee to the provider's total fee count for the week. 
	 * @param fee The amount to add to the provider's total fee count for the week.
	 */
	public void addFee(int fee) {
		totalFee += fee;
	}
	
	/**
	 * Accesses and returns the provider's total fees for the week.
	 * @return The provider's total fees for the week.
	 */
	public int getTotalFee() {
		return totalFee;
	}
}
