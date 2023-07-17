package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class Controller_3_2_1 implements Initializable{
  
  @FXML
  WebView view1,view2,view3,view4,view5,textview;
  
  ArrayList<WebView> array = new ArrayList<>();
  ArrayList<ComboBox> pointArray = new ArrayList<>();
  
  @FXML 
  TextField name;
  
  @FXML
  VBox vbox3,vbox4,vbox5,vbox;
  
  @FXML
  ComboBox<String> combo1,combo2,combo3,combo4,combo5,combo;
  
  @FXML 
  Button morechoice,savechanges1,savechanges,cancel,savechanges2,savechanges11,cancel1;
  
  @FXML 
  AnchorPane top,bottom,mid;
  
  String htmlContent = store.ckeditor;
  
  void hide()
  {
	  vbox3.setVisible(false);
	  vbox4.setVisible(false);
	  vbox5.setVisible(false);
	  cancel1.setVisible(false);
	  savechanges11.setVisible(false);
	  savechanges2.setVisible(false);
	  
	 // vbox.getChildren().remove(vbox3);
	//  vbox.getChildren().remove(vbox4);
	 // vbox.getChildren().remove(vbox5);
	//  vbox.requestLayout();
	  
	 
  }
  
  @FXML 
  void blank()
  {
	  morechoice.setVisible(false);
	  cancel.setVisible(false);
	  savechanges1.setVisible(false);
	  savechanges.setVisible(false);
	  vbox3.setVisible(true);
	  vbox4.setVisible(true);
	  vbox5.setVisible(true);
	  cancel1.setVisible(true);
	  savechanges11.setVisible(true);
	  savechanges2.setVisible(true);

  }
  
  public static String removeLastCharAndMinus(String str) {
      if (str.length() > 0) {
          if (str.charAt(0) == '-') {
              str = str.substring(1);
          }
          str = str.substring(0, str.length() - 1);
      }
      return str;
  }
  
  @FXML 
  void savechanges1()
  {
	  String insert="Insert into quest_list(name,text,choice1,grade1,choice2,grade2,choice3,grade3,choice4,grade4,choice5,grade5) values (";
	  String questionName=name.getText();
	  String cateName=combo.getValue();
	  if(cateName==null) cateName="default";
	  insert=insert+"'"+questionName+"',";
	  String questionText=textview.getEngine().executeScript("editor.getData()").toString();
	  insert=insert+"'"+questionText+"'";
	  int j=0;
      ArrayList<String > stringInsert= new ArrayList();
	  for(WebView i:array)
	  {
		  
          String data = i.getEngine().executeScript("editor.getData()").toString();
          //System.out.println("CKEditor Data: " + data);
          double point=0;
          System.out.println(data);
          if(!data.isEmpty())
          {
        	  Object current=pointArray.get(j).getValue();
        	  String curr;
        	  if(current==null) curr="";
        	  else curr=removeLastCharAndMinus(current.toString());
        	  if(curr.isEmpty()||curr=="None")
        	  {
        		  point=0;
        	  }
        	  else
        	  {
        	  point=Double.parseDouble(curr);
        	  }
        	  insert=insert+",'"+data+"',"+point;
        	  stringInsert.add("");
          }
          j++;
	  }
	  while(stringInsert.size()<5)
      {
    	  insert=insert+",'',0";
    	  stringInsert.add("''");
      }
	  insert=insert+')';
	  try {
  	    // Connection code from the previous step

  	    Statement statement = Main.connection.createStatement();
  	    statement.executeQuery(insert);
  	    //System.out.println(insert);
  	    
  	} catch (SQLException e) {
  	    e.printStackTrace();
  	}
  	 try {
  		    Statement statement = Main.connection.createStatement();
  		    System.out.println("asd");
    	    statement.executeQuery("Insert into cate_ques values ('"+cateName+"','"+questionName+"')");
  	 }
  	catch (SQLException e) {
  	    e.printStackTrace();
  	}
  }
  @FXML
  void savechanges() throws Exception
  {
	  savechanges1();
	  Main.setRoot("Gui_3_1");
  }
  @FXML
  void cancel() throws Exception
  {
	   Main.setRoot("Gui_3_1");
  }
  
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
      Question newQuest = new Question();
	  top.setPrefWidth(Main.screenWidth);
      mid.setPrefWidth(Main.screenWidth);    
	  hide();
	  DecimalFormat df = new DecimalFormat("#.#####");
	 // df.setRoundingMode(RoundingMode.CEILING);
	  
	//  vbox.requestLayout();
      //webview.getEngine().loadContent(htmlContent);
	  textview.getEngine().loadContent(htmlContent);
      view1.getEngine().loadContent(htmlContent);
      view2.getEngine().loadContent(htmlContent);
      view3.getEngine().loadContent(htmlContent);
      view4.getEngine().loadContent(htmlContent);
      view5.getEngine().loadContent(htmlContent);
      array.add(view1);
      array.add(view2);
      array.add(view3);
	  array.add(view4);
	  array.add(view5);
	  
      pointArray.add(combo1);
      pointArray.add(combo2);
      pointArray.add(combo3);
      pointArray.add(combo4);
      pointArray.add(combo5);
      for(ComboBox i:pointArray)
      {
    	  i.setItems(FXCollections.observableArrayList(store.pointArray));
      }
      if(newQuest.choice.size()>2) blank();
      
      
      combo.setItems(FXCollections.observableArrayList(Main.Category));
      try {
	  	    // Connection code from the previous step

	  	    Statement statement = Main.connection.createStatement();   	  	  
	  	    ResultSet resultSet=statement.executeQuery("Select * from cate_ques where ques ='"+QuestionEdit.name+"'");
	  	    if(resultSet.next())
	  	    {
	  	    	combo.setValue(resultSet.getString("cate"));
	  	    }
	  	    
	  	    
	        // System.out.println(df.format(d));
	    }
	  	    
	  	 catch (SQLException e) {
	  	    e.printStackTrace();
	  	}
	  try {
	  	    // Connection code from the previous step

	  	    Statement statement = Main.connection.createStatement();   	  	  
	  	    ResultSet resultSet=statement.executeQuery("Select * from quest_list where name ='"+QuestionEdit.name+"'");
	  	    if (resultSet.next()) {
	        String name = resultSet.getString("name");
	        String text = resultSet.getString("text");
	        String choice1 = resultSet.getString("choice1");
	        Double grade1 = resultSet.getDouble("grade1");
	        String choice2 = resultSet.getString("choice2");
	        Double grade2 = resultSet.getDouble("grade2");
	        String choice3 = resultSet.getString("choice3");
	        Double grade3 = resultSet.getDouble("grade3");
	        String choice4 = resultSet.getString("choice4");
	        Double grade4 = resultSet.getDouble("grade4");
	        String choice5 = resultSet.getString("choice5");
	        Double grade5 = resultSet.getDouble("grade5");
	         newQuest = new Question(name,text,choice1,grade1,choice2,grade2,choice3,grade3,choice4,grade4,choice5,grade5);
	        // System.out.println(df.format(d));
	    }
	  	    
	  	} catch (SQLException e) {
	  	    e.printStackTrace();
	  	}
	    if(newQuest.choice.size()>2) blank();
	    for(int i=0;i<newQuest.choice.size();i++)
	    {
	    	WebView j=array.get(i);
	    	String p=newQuest.choice.get(i);
	    	ComboBox q=pointArray.get(i);
	    	//System.out.println(p);
	    	String questionText=textview.getEngine().executeScript("editor.getData()").toString();
	    	//j.getEngine().executeScript("editor.setData('"+p+"')");
	    	if(newQuest.grade.get(i)!=0)
	    	q.setValue(df.format(newQuest.grade.get(i).doubleValue())+"%");
	    	else
	    		q.setValue("None");
	    	
	    }
      
    //  view2.getEngine().loadContent(htmlContent);
  }
  
}
