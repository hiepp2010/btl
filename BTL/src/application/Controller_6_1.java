package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller_6_1{
    @FXML
    private Label quiz;

    @FXML
    private Label quiz_2;

    @FXML
    private Label time_limit;

    public void switchTo() throws Exception{
        Main.setRoot("Gui_6_2");
    }

    public void switchTo_7_2() throws Exception{
        Main.setRoot("Gui_7_2");
    }

    public void set_quiz(String name_quiz,int time_quiz,String ttype_quiz){
        quiz.setText(name_quiz);
        quiz_2.setText(name_quiz);
        time_limit.setText("Time limit: "+time_quiz+" "+ttype_quiz);
    }

}
