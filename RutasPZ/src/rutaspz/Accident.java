/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz;

/**
 *
 * @author robri
 */
public class Accident {
    private Vertex v1;
    private Vertex v2;

    public Accident(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Accident() {
    }

    public Vertex getV1() {
        return v1;
    }

    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public void setV2(Vertex v2) {
        this.v2 = v2;
    }
    
    public Boolean exists(Vertex v1,Vertex v2){
        if(v1.equals(this.v1)&&v2.equals(this.v2))
            return true;
        else return false;
    }
}
