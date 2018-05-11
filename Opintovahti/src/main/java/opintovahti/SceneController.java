package opintovahti;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class SceneController implements Initializable {

    private Integer currentPeriod;

    private Integer currentUser;

    @FXML
    private GridPane gridPane = new GridPane();
    
    @FXML 
    private TextArea slotNotes = new TextArea();

    @FXML
    private JFXComboBox periodi_id = new JFXComboBox();
    
    @FXML
    private Label loggedInLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Asetetaan State-oliolle tieto nykyisestä periodista ja käyttäjästä
        
        try {
            this.currentPeriod = State.globalState.getPeriodId();
        }
        catch (Exception e) {
            this.currentPeriod = 1;
        }

        try {
            this.currentUser = State.globalState.getUserId();
        }
        catch (Exception e) {
            this.currentUser = 0;
        }

        //Lisätään vaihtoehdot periodien valitsemista varten
        //Mikäli käyttäjä on olemassa, avataan viimeksi avoinna ollut periodi
        
        this.periodi_id.setValue(State.globalState.getPeriodId().toString());
        this.periodi_id.getItems().addAll("1", "2", "3", "4");

        //Asetetaan tiedot lukujärjestykseen
        
        try {
            renderGridPane();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        //Asetetaan tiedot muistilappuun
        
        try {
            renderNotesPane();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Periodia vaihdetaan ComboBox-elementistä löytyvillä vaihtoehdoilla
    //
    
    @FXML
    public void comboBoxUpdate() throws Exception {
        Databases db = State.globalState.getDatabase();

        this.currentPeriod = Integer.parseInt(this.periodi_id.getValue().toString());
        System.out.println(this.currentPeriod.toString());

        try {
            System.out.println(this.currentUser.toString());
        }
        catch (Exception e) {
            closeWindow();
        }

        db.saveCurrentPeriod(this.currentPeriod, this.currentUser);
        renderGridPane();
        renderNotesPane();
    }

    private PauseTransition pause = new PauseTransition(Duration.millis(200));

    @FXML
    private void renderGridPane() throws Exception {
        final Databases db = State.globalState.getDatabase();
        int slotId = 0;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                // Haetaan TextArea tietokannasta usernamen, period:n, ja id:n avulla
                String periodText = db.getTimetableEntry(this.currentUser, slotId, this.currentPeriod);
                if (periodText == null) {
                    periodText = "";
                }
                TextArea ta = new TextArea(periodText);

                //Annetaan Listener TextArealle
                final int finalSlotId = slotId;
                ta.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                        pause.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    db.setTimetableEntry(currentUser, finalSlotId, currentPeriod, newValue);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        pause.playFromStart();
                    }
                });

                // Lisätään TextArea gridPaneen
                gridPane.add(ta, x, y);
                slotId++;
            }
        }
        this.loggedInLabel.setText("Kirjautuneena käyttäjänä: " + db.getUsername(State.globalState.getUserId()));

    }
    
    // Pitkälti samanlainen toiminnallisuus kuin renderGridPanella, mutta vain muistiinpanoja varten
    
    @FXML
    private void renderNotesPane() throws Exception {
        final Databases db = State.globalState.getDatabase();
        
        String periodText = db.getNotesEntry(this.currentUser, this.currentPeriod);
        if (periodText == null) {
            periodText = "";
        }
        slotNotes.textProperty().setValue(periodText);
        slotNotes.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                pause.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            db.setNotesEntry(currentUser, currentPeriod, newValue);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                pause.playFromStart();
            }
        });
    }

    @FXML
    public void closeWindow() {
        Platform.exit();
    }

}
