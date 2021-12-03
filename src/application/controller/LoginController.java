/**
 * The LoginController class is responsible for changing the Login view based on the user's inputs
 * 
 * 	@author Payton Chism (xja124), Beryl Mohanadhas (eaq312), Bryan Alvarado (zvy639)
 */
package application.controller;

import java.io.IOException;
import java.util.ArrayList;
import application.Main;
import application.model.User;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements EventHandler<ActionEvent>{
	
	@FXML
	private Button loginButton;
	@FXML
	private Stage stage;
	@FXML
	private TextField usernameField, passwordField;
	@FXML
	private Label incorrectLoginLabel;
	
	public static User activeUser;
	
	/**
	 * The handle method does nothing
	 * @param event The press of a button (ActionEvent)
	 */
	@Override
	public void handle(ActionEvent event) {	
		
	}
	
	/**
	 * Changes to the appropriate view based on the user's inputs
	 * @param event The press of a button (ActionEvent)
	 * @throws IOException An exception if a file cannot be found
	 */
	public void loginHandle(ActionEvent event) throws IOException {
		ArrayList<User> users = User.loadUsers("data/users.csv");
		String username = usernameField.getText();
		String password = passwordField.getText();
		activeUser = User.validate(users, username, password);
		if(activeUser != null) {
			try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Inventory.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			incorrectLoginLabel.setText("Incorrect Login. Please try again.");
		}
	}
	
}
