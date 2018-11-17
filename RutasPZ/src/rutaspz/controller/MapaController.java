/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRadioButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import rutaspz.Dijkstra;
import rutaspz.Floyd;
import rutaspz.Grafo;
import rutaspz.Vertex;
import rutaspz.util.Animation;
import rutaspz.util.AppContext;
import rutaspz.util.Mensaje;
import rutaspz.util.VertexUtils;
import rutaspz.util.Utils;

/**
 * FXML Controller class
 *
 * @author robri
 */
public class MapaController extends Controller implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private AnchorPane apInterfaz;
    private FontAwesomeIconView car;
    private FontAwesomeIconView flag;
    private ArrayList<Line> pathLines;//líneas de ruta pricipal
    private ArrayList<Line> pathLines2;//líneas de ruta seguida
    private ArrayList<Vertex> generalVertices;//todos los vértices
    private ArrayList<Vertex> selectedVertices;//vértices de ruta
    private Integer cont;
    private Grafo grafo;
    private Floyd floyd;
    private Dijkstra dijkstra;
    private Vertex selVertex;
    private Vertex startVertex;
    private Vertex endVertex;
    private SimpleBooleanProperty selectedChanged;
    private SimpleBooleanProperty startChanged;
    private SimpleBooleanProperty endChanged;
    private Boolean isDijkstra;
    //private ArrayList<ArrayList<Point2D>> pathPointsArray;
    //private ArrayList<Point2D> pathPoints;
    //private ArrayList<Vertex> verticesMap;
    @FXML
    private Label lblNodo;
    @FXML
    private Label lblSel;
    @FXML
    private ToggleGroup tgVertexRoll;
    @FXML
    private ToggleGroup tgVertexStatus;
    @FXML
    private ToggleGroup tgAlgorithm;
    @FXML
    private Label lblStart;
    @FXML
    private Label lblEnd;
    @FXML
    private JFXRadioButton rbStart;
    @FXML
    private JFXRadioButton rbEnd;
    @FXML
    private JFXRadioButton rbStateOpen;
    @FXML
    private JFXRadioButton rbStateSlow;
    @FXML
    private JFXRadioButton rbStateClosed;
    @FXML
    private JFXRadioButton rbDijkstra;
    @FXML
    private JFXRadioButton rbFloyd;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initInstances();//inicializa todas las instancias
        //Utils.getInstance().createHamburgerTransition((JFXHamburger) AppContext.getInstance().get("hambBtn"), drawer,"Menu");//función hamburger btn
        //Utils.getInstance().createCloseDrawerEvent(drawer, apInterfaz);//menú lateral
        initMouseEvent();//eventos a darle click sobre el mapa
        createVertices();//crea los vértices
        grafo.init();//inicializa la información del grafo
        AppContext.getInstance().set("grafo",grafo);
        initListeners();
        initAlgorithms();
    }    

    @Override
    public void initialize() {
    }
    
    private void initInstances(){
        //route=new ArrayList<>();
        //pathPoints = VertexUtils.getInstance().getPoints();
        //pathPointsArray = VertexUtils.getInstance().getPointsArray();
        car = Utils.getInstance().createIcon(apInterfaz, FontAwesomeIcon.CAR);
        car.getStyleClass().add("glyph-icon-car");
        flag = Utils.getInstance().createIcon(apInterfaz, FontAwesomeIcon.FLAG_CHECKERED);
        generalVertices = VertexUtils.getInstance().getVerticesList();
        selectedVertices = VertexUtils.getInstance().getSelectedVertices();
        pathLines = new ArrayList<>();
        AppContext.getInstance().set("lines",pathLines);
        pathLines2 = new ArrayList<>();
        AppContext.getInstance().set("lines2",pathLines2);
        AppContext.getInstance().set("parent",apInterfaz);
        selVertex = new Vertex();
        AppContext.getInstance().set("selVertex",selVertex);
        startVertex = new Vertex();
        endVertex = new Vertex();
        cont=0;
        selectedVertices.clear();
        grafo = new Grafo(generalVertices);
        AppContext.getInstance().set("infoNodo",lblSel);
        this.selectedChanged = new SimpleBooleanProperty(false);
        AppContext.getInstance().set("cambio",this.selectedChanged);
        this.startChanged = new SimpleBooleanProperty(false);
        this.endChanged = new SimpleBooleanProperty(false);
        this.isDijkstra = true;
    }
    
    private void initListeners(){
        this.selectedChanged.addListener((o,f,t)->{
            if(t){
                quitOldSelectedInfo();
                this.selVertex = (Vertex) AppContext.getInstance().get("selVertex");
                loadNewSelectedInfo();
            }
        });
        this.startChanged.addListener((o,f,t)->{
            if(t){
                if(this.startVertex!=null)
                    this.startVertex.setId(null);
                this.startVertex = this.selVertex;
                this.startVertex.setId("start-vertex");
                lblStart.setText(startVertex.getIndex().toString());
            }
        });
        this.endChanged.addListener((o,f,t)->{
            if(t){
                if(this.endVertex!=null)
                    this.endVertex.setId(null);
                this.endVertex = this.selVertex;
                this.endVertex.setId("end-vertex");
                lblEnd.setText(endVertex.getIndex().toString());
            }
        });
    }
    
    private void quitOldSelectedInfo(){
        if(this.selVertex != null&&!this.selVertex.equals(this.startVertex)&&!this.selVertex.equals(this.endVertex)){
            this.selVertex.setId(null);
        }
    }
    
    private void loadNewSelectedInfo(){
        try{
            selVertex.setId("selected-vertex");
            lblSel.setText(selVertex.getIndex().toString());
            
            switch(selVertex.getState()){
                case 1:
                    tgVertexStatus.selectToggle(rbStateOpen);
                    break;
                case 2:
                    tgVertexStatus.selectToggle(rbStateSlow);
                    break;
                case 3:
                    tgVertexStatus.selectToggle(rbStateClosed);
                    break;
                default:
                    break;
            }
            
            tgVertexRoll.selectToggle(null);
            
            if(this.startVertex!=null&&this.startVertex.equals(this.selVertex)){
                this.selVertex.setId("start-vertex");
                tgVertexRoll.selectToggle(rbStart);
            }
               
            if(this.endVertex!=null&&this.endVertex.equals(this.selVertex)){
                this.selVertex.setId("end-vertex");
                tgVertexRoll.selectToggle(rbEnd);
            }    
            
        }
        catch(NullPointerException e){
            System.out.println("vértice seleccionado nulo");
        }    
        
    }
    
    private void initAlgorithms(){
        floyd = new Floyd(grafo.getNodes(),grafo.getWeigths().clone());
        floyd.initInfo();
        dijkstra = new Dijkstra(grafo.getNodes(),grafo.getAdjacents().clone(),grafo.getWeigths().clone());
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
        //selVertex.addEventFilter(EventType.ROOT, eventFilter);
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
    
    
    private void moveCar(){
        car.setVisible(true);
        VertexUtils.getInstance().clearRouteLines();//resetea líneas azules
        VertexUtils.getInstance().clearFollowedLines();//resetea líneas amarillas
        VertexUtils.getInstance().drawSelectedRoute();//dibuja líneas azules
        Animation.getInstance().desplazarListaMovsV(car, selectedVertices);//mueve el carrito
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
    
    private Boolean isValidMove(){
        Boolean valid = true;
        Mensaje msj = new Mensaje();
       
        if(this.startVertex==null){
            valid = false;
            msj.show(Alert.AlertType.NONE, "Ruta Inválida", "seleccione vértice de salida");
        }
        
        else if(this.endVertex==null){
            valid = false;
            msj.show(Alert.AlertType.NONE, "Ruta Inválida", "seleccione vértice de llegada");
        }
        
        else if(this.startVertex.equals(this.endVertex)){
            valid = false;
            msj.show(Alert.AlertType.NONE, "Ruta Inválida", "Vértices de salida y llegada deben ser diferentes");
        }
        
        return valid;
    }

    /**
     * cambiar función de un vértice (inicial o final)
     * @param event 
     */
    @FXML
    private void changeVertexRoll(ActionEvent event) {
        RadioButton selected = (RadioButton) tgVertexRoll.getSelectedToggle();
        if (selected==rbStart) {
            //System.out.println("cambio de vértice inicial");
            startChanged.set(true);
            startChanged.set(false);
        }
        else{
            //System.out.println("cambio de vértice final");
            endChanged.set(true);
            endChanged.set(false);
        }
    }

    /**
     * cambia el estado de un vértice (libre,tráfico lento, cerrado)
     * @param event 
     */
    @FXML
    private void changeVertexState(ActionEvent event) {
        RadioButton selected = (RadioButton) tgVertexStatus.getSelectedToggle();
        if (selected==rbStateOpen) {
            this.selVertex.setState(1);
        }
        else if(selected==rbStateSlow){
            this.selVertex.setState(2);
        }
        else{
            this.selVertex.setState(3);
        }
    }

    /**
     * cambia algoritmo a aplicar
     * @param event 
     */
    @FXML
    private void changeAlgorithm(ActionEvent event) {
        RadioButton selected = (RadioButton) tgAlgorithm.getSelectedToggle();
        if (selected==rbDijkstra) {
            this.isDijkstra = true;
        }
        else{
            this.isDijkstra = false;
        }
    }
    
    /**
     * llamar aquí los algoritmos
     * @param event 
     */
    @FXML
    private void move(ActionEvent event) {
        try{
            if(isValidMove()){
                if(isDijkstra){//lamar a Dijkstra
                    selectedVertices.add(startVertex);//pruebas movimiento carro (quitar)
                    selectedVertices.add(endVertex);
                    dijkstra.getShortestWay(startVertex.getIndex(), endVertex.getIndex());
                }
                else{//llamar a Floyd
                    selectedVertices.add(startVertex);//pruebas movimiento carro (quitar)
                    selectedVertices.add(endVertex);
                    floyd.getNodeList(startVertex, endVertex);
                }
                //moveCar();
            }
        }
        catch(NullPointerException e){
            System.out.println("puntero nulo");
        }
    }
}
