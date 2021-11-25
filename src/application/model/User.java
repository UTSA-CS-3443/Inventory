package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//This class contains the user object
public class User {
	private String username, password;

	
	
	//constructor
	public User(String u, String p) {
		this.setUsername(u);
		this.setPassword(p);

	}

	//checks the given username and password against the input file's login information. returns a user that matched
	//with the given username and password.
	public static User validate(ArrayList<User> users, String u, String p) {
		//creates a null user to be assigned with the user from the file
		User n = null;
		for (User i : users) {
			//checks if the username and password equal the one in the file
			if(u.equals(i.getUsername()) && (p.equals(i.getPassword()))) {
				//sets the user equal to the matched user
				n = i;
			}
			}
		//return the matched user. returns null if there is no matched user.
		return n;
	}

	//method to load users into an arrayList, accepts fileName and returns an arraylist
	public static ArrayList<User> loadUsers(String fileName) {
		File file = new File(fileName);
		ArrayList<User> users = new ArrayList<User>();
		try {
		
			// opening scanner to read through the file
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				// reading through the file to gather the information
				if (tokens != null && tokens.length != 0) {

					String username = tokens[0];
					String password = tokens[1];
					
					//creates a user object and adds it to the arraylist
					User user = new User(username, password);
					users.add(user);
				}
			}

			// closing scanner
			scan.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	
	
	//toString method
	public String toString() {
		String retString = "";
		retString += ("username: " + this.getUsername() + " password: " + this.getPassword());
		return retString;
	}
	
	
	
	//setters and getters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
