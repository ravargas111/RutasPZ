/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import rutaspz.util.FlowController;

/**
 * FXML Controller class
 *
 * @author robri
 */
public class LogInController extends Controller implements Initializable {

    @FXML
    private StackPane root;
    @FXML
    private StackPane vbLogIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irInicioSesion(ActionEvent event) {
        FlowController.getInstance().cerrarVentana(root);
        FlowController.getInstance().goMain();
        FlowController.getInstance().goView("Mapa");
    }

    @FXML
    private void irRegistroCliente(ActionEvent event) {
    }


    @Override
    public void initialize() {
    }
    
}
