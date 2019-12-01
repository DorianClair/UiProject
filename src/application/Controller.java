package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;

public class Controller {
	@FXML
    private void submit() {
		System.out.println("TEST");
    }
	//User clicks the enter into Icon
	public void mouseReleased1() {
		System.out.println("Enter info button was pressed");
		new EnterInfo(Main.root);
	}
	//User clicks the graph Icon
	public void mouseReleased2() {
		System.out.println("chart button was pressed");
		new ChartScreen(Main.root);
	}
	
	//User clicks on the clipBoard Icon
	public void mouseReleased4() {
		new CircleChart(Main.root);
		System.out.println("Clipboard button was pressed");
	}

	
	//User clicks on the settings Icon
	public void mouseReleased3() {
		System.out.println("settings button was pressed");
	}

	public Controller() {
		
	}
}
