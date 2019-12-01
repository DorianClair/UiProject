package application;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

public class CircleChart extends Pane{
	
	double percentFilled;
	double ratio;
	Canvas c = new Canvas();
	GraphicsContext gc = c.getGraphicsContext2D();
	TextField t = new TextField();
	Button b = new Button("");
	Color myBlue = Color.rgb(72, 150, 236, 1);
	Color myOrange = Color.rgb(241, 156, 65, 1);
	Color graphColor;
	Point2D center = new Point2D(200,200);
	
	public CircleChart()
	{
		this.getChildren().addAll(c, t, b);
	}
	
	@Override
	public void layoutChildren()
	{
		c.setWidth(500);
		c.setHeight(400);
		draw();
	}
	
	public void draw()
	{
		percentFilled = Main.CIRCLE_INFO.getAmount() / Main.CIRCLE_INFO.getMax();
		if(percentFilled > 1.0)
		{
			percentFilled = 1.0;
			graphColor = Color.RED;
		}
		else if(percentFilled < 0.0)
		{
			percentFilled = 0.0;
		}
		else
		{
			graphColor = myOrange;
		}
		
		ratio = percentFilled * 270;
		
		gc.setFill(myBlue);
		gc.fillRect(0, 0, 500, 500);
		
		
		gc.setFill(graphColor);		
		gc.fillArc(center.getX() - 100, center.getY() - 100, 200,  200, 225, -ratio, ArcType.ROUND);		
	
		gc.setFill(myBlue);
		gc.fillOval(center.getX() - 90, center.getY() - 90, 180, 180);
	
		gc.setFill(Color.WHITE);
		gc.setFont(new Font("Times", 24));
		gc.fillText("Calorie Tracker", 110, 60);
		gc.setFont(new Font("Times", 14));
		gc.fillText("Daily Budget: " + (int) Main.CIRCLE_INFO.getMax() + "\nConsumed: " + (int) Main.CIRCLE_INFO.getAmount() + 
				"\nNet: " + (int)(Main.CIRCLE_INFO.getAmount() - Main.CIRCLE_INFO.getMax()), 142, 175);
	}

}
