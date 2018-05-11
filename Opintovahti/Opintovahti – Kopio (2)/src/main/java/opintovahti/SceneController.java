package opintovahti;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;



public class SceneController implements Initializable {
    
    private Integer currentPeriod;
    private Integer currentUser;
    private State state;
    
    @FXML
    private JFXComboBox periodi_id = new JFXComboBox();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        try {
            this.currentPeriod = this.state.getPeriodId();
            System.out.println("toimi " + this.currentPeriod.toString());
        } catch (Exception e) {
            this.currentPeriod = 1;
        }
        
        try {
            this.currentUser = this.state.getUserId();
        } catch (Exception e) {
            setField(0);
        }
        
//        this.periodi_id.setValue(this.state.getPeriodId().toString());
        this.periodi_id.getItems().addAll("1","2","3","4");
        
    }
    
    @FXML
    public void setState(State state) {
        this.state = state;
    }
    
    @FXML
    public void comboBoxUpdate() throws Exception {

//        System.out.println(getField().toString());
        
        this.currentPeriod = Integer.parseInt(this.periodi_id.getValue().toString());
        System.out.println(this.currentPeriod.toString());
        
        try {
            System.out.println(getField().toString());
        } catch (Exception e){
            closeWindow();
        }
        
        Databases.saveCurrentPeriod(this.currentPeriod, getField());
        
    }
    
    @FXML
    public Integer getField() {
        return this.currentUser;
    }
    
    @FXML
    public void setField(Integer value){
        this.currentUser = value;
    }
    
    public void saveSession() throws Exception {
        
    }
        
    @FXML
    public void closeWindow() {
        Platform.exit();
    }
    
    
    
}
