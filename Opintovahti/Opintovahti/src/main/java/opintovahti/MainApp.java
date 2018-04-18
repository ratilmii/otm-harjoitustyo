package opintovahti;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApp extends Application {

    private double xOffset = 0; 
    private double yOffset = 0;
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent login_parent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        login_parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        
        login_parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        
        Scene scene = new Scene(login_parent);
        scene.getStylesheets().add("/styles/Styles.css");
        
        primaryStage.setTitle("Opintovahti");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        
            
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
