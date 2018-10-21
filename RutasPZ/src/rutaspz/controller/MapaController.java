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
import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ArrayList;
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
import rutaspz.util.Points2DUtils;
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
    private FontAwesomeIconView flag;
    private ArrayList<Point2D> pathPoints;
    private ArrayList<ArrayList<Point2D>> pathPointsArray;
    private ArrayList<Line> pathLines;
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
        //route=new ArrayList<>();
        car = Utils.getInstance().createIcon(apInterfaz, FontAwesomeIcon.CAR);
        flag = Utils.getInstance().createIcon(apInterfaz, FontAwesomeIcon.FLAG_CHECKERED);
        pathPoints = Points2DUtils.getInstance().getPoints();
        pathPointsArray = Points2DUtils.getInstance().getPointsArray();
        pathLines = new ArrayList<>();
        AppContext.getInstance().set("lines",pathLines);
        AppContext.getInstance().set("parent",apInterfaz);
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
        clearLines();
        pathLines.clear();
        Utils.getInstance().putIcon(flag, e.getX(), e.getY());
        Utils.getInstance().quitIcon(car);
        pathPoints.clear();
        pathPointsArray.add(pathPoints);
        insertPointToRoute(e.getX(),e.getY());
    }
    
    private void rigthClickEvent(MouseEvent e){
        //refreshFinishPoints(e.getX(),e.getY());
        //createLine(apInterfaz,lFromX,lToX,lFromY,lToY);
        //refreshStartPoints(e.getX(),e.getY());
        //insertPointToRoute(e.getX(),e.getY());
        insertPointToRoute(e.getX(),e.getY());
        //Utils.getInstance().createIcon(apInterfaz, e.getX(), e.getY(), FontAwesomeIcon.DOT_CIRCLE_ALT);
        //createRouteLines();
    }
    
    private void middleClickEvent(MouseEvent e){
        printRoute();
        //Points2DUtils.getInstance().addNewRouteToArray(pathPoints);
        //Points2DUtils.getInstance().printDistances();
        Utils.getInstance().putIcon(car, 0.0, 0.0);
        createRouteLines();
        //System.out.println("\n\n*********Desplazando*********");
        Animation.getInstance().desplazarListaMovs(car, pathPoints);
    }
    
    private void insertPointToRoute(Double x,Double y){
        Points2DUtils.getInstance().insertPoint2D(x, y);
        //Utils.getInstance().createIcon(apInterfaz, x, y, FontAwesomeIcon.DOT_CIRCLE_ALT);
        //System.out.println("(X"+x+",Y:"+y+")");
    }
    
    private void printRoute(){
        System.out.println("\n\n*********Puntos de la ruta*********");
        Points2DUtils.getInstance().printRoutePoints();
    }
    
    private void createRouteLines(){
        //ArrayList<Line> ruta = new ArrayList<>();
        //System.out.println("\n*********Ruta Creada*********");
        Integer i=0;
        for(Point2D p:pathPoints){
            Point2D p2 = Points2DUtils.getInstance().getNextPoint(i);
            if(p2!=null){
                drawLine(p,p2);
                //Points2DUtils.getInstance().printPoints(p, p2);
            }
            i++;
        }
    }
    
    private void drawLine(Point2D p1,Point2D p2){
        Line line=new Line();
        apInterfaz.getChildren().add(line);
        line.setStartX(p1.getX());
        line.setStartY(p1.getY());
        line.setEndX(p2.getX());
        line.setEndY(p2.getY());
        pathLines.add(line);
        line.getStyleClass().add("line");
    }
    
    private void clearLines(){
        if(!pathLines.isEmpty()){
            pathLines.stream().forEach(e->{
                Utils.getInstance().quitObject(apInterfaz, e);
            });
        }   
    }

}
