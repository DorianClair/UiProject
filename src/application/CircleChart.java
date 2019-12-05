package application;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

public class CircleChart extends Pane{
	
	double percentFilled;
	double ratio;
	int height;
	int weight;
	int ppw;
	LocalDate dob;
	int gender;
	int yearsOld;
	int calBudget;
	int calConsumed;
	Canvas c = new Canvas(800,350);
	GraphicsContext gc = c.getGraphicsContext2D();
	TextField t = new TextField();
	Button b = new Button("");
	Color myBlue = Color.rgb(72, 150, 236, 1);
	Color myOrange = Color.rgb(241, 156, 65, 1);
	Color graphColor;
	Point2D center = new Point2D(345,200);
	
	public CircleChart(BorderPane root)
	{
		try {
			init();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		TextField t = new TextField();
		Button b = new Button("Add Calories");
		
		b.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	try{
		    		int add =  (int) Double.parseDouble(t.getText());
				    calConsumed += add;
				   	t.clear();
				   	update(calConsumed);
				   	refresh();
		    		}
		    		catch(Exception e1)
		    		{ //Illegal input
		    			t.clear();
		    		}
		    }
		});
	   	draw();

		HBox h = new HBox();
		h.getChildren().add(t);
		h.getChildren().add(b);
		h.setSpacing(2);
		h.setPadding(new Insets(10,10,50,200));
		
		VBox v = new VBox();
		v.getChildren().addAll(c, h);
		
		root.setCenter(v);
	}
	
	@Override
	public void layoutChildren()
	{
		c.setWidth(800);
		c.setHeight(400);
		draw();
	}
	
	public void draw()
	{		
		if(calBudget != 0)
		{
			percentFilled = (Double.valueOf(calConsumed) / Double.valueOf(calBudget));
		}
		else
		{
			percentFilled = 0;
		}
		
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
		gc.fillRect(0, 0, 800, 300);
		
		gc.setFill(myOrange);
		gc.fillRect(100, 25, 500, 600);
		
		gc.setFill(Color.CORNFLOWERBLUE);
		gc.fillRect(110, 35, 480, 304);
		
		gc.setFill(graphColor);		
		gc.fillArc(center.getX() - 100, center.getY() - 100, 200,  200, 225, -ratio, ArcType.ROUND);		
	
		gc.setFill(Color.CORNFLOWERBLUE);
		gc.fillOval(center.getX() - 90, center.getY() - 90, 180, 180);
	
		gc.setFill(Color.WHITE);
		gc.setFont(new Font("Times", 24));
		gc.fillText("Calorie Tracker", 260, 60);
		gc.setFont(new Font("Times", 14));
		gc.fillText("Daily Budget: " + calBudget + "\nConsumed: " + calConsumed + 
				"\nNet: " + (calConsumed - calBudget), 285, 175);
	}
	
	private void init() throws SQLException {
		ResultSet rs1 = Main.db.query("SELECT date, budget, consumed FROM stuff");
		ResultSet rs2 = Main.db.query("SELECT NAME, DOB, HEIGHT, WEIGHT, PPW, GENDER FROM info");
		
		if(!rs1.next())
		{
			//calConsumed = 0;
			
			if(rs2.next()){
				dob = LocalDate.parse(rs2.getString("DOB"));
				Period diff = Period.between(dob, LocalDate.now());
				yearsOld = diff.getYears();
				weight = Integer.parseInt(rs2.getString("WEIGHT"));
				height = Integer.parseInt(rs2.getString("HEIGHT"));
				ppw = Integer.parseInt(rs2.getString("PPW"));
				gender = Integer.parseInt(rs2.getString("GENDER"));
				
				if(gender == 0)
				{ // female
					calBudget = (int) (((655 + (4.3 * weight) + (4.7 * height) - (4.7 * yearsOld)) * (1.5)) - (500 * ppw));
				}
				else if(gender == 1)
				{ // male
					calBudget = (int) (((66 + (6.3 * weight) + (12.9 * height) - (6.8 * yearsOld)) * (1.5)) - (500 * ppw));
				}
				
				Main.db.execute("INSERT INTO stuff("
						+ "date, budget, consumed) "
						+ "VALUES('" 
						+ Date.valueOf(LocalDate.now()) + "', '"
						+ calBudget + "', '"
						+ calConsumed
						+ "')");
			}
		}
		else
		{
			calConsumed = Integer.parseInt(rs1.getString("consumed"));
			calBudget = Integer.parseInt(rs1.getString("budget"));
		}
		
		rs1.close();
		rs2.close();
		
	}
	
	private void refresh() throws SQLException
	{
			ResultSet rs = Main.db.query("SELECT date, budget, consumed FROM stuff");
			if(rs.next())
			{
				calBudget = Integer.parseInt(rs.getString("budget"));
				calConsumed = Integer.parseInt(rs.getString("consumed"));
			}

			rs.close();
    }
	
	private void update(int c) throws SQLException
	{
		Main.db.execute(  "UPDATE stuff "
				+ "SET "
					+ "date = '" + Date.valueOf(LocalDate.now()) + "', "
					+ "budget = '" + calBudget + "', "
					+ "consumed = '" + calConsumed + "'");
		draw();
	}

}
