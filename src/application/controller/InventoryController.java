/**
 * The InventoryController class is responsible for updating the Inventory view based on the user's inputs
 * 
 * 	@author Payton Chism (xja124), Beryl Mohanadhas (eaq312), Valeria Villanueva (epi655)
 */

package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class InventoryController implements EventHandler<Event>, Initializable {
	
	@FXML
	Button button;
	@FXML
	TableView<Item> inventoryDisplay;
	@FXML
	AnchorPane mainPane;
	
	/**
	 * Changes to the appropriate view based on which button the user presses
	 * @param event The press of a button (Event)
	 */
	@Override
	public void handle(Event event) {
		button = (Button) event.getSource();
		if(button.getId().equals("logout")) {
			try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(button.getId().equals("add")) {
			try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Create.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(button.getId().equals("delete")) {
			try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Delete.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(button.getId().equals("updateButton")) {
			try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Update.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Updates the table displaying the current inventory
	 * @param location (URL
	 * @param resources (ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Item> items = FXCollections.observableArrayList(Item.loadInventory("data/inventory.csv"));
		TableColumn<Item, String> IDColumn = new TableColumn<Item, String>("Item ID");
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));
		TableColumn<Item, String> NameColumn = new TableColumn<Item, String>("Item Name");
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		TableColumn<Item, String> BrandColumn = new TableColumn<Item, String>("Item Brand");
		BrandColumn.setCellValueFactory(new PropertyValueFactory<>("brandName"));
		TableColumn<Item, String> PriceColumn = new TableColumn<Item, String>("Item Price");
		PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<Item, String> QuantityColumn = new TableColumn<Item, String>("Item Quantity");
		QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		TableColumn<Item, String> DescriptionColumn = new TableColumn<Item, String>("Item Description");
		DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		inventoryDisplay = new TableView<Item>();
		inventoryDisplay.setPrefHeight(544);
		inventoryDisplay.setPrefWidth(792);
		inventoryDisplay.setItems(items);
		inventoryDisplay.getColumns().addAll(IDColumn, NameColumn, BrandColumn, PriceColumn, QuantityColumn, DescriptionColumn);
		
		mainPane.getChildren().addAll(inventoryDisplay);
	}
 
}
