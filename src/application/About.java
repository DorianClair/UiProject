package application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class About {
	
	public About(BorderPane root){
		
		root.setCenter(null);
		Pane temp = null;
		try {
			temp = FXMLLoader.<Pane>load(getClass().getResource("About.fxml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        root.setCenter(temp);
        
	}
	
}
