package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller_3_4 implements Initializable{
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
	  File file = fc.showOpenDialog(stage);
	  if(file != null) {
		 label.setText(file.getName());
		  
	  }
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
              File file = dragboard.getFiles().get(0); // Get the first file
              label.setText(file.getName()); // Display the file name on the label
              success = true;
          }
          event.setDropCompleted(success);
          event.consume();
      });
  }
}
