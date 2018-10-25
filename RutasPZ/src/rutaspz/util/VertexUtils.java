/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import rutaspz.Vertex;

/**
 *
 * @author robri
 */
public class VertexUtils {
    private static VertexUtils INSTANCE = null;
    private static ArrayList<Point2D> points=new ArrayList<>();//no se usan
    private static ArrayList<ArrayList<Point2D>> pointsArray=new ArrayList<ArrayList<Point2D>>();//no se usan
    private static ArrayList<Vertex> verticesList = new ArrayList<>();//guarda la lista de vértices general
    private static ArrayList<Vertex> selectedVertices = new ArrayList<>();//guarda la lista de vértices para la ruta creada
    private static ArrayList<Line> routeLines = new ArrayList<>();//guarda las líneas de la ruta principal
    private static ArrayList<Line> followedLines = new ArrayList<>();//guarda las líneas de la ruta seguida
    private static Vertex selectedVertex = new Vertex();
    private static Vertex startVertex = new Vertex();
    private static Vertex endVertex = new Vertex();
    public static enum COLOR {RED,YELLOW,BLUE};
    public VertexUtils() {
    }
    
    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AppContext.class) {
                if (INSTANCE == null) {
                    INSTANCE = new VertexUtils();
                }
            }
        }
    }

    public static VertexUtils getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public ArrayList<Point2D> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point2D> points) {
        VertexUtils.points = points;
    }

    public ArrayList<ArrayList<Point2D>> getPointsArray() {
        return pointsArray;
    }

    public ArrayList<Vertex> getVerticesList() {
        return verticesList;
    }

    public void setVerticesList(ArrayList<Vertex> verticesList) {
        VertexUtils.verticesList = verticesList;
    }

    public ArrayList<Vertex> getSelectedVertices() {
        return selectedVertices;
    }

    public void setSelectedVertices(ArrayList<Vertex> selectedVertices) {
        VertexUtils.selectedVertices = selectedVertices;
    }

    public void setPointsArray(ArrayList<ArrayList<Point2D>> pointsArray) {
        VertexUtils.pointsArray = pointsArray;
    }

    public Vertex getSelectedVertex() {
        return selectedVertex;
    }

    public void setSelectedVertex(Vertex selectedVertex) {
        VertexUtils.selectedVertex = selectedVertex;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        VertexUtils.startVertex = startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex endVertex) {
        VertexUtils.endVertex = endVertex;
    }
    
    public Point2D getLastPoint(){
        if(points.size()>0)
        return points.get(points.size()-1);
        else return null;
    }
    
    public Point2D getFirstPoint(){
        if(points.size()>0)
        return points.get(0);
        else return null;
    }
    
    public Vertex getLastVertex(){
        try{
           return selectedVertices.get(points.size()-1); 
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
    
    public Vertex getFirstVertex(){
        try{
           return selectedVertices.get(0); 
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }  
    }
    
    public Point2D getPoint(Integer i){
        if(points.size()<i)
        return points.get(i);
        else return null;
    }
    
    public Vertex getVertex(Integer i){
        if(selectedVertices.size()<i)
        return selectedVertices.get(i);
        else return null;
    }
    
    public Integer getPointIndex(Point2D p){
        if(points.contains(p)){
            return points.indexOf(p);
        }
        else return null;
    }
    
    public Integer getVertexIndex(Vertex p){
        if(selectedVertices.contains(p)){
            return selectedVertices.indexOf(p);
        }
        else return null;
    }
    
    public Point2D getNextPoint(Integer i){
        try{
        return points.get(i+1);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
    
    public Vertex getNextVertex(Integer i){
        try{
        return verticesList.get(i+1);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
    
    public Vertex getNextVertexS(Integer i){
        try{
        return selectedVertices.get(i+1);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
    
    /**
     * obtiene el siguiente punto de la lista de puntos
     * @param p
     * @return 
     */
    public Point2D getNextPoint(Point2D p){
        Integer i=0;
        if(points.contains(p)){
            i=points.indexOf(p);
            try{
                return points.get(points.indexOf(p)+1);
            }
            catch(IndexOutOfBoundsException e){
                return null;
            }
        }
        else return null;
    }
    
    public Vertex getNextVertex(Vertex p){
        Integer i=0;
        if(selectedVertices.contains(p)){
            i=selectedVertices.indexOf(p);
            try{
                return selectedVertices.get(selectedVertices.indexOf(p)+1);
            }
            catch(IndexOutOfBoundsException e){
                return null;
            }
        }
        else return null;
    }
    
    public Point2D getPreviousPoint(Integer i){
        try{
            return points.get(i-1);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
    
    public Point2D getPreviousPoint(Point2D p){
        Integer i=0;
        if(points.contains(p)){
            i=points.indexOf(p);
            try{
                return points.get(points.indexOf(p)-1);
            }
            catch(IndexOutOfBoundsException e){
                return null;
            }
        }
        else return null;
    }
    
    public Vertex getPreviousVertex(Vertex p){
        Integer i=0;
        if(selectedVertices.contains(p)){
            i=selectedVertices.indexOf(p);
            try{
                return selectedVertices.get(selectedVertices.indexOf(p)-1);
            }
            catch(IndexOutOfBoundsException e){
                return null;
            }
        }
        else return null;
    }
    
    public void insertPoint2D(Double x,Double y){
        Point2D p=new Point2D() {
            @Override
            public double getX() {
                return x;
            }

            @Override
            public double getY() {
                return y;
            }

            @Override
            public void setLocation(double d, double d1) {
                //
            }
        };
        
        points.add(p);
    }
    
    /**
     * crea un punto 2D a partir de coordenadas
     * @param x
     * @param y
     * @return 
     */
    public Point2D createPoint2D(Double x,Double y){
        Point2D p=new Point2D() {
            @Override
            public double getX() {
                return x;
            }

            @Override
            public double getY() {
                return y;
            }

            @Override
            public void setLocation(double d, double d1) {
                //
            }
        };
        
        return p;
    }
    
    /**
     * le crea un punto 2D a un vértice
     * @param v 
     */
    public void createPoint2D(Vertex v){
        Point2D p=new Point2D() {
            @Override
            public double getX() {
                return v.getX();
            }

            @Override
            public double getY() {
                return v.getY();
            }

            @Override
            public void setLocation(double d, double d1) {
                //
            }
        };
        
        v.setPoint(p);
    }
    
    public void insertVertex(Double x,Double y,Integer index){
        
    }
    
    public void printPoint(Point2D p){
        System.out.println("("+p.getX()+","+p.getY()+")");
    }
    
    /**
     * imprime en consola la información entre dos puntos
     * @param v 
     */
    public void printPoints(Point2D p1,Point2D p2){
        if(p1!=null)
            System.out.print("("+p1.getX()+","+p1.getY()+")");
        //else{
        if(p2!=null){
            System.out.print("--> ("+p2.getX()+","+p2.getY()+")");
            System.out.print(" ---> Distancia ="+getDistance(p1, p2)+"\n");
            }
            else
                System.out.print("punto 2 nulo");
        //}
    }
    
    /**
     * imprime en consola la información de un vértice
     * @param v 
     */
    public void printVertexInfo(Vertex v){
        System.out.print("\n(index "+v.getIndex()+")");
        System.out.print(" --> ("+v.getX()+","+v.getY()+")");
        
    }
    
    /**
     * imprime en consola la información entre dos puntos
     * @param v 
     */
    public void printPointsInfo(Point2D p1,Point2D p2){
        if(p1!=null)
            System.out.print("\n("+p1.getX()+","+p1.getY()+")");
        //else{
        if(p2!=null){
            System.out.print("--> ("+p2.getX()+","+p2.getY()+")");
            System.out.print(" ---> Distancia ="+getDistance(p1, p2)+"\n");
            }
            else
                System.out.print("punto 2 nulo");
        //}
    }
    
    /**
     * inprime en la consola información de dos vértices
     * @param v1
     * @param v2 
     */
    public void printVerticesInfo(Vertex v1,Vertex v2){
        if(v1!=null)
            System.out.print("\n("+v1.getX()+","+v1.getY()+")");
        //else{
        if(v2!=null){
            System.out.print("--> ("+v2.getX()+","+v2.getY()+")");
            System.out.print(" ---> Distancia ="+getDistance(v1, v2)+"\n");
            }
            else
                System.out.print("Fin de la ruta");
    }
    
    public void printVerticesRoute(){
        selectedVertices.stream().forEach(e->{
            printVerticesInfo(e,getNextVertex(e));
        });
    }
    
    public void printRoutePoints(){
        points.stream().forEach(e->{
            printPoint(e);
        });
        System.out.println("****Distancia total****\n"+getTotalDistance());
    }
    
    public Integer getX(Point2D p){
        return ((Double)p.getX()).intValue();
    }
    
    public Integer getY(Point2D p){
        return ((Double)p.getY()).intValue();
    }
    
    /**
     * distancia entre dos puntos
     * @param p1
     * @param p2
     * @return 
     */
    public Double getDistance(Point2D p1,Point2D p2){
        try{
        Double distance = p1.distance(p2);
        return ((double)Math.round(distance * 100d) / 100d);
        }
        catch(NullPointerException e){
            return 0.0;
        }
    }
    
    /**
     * distancia entre dos vértices
     * @param p1
     * @param p2
     * @return 
     */
    public Double getDistance(Vertex v1,Vertex v2){
        try{
        Double distance = v1.distance(v2);
        return ((double)Math.round(distance * 100d) / 100d);
        }
        catch(NullPointerException e){
            return 0.0;
        }
    }
    
    /**
     * obtiene la distacia total de una ruta de puntos
     * @return 
     */
    public Double getTotalDistance(){
        Double distance=0.0;
        for(Point2D p:points){
            Point2D p2=getNextPoint(p);
            distance+=getDistance(p, p2);
        }
        return ((double)Math.round(distance * 100d) / 100d);
    }
    
    /**
     * obtiene la distacia total de la ruta de vértices seleccionados
     * @return 
     */
    public Double getTotalDistanceV(){
        Double distance=0.0;
        for(Vertex p:selectedVertices){
            Vertex p2=getNextVertex(p);
            distance+=getDistance(p, p2);
        }
        return ((double)Math.round(distance * 100d) / 100d);
    }
    
    public Double getTotalDistance(ArrayList<Point2D> pointsA){
        Double distance=0.0;
        for(Point2D p:pointsA){
            Point2D p2=getNextPoint(p);
            distance+=getDistance(p, p2);
        }
        return ((double)Math.round(distance * 100d) / 100d);
    }
    
    public Integer compareDistace(ArrayList<Point2D> pointsA){
        try{
            Double d1 = getTotalDistance();
            Double d2 = getTotalDistance(pointsA);
            return d1.compareTo(d2);
        }
        catch(NullPointerException e){
            return null;
        }
    }
    
    public void setBetterRoute(ArrayList<Point2D> pointsA){
        try{
           Integer i = compareDistace(pointsA);
           if(i<0){
               System.out.println("Era menor la segunda ruta....cambiando");
               points.clear();
               points.addAll(pointsA);
           }
               
        }
        catch(NullPointerException e){
            System.err.println("Algo salió mal al comparar distancias");
        }
        
    }
    
    public void setBetterRoute(){
        pointsArray.stream().forEach(pA->{
            setBetterRoute(pA);
        });
    }
    
    public void printDistances(){
        System.out.println("*******Distancias de todas las rutas********");
        pointsArray.stream().forEach(e->{
            System.out.println("Distancia: "+getTotalDistance(e));;
        });
    }
    
    public void addNewRouteToArray(ArrayList<Point2D> pointsA){
        ArrayList<Point2D> pointsN = new ArrayList<>();
        pointsN.addAll(pointsA);
        pointsArray.add(pointsN);
    }
    
    public void drawLine(Pane parent,Point2D p1,Point2D p2){
        Line line=new Line();
        parent.getChildren().add(line);
        line.setStartX(p1.getX());
        line.setStartY(p1.getY());
        line.setEndX(p2.getX());
        line.setEndY(p2.getY());
        routeLines.add(line);
        //pathLines.add(line); 
    }
    
    public void drawLine(Pane parent,Vertex p1,Vertex p2){
        Line line=new Line();
        parent.getChildren().add(line);
        line.setStartX(p1.getX());
        line.setStartY(p1.getY());
        line.setEndX(p2.getX());
        line.setEndY(p2.getY());
        routeLines.add(line);
        //pathLines.add(line); 
    }
    
    public Line drawLine(Point2D p1,Point2D p2,COLOR c){
        AnchorPane parent = (AnchorPane) AppContext.getInstance().get("parent");
        Line line=new Line();
        line.setStartX(p1.getX());
        line.setStartY(p1.getY());
        line.setEndX(p2.getX());
        line.setEndY(p2.getY());
        parent.getChildren().add(line);
        switchLineColor(line,c);
        routeLines.add(line);
        return line;
    }
    
    /**
     * dibuja una línea para la ruta establecida
     * @param p1 vértice inicio
     * @param p2 vértice fin
     * @param c color
     * @return Línea a añadir
     */
    public Line drawLineVSel(Vertex p1,Vertex p2,COLOR c){
        AnchorPane parent = (AnchorPane) AppContext.getInstance().get("parent");
        Line line=new Line();
        line.setStartX(p1.getX());
        line.setStartY(p1.getY());
        line.setEndX(p2.getX());
        line.setEndY(p2.getY());
        parent.getChildren().add(line);
        line.toBack();
        switchLineColor(line,c);
        routeLines.add(line);
        return line;
    }
    
    /**
     * dibuja una línea para la ruta seguida
     * @param p1 vértice inicio
     * @param p2 vértice fin
     * @param c color
     * @return Línea a añadir
     */
    public Line drawFollowedLine(Vertex p1,Vertex p2,COLOR c){
        AnchorPane parent = (AnchorPane) AppContext.getInstance().get("parent");
        Line line=new Line();
        line.setStartX(p1.getX());
        line.setStartY(p1.getY());
        line.setEndX(p2.getX());
        line.setEndY(p2.getY());
        parent.getChildren().add(line);
        p1.toFront();
        p2.toFront();
        switchLineColor(line,c);
        followedLines.add(line);
        return line;
    }
    
    public void drawRouteList(){
        Integer i=0;
        //AnchorPane parent= (AnchorPane) AppContext.getInstance().get("parent");
        for(Vertex p:verticesList){
            Vertex p2 = VertexUtils.getInstance().getNextVertex(i);
            if(p2!=null){
                drawLineVSel(p,p2,COLOR.BLUE);
                //Points2DUtils.getInstance().printPoints(p, p2);
            }
            i++;
        }
    }
    
    public void drawSelectedRoute(){
        Integer i=0;
        AnchorPane parent= (AnchorPane) AppContext.getInstance().get("parent");
        for(Vertex p:selectedVertices){
            Vertex p2 = VertexUtils.getInstance().getNextVertexS(i);
            if(p2!=null){
                drawLineVSel(p,p2,COLOR.BLUE);
                //Points2DUtils.getInstance().printPoints(p, p2);
            }
            i++;
        }
    }
    
    private void switchLineColor(Line l,COLOR c){
        l.setStrokeWidth(7);
        switch (c) {
            case YELLOW:
                l.setStroke(Color.YELLOW);
                break;
            case RED:
                l.setStroke(Color.RED);
                break;
            case BLUE:
                l.setStroke(Color.BLUE);
                break;
            default:
                break;
        }
    }
    
    public void drawVertex(Point2D p,Integer index){
        AnchorPane parent =(AnchorPane) AppContext.getInstance().get("parent");
        Vertex vertex = new Vertex(index, p.getX(), p.getY());
        parent.getChildren().add(vertex);
        vertex.getStyleClass().add("circle");
        vertex.setOnMouseClicked(e->{
            //vertex.getIndex();
            System.out.println("Index:"+vertex.getIndex());
            
        });
        verticesList.add(vertex);
    }
    
    public void drawVertex(Double x,Double y,Integer index){
        AnchorPane parent =(AnchorPane) AppContext.getInstance().get("parent");
        Vertex vertex = new Vertex(x,y,5);
        vertex.setIndex(index);
        parent.getChildren().add(vertex);
        vertex.getStyleClass().add("circle");
        vertex.setOnMouseClicked(e->{
            vertex.getIndex();
            System.out.println("Index: "+vertex.getIndex());
        });
        verticesList.add(vertex);
    }
    
    /**
     * dibuja un vértice en el parent y añade evento (por el momento añade a vértices seleccionados)
     * @param v 
     */
    public void drawVertex(Vertex v){
        AnchorPane parent =(AnchorPane) AppContext.getInstance().get("parent");
        Label infoNodo = (Label) AppContext.getInstance().get("infoNodo");//todo quitar
        parent.getChildren().add(v);
        v.getStyleClass().add("circle");
        createPoint2D(v);
        Utils.getInstance().createPopUp(v);
        v.setOnMouseClicked(e->{
            //printPoint(v);
            //Utils.getInstance().createPopUp(v,parent);
            //todo descomentar la parte de seleccionar vértice
            //selectedVertices.add(v);
            if(e.getButton()==MouseButton.PRIMARY){
                selectedVertex = v;
                System.out.println("//Adyacencias vértice "+selectedVertex.getIndex());
                infoNodo.setText(v.getIndex().toString());
            }
            else if(e.getButton()==MouseButton.SECONDARY){
                System.out.println("putAdyacencia("+selectedVertex.getIndex()+","+v.getIndex()+");");
            }
        });
    }
    
    private void createPopUp(Vertex node){
        JFXPopup popUp = new JFXPopup();
        VBox vbox = popUpContent(node);
        node.setOnMouseClicked(e->{
            //System.out.println("mostrando pop");
            popUp.show(node, e.getScreenX(), e.getScreenY());
        });
        
        popUp.setPopupContent(vbox);
    }
    
    private VBox popUpContent(Node node){
        VBox vbox=new VBox();
        JFXButton b1 = new JFXButton("asdasd");
        vbox.getChildren().add(b1);
        return vbox;
    }
    
    public void drawPointsList(){
        Integer i=0;
        for(Point2D p:points){
           drawVertex(p, i);
            i++; 
        } 
    }
    
    /**
     * dibuja los vértices en el parent a partir de una lista de vértices
     */
    public void drawVerticesList(){
        Integer i=0;
        for(Vertex p:verticesList){
           drawVertex(p);
           p.setIndex(i);
            i++; 
        }
    }
    
    /**
     * limpia la lista de vértices
     */
    public void clearVertices(){
        VertexUtils.verticesList.clear();
    }
    
    /**
     * elimina las líneas de la ruta establecida
     */
    public void clearRouteLines(){
        
        AnchorPane parent = (AnchorPane) AppContext.getInstance().get("parent");
        if(!routeLines.isEmpty()){
            routeLines.stream().forEach(e->{
                Utils.getInstance().quitObject(parent, e);
            });
        }
        routeLines.clear();
    }
    
    /**
     * elimina las líneas de la ruta seguida por el nodo
     */
    public void clearFollowedLines(){
        AnchorPane parent = (AnchorPane) AppContext.getInstance().get("parent");
        if(!followedLines.isEmpty()){
            followedLines.stream().forEach(e->{
                Utils.getInstance().quitObject(parent, e);
            });
        }
        followedLines.clear();
    }
}
