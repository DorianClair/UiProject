package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class Controller {
	
	public void mouseReleased1() {
		System.out.println("Enter info button was pressed");
	    try {
			Main.scene.setRoot(FXMLLoader.load(getClass().getResource("InformationEntry.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void mouseReleased2() {
		System.out.println("chart button was pressed");
		try {
			Main.scene.setRoot(FXMLLoader.load(getClass().getResource("Graph.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void mouseReleased3() {
		System.out.println("settings button was pressed");
		try {
			Main.scene.setRoot(FXMLLoader.load(getClass().getResource("EnterCaloriesCircleChart.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Controller() {
		
	}
}
