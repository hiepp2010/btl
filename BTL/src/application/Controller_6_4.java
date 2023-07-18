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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller_6_4 implements Initializable {
    @FXML
    private VBox vbox;

    @FXML
    private ComboBox<String> cate_list;

    @FXML
    private ComboBox<Integer> random_ques;

    @FXML
    CheckBox also = new CheckBox();

    ObservableList<String> list = FXCollections.observableArrayList(Main.Category1);

    ObservableList<Integer> num_ques;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cate_list.setItems(list);
        num_ques = FXCollections.observableArrayList();
        int cnt=0;
        for(Cate_ques a:Cate_ques_list.array) {
            HBox hbox = new HBox();
            Label label = new Label();
            label.setText(a.getQues()+" "+a.getText());
            hbox.setSpacing(10);
            hbox.setPadding(new Insets(10, 10, 10, 10));
            hbox.getChildren().addAll(label);
            vbox.getChildren().add(hbox);
            cnt++;
            num_ques.add(cnt);
        }
        random_ques.setItems(num_ques);
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
            for(String s:full)
                if(a.getCate().trim().equals(s))
                    Cate_ques_list.array.add(a);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GUI_6_4.fxml"));
        Parent studentViewParent = fxmlLoader.load();
        Controller_6_4 controller = fxmlLoader.getController();
        controller.set_cate(name_cate);
        Main.setRoot_2(studentViewParent);

    }

    public void set_cate(String name_cate){
        cate_list.setValue(name_cate);
    }

    public void add_question(ActionEvent e) throws IOException {
        int dem = random_ques.getValue();
        Cate_ques_list.choose.clear();
        List<Integer> rand = new ArrayList<Integer>();
        for(int curr=0;curr<Cate_ques_list.array.size();curr++)
        {
        	rand.add(curr);
        }
        java.util.Collections.shuffle(rand);
        for(int i:rand)
        {
            Cate_ques_list.choose.add(Cate_ques_list.array.get(i));
            dem--;
            if(dem==0)
                break;
        }
        Main.setRoot("GUI_6_2");
    }
}
