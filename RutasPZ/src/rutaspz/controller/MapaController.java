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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Stream;
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
    private ArrayList<Double[]> route;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initInstances();
        Utils.getInstance().createHamburgerTransition((JFXHamburger) AppContext.getInstance().get("hambBtn"), drawer,"Menu");
        Utils.getInstance().createCloseDrawerEvent(drawer, apInterfaz);
        initMouseEvent();
    }    

    @Override
    public void initialize() {
    }
    
    private void initInstances(){
        route=new ArrayList<>();
        car=new FontAwesomeIconView(FontAwesomeIcon.CAR);
        apInterfaz.getChildren().add(car);
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
        car.setX(lFromX);
        car.setY(lFromY);
    }
    
    private void rigthClickEvent(MouseEvent e){
        lToX= e.getX();
        lToY= e.getY();
        createLine(apInterfaz,lFromX,lToX,lFromY,lToY);
        lFromX= e.getX();
        lFromY= e.getY();
    }
    
    private void middleClickEvent(MouseEvent e){
        printRoute();
        followRoute();
    }
    
    private void createLine(AnchorPane parent,Double fromX,Double toX,Double fromY,Double toY){
        Line line=new Line();
        parent.getChildren().add(line);
        line.setStartX(fromX);
        line.setEndX(toX);
        line.setStartY(fromY);
        line.setEndY(toY);
        insertPointToRoute(toX,toY);
    }
    
    private void pruebaLineas(){
        
    }
    
    private void insertPointToRoute(Double x,Double y){
        Double[] point={x,y};
        this.route.add(point);
        //System.out.println("(X"+x+",Y:"+y+")");
    }
    
    private Double[] getPointFromRoute(Integer i){
        try{
           return this.route.get(i);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("fuera de límite de ruta");
            return null;
        }
    }
    
    private void followRoute(){
        System.out.println("\n********siguiendo*******\n");
        lFromX=lFromY=null;   
        route.stream().forEach(e->{
           if(lFromX==null&&lFromY==null){
               lFromX=e[0];
               lFromY=e[1];
           }
           else{
            lToX= e[0];
            lToY= e[1];
            Animation.getInstance().desplazar(car, lFromX, lToX, lFromY, lToY);
            System.out.println("(X"+lFromX+",Y:"+lFromY+")"+"->"+"(X"+lToX+",Y:"+lToY+")");
            lFromX=new Double(lToX);
            lFromY=new Double(lToY);
           }
        });
        route.clear();
        lFromX=lFromY=lToX=lToY=null; 
    }
    
    private void printRoute(){
        System.out.println("\n\n*********ruta*********");
        route.stream().forEach(e->{
            System.out.println("(X"+e[0]+",Y:"+e[1]+")");
        });
    }
}
