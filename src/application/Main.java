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
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = FXMLLoader.<BorderPane>load(getClass().getResource("Graph.fxml"));
			//BorderPane root = new BorderPane();
			scene = new Scene(root,800,450);
			ImageView bt1  = (ImageView) scene.lookup("btn1");
//			bt1.setOnMousePressed(new EventHandler<MouseEvent>() {
//	            @Override
//	            public void handle(MouseEvent event) {
//	                System.out.println("Hey, you click on it");
//	            }            
//	        });
			//defining the axes
	        //creating the chart
			scene.getStylesheets().add(getClass().getResource("MainSheet.css").toExternalForm());
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println();

			Label bottem = new Label("                                            Current date: " + formatter.format(date) + "  |  " + "Estimated Goal Date: 18-12-2019 ");
			//bottem.setTextFill(Color.WHITE);
	        bottem.setTextAlignment(TextAlignment.CENTER); 
	        CategoryAxis xAxis = new CategoryAxis();
	        xAxis.setLabel("Month");
	        

	        NumberAxis yAxis = new NumberAxis();
	        yAxis.setLabel("Weight in LB");

	        LineChart lineChart = new LineChart(xAxis, yAxis);
	        lineChart.setTitle("Weight History");
	        lineChart.setLegendVisible(false);


	        XYChart.Series series = new XYChart.Series();
	        series.setName("2014");

	        series.getData().add(new XYChart.Data("Jan", 23));
	        series.getData().add(new XYChart.Data("Feb", 14));
	        series.getData().add(new XYChart.Data("Mar", 15));
	        series.getData().add(new XYChart.Data("Apr", 24));
	        series.getData().add(new XYChart.Data("May", 34));
	        series.getData().add(new XYChart.Data("Jun", 36));
	        series.getData().add(new XYChart.Data("Jul", 22));
	        series.getData().add(new XYChart.Data("Aug", 45));
	        series.getData().add(new XYChart.Data("Sep", 43));
	        series.getData().add(new XYChart.Data("Oct", 17));
	        series.getData().add(new XYChart.Data("Nov", 29));
	        series.getData().add(new XYChart.Data("Dec", 25));

	        lineChart.getData().add(series);
	        
	        VBox vbox2 = new VBox(lineChart);
	        vbox2.getChildren().add(bottem);
	       // root.getChildren().add(vbox2);
	        //root.setAlignment(vbox2, Pos.CENTER);
	        //root.se
	        //root.setBottom(bottem);
	        root.setCenter(vbox2);

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
