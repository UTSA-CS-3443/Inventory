/**
 * The Item class stores information about an Item and deals with functions pertaining to an Item
 * 
 * 	@author Payton Chism (xja124), Beryl Mohanadhas (eaq312), Valeria Villanueva (epi655), Bryan Alvarado (zvy639)
 */

package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Item {
	
	private String itemName, brandName, price ,quantity, description, itemID;
	
	/**
	 * Constructor to create an Item
	 * @param itemID The item ID of the item (String)
	 * @param i The item name of the item (String)
	 * @param b The brand name of the item (String)
	 * @param p The price of the item (String)
	 * @param q The quantity of the item (String)
	 * @param d The description of the item (String)
	 */
	public Item(String itemID, String i, String b, String p, String q, String d) {
		this.itemID = itemID;
		this.itemName = i;
		this.brandName = b;
		this.price = p;
		this.quantity = q;
		this.description = d;
	}

	/**
	 * Gets a list of Items from a csv file containing the inventory
	 * @param fileName The filename of the inventory (String)
	 * @return ArrayList<Item> an ArrayList of Item objects of the inventory
	 */
	public static ArrayList<Item> loadInventory(String fileName){
		File file = new File(fileName);
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				if (tokens != null && tokens.length > 1) {
					String itemNumber = tokens[0];
					String itemName = tokens[1];
					String brand = tokens[2];
					String price = tokens[3];
					String quantity = tokens[4];
					String desc = tokens[5];
					Item item = new Item(itemNumber, itemName, brand, price, quantity, desc);
					items.add(item);
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return items;	
	}
	
	/**
	 * Saves an item to the inventory
	 * @param itemID The item id of the Item (String)
	 * @param nameText The name of the Item (String)
	 * @param brandText The brand of the Item (String)
	 * @param priceText The price of the Item (String)
	 * @param qtyText The quantity of the Item (String)
	 * @param infoText The description of the Item (String)
	 * @param i The list of items in the inventory (ArrayList<Item>)
	 */
	public static void saveToInventory(String itemID, String nameText, String brandText, String priceText, String qtyText, String infoText, ArrayList<Item> i) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("data/inventory.csv", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		if(itemExists(itemID, i) == false) {
			pw.println(itemID + "," + nameText + "," + brandText + "," + priceText + "," + qtyText + "," + infoText);
		}
		pw.flush();
		pw.close();
		return;
	}
	
	/**
	 * Updates info about a particular item
	 * @param itemID The item id of the Item (String)
	 * @param name The new name of the Item (String)
	 * @param brand The new brand of the Item (String)
	 * @param price The new price of the Item (String)
	 * @param qty The new quantity of the Item (String)
	 * @param info The new info of the Item (String)
	 */
	public static void updateItem(String itemID, String name, String brand, String price, String qty, String info) {
		if(itemID.equals("")) {
			return;
		}
		FileWriter fw = null;
		String tempFile = "temp.csv";
		String filePath = "data/inventory.csv";
		File oldFile = new File(filePath);
		File newFile = new File(tempFile);
		try {
			try {
				fw = new FileWriter(tempFile, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Scanner scan  = new Scanner(new File(filePath));
			scan.useDelimiter(",\n");		
			while(scan.hasNextLine()) {
				String line  = scan.nextLine();
				String[] tokens = line.split(",");
				String ID = tokens[0];
				if(ID.equals(itemID)) {
					if(name.equals("")) {
						name = tokens[1];
					}
					if(brand.equals("")){
						brand = tokens[2];
					}
					if(price.equals("")) {
						price = tokens[3];
					}
					if(qty.equals("")) {
						qty = tokens[4];
					}
					if(info.equals("")) {
						info = tokens[5];
					} 
					pw.println(itemID + "," + name + "," + brand + "," + price + "," + qty + "," + info);
				}
				else {
					pw.println(tokens[0] + "," + tokens[1] + "," + tokens[2] + "," + tokens[3] + "," + tokens[4] + "," + tokens[5]);
				}
			}
			scan.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filePath);
			newFile.renameTo(dump);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes an item from the inventory
	 * @param itemID The item ID of the Item (String)
	 * @param i The list of items in the inventory (ArrayList<Item)
	 */
	public static void deleteItem(String itemID, ArrayList<Item> i) {
		FileWriter fw = null;
		String tempFile = "temp.csv";
		String filePath = "data/inventory.csv";
		File oldFile = new File(filePath);
		File newFile = new File(tempFile);
		if(itemExists(itemID, i) == false) {
			return;
		}
		try {
			fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Scanner scan  = new Scanner(new File(filePath));
			scan.useDelimiter(",\n");
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				if (tokens != null && tokens.length > 1 ) {
					String ID = tokens[0];
					String name = tokens[1];
					String brand = tokens[2];
					String price = tokens[3];
					String quantity = tokens[4];
					String desc = tokens[5];
				if(!ID.equals(itemID)) {
					pw.println(ID + "," + name + "," + brand + "," + price + "," + quantity + "," + desc);
				}
				}
			}
			scan.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filePath);
			newFile.renameTo(dump);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if an item exists in the inventory
	 * @param itemID The item ID of the Item (String)
	 * @param i The list of items in the inventory (ArrayList<Item>)
	 * @return boolean whether the item exists
	 */
	public static boolean itemExists(String itemID, ArrayList<Item> i) {
		boolean b = false;
		for(Item j : i) {
			if(j.getItemID().equals(itemID)) {
				b = true;
				return b;
			}
		}
		return b;
	}
	
	/**
	 * Returns a string representation of an Item
	 * @return String a string representation of an Item
	 */
	public String toString() {
		String retString = "";
		retString += (this.getItemID() + "\t\t\t" + this.getItemName() +  "\t\t\t" + this.getBrandName() + "\t\t\t" + this.getPrice() + "\t\t\t" +
		this.getQuantity() + "\t\t\t" + this.getDescription() + "\n");
		return retString;
	}
	
	/**
	 * Gets the name of the Item
	 * @return String the name of the Item
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Gets the ID of the Item
	 * @return String the ID of the Item
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * Changes the ID of the Item
	 * @param itemID The new ID of the Item (String)
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * Changes the name of the Item
	 * @param itemName The new name of the Item (String)
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Gets the brand name of the Item 
	 * @return String the brand name of the Item
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * Changes the brand name of the Item
	 * @param brandName The new brand name of the Item (String)
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * Gets the price of the Item
	 * @return String the price of the Item
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Changes the price of the Item
	 * @param price The new price of the Item (String)
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * Gets the quantity of the Item
	 * @return String the quantity of the Item
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * Changes the quantity of the Item
	 * @param quantity The new quantity of the Item (String)
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the description of the Item
	 * @return String the description of the Item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Changes the description of the Item
	 * @param description The new description of the Item (String)
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
