package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller_6_2 implements Initializable {
    @FXML
    public void switchTo_6_3() throws Exception{
        Main.setRoot("Gui_6_3");
    }

    @FXML
    public void switchTo_6_4() throws Exception{
        Main.setRoot("Gui_6_4");
    }
    
    @FXML
    public void switchTo_1_1() throws Exception{
   	 Main.setRoot("Gui_1_1");
   	 //Main.setScreen();
   	
   }

    @FXML
    private Label quiz;

    @FXML
    private Label quiz_2;

    @FXML
    private VBox vbox;

    @FXML
    private Label Total_ques;

    @FXML
    private Label Total_marks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quiz.setText(currentQuiz.curr);
        quiz_2.setText(currentQuiz.curr);

        Total_marks.setText("Total of marks: "+Cate_ques_list.choose.size()+".00");
        Total_ques.setText("Question: "+Cate_ques_list.choose.size());
        int cnt = 0;
        for(Cate_ques a:Cate_ques_list.choose) {
            HBox hbox = new HBox();

            TextField field = new TextField();
            field.setText(String.valueOf(cnt+1));
            field.setMaxSize(25,15);

            Label label = new Label();
            label.setText(a.getQues()+" "+a.getText());
            label.setMinWidth(700);
            label.setMaxWidth(700);

            Button delete = new Button();
            delete.setText("Delete");

            TextField grade = new TextField();
            grade.setText("1.00");
            grade.setMaxWidth(50);

            hbox.setSpacing(10);
            hbox.setPadding(new Insets(10, 10, 10, 10));
            hbox.getChildren().addAll(field, label,delete,grade);
            vbox.getChildren().add(hbox);
            cnt++;

            delete.setOnAction(e ->{
                try {
                    Cate_ques_list.choose.remove(a);
                    Main.setRoot("Gui_6_2");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }

    public void Save(ActionEvent event) throws IOException, SQLException {
       
        for(Cate_ques a:Cate_ques_list.choose) {
            String insert = "Insert into quiz_ques(name,ques_name) values (";
            insert = insert + "N'" + currentQuiz.curr + "',N'" + a.getQues() + "')";
            try {
                // Connection code from the previous step
                Statement statement = Main.connection.createStatement();
                statement.executeQuery(insert);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Main.setRoot("Gui_6_1");
    }

    @FXML
    private CheckBox is_shuffle;
    public void Is_shuffle(ActionEvent e) throws IOException {
        if(is_shuffle.isSelected())
            currentQuiz.isShuffle = 1;
        else
            currentQuiz.isShuffle = 0;
        System.out.println(currentQuiz.isShuffle);
    }

}
