package opintovahti;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;



public class SceneController implements Initializable {
    
    private Integer currentPeriod;
    private Integer currentUser;

    @FXML 
    private GridPane gridPane = new GridPane();
    
    
    @FXML 
    private JFXComboBox periodi_id = new JFXComboBox();
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        try {
            this.currentPeriod = State.globalState.getPeriodId();
            System.out.println("toimi " + this.currentPeriod.toString());
        } catch (Exception e) {
            this.currentPeriod = 1;
        }
        
        try {
            this.currentUser = State.globalState.getUserId();
        } catch (Exception e) {
            this.currentUser = 0;
        }
        
        this.periodi_id.setValue(State.globalState.getPeriodId().toString());
        this.periodi_id.getItems().addAll("1","2","3","4");
        
    }
    
    @FXML
    public void setState(State state) {
        State.globalState = state;
    }
    
    @FXML
    public void comboBoxUpdate() throws Exception {

        
        this.currentPeriod = Integer.parseInt(this.periodi_id.getValue().toString());
        System.out.println(this.currentPeriod.toString());
        
        try {
            System.out.println(this.currentUser.toString());
        } catch (Exception e){
            closeWindow();
        }
        
        Databases.saveCurrentPeriod(this.currentPeriod, this.currentUser);
    }
    

    
    public void saveSession() throws Exception {
    }
        
    @FXML
    public void closeWindow() {
        Platform.exit();
    }
    
    
    
}
