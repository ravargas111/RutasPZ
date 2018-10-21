/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author robri
 */
public class Points2DUtils {
    private static Points2DUtils INSTANCE = null;
    private static ArrayList<Point2D> points=new ArrayList<>();
    private static ArrayList<Point2D> points2=new ArrayList<>();
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
}
