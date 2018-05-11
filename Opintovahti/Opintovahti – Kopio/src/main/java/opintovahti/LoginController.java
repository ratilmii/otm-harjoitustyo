package opintovahti;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
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


public class LoginController implements Initializable {
    
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
        Scene timetable_scene = new Scene(scene_parent);
        final Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
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
                app_stage.setX(event.getScreenX() - xOffset);
                app_stage.setY(event.getScreenY() - yOffset);
            }
        });
        
        timetable_scene.getStylesheets().add("/styles/Styles.css");
        
        app_stage.hide();
        app_stage.setScene(timetable_scene);
        
        app_stage.show();
    }    
    
    @FXML
    public void newUserCreated(ActionEvent event) throws Exception {
        String Username = this.txtUsr.getText();
        String password = this.txtPswd.getText();

        if(!Databases.checkIfUserExists(Username)) {
            User user = new User(Username, password);
            Databases.createUser(user.getName(), user.getHash());

            newTimetableScene(event);
        }else {
            this.signupLabel.setText("Käyttäjänimi on jo käytössä!");
        }  
    }
    
    @FXML
    public void login(ActionEvent event) throws Exception {
        String LoginUsername = this.txtUsr.getText();
        String loginPassword = this.txtPswd.getText();

        if (Databases.checkIfUserExists(LoginUsername)) {
            String hash = Databases.getHash(LoginUsername);
            
            if (BCrypt.checkpw(loginPassword, hash)) {
                newTimetableScene(event);
            }else {
                this.signupLabel.setText("Väärä salasana!");
            }
        }else {
            this.signupLabel.setText("Käyttäjää ei ole olemassa!");
        }
    }
   
    
    
    
    

    
    
}
