/**
 * The UpdateController class is responsible for changing the Update view based on the user's inputs
 * 
 * 	@author Payton Chism (xja124), Beryl Mohanadhas (eaq312), Valeria Villanueva (epi655), Bryan Alvarado (zvy639)
 */

package application.controller;

import java.util.ArrayList;

import application.Main;
import application.model.Item;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UpdateController {
	
	@FXML
	Button button;
	@FXML
	TextField itemName;
	@FXML
	TextField itemBrand;
	@FXML
	TextField itemPrice;
	@FXML
	TextField itemQty, itemID = new TextField();
	@FXML
	TextArea itemInfo;
	@FXML
	Label itemExistence;
	
	/**
	 * Changes to the appropriate view based on the user's inputs
	 * @param event The press of a button (Event)
	 */
	public void handle(Event event) {
		button = (Button) event.getSource();
		if(button.getId().equals("updateButton")) {
			try {
				ArrayList<Item> items = Item.loadInventory("data/inventory.csv");
				if(Item.itemExists(itemID.getText(), items) == true) {
					Item.updateItem(itemID.getText(), itemName.getText(), itemBrand.getText(), itemPrice.getText(), itemQty.getText(), itemInfo.getText());
				}
				else {
					itemExistence = new Label();
					itemExistence.setText("Item Does Not Exist");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			try {
				Parent root = FXMLLoader.load(getClass().getResource("../view/Update.fxml"));
				Main.stage.setScene(new Scene(root, 800, 800));
				Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(button.getId().equals("cancelButton")) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("../view/Inventory.fxml"));
				Main.stage.setScene(new Scene(root, 800, 800));
				Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Saves an inputted item to the inventory
	 */
	public void saveItem() {
		ArrayList<Item> items = Item.loadInventory("data/inventory.csv");
		String numText = itemID.getText();
		String nameText = itemName.getText();
		String brandText = itemBrand.getText();
		String priceText = itemPrice.getText();
		String qtyText = itemQty.getText();
		String infoText = itemInfo.getText();
		Item.saveToInventory(numText, nameText, brandText, priceText, qtyText, infoText, items);
	}
	
}
