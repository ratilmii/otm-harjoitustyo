package opintovahti;


import javafx.application.Application;
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
    public void start(final Stage stage) throws Exception {
        
        //Luodaan State-objekti joka pitää kirjaa käyttäjästä ja avoinna olevasta periodista
        
        State.globalState = new State();
        
        //Luodaan kirjautumisikkuna, jolla ei ole reunuksia ja jota voidaan liikutetella hiirellä vetämällä
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Opintovahti");
        stage.setResizable(false);
        
        stage.setScene(scene);               
        stage.show();
        
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
