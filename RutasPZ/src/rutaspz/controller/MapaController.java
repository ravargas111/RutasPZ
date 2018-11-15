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
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import rutaspz.Grafo;
import rutaspz.Vertex;
import rutaspz.util.Animation;
import rutaspz.util.AppContext;
import rutaspz.util.VertexUtils;
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
    private ArrayList<Line> pathLines;//líneas de ruta pricipal
    private ArrayList<Line> pathLines2;//líneas de ruta seguida
    private ArrayList<Vertex> generalVertices;//todos los vértices
    private ArrayList<Vertex> selectedVertices;//vértices de ruta
    private Integer cont;
    private Grafo grafo;
    //private ArrayList<ArrayList<Point2D>> pathPointsArray;
    //private ArrayList<Point2D> pathPoints;
    //private ArrayList<Vertex> verticesMap;
    @FXML
    private Label lblNodo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initInstances();//inicializa todas las instancias
        Utils.getInstance().createHamburgerTransition((JFXHamburger) AppContext.getInstance().get("hambBtn"), drawer,"Menu");//función hamburger btn
        Utils.getInstance().createCloseDrawerEvent(drawer, apInterfaz);//menú lateral
        initMouseEvent();//eventos a darle click sobre el mapa
        createVertices();//crea los vértices
        grafo.init();//inicializa la información del grafo
        AppContext.getInstance().set("grafo",grafo);
    }    

    @Override
    public void initialize() {
    }
    
    private void initInstances(){
        //route=new ArrayList<>();
        //pathPoints = VertexUtils.getInstance().getPoints();
        //pathPointsArray = VertexUtils.getInstance().getPointsArray();
        car = Utils.getInstance().createIcon(apInterfaz, FontAwesomeIcon.CAR);
        flag = Utils.getInstance().createIcon(apInterfaz, FontAwesomeIcon.FLAG_CHECKERED);
        generalVertices = VertexUtils.getInstance().getVerticesList();
        selectedVertices = VertexUtils.getInstance().getSelectedVertices();
        pathLines = new ArrayList<>();
        AppContext.getInstance().set("lines",pathLines);
        pathLines2 = new ArrayList<>();
        AppContext.getInstance().set("lines2",pathLines2);
        AppContext.getInstance().set("parent",apInterfaz);
        AppContext.getInstance().set("infoNodo",lblNodo);
        cont=0;
        selectedVertices.clear();
        grafo = new Grafo(generalVertices);
    }
    
    /**
     * selecciona los eventos dependiendo del botón del click
     */
    private void initMouseEvent(){
        this.apInterfaz.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
            if(e.getButton()==MouseButton.PRIMARY)
                leftClickEvent(e);
            else if(e.getButton()==MouseButton.SECONDARY)
                rigthClickEvent(e);
            else if(e.getButton()==MouseButton.MIDDLE)
                middleClickEvent(e); 
        });
        this.apInterfaz.addEventHandler(KeyEvent.KEY_TYPED, (e)->{
            if(e.isControlDown())
                spaceBarEvent();
    });
    }
    
    /**
     * acciones barra espaciadora(falta asignar)
     */
    private void spaceBarEvent(){
        System.out.println("Selected size: "+selectedVertices.size());
    }
    
    /**
     * acciones click izquierdo
     * @param e 
     */
    private void leftClickEvent(MouseEvent e){
        /*clearLines();
        clearLines2();
        clearVertices();
        Utils.getInstance().putIcon(flag, e.getX(), e.getY());
        Utils.getInstance().quitIcon(car);
        pathPoints.clear();
        pathPointsArray.add(pathPoints);
        */
        //imprimirNuevoVertice(e);
    }
    
    /**
     * acciones click derecho
     * @param e 
     */
    private void rigthClickEvent(MouseEvent e){
        VertexUtils.getInstance().clearRouteLines();
        VertexUtils.getInstance().clearFollowedLines();
    }
    
    /**
     * acciones click del centro
     * @param e 
     */
    private void middleClickEvent(MouseEvent e){
        //printRoute();
        //Points2DUtils.getInstance().addNewRouteToArray(pathPoints);
        //Points2DUtils.getInstance().printDistances();
        //createRouteLines();
        //System.out.println("\n\n*********Desplazando*********");
        //Animation.getInstance().desplazarListaMovs(car, pathPoints);
        //Utils.getInstance().putObject(apInterfaz, car);
        car.setVisible(true);
        VertexUtils.getInstance().drawSelectedRoute();
        Animation.getInstance().desplazarListaMovsV(car, selectedVertices);
        VertexUtils.getInstance().printVerticesRoute();
    }
    
    private void printRoute(){
        System.out.println("\n\n*********Puntos de la ruta*********");
        VertexUtils.getInstance().printVerticesRoute();
    }
    
    /**
     * añade nuevos vértices (lógicos a la lista de vértices)
     */
    public void createVertices(){
        generalVertices.addAll(Arrays.asList(
            new Vertex(512.0,6.0,4),
            new Vertex(484.0,31.0,4),
            new Vertex(476.0,57.0,4),
            new Vertex(460.0,137.0,4),
            new Vertex(458.0,166.0,4),
            new Vertex(457.0,197.0,4),
            new Vertex(443.0,259.0,4),
            new Vertex(431.0,283.0,4),
            new Vertex(414.0,303.0,4),
            new Vertex(390.0,341.0,4),//10
            new Vertex(367.0,374.0,4),
            new Vertex(343.0,406.0,4),
            new Vertex(298.0,466.0,4),
            new Vertex(496.0,607.0,4),
            new Vertex(531.0,630.0,4),
            new Vertex(580.0,657.0,4),
            new Vertex(596.0,659.0,4),
            new Vertex(620.0,585.0,4),
            new Vertex(640.0,530.0,4),
            new Vertex(654.0,481.0,4),//20
            new Vertex(668.0,436.0,4),
            new Vertex(680.0,393.0,4),
            new Vertex(684.0,381.0,4),
            new Vertex(635.0,276.0,4),
            new Vertex(612.0,226.0,4),
            new Vertex(581.0,164.0,4),
            new Vertex(566.0,131.0,4),
            //calle vertical
            new Vertex(625.0,308.0,4),
            new Vertex(611.0,355.0,4),
            new Vertex(598.0,399.0,4),//30
            new Vertex(584.0,446.0,4),
            new Vertex(569.0,492.0,4),
            new Vertex(555.0,539.0,4),
            //vértices internos
            new Vertex(509.0,117.0,4),
            new Vertex(526.0,157.0,4),
            new Vertex(488.0,177.0,4),
            new Vertex(526.0,207.0,4),
            new Vertex(564.0,225.0,4),
            new Vertex(603.0,247.0,4),
            new Vertex(504.0,250.0,4),//40
            new Vertex(529.0,263.0,4),
            new Vertex(552.0,273.0,4),
            new Vertex(590.0,292.0,4),
            new Vertex(608.0,301.0,4),
            new Vertex(585.0,316.0,4),
            new Vertex(599.0,321.0,4),
            new Vertex(466.0,296.0,4),
            new Vertex(501.0,303.0,4),
            new Vertex(515.0,309.0,4),
            new Vertex(539.0,319.0,4),//50
            new Vertex(576.0,337.0,4),
            new Vertex(649.0,376.0,4),
            new Vertex(455.0,324.0,4),
            new Vertex(493.0,343.0,4),
            new Vertex(526.0,362.0,4),
            new Vertex(543.0,371.0,4),
            new Vertex(564.0,381.0,4),
            new Vertex(634.0,419.0,4),
            new Vertex(440.0,369.0,4),
            new Vertex(452.0,377.0,4),//60
            new Vertex(476.0,389.0,4),
            new Vertex(513.0,408.0,4),
            new Vertex(530.0,415.0,4),
            new Vertex(542.0,423.0,4),
            new Vertex(618.0,463.0,4),
            new Vertex(397.0,408.0,4),
            new Vertex(432.0,429.0,4),
            new Vertex(458.0,443.0,4),
            new Vertex(494.0,460.0,4),
            new Vertex(529.0,475.0,4),//70
            new Vertex(601.0,506.0,4),
            new Vertex(512.0,491.0,4),
            new Vertex(522.0,496.0,4),
            new Vertex(488.0,500.0,4),
            new Vertex(505.0,507.0,4),
            new Vertex(365.0,427.0,4),
            new Vertex(379.0,444.0,4),
            new Vertex(411.0,478.0,4),
            new Vertex(517.0,519.0,4),
            new Vertex(588.0,549.0,4),//80
            new Vertex(478.0,529.0,4),
            new Vertex(588.0,568.0,4),
            new Vertex(355.0,478.0,4),
            new Vertex(429.0,520.0,4),
            new Vertex(504.0,565.0,5)
        ));
        
            VertexUtils.getInstance().drawVerticesList();//dibuja los vértices en el mapa
            
    }
    
    /**
     * limpia de la pantalla las rutas que estaban 
     */
    public void clearPreviousRoute(){
        clearLines();
        clearLines2();
        //clearVertices();
    }
    
    /**
     * limpia de la pantalla la ruta inicial
     */
    private void clearLines(){
        if(!pathLines.isEmpty()){
            pathLines.stream().forEach(e->{
                Utils.getInstance().quitObject(apInterfaz, e);
            });
        }
        pathLines.clear();
    }
    
    /**
     * limpia de la pantalla la ruta seguida
     */
    private void clearLines2(){
        if(!pathLines2.isEmpty()){
            pathLines2.stream().forEach(e->{
                Utils.getInstance().quitObject(apInterfaz, e);
            });
        }
        pathLines2.clear();
    }
    
    /**
     * limpia de la pantalla los vértices
     */
    private void clearVertices(){
        generalVertices.stream().forEach(e->{
            Utils.getInstance().quitObject(apInterfaz, e);
        });
        generalVertices.clear();
    }
    
    /**
     * usada para crear funciones que creaban los vértices (cuando estaba creando la matriz)
     * @param e 
     */
    private void printNewVertexFunction(MouseEvent e){
        System.out.println("new Vertex("+e.getX()+","+e.getY()+",4),");
    }
    
    //no usadas pero luego las borro XD
    /*
    private void insertPointToRoute(Double x,Double y){
        VertexUtils.getInstance().insertPoint2D(x, y);
        
        //Utils.getInstance().createIcon(apInterfaz, x, y, FontAwesomeIcon.DOT_CIRCLE_ALT);
        //System.out.println("(X"+x+",Y:"+y+")");
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
    
    private void createRouteLines(){
        //ArrayList<Line> ruta = new ArrayList<>();
        //System.out.println("\n*********Ruta Creada*********");
        Integer i=0;
        for(Point2D p:pathPoints){
            Point2D p2 = VertexUtils.getInstance().getNextPoint(i);
            if(p2!=null){
                drawLine(p,p2);
                //Points2DUtils.getInstance().printPoints(p, p2);
            }
            i++;
        }
        VertexUtils.getInstance().drawPointsList();
    }
    */
}
