package application;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Get_valueSQL {
    ResultSet rs = null;
    public List<Old_Quiz> get_list_quiz(){
        try{
            String query = "select * from quiz_list";
          
            Statement statement = Main.connection.createStatement();
            rs = statement.executeQuery(query);
            List<Old_Quiz> list = new ArrayList<>();
            while (rs.next()){
                Old_Quiz a = new Old_Quiz(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                list.add(a);
            }
            return  list;
        } catch (Exception e){

        }
        return null;
    }

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
