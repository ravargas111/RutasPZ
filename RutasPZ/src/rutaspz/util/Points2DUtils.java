/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import rutaspz.Vertex;

/**
 *
 * @author robri
 */
public class Points2DUtils {
    private static Points2DUtils INSTANCE = null;
    private static ArrayList<Point2D> points=new ArrayList<>();
    private static ArrayList<ArrayList<Point2D>> pointsArray=new ArrayList<ArrayList<Point2D>>();
    private static ArrayList<Vertex> verticesList = new ArrayList<>();
    public static enum COLOR {RED,YELLOW,BLUE};
    public Points2DUtils() {
    }
    
    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AppContext.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Points2DUtils();
                }
            }
        }
    }

    public static Points2DUtils getInstance() {
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
        Points2DUtils.points = points;
    }

    public ArrayList<ArrayList<Point2D>> getPointsArray() {
        return pointsArray;
    }

    public ArrayList<Vertex> getVerticesList() {
        return verticesList;
    }

    public void setVerticesList(ArrayList<Vertex> verticesList) {
        Points2DUtils.verticesList = verticesList;
    }

    public void setPointsArray(ArrayList<ArrayList<Point2D>> pointsArray) {
        Points2DUtils.pointsArray = pointsArray;
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
    
    public Point2D getPoint(Integer i){
        if(points.size()<i)
        return points.get(i);
        else return null;
    }
    
    public Integer getPointIndex(Point2D p){
        if(points.contains(p)){
            return points.indexOf(p);
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
    
    public void insertVertex(Double x,Double y,Integer index){
        
    }
    
    public void printPoint(Point2D p){
        System.out.println("("+p.getX()+","+p.getY()+")");
    }
    
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
    
    public Double getDistance(Point2D p1,Point2D p2){
        try{
        Double distance = p1.distance(p2);
        return ((double)Math.round(distance * 100d) / 100d);
        }
        catch(NullPointerException e){
            return 0.0;
        }
    }
    
    public Double getTotalDistance(){
        Double distance=0.0;
        for(Point2D p:points){
            Point2D p2=getNextPoint(p);
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
        //pathLines.add(line); 
    }
    
    public void drawLine(Pane parent,Vertex p1,Vertex p2){
        Line line=new Line();
        parent.getChildren().add(line);
        line.setStartX(p1.getX());
        line.setStartY(p1.getY());
        line.setEndX(p2.getX());
        line.setEndY(p2.getY());
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
        return line;
    }
    
    public Line drawLine(Vertex p1,Vertex p2,COLOR c){
        AnchorPane parent = (AnchorPane) AppContext.getInstance().get("parent");
        Line line=new Line();
        line.setStartX(p1.getX());
        line.setStartY(p1.getY());
        line.setEndX(p2.getX());
        line.setEndY(p2.getY());
        parent.getChildren().add(line);
        switchLineColor(line,c);
        return line;
    }
    
    public void createRouteLinesV(){
        Integer i=0;
        AnchorPane parent= (AnchorPane) AppContext.getInstance().get("parent");
        for(Vertex p:verticesList){
            Vertex p2 = Points2DUtils.getInstance().getNextVertex(i);
            if(p2!=null){
                drawLine(p,p2,COLOR.BLUE);
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
            vertex.getIndex();
            System.out.println("Index: "+vertex.getIndex());
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
    
    public void drawVertex(Vertex v){
        AnchorPane parent =(AnchorPane) AppContext.getInstance().get("parent");
        parent.getChildren().add(v);
        v.getStyleClass().add("circle");
        v.setOnMouseClicked(e->{
            v.getIndex();
            System.out.println("Index: "+v.getIndex());
        });
    }
    
    public void drawVertices(){
        Integer i=0;
        for(Point2D p:points){
           drawVertex(p, i);
            i++; 
        } 
    }
    
    public void drawVerticesV(){
        Integer i=0;
        for(Vertex p:verticesList){
           drawVertex(p);
           p.setIndex(i);
            i++; 
        }
        //System.out.println("Vertices List size:"+verticesList.size());
    }
    
    public void clearVertices(){
        this.verticesList.clear();
    }
    
}
