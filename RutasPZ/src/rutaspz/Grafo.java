/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz;

import java.util.ArrayList;
import rutaspz.util.VertexUtils;

/**
 *
 * @author robri
 */
public class Grafo {
    private Integer[][] adjacents;//matriz de adyacencia
    private Double[][] weigths;
    private Integer nodes;//cantidad de nodos
    private ArrayList<Vertex> vertices;//lista de vértices con información respectiva
    private Boolean empty;//si el grafo está vacío

    public Grafo() {
        this.nodes=0;
        this.vertices=new ArrayList<>();
        this.empty=true;
    }
    
    public Grafo(Integer nodes) {
        this();
        this.nodes = nodes;
        this.adjacents = new Integer[nodes][nodes];
        this.vertices = VertexUtils.getInstance().getVerticesList();
        
        for (int i = 0; i < nodes; i++) {
            //this.vertices.add(new Vertex());
            for (int j = 0; j < nodes; j++) {
                
            }
        }
    }

    public Grafo(ArrayList<Vertex> vertices) {
        this();
        this.vertices = vertices;
    }

    public Integer[][] getAdjacents() {
        return adjacents;
    }

    public void setAdjacents(Integer[][] adjacents) {
        this.adjacents = adjacents;
    }

    public Double[][] getWeigths() {
        return weigths;
    }

    public void setWeigths(Double[][] weigths) {
        this.weigths = weigths;
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
    
    private void printVerticesList(){
        System.out.println("\n\n****Información Grafo****");
        vertices.stream().forEach(e->{
            VertexUtils.getInstance().printVertexInfo(e);
        });
        System.out.println("\nTotal Nodos: "+nodes);
    }
    
    private void printMatrix(){
        System.out.println("\n\n****Matriz Adyacencia****");
        for (int i = 0; i < nodes; i++) {
            System.out.println("");
            for (int j = 0; j < nodes; j++) {
                System.out.print(" "+adjacents[i][j]);
            }
        }
        
        System.out.println("\n\n****Matriz Pesos****");
        for (int i = 0; i < nodes; i++) {
            System.out.println("");
            for (int j = 0; j < nodes; j++) {
                System.out.print(" "+weigths[i][j]);
            }
        }
    }
    
    /**
     * cuenta el número de nodos
     */
    private void countNodes(){
        vertices.stream().forEach(e->{
            nodes++;
        });
    }
    
    private void initMatrix(){
        adjacents = new Integer[nodes][nodes];
        weigths = new Double[nodes][nodes];
        
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                adjacents[i][j]=0;
                weigths[i][j]=0.0;
            }
        }
    }
    
    private void loadMatrix(){
        
    }
    
    public void init(){
        countNodes();
        initMatrix();
        
        printVerticesList();
        printMatrix();
    }
    
    
}
