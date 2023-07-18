package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Main extends Application  {

    private static Scene scene;
    static double screenWidth = javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
    static double screenHeight = javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();
    public static Stage primaryStage;
    public static Connection connection;
    public static ArrayList<String> Category = new ArrayList<>();
    public static ArrayList<String> Category1 = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
    	connectDatabase();
        initialCategory("default",0);
        initialCategory1("default",0);
        this.primaryStage=stage;
        scene = new Scene(loadFXML("Gui_1_1"), 640, 480);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
    
    static void initialCategory(String name,int level)
    {
    	
    	String custom="";
    	for(int i=1;i<=level;i++)
    		custom+="   ";
    	
    //	System.out.print(name);
    	int cnt=0;
    	try {
    		Statement statement = connection.createStatement();
    	    ResultSet resultSet = statement.executeQuery("Select count(*) from cate_ques where cate='"+name+"'");
    	    if(resultSet.next())
    	    {
    	    	//System.out.println(resultSet.getString("cate"));
    	    	cnt=resultSet.getInt(1);
    	    }
    	}
    	catch (SQLException e) {
    	    e.printStackTrace();
    	}
    	if(cnt==0)
    	Category.add(custom+name);
    	else
    	Category.add(custom+name+"( "+cnt+")");
    	try {
    	    // Connection code from the previous step

    	    Statement statement = connection.createStatement();
    	    ResultSet resultSet = statement.executeQuery("Select sub from cate_sub where parent='"+name+"'");
             
    	    while (resultSet.next()) {
    	        String column1Value = resultSet.getString("sub");
    	        initialCategory(column1Value, level+1);
        	     
    	    }
    	    

    	    
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
    	
    	
    }
    
    static void initialCategory1(String name,int level)
    {
    	
    	String custom="";
    	for(int i=1;i<=level;i++)
    		custom+="   ";
    	
    	Category1.add(custom+name);
    	try {
    	    // Connection code from the previous step

    	    Statement statement = connection.createStatement();
    	    ResultSet resultSet = statement.executeQuery("Select sub from cate_sub where parent='"+name+"'");
             
    	    while (resultSet.next()) {
    	        String column1Value = resultSet.getString("sub");
    	        initialCategory1(column1Value, level+1);
        	     
    	    }

    	    
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
    	
    	
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    static void setRoot_2(Parent ViewParent) throws IOException {
        scene.setRoot(ViewParent);
    }
    /*static void setRoot(String fxml, double a,double b) throws IOException {
        scene.setRoot(loadFXML(fxml));
        //scene= new Scene(loadFXML(fxml),a,b);
    }*/
    
    static void setCss(String css) {
    	scene.getStylesheets().add(Main.class.getResource(css+".css").toExternalForm());
    }
    
     static void setScreen() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    	primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
    }
     
    void connectDatabase()   {
    	String connectionString = "jdbc:sqlserver://localhost:1433;Database=BTL;";
    	try
    	{
    		//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		connection = DriverManager.getConnection(connectionString,"sa","123456");
    		System.out.println("OK");
    	}
    	catch(Exception e)
    	{
    		System.out.println("error connect sql");
    		e.printStackTrace();
    	}
    }
    


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}