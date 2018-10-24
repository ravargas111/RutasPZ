/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz;

import java.util.ArrayList;
import rutaspz.util.Points2DUtils;

/**
 *
 * @author robri
 */
public class Grafo {
    private Integer[][] adjacents;//matriz de adyacencia
    private Integer nodes;//cantidad de nodos
    private ArrayList<Vertex> vertices;//lista de vértices con información respectiva
    private Boolean empty;//si el grafo está vacío

    public Grafo() {
    }
    
    public Grafo(Integer nodes) {
        this.nodes = nodes;
        this.adjacents = new Integer[nodes][nodes];
        this.vertices = Points2DUtils.getInstance().getVerticesList();
        
        for (int i = 0; i < nodes; i++) {
            //this.vertices.add(new Vertex());
            for (int j = 0; j < nodes; j++) {
                
            }
        }
    }

    public Integer[][] getAdjacents() {
        return adjacents;
    }

    public void setAdjacents(Integer[][] adjacents) {
        this.adjacents = adjacents;
    }

    public Integer getNodes() {
        return nodes;
    }

    public void setNodes(Integer nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Boolean isEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }
    
    
    
}
