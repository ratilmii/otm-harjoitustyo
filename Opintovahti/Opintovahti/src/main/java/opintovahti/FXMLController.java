package opintovahti;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FXMLController implements Initializable {
    
    
    @FXML
    private Label label;
    
    @FXML
    private Pane pane;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }
    
    public void newUserCreated() {
        System.out.println("Uusi käyttäjä luotu.");
    }
    
    
}
