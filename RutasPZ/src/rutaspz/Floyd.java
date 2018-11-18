/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz;

import java.util.ArrayList;
import rutaspz.util.AppContext;

/**
 *
 * @author robri
 */
public class Floyd {
    final static Double INF = 99999.0;
    private Integer size;
    private Double weights[][];
    private Double[][] distances;//contiene las distancias mínimas entre cada parde vértices
    private Integer[][] visitedVertex;//los vértices para acceder a cada par
    private ArrayList<Vertex> generalVertices;

    public Floyd(Integer size, Double[][] weights) {
        this.size = size;
        this.weights = weights;
        this.distances = new Double[size][size];
        this.visitedVertex = new Integer[size][size];
        this.generalVertices = (ArrayList<Vertex>) AppContext.getInstance().get("generalVertices");
    }

    public Double[][] getWeights() {
        return weights;
    }

    public void setWeights(Double[][] weights) {
        this.weights = weights;
    }
    
    public void initInfo(){
        initDistances();
        //printDistances();
        initVisitedVertex();
        //printVisitedVertex();
    }
    
    private void initDistances(){
       //inicializa la matriz de caminos más cortos igual que la de pesos
        for (int i = 0; i < size; i++) 
            for (int j = 0; j < size; j++){
                if(i!=j){
                    if(!weights[i][j].equals(0.0))
                    distances[i][j] = weights[i][j];
                    else{
                        distances[i][j] = INF;
                        visitedVertex [i][j]=-10;
                    }
                }
                else distances[i][j] = 0.0;
            }
    }
    
    private void initVisitedVertex(){
        for (int i = 0; i < size; i++){
           for (int j = 0; j < size; j++){
                if(distances[i][j].equals(INF))
                    visitedVertex [i][j]=-1;
                else visitedVertex [i][j] = i;
           }       
        } 
              
                 
    }
    
    private void printDistances(){
        System.out.println("\n\n*****Matriz de distancias*****\n");
        for (int i = 0; i < size; i++) {
            System.out.println("");
            for (int j = 0; j < size; j++) {
                if(!distances[i][j].equals(INF))
                System.out.printf("%10.2f",distances[i][j]);
                else System.out.printf("%10s", "INF");
            }
        }
        System.out.println("");
    }
    
    private void printVisitedVertex(){
        System.out.println("\n\n*****Matriz de vértices intermediarios*****\n");
        for (int i = 0; i < size; i++) {
            System.out.println("");
            for (int j = 0; j < size; j++) {
                //System.out.print(visitedVertex[i][j]);
                System.out.printf("%5d", visitedVertex[i][j]);
            }
        }
        System.out.println("");
    }

    /**
     * crea la matriz con los caminos más cortos entre cada par de vértices y la matriz con los nodos intermediarios
     */
    public void getShortestPath(Vertex start,Vertex end){
        for (int k = 0; k < size; k++) 
        { 
            //selecciona vértice fuente
            for (int i = 0; i < size; i++) 
            { 
                // vértice destino para fuente seleccionada por i
                for (int j = 0; j < size; j++) 
                { 
                    // If vertex k is on the shortest path from 
                    // i to j, then update the value of dist[i][j] 
                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        //System.out.println("cambio: ("+k+","+j+")");
                        distances[i][j] = distances[i][k] + distances[k][j];
                        visitedVertex[i][j] = k;
                    }
                } 
            } 
        }
        
        getNodeList(start,end);
    }
    
    /**
     * obtiene la secuencia de nodos para llegar de A -> B
     * @param start vértice inicio
     * @param end vértice fin
     */
    private void getNodeList(Vertex start,Vertex end){
        ArrayList<Vertex> list = (ArrayList<Vertex>) AppContext.getInstance().get("selectedVertices");
        list.clear();
        Integer startIndex = start.getIndex();
        Integer endIndex = end.getIndex();
        Integer step;
        
        while (visitedVertex[startIndex][endIndex] != startIndex) {
            step=visitedVertex[startIndex][endIndex];
            list.add(0, generalVertices.get(step));
            endIndex = step;
    	}
        list.add(0,start);
        list.add(end);
        
        //System.out.println("Ruta seleccionada: ");
        for(Vertex v:list){
            //System.out.println(v.getIndex()+"->");
            
        }

    }

    private Double getCost(Vertex start,Vertex end){
        Double estimatedCost = distances[start.getIndex()][end.getIndex()];
        System.out.println("Costo estimado (Floyd): "+estimatedCost);
        return estimatedCost;
    }
}
