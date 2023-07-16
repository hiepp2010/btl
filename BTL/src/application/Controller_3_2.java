package application;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
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

public class Controller_3_2 implements Initializable{
  
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
	  array.add(view3);
	  array.add(view4);
	  array.add(view5);
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
	  
	  top.setPrefWidth(Main.screenWidth);
      mid.setPrefWidth(Main.screenWidth);    
	  hide();
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
      pointArray.add(combo1);
      pointArray.add(combo2);
      pointArray.add(combo3);
      pointArray.add(combo4);
      pointArray.add(combo5);
      for(ComboBox i:pointArray)
      {
    	  i.setItems(FXCollections.observableArrayList(store.pointArray));
      }
      combo.setItems(FXCollections.observableArrayList(Main.Category));
      
    //  view2.getEngine().loadContent(htmlContent);
  }
  
}
