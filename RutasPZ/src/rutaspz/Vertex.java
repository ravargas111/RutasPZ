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
    private Integer grade;
    private Boolean option;
    private Boolean accident;
    private Integer state;//1 normal,2 moderado,3 lento
    private Boolean open;//habilitado
    
    
    public Vertex() {
        super();
        index=0;
        grade=0;
        state=1;
        open=true;
        option=false;
        accident = false;
    }
    
    public Vertex(Integer index) {
        super();
        this.index=index;
        state=1;
        open=true;
        option=false;
        accident = false;
    }
    
    public Vertex(Integer index,double centerX, double centerY) {
        super(centerX,centerY,5);
        this.index=index;
        state=1;
        open=true;
        accident = false;
    }

    public Vertex(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);
        index=0;
        state=1;
        open=true;
        option=false;
        accident = false;
    }

    public Vertex(double centerX, double centerY, double radius, Paint fill) {
        super(centerX, centerY, radius, fill);
        index=0;
        state=1;
        open=true;
        option=false;
        accident = false;
    }

    /**
     * estado de disponibilidad del vértice
     * @return 1 diponible,2 lento, 3 cerrado
     */
    public Integer getState() {
        return state;
    }

    /**
     * estado de disponibilidad del vértice
     * @param state 1 diponible,2 lento, 3 cerrado
     */
    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getOption() {
        return option;
    }

    public void setOption(Boolean option) {
        this.option = option;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getAccident() {
        return accident;
    }

    public void setAccident(Boolean accident) {
        this.accident = accident;
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
