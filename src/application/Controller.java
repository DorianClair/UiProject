package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {
	
	
	//This is for the information entry
	//START
	@FXML public Label USRMSG;
	@FXML public Rectangle MSGBOXA, MSGBOXB;
	@FXML public TextField NAME = new TextField();
	@FXML public ToggleGroup tg = new ToggleGroup();
	@FXML public ToggleButton TGLBTNM = new ToggleButton();
	@FXML public ToggleButton TGLBTNF = new ToggleButton();
	@FXML public DatePicker DOB = new DatePicker();
	@FXML public TextField HEIGHT = new TextField();
	@FXML public TextField WEIGHT = new TextField();
	@FXML public TextField PPW = new TextField();
	
	private ToggleButton selectedTg;
	private LocalDate dob;
	private int gender;

	@FXML
	protected void initialize() throws SQLException {
		ResultSet rs2 = Main.db.query("SELECT NAME, DOB, HEIGHT, WEIGHT, PPW, GENDER FROM info");
		if(rs2.next()) {
			gender = Integer.parseInt(rs2.getString("GENDER"));
			if(gender >= 1) {
				tg.selectToggle(TGLBTNM);
			}
			else {
				tg.selectToggle(TGLBTNF);
			}
			NAME.setText(rs2.getString("NAME"));
			DOB.setValue(LocalDate.parse(rs2.getString("DOB")));
			HEIGHT.setText(rs2.getString("HEIGHT"));
			WEIGHT.setText(rs2.getString("WEIGHT"));
			PPW.setText(rs2.getString("PPW"));
		}
		else {
			NAME.setPromptText("Name...");
			DOB.setPromptText("mm/dd/yyyy");
			HEIGHT.setPromptText("Inc...");
			WEIGHT.setPromptText("Lbs...");
			PPW.setPromptText("Lbs...");
		}
		
	}

	@FXML
    private void submit(){
		try{
			ResultSet rs = Main.db.query("SELECT NAME, DOB, HEIGHT, WEIGHT, PPW, GENDER FROM info");
			if(!rs.next()){			
				dob = DOB.getValue();
				selectedTg = (ToggleButton) tg.getSelectedToggle();
				gender = ("Male".equals(selectedTg.getText())) ? 1 : 0;
				Main.db.execute("INSERT INTO info("
											+ "NAME, DOB, HEIGHT, WEIGHT, PPW, GENDER) "
											+ "VALUES('" 
											+ NAME.getText() + "', '"
											+ dob + "', "
											+ Integer.parseInt(HEIGHT.getText()) + ", "
											+ Integer.parseInt(WEIGHT.getText()) + ", "
											+ Integer.parseInt(PPW.getText()) + ", "
											+ gender
											+ ")");
				setMSG(1,"Information has been added");
			}
			else{
				dob = DOB.getValue();
				selectedTg = (ToggleButton) tg.getSelectedToggle();
				gender = ("Male".equals(selectedTg.getText())) ? 1 : 0;
				Main.db.execute(  "UPDATE info "
								+ "SET "
									+ "NAME ='" + NAME.getText() + "', "
									+ "DOB ='" + dob + "', "
									+ "HEIGHT = " + Integer.parseInt(HEIGHT.getText()) + ", "
									+ "WEIGHT = " + Integer.parseInt(WEIGHT.getText()) + ", "
									+ "PPW = " + Integer.parseInt(PPW.getText()) + ", "
									+ "GENDER = " + gender);
				setMSG(1,"Information has been updated");
			}
			rs.close();
		}
		catch (NumberFormatException e) {
			setMSG(0,"Please check that height, goal weight, and pounds per week are numbers");
			
		}
		catch (SQLException e) {
			setMSG(0,"Please check that all fields are filled in");
		}
		catch (NullPointerException e) {
			setMSG(0,"Please check that all fields are filled in");
		}
    }
	
	//END
	private void setMSG(int type, String str) {
		MSGBOXA.setVisible(true);
		MSGBOXB.setVisible(true);
		if(type <= 0) {
			MSGBOXB.setFill(Color.RED);
		}
		else {
			MSGBOXB.setFill(Color.GREEN);
		}
		USRMSG.setTextFill(Color.WHITE);
		USRMSG.setText(str);
	}
	
	
	
	
	//User clicks the enter info Icon
	public void mouseReleased1() throws SQLException {
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
	//User clicks on the help Icon
		public void mouseReleased5() {
			new HelpScreen(Main.root);
			System.out.println("settings button was pressed");
		}

	public Controller() {
	}
	

}
