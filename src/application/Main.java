package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

//@author: Payton Chism (xja124)
//lab 4
public class Main extends Application {
public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			//loads the main FXML file
			AnchorPane root = new AnchorPane();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("view/Login.fxml") );
			root = (AnchorPane) loader.load();
							
			Scene scene = new Scene( root );
			stage = primaryStage;
			primaryStage.setScene( scene );
			primaryStage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
