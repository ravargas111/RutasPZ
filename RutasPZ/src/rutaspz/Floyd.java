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
public class Floyd {
    final static Double INF = 99999.0;
    private Integer size;
    private Double weights[][];
    private Double[][] distances;//contiene las distancias mínimas entre cada parde vértices
    private Integer[][] visitedVertex;//los vértices para acceder a cada par

    public Floyd(Integer size, Double[][] weights) {
        this.size = size;
        this.weights = weights;
        this.distances = new Double[size][size];
        this.visitedVertex = new Integer[size][size];
    }
    
    public void initInfo(){
        initDistances();
        printDistances();
        initVisitedVertex();
        printVisitedVertex();
    }
    
    private void initDistances(){
       //inicializa la matriz de caminos más cortos igual que la de pesos
        for (int i = 0; i < size; i++) 
            for (int j = 0; j < size; j++){
                if(!weights[i][j].equals(0.0))
                distances[i][j] = weights[i][j];
                else distances[i][j] = INF;
            }
    }
    
    private void initVisitedVertex(){
        for (int i = 0; i < size; i++) 
            for (int j = 0; j < size; j++) 
                visitedVertex [i][j] = j; 
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

    private void minRoute(){
        int i, j, k; 
        
        //initInfo();
        
        
        
    }
}
