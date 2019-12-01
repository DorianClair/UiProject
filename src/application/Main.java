package application;
	
import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


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
	public static final CircleChartInfo CIRCLE_INFO = new CircleChartInfo();
	public final CircleChart CIRCLE_CHART = new CircleChart();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			primaryStage.setResizable(false);
			
			TextField t = new TextField();
			Button b = new Button("Add Calories");
			
			b.setOnAction(new EventHandler<ActionEvent>() {
			    @Override 
			    public void handle(ActionEvent e) {
			    	try{
			    		int add =  (int) Double.parseDouble(t.getText());
					    CIRCLE_INFO.setAmount(add);
					   	t.clear();
					   	CIRCLE_CHART.draw();
			    		}
			    		catch(Exception e1)
			    		{ //Illegal input
			    			t.clear();
			    		}
			    }
			});
			
			HBox h = new HBox();
			h.getChildren().add(t);
			h.getChildren().add(b);
			h.setSpacing(2);
			h.setPadding(new Insets(10,10,50,50));
			
			VBox v = new VBox();
			v.getChildren().addAll(CIRCLE_CHART, h);
			
			root.setCenter(v);
						
			Scene scene = new Scene(root,375,400);
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
