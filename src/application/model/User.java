/**
 * The user class stores information about a user and deals with functions pertaining to a User object
 * 
 * 	@author Payton Chism (xja124), Beryl Mohanadhas (eaq312), Valeria Villanueva (epi655), Bryan Alvarado (zvy639)
 */

package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
	
	private String username, password;

	/**
	 * Contructor to create a User
	 * @param u The username of the user (String)
	 * @param p The password of the user (String)
	 */
	public User(String u, String p) {
		this.setUsername(u);
		this.setPassword(p);
	}
	
	/**
	 * Checks the given username and password combination against the file's login information. Returns a User object if there is a match
	 * @param users The list of User objects to check against (ArrayList<User>)
	 * @param u The username to check for (String)
	 * @param p The password to check for (String)
	 * @return User the User object matching the inputs or null if no combination was found
	 */
	public static User validate(ArrayList<User> users, String u, String p) {
		User n = null;
		for (User i : users) {
			if(u.equals(i.getUsername()) && (p.equals(i.getPassword()))) {
				n = i;
			}
		}
		return n;
	}

	/**
	 * Creates an ArrayList of User objects given a csv file containing User data
	 * @param fileName The name of the file containing User data (String)
	 * @return ArrayList<User> an ArrayList of User objects
	 */
	public static ArrayList<User> loadUsers(String fileName) {
		File file = new File(fileName);
		ArrayList<User> users = new ArrayList<User>();
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				if (tokens != null && tokens.length != 0) {
					String username = tokens[0];
					String password = tokens[1];
					User user = new User(username, password);
					users.add(user);
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * Returns a String representation of a User object
	 * @return String the string representation of a User object
	 */
	public String toString() {
		String retString = "";
		retString += ("username: " + this.getUsername() + " password: " + this.getPassword());
		return retString;
	}
	
	/**
	 * Gets the username of the User
	 * @return String the username of the User
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Changes the username of the User
	 * @param username The new username (String)
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the password of the User
	 * @return String the password of the User
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Changes the password of the User
	 * @param password The new password (String)
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
