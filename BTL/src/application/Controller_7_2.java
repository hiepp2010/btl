package application;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ironsoftware.ironpdf.License;
import com.ironsoftware.ironpdf.PdfDocument;
import com.ironsoftware.ironpdf.security.SecurityManager;
import com.ironsoftware.ironpdf.security.SecurityOptions;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class Controller_7_2 implements Initializable{
  String content=" ";
  @FXML 
  AnchorPane screen;
  
  @FXML
  void start() throws Exception {
	  Main.setRoot("Gui_7_3");
  }
  
  @FXML
  void cancel() throws Exception {
	  Main.setRoot("Gui_6_1");
  }
  
  void getData()
  {
	  try {
	  	    // Connection code from the previous step

	  	    Statement statement = Main.connection.createStatement();
	  	    System.out.println(currentQuiz.curr);
	  	     ResultSet resultSet=statement.executeQuery("Select * from quiz_ques where name='"+currentQuiz.curr+"'");
	  	     ArrayList<String> question =new ArrayList();
	  	     while(resultSet.next())
	  	     {
	  	    	 question.add(resultSet.getString("ques_name").trim());
	  	     }
	  	    String id="(";
	  	    //id=id+"''";
	  	    for(String i:question)
	  	    {
	  	    	id=id+"N'"+i.trim()+"',";
	  	    }
	  	    id=id.substring(0,id.length()-1);
	  	    id=id+")";
	  	    Quiz.array.clear();
	  	    Quiz.isShuffle=currentQuiz.isShuffle;
	  	    Quiz.mark=0;
	  	    Quiz.time=currentQuiz.time;
	  	    //System.out.println(id);
	  	    //System.out.println(insert);
	  	     statement = Main.connection.createStatement();
	  	     
	  	     resultSet=statement.executeQuery("Select * from quest_list where name in"+id);
	  	    while (resultSet.next()) {
	  	    	System.out.println("qqqq");
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
    	        Question newQuest = new Question(name,text,choice1,grade1,choice2,grade2,choice3,grade3,choice4,grade4,choice5,grade5);
    	        Quiz.array.add(newQuest);
    	    }
	  	    
	  	} catch (SQLException e) {
	  	    e.printStackTrace();
	  	}
  }
  
  @FXML
  void export() {
	  
	  Dialog<Pair<String,String>> dialog = new Dialog<>();
	   for(Question i:Quiz.array)
	   {
		   content+=i.text;
		   List<Integer> list = new ArrayList<Integer>();
	       for(int curr=0;curr<i.choice.size();curr++)
	       {
	       	list.add(curr);
	       }
	       for(int index: list)
       	  {	
	        	content+=i.choice.get(list.get(index));
       	 }
	   }
	   System.out.println(content);
	  
	   dialog.setTitle("login dialog");
	   dialog.setHeaderText("Sign up");
	   ButtonType loginButtonType=new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
	   dialog.getDialogPane().getButtonTypes().addAll(loginButtonType,ButtonType.CANCEL);
	 
	   GridPane grid = new GridPane();
	   grid.setHgap(10);
	   grid.setVgap(10);
	   grid.setPadding(new Insets(20 , 150, 10, 10));
	   
	   TextField userName=new TextField();
	   userName.setPromptText("FileName");
	   PasswordField password = new PasswordField();
	   password.setPromptText("Password");
	   
	   grid.add(new Label("FileName"), 0, 0);
	   grid.add(userName, 1, 0);
	   grid.add(new Label("Password"), 0, 1);
	   grid.add(password, 1, 1);
	   
	   Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
	   loginButton.setDisable(true);
	   
	   userName.textProperty().addListener((observable,oldValue,newValue)->{
		   System.out.println(observable);
		   loginButton.setDisable(newValue.trim().isEmpty());
	   });
	    
	   dialog.getDialogPane().setContent(grid);
	   
	   dialog.setResultConverter(dialogButton->{
		   if(dialogButton == loginButtonType) {
			   return new Pair<>(userName.getText(),password.getText());
		   }
		   return null;
	   });
	   Optional<Pair<String,String>> result = dialog.showAndWait();
	   result.ifPresent(userNamepassword->{
		   System.out.println("Username="+userNamepassword.getKey()+"Password="+userNamepassword.getValue());
		    License.setLicenseKey("");
	    	PdfDocument myPdf = PdfDocument.renderHtmlAsPdf(content);
	    	SecurityManager securityManager = myPdf.getSecurity();
	    	SecurityOptions securityOptions = new SecurityOptions();
	    	securityOptions.setUserPassword(userNamepassword.getValue());
	    	securityManager.setSecurityOptions(securityOptions);
	    	try {
				myPdf.saveAs(Paths.get(userNamepassword.getKey()+".pdf"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	   });
	   }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
	  getData();
	  screen.setPrefWidth(Main.screenWidth);
  }
  
}
