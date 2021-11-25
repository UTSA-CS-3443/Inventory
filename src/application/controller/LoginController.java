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

//controller class for the login Screen
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
	
	@Override
	public void handle(ActionEvent event) {

		
	}
	public void loginHandle(ActionEvent event) throws IOException {
				
				//loads the user list
				ArrayList<User> users = User.loadUsers("data/users.csv");
				
				//sets the username and password to variables to be compared
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				//compares the username and pw to the file
				activeUser = User.validate(users, username, password);
				
				//if there is a match
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
					//if the method returns null(if they arent matched)
					incorrectLoginLabel.setText("Incorrect Login. Please try again.");
					
				}
	}


}
