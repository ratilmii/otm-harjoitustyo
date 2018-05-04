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
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;


public class FXMLController implements Initializable {
    
    private double xOffset = 0; 
    private double yOffset = 0;
    
    @FXML 
    private JFXTextField txtUsr;
    
    @FXML
    private JFXPasswordField txtPswd;
    
    @FXML
    private Label signupLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }

    @FXML
    public void newTimetableScene(ActionEvent event) throws Exception {
        
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
    public void newUserCreated(ActionEvent event) throws Exception {
        String username = this.txtUsr.getText();
        String password = this.txtPswd.getText();

        if(!Databases.checkIfUserExists(username)) {
            User user = new User(username, password);
            Databases.createUser(user.getName(), user.getHash());

            newTimetableScene(event);
        }else {
            this.signupLabel.setText("Käyttäjänimi on jo käytössä!");
        }  
    }
    
    @FXML
    public void login(ActionEvent event) throws Exception {
        String loginUsername = this.txtUsr.getText();
        String loginPassword = this.txtPswd.getText();

        if (Databases.checkIfUserExists(loginUsername)) {
            String hash = Databases.getHash(loginUsername);
            
            if (BCrypt.checkpw(loginPassword, hash)) {
                newTimetableScene(event);
            }else {
                this.signupLabel.setText("Väärä salasana!");
            }
        }else {
            this.signupLabel.setText("Käyttäjää ei ole olemassa!");
        }
    }
   
    @FXML
    public void closeWindow() {
        Platform.exit();
    }
    
    
}
