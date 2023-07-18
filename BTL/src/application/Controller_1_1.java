package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    public void switchTo_5_1() throws Exception{
        Main.setRoot("Gui_5_1");
    }

    @FXML
    private TableView<Old_Quiz> table;

    @FXML
    private TableColumn<Old_Quiz, String> name_column;

    @FXML
    private TableColumn<Old_Quiz, Integer> time_column;

    @FXML
    private TableColumn<Old_Quiz, String> ttype_column;

    private ObservableList<Old_Quiz> quiz_list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        top.setPrefWidth(Main.screenWidth);
        mid.setPrefWidth(Main.screenWidth);

        // Set the preferred height of the AnchorPane to enable scrolling
      //  anchorpane.setPrefHeight(30.0 * 100);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        /*Label field = new Label("Hang 1");
        Label field_2 = new Label("Hang 1");
        vbox.getChildren().addAll(field,field_2);
        vbox.setPadding(new Insets(10,10,10,20));*/

        quiz_list = FXCollections.observableArrayList();
        List<Old_Quiz> list = new Get_valueSQL().get_list_quiz();
        quiz_list.addAll(list);

        name_column.setCellValueFactory(new PropertyValueFactory<Old_Quiz,String>("name"));
        time_column.setCellValueFactory(new PropertyValueFactory<Old_Quiz,Integer>("time_limit"));
        ttype_column.setCellValueFactory(new PropertyValueFactory<Old_Quiz,String>("time_type"));
        table.setItems(quiz_list);
    }

    public void switchTo_6_1(ActionEvent e) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GUI_6_1.fxml"));
        Parent studentViewParent = fxmlLoader.load();
        Controller_6_1 controller = fxmlLoader.getController();
        ObservableList<Old_Quiz> selected = table.getSelectionModel().getSelectedItems();
        String name_quiz = new String();
        int time_quiz = 0;
        String ttype_quiz = new String();
        for(Old_Quiz a: selected) {
            name_quiz = a.getName();
            time_quiz = a.getTime_limit();
            ttype_quiz = a.getTime_type();
        }

        currentQuiz.curr = name_quiz;
        if(ttype_quiz.equals("Minutes"))
            currentQuiz.time = time_quiz*60;
        if(ttype_quiz.equals("Hours"))
            currentQuiz.time = time_quiz*60*60;
        if(ttype_quiz.equals("Day"))
            currentQuiz.time = time_quiz*60*24*60;
        if(ttype_quiz==null)
        	currentQuiz.time = 99999;

        controller.set_quiz(name_quiz,time_quiz,ttype_quiz);
        Main.setRoot_2(studentViewParent);
    }
}
	
	
