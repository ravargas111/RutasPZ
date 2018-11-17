/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.controller;

import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import rutaspz.util.AppContext;

/**
 * FXML Controller class
 *
 * @author robri
 */
public class ContainerController extends Controller implements Initializable {

    @FXML
    private BorderPane root;
    private JFXHamburger hambBtn;
    private Label lblNumVert;
    private Label lblSel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //AppContext.getInstance().set("hambBtn",hambBtn);
        //AppContext.getInstance().set("infoNodo",lblSel);
        //lblSel.textProperty().bind(observable);
        //Utils.getInstance().createPopUp(hambBtn);
    }    



    @Override
    public void initialize() {
    }

    
}
