//package application;
//	
//import java.io.File;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javafx.application.Application;
//<<<<<<< HEAD
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//=======
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Pos;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Date;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.LineChart;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.TextAlignment;
//>>>>>>> 768595a71c7c46a2d62c4508cd2f2b80d52db698
//
//
//public class Main extends Application {
//	
//	public String name; //user's first name
//	public double startWeight; // user's starting weight
//	public double goalWeight; // user's goal weight
//	public int heightInches; //user's height in inches
//	public Date dateOfBirth; //user's date of birth
//	public double poundsPerWeek; //user's desired pounds to lose per week
//	public Boolean gender; //user's gender
//	
//	public static final Controller CONTROLLER = new Controller();
//	public static final Model MODEL = new Model();
//	public static final View VIEW = new View();
//<<<<<<< HEAD
//	public static final CircleChartInfo CIRCLE_INFO = new CircleChartInfo();
//	public final CircleChart CIRCLE_CHART = new CircleChart();
//=======
//	public static Scene scene;
//	public static BorderPane root;
//	public static SQLite db;
//	public static CircleChartInfo CIRCLE_INFO = new CircleChartInfo();
//	public final CircleChart CIRCLE_CHART =  new CircleChart();
//	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		db = new SQLite();
//		db.getConnection();
//		try {
//			db.execute("INSERT INTO info VALUES(1,'GRIFF','11/12/19',6,1);");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		launch(args);
//	}
//>>>>>>> 768595a71c7c46a2d62c4508cd2f2b80d52db698
//	
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//<<<<<<< HEAD
//			BorderPane root = new BorderPane();
//			primaryStage.setResizable(false);
//			
//			TextField t = new TextField();
//			Button b = new Button("Add Calories");
//			
//			b.setOnAction(new EventHandler<ActionEvent>() {
//			    @Override 
//			    public void handle(ActionEvent e) {
//			    	try{
//			    		int add =  (int) Double.parseDouble(t.getText());
//					    CIRCLE_INFO.setAmount(add);
//					   	t.clear();
//					   	CIRCLE_CHART.draw();
//			    		}
//			    		catch(Exception e1)
//			    		{ //Illegal input
//			    			t.clear();
//			    		}
//			    }
//			});
//			
//			HBox h = new HBox();
//			h.getChildren().add(t);
//			h.getChildren().add(b);
//			h.setSpacing(2);
//			h.setPadding(new Insets(10,10,50,50));
//			
//			VBox v = new VBox();
//			v.getChildren().addAll(CIRCLE_CHART, h);
//			
//			root.setCenter(v);
//						
//			Scene scene = new Scene(root,375,400);
//=======
//			root = FXMLLoader.<BorderPane>load(getClass().getResource("SplashPage.fxml"));
//			//new ChartScreen(root);
//			//BorderPane root = new BorderPane();
//			scene = new Scene(root,800,450);
//			scene.getStylesheets().add(getClass().getResource("MainSheet.css").toExternalForm());
//
//
//			//Scene scene = new Scene(root,450,300);
//>>>>>>> 768595a71c7c46a2d62c4508cd2f2b80d52db698
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//}
