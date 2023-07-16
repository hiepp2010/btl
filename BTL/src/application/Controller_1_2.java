package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class Controller_1_2 implements Initializable{
	
  @FXML 
  AnchorPane anchorpane;
  @FXML
  private void console() throws Exception{
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// Main.setCss("Gui_1_2");
		   Main.setRoot("Gui_3_1");
 		// scene.getRoot().setStyle("-fx-background-color: white;");
		}
  @Override
  public void initialize(URL location, ResourceBundle resources)  {
	    
      //double screenWidth = javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
      //anchorpane.setPrefWidth(screenWidth);
    

      // Set the preferred height of the AnchorPane to enable scrolling
    //  anchorpane.setPrefHeight(30.0 * 100);

  }
  
}
