 package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class Controller_7_4 implements Initializable{
	
	   @FXML
	   FlowPane flowpane = new FlowPane();
	   int p=0;
	   List<Double> score = new ArrayList<Double>();
	   @FXML
	   Label mark,grade=new Label();
	   @FXML
	   WebView webview;
	   Timeline timeline;
	   int remainingSeconds=3600;
	   String htmlContent = store.ckeditor;
	   @FXML
	   VBox vbox= new VBox();
	   @FXML
	   ScrollPane scrollpane= new ScrollPane();
	   @FXML
	   public void switchTo() throws Exception{
		     double sum=0;
	    	 Main.setRoot("Gui_1_1");
	    	 //Main.setScreen();
	    	
	    }
	  
	  void getData()
	  {
		  try {
		  	    // Connection code from the previous step

		  	    Statement statement = Main.connection.createStatement();
		  	     ResultSet resultSet=statement.executeQuery("Select * from quest_list");
		  	    Quiz.array.clear();
		  	    //System.out.println(insert);
		  	    while (resultSet.next()) {
	    	        String name = resultSet.getString("name");
	    	        String text = resultSet.getString("text");
	    	        String choice1 = resultSet.getString("choice1");
	    	        Double grade1 = resultSet.getDouble("grade1");
	    	        String choice2 = resultSet.getString("choice2");
	    	        Double grade2 = resultSet.getDouble("grade2");
	    	        String choice3 = resultSet.getString("choice3");
	    	        Double grade3 = resultSet.getDouble("grade3");
	    	        String choice4 = resultSet.getString("choice4");
	    	        Double grade4 = resultSet.getDouble("grade4");
	    	        String choice5 = resultSet.getString("choice5");
	    	        Double grade5 = resultSet.getDouble("grade5");
	    	        Question newQuest = new Question(name,text,choice1,grade1,choice2,grade2,choice3,grade3,choice4,grade4,choice5,grade5);
	    	        Quiz.array.add(newQuest);
	    	    }
		  	    
		  	} catch (SQLException e) {
		  	    e.printStackTrace();
		  	}
	  }
	  
	  void initWeb(WebView a, String b,String location)
	  {
		  a.getEngine().loadContent(b);
		  
		 a.setPrefHeight(-1);
          a.getEngine().getLoadWorker().stateProperty().addListener((obj, prev, newv) -> {
          if (newv == Worker.State.SUCCEEDED)  
          {
          String heightText = a.getEngine().executeScript(   // <- Some modification, which gives moreless the same result than the original
                  "var body = document.body,"
                  + "html = document.documentElement;"
                  + "Math.max( body.scrollHeight , body.offsetHeight, "
                  + "html.clientHeight, html.scrollHeight , html.offsetHeight );"
          ).toString();

       //   System.out.println("heighttext: " + heightText);
          Double height = Double.parseDouble(heightText.replace("px", "")) ;  // <- Why are this 15.0 required??
          a.setPrefHeight(height);
          a.getEngine().setUserStyleSheetLocation(getClass().getResource(location+".css").toString());



      //    text.setPrefHeight(height);
          }});
	  }
	  
	  void init()
	  {
		  mark.setText(Quiz.mark+"/"+Quiz.array.size()+".00");
		  grade.setText(Quiz.mark/Quiz.array.size()*10+" out of 10.00("+ Quiz.mark/Quiz.array.size()*100+"%)");
		  vbox.setSpacing(20); 
		  for(int i=0;i<Quiz.array.size();i++) score.add((double)0);
		  for(Question i:Quiz.array)
		  {
			Button navigation = new Button(String.valueOf(p+1));
			navigation.setPrefSize(20,30);
	        navigation.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 2px; -fx-font-weight: bold;");
	        flowpane.getChildren().add(navigation);
			WebView text=new WebView();
			VBox subVbox = new VBox();
			
			subVbox.setPrefWidth(1600);
			subVbox.getChildren().add(text);
			//System.out.println(i.text);
			HBox hbox = new HBox();
			vbox.getChildren().add(hbox);
			AnchorPane anchorpane = new AnchorPane();
	        anchorpane.setStyle("-fx-background-color: rgb(248, 249, 250);");
			Label questionLabel = new Label("Question "+(p+1)+"\nNot yet\nanswered");
			questionLabel.setPrefWidth(100);
	        AnchorPane.setTopAnchor(questionLabel, 10.0);
	        AnchorPane.setLeftAnchor(questionLabel, 10.0);
	        AnchorPane.setRightAnchor(questionLabel, 10.0);

	        Label markLabel = new Label("Marked out of\n1.00");
	        AnchorPane.setTopAnchor(markLabel, 65.0);
	        AnchorPane.setLeftAnchor(markLabel, 10.0);
	        AnchorPane.setRightAnchor(markLabel, 10.0);

	        anchorpane.getChildren().addAll(questionLabel, markLabel);
	        hbox.getChildren().addAll(anchorpane,subVbox);
	        initWeb(text, i.text,"Gui_7_3");
	     
	        List<Integer> list = new ArrayList<Integer>();
	        for(int curr=0;curr<i.choice.size();curr++)
	        {
	        	list.add(curr);
	        }
	       if(Quiz.isShuffle!=0)
	       {
	    	   Collections.shuffle(list);
	       }
	        
	        String res="<p>The correct answer is:</p>";
	        if(i.type==1)
	        {
	            char index1='A';
	        	ToggleGroup toggleGroup = new ToggleGroup();
	        	for(int index: list)
	        	{
	        		HBox hboxChoice= new HBox();
	        		hbox.setPrefWidth(1600);
	                hbox.setStyle("-fx-background-color:  rgb(231,243,245);"); // Set the background color using an inline style
	                RadioButton radioButton = new RadioButton(Character.toString(index1));
	                index1++;
	                radioButton.setPrefWidth(50);
	                radioButton.setStyle("-fx-padding: 8 0 0 0;"); // Set top padding of 10 pixels
	                radioButton.setToggleGroup(toggleGroup);
	                final int q=p;
	                toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
	                    if (newValue == radioButton) {
	                    	System.out.println(q);
	                        score.set(q,i.grade.get(list.get(index)));
	                }});
		        	WebView choice= new WebView();
		        	choice.setPrefWidth(1550);
		        	subVbox.getChildren().add(hboxChoice);
		        	hboxChoice.getChildren().addAll(radioButton,choice);
		        	initWeb(choice,i.choice.get(list.get(index)),"Gui_7_3");
		        	if(i.grade.get(list.get(index))>0)
		        		res+=i.choice.get(list.get(index));
		         
	        	}
	        	 
	        }
	        else
	        {
	        	    char index1='A';
		        	ToggleGroup toggleGroup = new ToggleGroup();
		        	for(int index: list)
		        	{
		        		HBox hboxChoice= new HBox();
		                CheckBox checkBox = new CheckBox(Character.toString(index1));
		                hbox.setStyle("-fx-background-color:  rgb(231,243,245);"); // Set the background color using an inline style
		                checkBox.setPrefWidth(50);
		                index1++;
		                checkBox.setStyle("-fx-padding: 8 0 0 0;"); // Set top padding of 10 pixels
		                final int q=p;
		                checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
		                    if (newValue) {
		                        score.set(q,score.get(q)+i.grade.get(list.get(index)));
		                        
		                    } else {
		                        score.set(q,score.get(q)-i.grade.get(list.get(index)));
		                    }
		                });
			        	WebView choice= new WebView();
			        	choice.setPrefWidth(1550);
			        	subVbox.getChildren().add(hboxChoice);
			        	hboxChoice.getChildren().addAll(checkBox,choice);
			        	initWeb(choice,i.choice.get(list.get(index)),"Gui_7_3");
			        	if(i.grade.get(list.get(index))>0)
			        		res+=i.choice.get(list.get(index));	
		        	}
	        }
	        p++;
	        WebView Answer=new WebView();
	        initWeb(Answer, res,"Gui_7_4");
	        Answer.setPrefWidth(1550);
	        subVbox.getChildren().add(Answer);

	        navigation.setOnAction(e -> {
	        	double vValue = hbox.getLayoutY();
	        	Bounds bounds = scrollpane.getViewportBounds();
	        	scrollpane.setVvalue(vValue *
                        (1/(vbox.getHeight()-bounds.getHeight())));
	           // scrollpane.setVvalue(vValue);
	            System.out.println(vValue);
	        });
		  }
		 
		 
	  }
	  @Override
	  public void initialize(URL location, ResourceBundle resources) {
	    //  getData();
	      init();

	  }
}
