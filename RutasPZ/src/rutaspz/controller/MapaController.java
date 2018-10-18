/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import rutaspz.util.Animation;
import rutaspz.util.AppContext;
import rutaspz.util.Utils;

/**
 * FXML Controller class
 *
 * @author robri
 */
public class MapaController extends Controller implements Initializable {

    @FXML
    private StackPane root;
    @FXML
    private AnchorPane apInterfaz;
    @FXML
    private JFXDrawer drawer;
    private FontAwesomeIconView car;
    private Double lFromX;
    private Double lFromY;
    private Double lToX;
    private Double lToY;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        car=new FontAwesomeIconView(FontAwesomeIcon.CAR);
        //car.set
        apInterfaz.getChildren().add(car);
        Utils.getInstance().createHamburgerTransition((JFXHamburger) AppContext.getInstance().get("hambBtn"), drawer,"Menu");
        Utils.getInstance().createCloseDrawerEvent(drawer, apInterfaz);
        initMouseEvent();
    }    

    @Override
    public void initialize() {
    }
    
    private void initMouseEvent(){
        this.apInterfaz.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
            if(e.getButton()==MouseButton.PRIMARY)
                leftClickEvent(e);
            else if(e.getButton()==MouseButton.SECONDARY)
                rigthClickEvent(e);
            else if(e.getButton()==MouseButton.MIDDLE)
                middleClickEvent(e); 
        });
    }
    
    private void leftClickEvent(MouseEvent e){
        lFromX= e.getX();
        lFromY= e.getY();
    }
    
    private void rigthClickEvent(MouseEvent e){
        lToX= e.getX();
        lToY= e.getY();
        createLine(apInterfaz,lFromX,lToX,lFromY,lToY);
        lFromX= e.getX();
        lFromY= e.getY();
    }
    
    private void middleClickEvent(MouseEvent e){
        Animation.getInstance().desplazar(car,lFromX,lToX,lFromY,lToY);
    }
    
    private void createLine(AnchorPane parent,Double fromX,Double toX,Double fromY,Double toY){
        Line line=new Line();
        parent.getChildren().add(line);
        line.setStartX(fromX);
        line.setEndX(toX);
        line.setStartY(fromY);
        line.setEndY(toY);
        
        //Animation.getInstance().desplazar(car,lFromX,lToX,lFromY,lToY);
    }
    
    private void pruebaLineas(){
        
    }
    
    
}
