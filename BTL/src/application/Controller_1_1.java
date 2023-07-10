package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class Controller_1_1 implements Initializable {
	

	@FXML
    private AnchorPane top,mid;
    
    @FXML
    private AnchorPane bottom;

    @FXML
    private ScrollPane scroll;
    
    @FXML
    public void switchTo() throws Exception{
    	 Main.setRoot("Gui_1_2");
    	 Main.setCss("Gui_1_2");
    	 //Main.setScreen();
    	
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        top.setPrefWidth(Main.screenWidth);
        mid.setPrefWidth(Main.screenWidth);
        
       

        // Set the preferred height of the AnchorPane to enable scrolling
      //  anchorpane.setPrefHeight(30.0 * 100);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

    }
}
	
	
