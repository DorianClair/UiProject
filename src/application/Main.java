package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.sql.SQLException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static final Controller CONTROLLER = new Controller();
	public static final Model MODEL = new Model();
	public static final View VIEW = new View();
	public static Scene scene;
	public static BorderPane root;
	public static SQLite db;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		db = new SQLite();
		db.getConnection();

		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.<BorderPane>load(getClass().getResource("SplashPage.fxml"));
			scene = new Scene(root,800,450);
			scene.getStylesheets().add(getClass().getResource("MainSheet.css").toExternalForm());

			primaryStage.setOnCloseRequest(event -> {
			    db.closeConn();
			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
