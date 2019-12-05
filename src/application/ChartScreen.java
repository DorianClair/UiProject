package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;


/* Written and Developed By Dorian Clair
 * 
 * Inputs: The root BorderPane you need to add a chartScreen to
 * 
 * Function: Renders a chart onto the screen
 * Features: Render previously answered weights on start-up by date they were made
 * 			 Add new weights and render them on the chart with pretty animations
 * */

public class ChartScreen {
	public static Button submit;
	public static XYChart.Series series;
	public static TextField t;
	
	public ChartScreen(BorderPane root) {
		//Whatever is in the borderPanes Center, clear it
		root.setCenter(null);
		//Make a new date to use for the text at the bottom of the page
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		//This is a really gross print statements but if you touch it it will break things.
		Label bottem = new Label("                                            Current date: " + formatter.format(date) + "  |  " + "Estimated Goal Date: 18-12-2019 ");
		Label weightPls = new Label("                               Please Enter your Weight today: ");
		
		//Add a text field & submit button, add them to an hbox
		t  = new TextField();
		submit = new Button("Submit");
		HBox hbox = new HBox(8);
		hbox.getChildren().addAll(weightPls, t, submit);
		
		// Define chart axis's
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date Recorded");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Weight in LB");

        //Define linechart itself
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Weight History");
        lineChart.setLegendVisible(false);

        //Define the series, essentially an object that we will add the data too we want to display 
        series = new XYChart.Series();
        series.setName("Dates");
       
        //Populate the graph with any answers from previous uses of the app
        populateGraph(series);
		
        //Event handler for the submit button
        submit.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	try {
		    		Date date = new Date();
		    		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YY HH:mm:ss");
		    		formatter.format(date);
		    		
		    		Main.db.execute("INSERT INTO weight (WEIGHT, DATE) VALUES("+Integer.parseInt(t.getText()) + ",'" +date+ "');");
		    		addData(series,formatter.format(date), Integer.parseInt(t.getText()));

		    		}
		    		catch(Exception e1)
		    		{ //Illegal input
		    			t.clear();
		    		}
		    }
		});        
       

        //Finalize, append to vbox and then add to the root
        lineChart.getData().add(series);
        VBox vbox2 = new VBox(lineChart);
        vbox2.getChildren().addAll(bottem, hbox);
        root.setCenter(vbox2);
	}
	
	//Adds a new row of data to the graph
	public static void addData(XYChart.Series series, String date, int weight ) {
        series.getData().add(new XYChart.Data(date, weight));

	}
	
	//Initally populate the graph when its created
	public void populateGraph(XYChart.Series series) {
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {
			rs = Main.db.query("SELECT WEIGHT FROM weight");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			rs2 = Main.db.query("SELECT DATE FROM weight");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs2.next()) {
			    series.getData().add(new XYChart.Data(rs2.getString("DATE"), rs.getInt("WEIGHT")));
			    rs.next();
				//System.out.println(rs.getString("DATE"));
				//System.out.println(rs.getDate("DATE"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
