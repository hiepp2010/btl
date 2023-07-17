package application;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Get_valueSQL {
    ResultSet rs = null;
  

    public List<Cate_ques> get_cate_ques(){
        try{
            String query = "select * from cate_ques";
            Statement statement = Main.connection.createStatement();
            rs = statement.executeQuery(query);
            List<Cate_ques> list = new ArrayList<>();
            while (rs.next()){
                Cate_ques a = new Cate_ques(rs.getString(1), rs.getString(2),rs.getString(3));
                list.add(a);
            }
            return  list;
        } catch (Exception e){

        }
        return null;
    }
}
