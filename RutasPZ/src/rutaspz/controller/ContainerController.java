/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author robri
 */
public class ContainerController extends Controller implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private AnchorPane apTop;
    @FXML
    private FontAwesomeIconView btnOpcionesUsu;
    @FXML
    private Hyperlink hlNombreUsuario;
    @FXML
    private VBox menuAdmin;
    @FXML
    private VBox menuUsu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irParamG(MouseEvent event) {
    }

    @FXML
    private void irMantUsuarios(MouseEvent event) {
    }

    @FXML
    private void irMantPeliculas(MouseEvent event) {
    }

    @FXML
    private void irMantSalas(MouseEvent event) {
    }

    @FXML
    private void irInfoUsuario(MouseEvent event) {
    }

    @FXML
    private void irInfoCine(MouseEvent event) {
    }

    @FXML
    private void irCartelera(MouseEvent event) {
    }

    @FXML
    private void irProximas(MouseEvent event) {
    }

    @Override
    public void initialize() {
    }
    
}
