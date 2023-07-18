package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller_5_1 implements Initializable {


    @FXML
    private TextField name_field;
    @FXML
    private TextArea des_field;
    @FXML
    private TextField time_field;
    @FXML
    private ComboBox<String> ttype_field;
    @FXML
    CheckBox checkBox = new CheckBox();

    ObservableList<String> list = FXCollections.observableArrayList("Minutes", "Hour", "Day");

    public void add_quiz(ActionEvent e) throws IOException, SQLException {
        /*Quiz quiz = new Quiz();
        quiz.setName(name_field.getText());
        quiz.setDescription(des_field.getText());
        quiz.setTime_limit(Integer.parseInt(time_field.getText()));
        quiz.setTime_type(ttype_field.getValue());*/

       
        String insert="Insert into quiz_list(name,description,time_limit,time_type) values (";
        insert=insert+"N'"+name_field.getText()+"',";
        insert=insert+"N'"+des_field.getText()+"',";
        if(checkBox.isSelected())
        	insert=insert+time_field.getText()+",";
        else     	
        insert=insert+"99999"+",";
        if(ttype_field.getValue() == null||checkBox.isSelected())
            insert=insert+"N'Minutes')";
        else
            insert=insert+"N'"+ttype_field.getValue()+"')";
        System.out.println(insert);
        Statement statement = Main.connection.createStatement();
        statement.executeQuery(insert);
        Main.setRoot("Gui_1_1");

        /*Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GUI_1_1.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        Controller_1_1 controller = loader.getController();
        controller.set_value(quiz);
        stage.setScene(scene);*/

        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GUI_1_1.fxml"));
        Parent studentViewParent = fxmlLoader.load();
        Controller_1_1 controller = fxmlLoader.getController();
        controller.set_value(quiz);
        Main.setRoot_2(studentViewParent);*/
    }

    public void cancel(ActionEvent e) throws IOException {
        Main.setRoot("Gui_1_1");
    }
    @FXML
    public void switchTo_1_1() throws Exception{
   	 Main.setRoot("Gui_1_1");
   	 //Main.setScreen();
   	
   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ttype_field.setItems(list);
        checkBox.setStyle("-fx-padding: 8 0 0 0;"); // Set top padding of 10 pixels
        time_field.setDisable(true);
        ttype_field.setDisable(true);
        time_field.setDisable(false);
        ttype_field.setDisable(false);
        time_field.setDisable(true);
        ttype_field.setDisable(true);
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
        	if(newValue)
        	{
        		 time_field.setDisable(false);
                 ttype_field.setDisable(false);
        	}
        	else
        	{
        		 time_field.setDisable(true);
                 ttype_field.setDisable(true);
        	}
           
        });
    }
}