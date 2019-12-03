package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/* Written and Developed By Dorian Clair
 * 
 * Inputs: The root BorderPane you need to add a helpScreen to
 * 
 * Function: Renders a helpful info onto the  screen
 * Features: render a table of common solutions and answers
 * */

public class HelpScreen {
	public static Button submit;
	public static XYChart.Series series;
	public static TextField t;
	
	public HelpScreen(BorderPane root) {
		//Whatever is in the borderPanes Centre, clear it
		root.setCenter(null);
		
		
	    TableView table = new TableView();

		table.setEditable(true);
		 
        TableColumn issueCol = new TableColumn("Issue");
        TableColumn solutionCol = new TableColumn("Solution");
        
        table.getColumns().addAll(issueCol, solutionCol);
        Label tableTitle = new Label("Common issues & Solutions");
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(tableTitle, table);
        issueCol.setCellValueFactory(
                new PropertyValueFactory<helpIssue, String>("issue"));
        issueCol.setMinWidth(300);
        issueCol.setSortable(false);
        solutionCol.setCellValueFactory(
                new PropertyValueFactory<helpIssue, String>("solution"));
        solutionCol.setMinWidth(400);
        solutionCol.setSortable(false);

        ObservableList<helpIssue> data =
                FXCollections.observableArrayList(
                    new helpIssue("I'm having issues with data not \n loading or not being added", "Delete the database.db file and run the project again"),
                    new helpIssue("I'm getting a large error message", "Attempt closing the program and trying again"),
                    new helpIssue("Nothing is showing up", "Make sure your computer is plugged in \n AND turned on"),
                    new helpIssue("I can't open the file", "Open the project in intellij and press on the green \n circle with an arrow to run the project"),
                    new helpIssue("I get a javafx or sql error", "at the top of eclipse, go to project -> properties \n -> java build path, make sure the javafx/sqlLite jars exist"),
                    new helpIssue("I'm not losing weight", "You need to utilize the app more and perhaps \n donate money to make the app better")
                );
        table.setFixedCellSize(Region.USE_COMPUTED_SIZE);
        table.setItems(data);
		
		Label title = new Label("           Calorie Tracker Pro");
		title.setId("helpTitle");
		Label bottem = new Label("   If you have an issue seperate to the ones listed below, please contact calorieTrackerPro@gmail.com");
        VBox vbox2 = new VBox(8);
        vbox2.getChildren().addAll(title, bottem, vbox);
        root.setCenter(vbox2);
	}
	

}
