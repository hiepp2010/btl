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
  Question newQuest= new Question();
  
  @FXML
  private void switchTo1_1() throws Exception{
      //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      // Main.setCss("Gui_1_2");
         Main.setRoot("Gui_1_1");
      // scene.getRoot().setStyle("-fx-background-color: white;");
      }
  
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
  
  String html(String a)
  {
	  return "<!DOCTYPE html>\r\n"
		  		+ "<html lang=\"en\">\r\n"
		  		+ "  <head>\r\n"
		  		+ "    <meta charset=\"UTF-8\" />\r\n"
		  		+ "  </head>\r\n"
		  		+ "  <body>\r\n"
		  		+ "    <style>\r\n"
		  		+ "      #container {\r\n"
		  		+ "        width: 100%;\r\n"
		  		+ "        margin: 20px auto;\r\n"
		  		+ "      }\r\n"
		  		+ "      .ck-editor__editable[role=\"textbox\"] {\r\n"
		  		+ "        /* editing area */\r\n"
		  		+ "        min-height: 200px;\r\n"
		  		+ "      }\r\n"
		  		+ "      .ck-content .image {\r\n"
		  		+ "        /* block images */\r\n"
		  		+ "        max-width: 80%;\r\n"
		  		+ "        margin: 20px auto;\r\n"
		  		+ "      }\r\n"
		  		+ "    </style>\r\n"
		  		+ "    <div id=\"container\">\r\n"
		  		+ "      <div id=\"editor\"></div>\r\n"
		  		+ "    </div>\r\n"
		  		+ "    <!--\r\n"
		  		+ "            The \"super-build\" of CKEditor 5 served via CDN contains a large set of plugins and multiple editor types.\r\n"
		  		+ "            See https://ckeditor.com/docs/ckeditor5/latest/installation/getting-started/quick-start.html#running-a-full-featured-editor-from-cdn\r\n"
		  		+ "        -->\r\n"
		  		+ "    <script src=\"https://cdn.ckeditor.com/ckeditor5/38.1.0/super-build/ckeditor.js\"></script>\r\n"
		  		+ "    <!--\r\n"
		  		+ "            Uncomment to load the Spanish translation\r\n"
		  		+ "            <script src=\"https://cdn.ckeditor.com/ckeditor5/38.1.0/super-build/translations/es.js\"></script>\r\n"
		  		+ "        -->\r\n"
		  		+ "    <script>\r\n"
		  		+ "      // This sample still does not showcase all CKEditor 5 features (!)\r\n"
		  		+ "      // Visit https://ckeditor.com/docs/ckeditor5/latest/features/index.html to browse all the features.\r\n"
		  		+ "      let editor;\r\n"
		  		+ "      CKEDITOR.ClassicEditor.create(document.getElementById(\"editor\"), {\r\n"
		  		+ "        // https://ckeditor.com/docs/ckeditor5/latest/features/toolbar/toolbar.html#extended-toolbar-configuration-format\r\n"
		  		+ "        toolbar: {\r\n"
		  		+ "          items: [\r\n"
		  		+ "            \"exportPDF\",\r\n"
		  		+ "            \"exportWord\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"findAndReplace\",\r\n"
		  		+ "            \"selectAll\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"heading\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"bold\",\r\n"
		  		+ "            \"italic\",\r\n"
		  		+ "            \"strikethrough\",\r\n"
		  		+ "            \"underline\",\r\n"
		  		+ "            \"code\",\r\n"
		  		+ "            \"subscript\",\r\n"
		  		+ "            \"superscript\",\r\n"
		  		+ "            \"removeFormat\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"bulletedList\",\r\n"
		  		+ "            \"numberedList\",\r\n"
		  		+ "            \"todoList\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"outdent\",\r\n"
		  		+ "            \"indent\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"undo\",\r\n"
		  		+ "            \"redo\",\r\n"
		  		+ "            \"-\",\r\n"
		  		+ "            \"fontSize\",\r\n"
		  		+ "            \"fontFamily\",\r\n"
		  		+ "            \"fontColor\",\r\n"
		  		+ "            \"fontBackgroundColor\",\r\n"
		  		+ "            \"highlight\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"alignment\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"link\",\r\n"
		  		+ "            \"insertImage\",\r\n"
		  		+ "            \"blockQuote\",\r\n"
		  		+ "            \"insertTable\",\r\n"
		  		+ "            \"mediaEmbed\",\r\n"
		  		+ "            \"codeBlock\",\r\n"
		  		+ "            \"htmlEmbed\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"specialCharacters\",\r\n"
		  		+ "            \"horizontalLine\",\r\n"
		  		+ "            \"pageBreak\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"textPartLanguage\",\r\n"
		  		+ "            \"|\",\r\n"
		  		+ "            \"sourceEditing\",\r\n"
		  		+ "          ],\r\n"
		  		+ "          shouldNotGroupWhenFull: true,\r\n"
		  		+ "        },\r\n"
		  		+ "        // Changing the language of the interface requires loading the language file using the <script> tag.\r\n"
		  		+ "        // language: 'es',\r\n"
		  		+ "        list: {\r\n"
		  		+ "          properties: {\r\n"
		  		+ "            styles: true,\r\n"
		  		+ "            startIndex: true,\r\n"
		  		+ "            reversed: true,\r\n"
		  		+ "          },\r\n"
		  		+ "        },\r\n"
		  		+ "        // https://ckeditor.com/docs/ckeditor5/latest/features/headings.html#configuration\r\n"
		  		+ "        heading: {\r\n"
		  		+ "          options: [\r\n"
		  		+ "            {\r\n"
		  		+ "              model: \"paragraph\",\r\n"
		  		+ "              title: \"Paragraph\",\r\n"
		  		+ "              class: \"ck-heading_paragraph\",\r\n"
		  		+ "            },\r\n"
		  		+ "            {\r\n"
		  		+ "              model: \"heading1\",\r\n"
		  		+ "              view: \"h1\",\r\n"
		  		+ "              title: \"Heading 1\",\r\n"
		  		+ "              class: \"ck-heading_heading1\",\r\n"
		  		+ "            },\r\n"
		  		+ "            {\r\n"
		  		+ "              model: \"heading2\",\r\n"
		  		+ "              view: \"h2\",\r\n"
		  		+ "              title: \"Heading 2\",\r\n"
		  		+ "              class: \"ck-heading_heading2\",\r\n"
		  		+ "            },\r\n"
		  		+ "            {\r\n"
		  		+ "              model: \"heading3\",\r\n"
		  		+ "              view: \"h3\",\r\n"
		  		+ "              title: \"Heading 3\",\r\n"
		  		+ "              class: \"ck-heading_heading3\",\r\n"
		  		+ "            },\r\n"
		  		+ "            {\r\n"
		  		+ "              model: \"heading4\",\r\n"
		  		+ "              view: \"h4\",\r\n"
		  		+ "              title: \"Heading 4\",\r\n"
		  		+ "              class: \"ck-heading_heading4\",\r\n"
		  		+ "            },\r\n"
		  		+ "            {\r\n"
		  		+ "              model: \"heading5\",\r\n"
		  		+ "              view: \"h5\",\r\n"
		  		+ "              title: \"Heading 5\",\r\n"
		  		+ "              class: \"ck-heading_heading5\",\r\n"
		  		+ "            },\r\n"
		  		+ "            {\r\n"
		  		+ "              model: \"heading6\",\r\n"
		  		+ "              view: \"h6\",\r\n"
		  		+ "              title: \"Heading 6\",\r\n"
		  		+ "              class: \"ck-heading_heading6\",\r\n"
		  		+ "            },\r\n"
		  		+ "          ],\r\n"
		  		+ "        },\r\n"
		  		+ "        // https://ckeditor.com/docs/ckeditor5/latest/features/editor-placeholder.html#using-the-editor-configuration\r\n"
		  		+ "        placeholder: \"Welcome to CKEditor 5!\",\r\n"
		  		+ "        // https://ckeditor.com/docs/ckeditor5/latest/features/font.html#configuring-the-font-family-feature\r\n"
		  		+ "        fontFamily: {\r\n"
		  		+ "          options: [\r\n"
		  		+ "            \"default\",\r\n"
		  		+ "            \"Arial, Helvetica, sans-serif\",\r\n"
		  		+ "            \"Courier New, Courier, monospace\",\r\n"
		  		+ "            \"Georgia, serif\",\r\n"
		  		+ "            \"Lucida Sans Unicode, Lucida Grande, sans-serif\",\r\n"
		  		+ "            \"Tahoma, Geneva, sans-serif\",\r\n"
		  		+ "            \"Times New Roman, Times, serif\",\r\n"
		  		+ "            \"Trebuchet MS, Helvetica, sans-serif\",\r\n"
		  		+ "            \"Verdana, Geneva, sans-serif\",\r\n"
		  		+ "          ],\r\n"
		  		+ "          supportAllValues: true,\r\n"
		  		+ "        },\r\n"
		  		+ "        // https://ckeditor.com/docs/ckeditor5/latest/features/font.html#configuring-the-font-size-feature\r\n"
		  		+ "        fontSize: {\r\n"
		  		+ "          options: [10, 12, 14, \"default\", 18, 20, 22],\r\n"
		  		+ "          supportAllValues: true,\r\n"
		  		+ "        },\r\n"
		  		+ "        // Be careful with the setting below. It instructs CKEditor to accept ALL HTML markup.\r\n"
		  		+ "        // https://ckeditor.com/docs/ckeditor5/latest/features/general-html-support.html#enabling-all-html-features\r\n"
		  		+ "        htmlSupport: {\r\n"
		  		+ "          allow: [\r\n"
		  		+ "            {\r\n"
		  		+ "              name: /.*/,\r\n"
		  		+ "              attributes: true,\r\n"
		  		+ "              classes: true,\r\n"
		  		+ "              styles: true,\r\n"
		  		+ "            },\r\n"
		  		+ "          ],\r\n"
		  		+ "        },\r\n"
		  		+ "        // Be careful with enabling previews\r\n"
		  		+ "        // https://ckeditor.com/docs/ckeditor5/latest/features/html-embed.html#content-previews\r\n"
		  		+ "        htmlEmbed: {\r\n"
		  		+ "          showPreviews: true,\r\n"
		  		+ "        },\r\n"
		  		+ "        // https://ckeditor.com/docs/ckeditor5/latest/features/link.html#custom-link-attributes-decorators\r\n"
		  		+ "        link: {\r\n"
		  		+ "          decorators: {\r\n"
		  		+ "            addTargetToExternalLinks: true,\r\n"
		  		+ "            defaultProtocol: \"https://\",\r\n"
		  		+ "            toggleDownloadable: {\r\n"
		  		+ "              mode: \"manual\",\r\n"
		  		+ "              label: \"Downloadable\",\r\n"
		  		+ "              attributes: {\r\n"
		  		+ "                download: \"file\",\r\n"
		  		+ "              },\r\n"
		  		+ "            },\r\n"
		  		+ "          },\r\n"
		  		+ "        },\r\n"
		  		+ "        // https://ckeditor.com/docs/ckeditor5/latest/features/mentions.html#configuration\r\n"
		  		+ "        mention: {\r\n"
		  		+ "          feeds: [\r\n"
		  		+ "            {\r\n"
		  		+ "              marker: \"@\",\r\n"
		  		+ "              feed: [\r\n"
		  		+ "                \"@apple\",\r\n"
		  		+ "                \"@bears\",\r\n"
		  		+ "                \"@brownie\",\r\n"
		  		+ "                \"@cake\",\r\n"
		  		+ "                \"@cake\",\r\n"
		  		+ "                \"@candy\",\r\n"
		  		+ "                \"@canes\",\r\n"
		  		+ "                \"@chocolate\",\r\n"
		  		+ "                \"@cookie\",\r\n"
		  		+ "                \"@cotton\",\r\n"
		  		+ "                \"@cream\",\r\n"
		  		+ "                \"@cupcake\",\r\n"
		  		+ "                \"@danish\",\r\n"
		  		+ "                \"@donut\",\r\n"
		  		+ "                \"@dragée\",\r\n"
		  		+ "                \"@fruitcake\",\r\n"
		  		+ "                \"@gingerbread\",\r\n"
		  		+ "                \"@gummi\",\r\n"
		  		+ "                \"@ice\",\r\n"
		  		+ "                \"@jelly-o\",\r\n"
		  		+ "                \"@liquorice\",\r\n"
		  		+ "                \"@macaroon\",\r\n"
		  		+ "                \"@marzipan\",\r\n"
		  		+ "                \"@oat\",\r\n"
		  		+ "                \"@pie\",\r\n"
		  		+ "                \"@plum\",\r\n"
		  		+ "                \"@pudding\",\r\n"
		  		+ "                \"@sesame\",\r\n"
		  		+ "                \"@snaps\",\r\n"
		  		+ "                \"@soufflé\",\r\n"
		  		+ "                \"@sugar\",\r\n"
		  		+ "                \"@sweet\",\r\n"
		  		+ "                \"@topping\",\r\n"
		  		+ "                \"@wafer\",\r\n"
		  		+ "              ],\r\n"
		  		+ "              minimumCharacters: 1,\r\n"
		  		+ "            },\r\n"
		  		+ "          ],\r\n"
		  		+ "        },\r\n"
		  		+ "        // The \"super-build\" contains more premium features that require additional configuration, disable them below.\r\n"
		  		+ "        // Do not turn them on unless you read the documentation and know how to configure them and setup the editor.\r\n"
		  		+ "        removePlugins: [\r\n"
		  		+ "          // These two are commercial, but you can try them out without registering to a trial.\r\n"
		  		+ "          // 'ExportPdf',\r\n"
		  		+ "          // 'ExportWord',\r\n"
		  		+ "          \"CKBox\",\r\n"
		  		+ "          \"CKFinder\",\r\n"
		  		+ "          \"EasyImage\",\r\n"
		  		+ "          // This sample uses the Base64UploadAdapter to handle image uploads as it requires no configuration.\r\n"
		  		+ "          // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/base64-upload-adapter.html\r\n"
		  		+ "          // Storing images as Base64 is usually a very bad idea.\r\n"
		  		+ "          // Replace it on production website with other solutions:\r\n"
		  		+ "          // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/image-upload.html\r\n"
		  		+ "          // 'Base64UploadAdapter',\r\n"
		  		+ "          \"RealTimeCollaborativeComments\",\r\n"
		  		+ "          \"RealTimeCollaborativeTrackChanges\",\r\n"
		  		+ "          \"RealTimeCollaborativeRevisionHistory\",\r\n"
		  		+ "          \"PresenceList\",\r\n"
		  		+ "          \"Comments\",\r\n"
		  		+ "          \"TrackChanges\",\r\n"
		  		+ "          \"TrackChangesData\",\r\n"
		  		+ "          \"RevisionHistory\",\r\n"
		  		+ "          \"Pagination\",\r\n"
		  		+ "          \"WProofreader\",\r\n"
		  		+ "          // Careful, with the Mathtype plugin CKEditor will not load when loading this sample\r\n"
		  		+ "          // from a local file system (file://) - load this site via HTTP server if you enable MathType.\r\n"
		  		+ "          \"MathType\",\r\n"
		  		+ "          // The following features are part of the Productivity Pack and require additional license.\r\n"
		  		+ "          \"SlashCommand\",\r\n"
		  		+ "          \"Template\",\r\n"
		  		+ "          \"DocumentOutline\",\r\n"
		  		+ "          \"FormatPainter\",\r\n"
		  		+ "          \"TableOfContents\",\r\n"
		  		+ "        ],\r\n"
		  		+ "        mediaEmbed: { previewsInData: true },\r\n"
		  		+ "      }).then((newEditor) => {\r\n"
		  		+ "        // Get the HTML source code and display it\r\n"
		  		+ "        editor = newEditor;editor.setData('"+a+"')"+"\r\n"
		  		+ "      });\r\n"
		  		+ "    </script>\r\n"
		  		+ "  </body>\r\n"
		  		+ "</html>\r\n"
		  		+ "";
  }
  
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {

	  top.setPrefWidth(Main.screenWidth);
      mid.setPrefWidth(Main.screenWidth);    
	  hide();
	  DecimalFormat df = new DecimalFormat("#.#####");
	 // df.setRoundingMode(RoundingMode.CEILING);
	  
	//  vbox.requestLayout();
      //webview.getEngine().loadContent(htmlContent);
	  
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
	         textview.getEngine().loadContent(html(text));
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
  	    	System.out.println(p);
  	    	j.getEngine().loadContent(html(p));
  	    
  	    //	j.getEngine().executeScript("editor.getData()");
            
  	    	if(newQuest.grade.get(i)!=0)
  	    	q.setValue(df.format(newQuest.grade.get(i).doubleValue())+"%");
  	    	else
  	    		q.setValue("None");
  	    	
  	    }
	    
      
    //  view2.getEngine().loadContent(htmlContent);
  }
  
}
