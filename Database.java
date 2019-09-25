package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The central Database that holds all the information for members, providers, visits, services, and the provider directory.
 * @author David Hall
 */
public class Database {
	
	public static ArrayList<User> members = new ArrayList<User>();
	public static ArrayList<User> providers = new ArrayList<User>();
	public static ArrayList<Visit> visits = new ArrayList<Visit>();
	public static ArrayList<Service> services = new ArrayList<Service>();
	public static ArrayList<String> providerDirectory = new ArrayList<String>();
	
	/**
	 * Creates the database by reading in all the information from files and stores them in array lists.
	 * @throws FileNotFoundException If the given file cannot be found.
	 */
	public static void createDatabase() throws FileNotFoundException{
		File file = new File("src/members.txt");
		Scanner scan = new Scanner(file);
		String name, addr, city, state, zip, date, time, comments;
		int id, pId, mId;
		boolean type = true;
		int num = 0;
		while(scan.hasNext()){
			if(num != 0) {
				scan.nextLine();
			}
			name = scan.nextLine();
			addr = scan.nextLine();
			city = scan.nextLine();
			state = scan.nextLine();
			zip = scan.nextLine();
			id = scan.nextInt();
			User u = new User(name, addr, city, state, zip, id, type);
			members.add(u);
			num++;
		}
		type = false;
		scan.close();
		file = new File("src/providers.txt");
		scan = new Scanner(file);
		num = 0;
		while(scan.hasNextLine()){
			if(num != 0) {
				scan.nextLine();
			}
				if(scan.hasNextLine()) {
				name = scan.nextLine();
				addr = scan.nextLine();
				city = scan.nextLine();
				state = scan.nextLine();
				zip = scan.nextLine();
				id = scan.nextInt();
				User u = new User(name, addr, city, state, zip, id, type);
				providers.add(u);
				num++;
			}
		}
		scan.close();
		int code = 0;
		int fee;
		String c = "";
		String f = "";
		name = "";
		file = new File("src/providerDirectory.txt");
		scan = new Scanner(file);
		scan.useDelimiter(" - ");
		scan.nextLine();
		scan.nextLine();
		num = 0;
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			Scanner scann = new Scanner(line);
			scann.useDelimiter(" - ");
			name = scann.next();
			c = scann.next();
			f = scann.next();
			code = Integer.parseInt(c);
			fee = Integer.parseInt(f);
			Service s = new Service(name, code, fee);
			services.add(s);
			scann.close();
		}
		scan.close();
		file = new File("src/providerDirectory.txt");
		scan = new Scanner(file);
		while(scan.hasNextLine()) {
			providerDirectory.add(scan.nextLine());
		}
		scan.close();
		file = new File("src/visits.txt");
		scan = new Scanner(file);
		num = 0;
		while(scan.hasNextLine()){
			if(num != 0) {
				scan.nextLine();
			}
				if(scan.hasNextLine()) {
					comments = scan.nextLine();
					date = scan.nextLine();
					time = scan.nextLine();
					code = scan.nextInt();
					mId = scan.nextInt();
					pId = scan.nextInt();
					Visit v = new Visit(date, time, code, mId, pId, comments);
					visits.add(v);
					num++;
			}
		}
		scan.close();
	}
}
