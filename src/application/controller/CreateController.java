/**
 * The Create Controller class is responsible for updating the create window based on the user's inputs
 * 
 * 	@author Payton Chism (xja124), Beryl Mohanadhas (eaq312), Valeria Villanueva (epi655), Bryan Alvarado (zvy639)
 */

package application.controller;

import java.util.ArrayList;

import application.Main;
import application.model.Item;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateController implements EventHandler<Event> {
	
	@FXML
	Button button;
	@FXML
	TextField itemName;
	@FXML
	TextField itemBrand;
	@FXML
	TextField itemPrice;
	@FXML
	TextField itemQty, itemNumber;
	@FXML
	TextArea itemInfo;
	@FXML
	Label itemExistence;
	

	/**
	 * Changes the appropriate view based on which button the user clicks
	 * @param event The press of a button (Event)
	 */
	@Override
	public void handle(Event event) {
		button = (Button) event.getSource();
			if(button.getId().equals("createButton")) {
			try {
				ArrayList<Item> items = Item.loadInventory("data/inventory.csv");
				if(Item.itemExists(itemNumber.getText(), items) == false) {
					FXMLLoader loader = new FXMLLoader(
						    getClass().getResource(
						      "InventoryController.fxml"
						    )
						  );
					InventoryController controller = loader.getController();
				saveItem(controller);
				
				}
				Parent root = FXMLLoader.load(getClass().getResource("../view/Create.fxml"));
				Main.stage.setScene(new Scene(root, 800, 800));
				Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(button.getId().equals("backButton")) {
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
	 * Saves an item into the inventory
	 * @param controller The ItemController to save an item to (ItemController)
	 */
	public void saveItem(InventoryController controller) {
		ArrayList<Item> items = Item.loadInventory("data/inventory.csv");
		String numText = itemNumber.getText();
		String nameText = itemName.getText();
		String brandText = itemBrand.getText();
		String priceText = itemPrice.getText();
		String qtyText = itemQty.getText();
		String infoText = itemInfo.getText();
		if(numText.equals("") || nameText.equals("") || brandText.equals("") || priceText.equals("") || qtyText.equals("") || infoText.equals("")) {
			return;
		}
		Item.saveToInventory(numText, nameText, brandText, priceText, qtyText, infoText, items);
	}

}
