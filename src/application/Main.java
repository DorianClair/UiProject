package application;
	
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.sql.SQLException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public String name; //user's first name
	public double startWeight; // user's starting weight
	public double goalWeight; // user's goal weight
	public int heightInches; //user's height in inches
	public Date dateOfBirth; //user's date of birth
	public double poundsPerWeek; //user's desired pounds to lose per week
	public Boolean gender; //user's gender
	
	public static final Controller CONTROLLER = new Controller();
	public static final Model MODEL = new Model();
	public static final View VIEW = new View();
	public static Scene scene;
	public static BorderPane root;
	public static SQLite db;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("hello world");
		db = new SQLite();
		db.getConnection();

		launch(args);
		
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.<BorderPane>load(getClass().getResource("SplashPage.fxml"));
			//new ChartScreen(root);
			//BorderPane root = new BorderPane();
			scene = new Scene(root,800,450);
			scene.getStylesheets().add(getClass().getResource("MainSheet.css").toExternalForm());

			primaryStage.setOnCloseRequest(event -> {
			    db.closeConn();
			    // Save file
			});

			//Scene scene = new Scene(root,450,300);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
