package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class Controller_7_2 implements Initializable{
	
  @FXML 
  AnchorPane screen;
  
  @FXML
  void start() throws Exception {
	  Main.setRoot("Gui_7_3");
  }
  
  @FXML
  void cancel() throws Exception {
	  Main.setRoot("Gui_6_1");
  }
  
  @FXML
  void export() {
	  
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
      
	  screen.setPrefWidth(Main.screenWidth);
  }
  
}
