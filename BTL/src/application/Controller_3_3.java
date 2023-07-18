package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller_3_3 implements Initializable{
	
  
  @FXML
  public  ComboBox<String> comboBox= new ComboBox<>();
  
  @FXML
  public TextField name = new TextField();

  
  @FXML 
  public void submit() {
      String text = name.getText();
      String cate="default";
      cate = comboBox.getValue();
      if(text.isEmpty()) 
      {
    	  Alert errorAlert = new Alert(AlertType.ERROR);
		  errorAlert.setHeaderText("Input not valid");
		  errorAlert.setContentText("");
		  errorAlert.showAndWait();
      }
      else
      {
    	  try {
      		Statement statement = Main.connection.createStatement();
      	    ResultSet resultSet = statement.executeQuery("Insert into cate_sub values("+initial(cate)+","+initial(text)+")");
      	}
      	catch (SQLException e) {
      	    e.printStackTrace();
      	}
    	try {
    		  Main.Category.clear();
    		  Main.Category1.clear();
    		  Main.initialCategory(cate, 0);
    		  Main.initialCategory1(cate, 0);
    		  Main.setRoot("Gui_3_1");
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
      }

  }
  
  public String initial(String a)
  {
	  return "'"+a.trim()+"'";
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
      comboBox.setItems(FXCollections.observableArrayList(Main.Category1));
  }
  
}