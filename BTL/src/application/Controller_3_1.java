package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class Controller_3_1 implements Initializable{
	
  @FXML 
  AnchorPane top;
  
  @FXML 
  AnchorPane mid;
  
  @FXML 
  AnchorPane bottom;
  
  @FXML
  public  ComboBox<String> comboBox= new ComboBox<>();
  ArrayList<String> itemList = new ArrayList<>();
  @FXML
  private void switchTo3_2() throws Exception{
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// Main.setCss("Gui_1_2");
		   Main.setRoot("Gui_3_2");
 		// scene.getRoot().setStyle("-fx-background-color: white;");
		}
  @FXML
  private void switchTo3_3() throws Exception{
	   Main.setRoot("Gui_3_3");
	   
  }
  @FXML
  private void switchTo3_4() throws Exception{
	   Main.setRoot("Gui_3_4");
	   
 }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
      
	  top.setPrefWidth(Main.screenWidth);
      mid.setPrefWidth(Main.screenWidth);    
      comboBox.setItems(FXCollections.observableArrayList(Main.Category));
      
      /*for (int i = 0; i < 100; i++) {
          Button button = new Button("Button " + (i + 1));
          bottom.setTopAnchor(button, 30.0 * i);
          bottom.setLeftAnchor(button, 1.0);
          bottom.getChildren().add(button);

          // Set positioning constraints for each button
      }*/

      // Set the preferred height of the AnchorPane to enable scrolling
    //  anchorpane.setPrefHeight(30.0 * 100);
 //     scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
      

   


  }
  
}
