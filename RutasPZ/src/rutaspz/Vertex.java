/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz;

import java.awt.geom.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author robri
 */
public class Vertex extends Circle{
    
    private Integer index;
    private Point2D point;
    
    public Vertex() {
        super();
        index=0;
    }
    
    public Vertex(Integer index) {
        super();
        this.index=index;
    }
    
    public Vertex(Integer index,double centerX, double centerY) {
        super(centerX,centerY,5);
        this.index=index;
    }

    public Vertex(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);
        index=0;
    }

    public Vertex(double centerX, double centerY, double radius, Paint fill) {
        super(centerX, centerY, radius, fill);
        index=0;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
    
    public void point2D(){
        point = new Point2D() {
            @Override
            public double getX() {
                return getCenterX();
            }

            @Override
            public double getY() {
                return getCenterY();
            }

            @Override
            public void setLocation(double d, double d1) {
                
            }
        };
    }

    public Point2D getPoint() {
        return point;
    }

    public void setPoint(Point2D point) {
        this.point = point;
    }
    
    public Double getX(){
        return this.getCenterX();
    }
    
    public Double getY(){
        return this.getCenterY();
    }
    
    /**
     * obtiene la distancia entre dos vértices
     * @param v2
     * @return 
     */
    public Double distance(Vertex v2){
        try{
        Double distance = point.distance(v2.getPoint());
        return ((double)Math.round(distance * 100d) / 100d);
        }
        catch(NullPointerException e){
            return 0.0;
        }
    }
    
}
