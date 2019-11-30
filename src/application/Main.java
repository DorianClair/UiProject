package application;
	
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	
	public String name; //user's first name
	public double startWeight; // user's starting weight
	public double goalWeight; // user's goal weight
	public int heightInches; //user's height in inches
	public Date dateOfBirth; //user's date of birth
	public double poundsPerWeek; //user's desired pounds to lose per week
	public String gender; //user's gender
	
	public static final Controller CONTROLLER = new Controller();
	public static final Model MODEL = new Model();
	public static final View VIEW = new View();
	public static Scene scene;
	public static BorderPane root;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.<BorderPane>load(getClass().getResource("SplashPage.fxml"));
			//new ChartScreen(root);
			//BorderPane root = new BorderPane();
			scene = new Scene(root,800,450);
			scene.getStylesheets().add(getClass().getResource("MainSheet.css").toExternalForm());

			

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
