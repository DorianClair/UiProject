package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class View extends Pane {

	Canvas canvas = new Canvas();
	GraphicsContext gc = canvas.getGraphicsContext2D();
	
	public View() {
		
	}
	
	@Override
	public void layoutChildren() {
		draw();
	}

	public void draw() {
		
	}
}