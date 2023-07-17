package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller_3_1 implements Initializable{
	
    @FXML
    AnchorPane top;

    @FXML
    AnchorPane mid;

    @FXML
    AnchorPane bottom;
    
    @FXML
    CheckBox also = new CheckBox();

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

    @FXML
    VBox vbox;

    @FXML
    private ComboBox<String> cate_list;

    ObservableList<String> list = FXCollections.observableArrayList(Main.Category1);

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

        cate_list.setItems(list);
        for(Cate_ques a:Cate_ques_list.array) {
            HBox hbox = new HBox();
            CheckBox checkbox = new CheckBox();
            Label label = new Label();
            label.setText(a.getQues()+" "+a.getText());
            label.setMinWidth(1030);
            Button button = new Button();
            button.setText("Edit");
            hbox.setSpacing(10);
            hbox.setPadding(new Insets(10, 10, 10, 10));
            hbox.getChildren().addAll(checkbox, label,button);
            vbox.getChildren().add(hbox);
            button.setOnAction(e ->{
                try {
                    QuestionEdit.name = a.getQues();
                    System.out.println(QuestionEdit.name);
                    Main.setRoot("Gui_3_2_1");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }

    public void Select_category(ActionEvent e) throws IOException {
        String name_cate = cate_list.getValue();
        ArrayList<String> full =new ArrayList();
        full.add(name_cate.trim());
        if(also.isSelected())
        {
        try {
    		Statement statement = Main.connection.createStatement();
    	    ResultSet resultSet = statement.executeQuery("Select sub from cate_sub where parent='"+name_cate.trim()+"'");
    	    while(resultSet.next())
    	    {
    	    	full.add(resultSet.getString("sub").trim());
    	    }
    	}
        
    	catch (SQLException f) {
    	    f.printStackTrace();
    	}
        }
        Cate_ques_list.array.clear();
        List<Cate_ques> ques_list = new Get_valueSQL().get_cate_ques();
        for(Cate_ques a:ques_list)
        {
        	//System.out.println(a.getCate());
        	for(String s:full)
        	{
            if(a.getCate().trim().equals(s))
            {
                Cate_ques_list.array.add(a);
            }
        	}
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GUI_3_1.fxml"));
        Parent studentViewParent = fxmlLoader.load();
        Controller_3_1 controller = fxmlLoader.getController();
        controller.set_cate(name_cate);
        Main.setRoot_2(studentViewParent);
        if(also.isSelected())
        {
        	controller.set_also();
        }
       // System.out.println(Cate_ques_list.array.size());

    }

    public void set_cate(String name_cate){
        cate_list.setValue(name_cate);
    }
    public void set_also(){
        also.setSelected(true);
    }
}
