package opintovahti;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    private double xOffset = 0; 
    private double yOffset = 0;
    
//    @FXML
//    private Label label;
//    
//    @FXML
//    private Pane pane;
    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
    
    @FXML 
    private JFXTextField txtUsr;
    
    @FXML
    private JFXPasswordField txtPswd;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }

    @FXML
    public void newUserCreated(ActionEvent event) throws Exception {

        User user = new User(this.txtUsr.getText(), this.txtPswd.getText());
        Databases.createUser(user.getName(), user.getSalt(), user.getHash());
        
        Parent scene_parent = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene sceneGUI = new Scene(scene_parent);
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(sceneGUI);
        stage.show();
        
            
        
        scene_parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        
        scene_parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        
        sceneGUI.getStylesheets().add("/styles/Styles.css");
                
    }
    
    @FXML
    public void login(ActionEvent event) throws Exception {
        
    }
   
    @FXML
    public void closeWindow() {
        Platform.exit();
    }
    
    
}
