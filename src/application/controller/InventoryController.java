package application.controller;

import java.io.IOException;

import application.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InventoryController implements EventHandler<Event> {
	
	@FXML
	Button button;

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
		
	}

}
