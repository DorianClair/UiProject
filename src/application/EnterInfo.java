package application;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class EnterInfo {
	
	public EnterInfo(BorderPane root) throws SQLException {
		
		root.setCenter(null);
		Pane temp = null;
		try {
			temp = FXMLLoader.<Pane>load(getClass().getResource("InformationEntry.fxml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        root.setCenter(temp);
        
	}
	
}
