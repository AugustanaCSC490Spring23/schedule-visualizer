package application.view;

import java.awt.Dimension;
import java.time.LocalDateTime;
import java.util.ArrayList;

import application.controller.TextHandler;
import application.model.Course;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainView extends Application {
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	protected final double COMPUTER_WIDTH = screenSize.getWidth()-100;
	protected final double COMPUTER_HEIGHT = screenSize.getHeight()-100;
	protected final Image APP_ICON = new Image(getClass().getResourceAsStream("images/logo.png"));
	
	private final ObservableList<String> timeOptions = FXCollections.observableArrayList("AM", "PM");
	/*private Stage stage;
	private Scene startScene;

	private BorderPane root;
	private BorderPane textRoot;
	private BorderPane calendarRoot;

	private Scene textScene;
	private Scene calendarScene;*/

	@Override
	public void start(Stage primaryStage) {
		
		Scene scene = new Scene(initializeWindow(primaryStage), 500, 500);
		
		
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.show();

	}
	
	private VBox initializeWindow(Stage primaryStage) {
		primaryStage.setTitle("Schedulizer");
		primaryStage.getIcons().add(APP_ICON);
		
		GridPane titleGrid = new GridPane();
		titleGrid.getStyleClass().add("title-grid");
		Text appTitle = new Text("schedulizer");
		appTitle.setId("app-title");
		Image signatureIcon = new Image(getClass().getResourceAsStream("images/signature.png"));
		Label signatureLabel = new Label();
		ImageView signatureView = new ImageView(signatureIcon);
		signatureView.setFitHeight(50);
		signatureView.setPreserveRatio(true);
		
		signatureLabel.setGraphic(signatureView);
		titleGrid.add(signatureLabel, 0, 0);
		titleGrid.add(appTitle, 1, 0);
		
		TabPane tabPane = new TabPane();
		tabPane.getStyleClass().add("background-tab");
		setTab1(tabPane);// Parse Text Tab
		setTab2(tabPane);// Create Tab

		VBox vBox = new VBox();
		
		vBox.getChildren().add(titleGrid);
		vBox.getChildren().add(tabPane);
		return vBox;
	}

	/**
	 * setTab1 is to create the Tab of the Parse Text User can use this class to
	 * parse their schedule that they copy from their Arches.
	 * 
	 * @param tabPane
	 */
	private void setTab1(TabPane tabPane) {
		Tab tab1 = new Tab("Parse Text");
		tab1.setClosable(false);
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setHgap(5);
		grid.setVgap(5);
		tab1.getStyleClass().add("parse-text-tab");

		// Text Area
		Text instruction = new Text("Parse your schedule here, we will get your courses schedule for you");
		TextArea textArea = new TextArea();
		textArea.setPrefHeight(1080);
		textArea.setPrefWidth(1920);
		grid.add(instruction, 0, 0);
		grid.add(textArea, 0, 1);

		// Submit Button
		Button submitButton = new Button("Schedulize");
		submitButton.getStyleClass().add("submit-button");
		grid.add(submitButton, 0, 2);

		submitButton.setOnAction(action -> {
			parseTextSchedulize(textArea);
		});

		tab1.setContent(grid);
		tabPane.getTabs().add(tab1);
	}
	
	private void parseTextSchedulize(TextArea textArea) {
		
		if (!textArea.getText().equals("")) {
			TextHandler parseText = new TextHandler(textArea.getText());
			ArrayList<Course> courses = parseText.getCourseArray();
			if (!courses.isEmpty()) {
				CalendarView run = new CalendarView();
				for (Course i : courses) {
					System.out.println(i.toString() + "\n");
				}
			} else {
				System.out.println("Cannot recognize course.");
			}
		} else {
			System.out.println("You entered nothing :< hung owes me $10 for portillos");
			
		}
		
		
	}
	

	/**
	 * setTab2 allows user to manually plug in their schedules class by class
	 * 
	 * @param tabPane
	 */
	private void setTab2(TabPane tabPane) {
		Tab tab2 = new Tab("Create");
		tab2.setClosable(false);
		tab2.getStyleClass().add("parse-text-tab2");
		
		Text instruction2 = new Text("Customize your own schedule here");
		GridPane primaryGrid = new GridPane();
		primaryGrid.setPadding(new Insets(10));
		primaryGrid.setVgap(10);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(15));
		grid.setStyle("-fx-background-color: #E3DE8F; -fx-background-radius: 10px;");
		grid.setHgap(5);
		grid.setVgap(5);
		
		
		// Area to input
		Label label1 = new Label("Course Code");
		label1.setPrefWidth(100);
		TextField courseCode = new TextField();
		grid.add(label1, 0, 0);
		grid.add(courseCode, 1, 0);
		
		Label label2 = new Label("Title");
		TextField courseTitle = new TextField();
		grid.add(label2, 0, 1);
		grid.add(courseTitle, 1, 1);
		
		Label label6 = new Label("Weekdays");
		GridPane weekGrid = new GridPane();
		weekGrid.setPadding(new Insets(5));
		weekGrid.setHgap(5);
		weekGrid.setVgap(5);
		
		CheckBox sun = new CheckBox("Sun");
		CheckBox mon = new CheckBox("Mon");
		CheckBox tue = new CheckBox("Tue");
		CheckBox wed = new CheckBox("Wed");
		CheckBox thu = new CheckBox("Thu");
		CheckBox fri = new CheckBox("Fri");
		CheckBox sat = new CheckBox("Sat");
		
		grid.add(label6, 0, 2);
		weekGrid.add(sun, 0, 0);
		weekGrid.add(mon, 1, 0);
		weekGrid.add(tue, 2, 0);
		weekGrid.add(wed, 3, 0);
		weekGrid.add(thu, 4, 0);
		weekGrid.add(fri, 5, 0);
		weekGrid.add(sat, 6, 0);
		
		grid.add(weekGrid, 1, 2, 3, 1);
		
		GridPane startTimeGrid = new GridPane();
		Label label7 = new Label("Start time");
		TextField startTime = new TextField();
		ComboBox<String> timeOption1 = new ComboBox<String>(timeOptions);
		startTime.setMaxWidth(60);
		timeOption1.setMaxWidth(70);
		grid.add(label7, 0, 3);
		startTimeGrid.add(startTime, 0, 0);
		startTimeGrid.add(timeOption1, 1, 0);
		grid.add(startTimeGrid, 1, 3);
		
		GridPane endTimeGrid = new GridPane();
		Label label8 = new Label("End time");
		ComboBox<String> timeOption2 = new ComboBox<String>(timeOptions);
		TextField endTime = new TextField();
		endTime.setMaxWidth(60);
		timeOption2.setMaxWidth(70);
		grid.add(label8, 2, 3);
		endTimeGrid.add(endTime, 0, 0);
		endTimeGrid.add(timeOption2, 1, 0);
		grid.add(endTimeGrid, 3, 3);
		
		Label label9 = new Label("Location");
		TextField location = new TextField();
		grid.add(label9, 0, 4);
		grid.add(location, 1, 4);
		
		Label label10 = new Label("Room No.");
		TextField roomNumber = new TextField();
		grid.add(label10, 0, 5);
		grid.add(roomNumber, 1, 5);
		
		Label label11 = new Label("Faculty");
		TextField facultyName = new TextField();
		grid.add(label11, 0, 6);
		grid.add(facultyName, 1, 6);
	
		//Add Prompt Texts into text field boxes
		courseCode.setPromptText("REGL-100");
		courseTitle.setPromptText("Intro to Ethics");
		startTime.setPromptText("9:00");
		endTime.setPromptText("10:00");
		location.setPromptText("Old Main");
		roomNumber.setPromptText("209");
		facultyName.setPromptText("Dr. Abbe");
		
		Button addCourseButton = new Button("Add Course");
		addCourseButton.getStyleClass().add("add-course-button");
		
		Button submitButtonCreate = new Button("Schedulize");
		submitButtonCreate.getStyleClass().add("submit-button");
		
		primaryGrid.add(instruction2, 0, 0);
		primaryGrid.add(grid, 0, 1);
		
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.BOTTOM_LEFT);
		hBox.getChildren().add(addCourseButton);
		hBox.getChildren().add(submitButtonCreate);
		
		primaryGrid.add(hBox, 0, 2);
		
		tab2.setContent(primaryGrid);
		tabPane.getTabs().add(tab2);
		System.out.print("This is a message");
		addCourseButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ArrayList<String> classDaysArray = new ArrayList<String>();
				String classDays = "";
				if (sun.isSelected() == true){
					classDays+= "S ";	
					classDaysArray.add("S ");
				}
				if (mon.isSelected() == true){
					classDays+= "M ";
					classDaysArray.add("M ");
				}
				if (tue.isSelected() == true){
					classDays+= "Tu ";
					classDaysArray.add("Tu ");
				}
				if (wed.isSelected() == true){
					classDays+= "W ";
					classDaysArray.add("W ");
				}
				if (thu.isSelected() == true){
					classDays+= "Th ";
					classDaysArray.add("Th ");
				}
				if (fri.isSelected() == true){
					classDays+= "F ";
					classDaysArray.add("F ");
				}
				if (sat.isSelected() == true){
					classDays+= "Sat ";
					classDaysArray.add("Sat ");
				}
				String fullLocation = location.getText() + roomNumber.getText();
				int lengthAList = classDaysArray.size();
				String[] classDaysList = new String[lengthAList];
				String timeOp1 = startTime.getText() + timeOption1.getValue().toString();
				String timeOp2 = endTime.getText() +  timeOption2.getValue().toString();
				Course courseCustom = new Course(courseCode.getText(), courseTitle.getText(), timeOp1, timeOp2, 
						classDaysList, fullLocation,  facultyName.getText());
/*				String[] customSchedule = new String[11];
				customSchedule[0] = courseCode.getText();
				customSchedule[1] = courseTitle.getText();
				customSchedule[5] = classDays;
				customSchedule[6] = timeOp1; 
				customSchedule[7] = timeOp2; 
				customSchedule[8] = location.getText();
				customSchedule[9] = roomNumber.getText();
				customSchedule[10] = facultyName.getText();
			*/	
				// this for Course Class testing
				//Course newCourse = new Course(customSchedule[0], customSchedule[1], customSchedule[3], customSchedule[4], customSchedule[6], customSchedule[7], classDays);
				//System.out.println(newCourse.toString());
				//Debugging and making sure I actually get what I was fucking looking for comment this out or remove it if you dont need it anymore (look in console window)
	//			for (int i = 0; i < 11; i++) {
	//				System.out.println(customSchedule[i]);
	//			}
				
				//Resetting the textfields to beblank
				courseCode.clear();
				courseTitle.clear();
				classDays = null;
				
				sun.setSelected(false);
				mon.setSelected(false);
				tue.setSelected(false);
				wed.setSelected(false);
				thu.setSelected(false);
				fri.setSelected(false);
				sat.setSelected(false);
				
				timeOption1.setValue(null);
				timeOption2.setValue(null);
				startTime.clear(); 
				endTime.clear();
				location.clear();
				roomNumber.clear();
				facultyName.clear();
				
			}
});
	}
		
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
