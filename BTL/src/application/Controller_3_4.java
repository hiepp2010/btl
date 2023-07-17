package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller_3_4 implements Initializable{
  File file;
  @FXML
  private AnchorPane ap;
  @FXML 
  private ImageView imageView;
  @FXML 
  private Label label;
 
  @FXML
  public void ChooseFile(ActionEvent e) {
	  Stage stage = (Stage) ap.getScene().getWindow();
	  FileChooser fc = new FileChooser();
	  fc.setTitle("Choose a text or doc file");
	  FileChooser.ExtensionFilter textFilter= new FileChooser.ExtensionFilter("Text file","*.doc","*.txt","*.docx");
	  fc.getExtensionFilters().add(textFilter);
	   file = fc.showOpenDialog(stage);
	  if(file != null) {
		 label.setText(file.getName());
		  
	  }
  }
  
  @FXML
  public void Import() throws Exception{
	  String result=Controller_4_1.Check_aiken(file);
	  if(result.charAt(0)=='S')
	  {
		  Alert errorAlert = new Alert(AlertType.INFORMATION);
		  errorAlert.setHeaderText("OK");
		  errorAlert.setContentText(result);
		  errorAlert.showAndWait();
	  }
	  else
	  {
		  Alert errorAlert = new Alert(AlertType.INFORMATION);
		  errorAlert.setHeaderText("Input not valid");
		  errorAlert.setContentText(result);
		  errorAlert.showAndWait();
	  }
	  Main.setRoot("Gui_3_1");
	  System.out.println(result);
  }
  @Override
  public void initialize(URL location, ResourceBundle resources) {
      label.setOnDragOver(event -> {
          if (event.getGestureSource() != label && event.getDragboard().hasFiles()) {
              event.acceptTransferModes(TransferMode.COPY);
          }
          event.consume();
      });

      label.setOnDragDropped(event -> {
          Dragboard dragboard = event.getDragboard();
          boolean success = false;
          if (dragboard.hasFiles()) {
              file = dragboard.getFiles().get(0); // Get the first file
              label.setText(file.getName()); // Display the file name on the label
              success = true;
          }
          event.setDropCompleted(success);
          event.consume();
      });
      
      
      
  }
}
