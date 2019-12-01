package application;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


public class ChartScreen {
	
	public ChartScreen(BorderPane root) {
		root.setCenter(null);
		//root.getChildren().clear();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println();

		Label bottem = new Label("                                            Current date: " + formatter.format(date) + "  |  " + "Estimated Goal Date: 18-12-2019 ");
		bottem.setId("dateLabel");
		Label weightPls = new Label("                               Please Enter your Weight today: ");
		weightPls.setId("weightPlsId");
		TextField t  = new TextField();
		Button submit = new Button("Submit");
		HBox hbox = new HBox(8);
		hbox.getChildren().addAll(weightPls, t, submit);
		submit.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	try {

		    		Main.db.execute("INSERT INTO weight VALUES(44," + Integer.parseInt(t.getText()) + ",'" +date+ "');");
		    		System.out.println("hey1");

		    		ResultSet rs = Main.db.query("SELECT * FROM weight");
		    		while(!rs.next()) {
			    		System.out.println("hey2");

		    			System.out.println(rs + "");
		    		}
		    	}
		    		catch(Exception e1)
		    		{ //Illegal input
		    			t.clear();
		    		}
		    }
		});

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
        vbox2.getChildren().addAll(bottem, hbox);
       // root.getChildren().add(vbox2);
        //root.setAlignment(vbox2, Pos.CENTER);
        //root.se
        //root.setBottom(bottem);
        root.setCenter(vbox2);
	}

}
