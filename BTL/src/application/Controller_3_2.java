package application;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class CKEditorContentExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create WebView
        WebView webView = new WebView();

        // Load CKEditor script and HTML content with a textarea
        String htmlContent = "<html><head><script src=\"ckeditor/ckeditor.js\"></script></head><body><textarea id=\"editor\"></textarea><script>CKEDITOR.replace('editor');</script></body></html>";

        // Load HTML content into WebView
        webView.getEngine().loadContent(htmlContent);

        // Get content from CKEditor
        webView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webView.getEngine().executeScript("window");
                JSObject editor = (JSObject) window.call("eval", "CKEDITOR.instances['editor']");
                String content = (String) editor.call("getData");
                System.out.println("CKEditor Content: " + content);
            }
        });

        // Create a StackPane and add WebView to it
        StackPane root = new StackPane(webView);

        // Create a Scene with the root StackPane
        Scene scene = new Scene(root, 800, 600);

        // Set the Scene on the Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("CKEditor Content Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
