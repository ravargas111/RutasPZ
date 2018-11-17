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
    public void shortestPaths(){
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
                        visitedVertex[i][j] = visitedVertex[k][j];//fila con la que se está tratando (revisar si solo poner i)
                        //visitedVertex[i][j] = k;
                    }
                } 
            } 
        } 
    }
    
    public void getNodeList(Vertex start,Vertex end){
        shortestPaths();
        printVisitedVertex();
        Integer origin = start.getIndex();
        Integer dest = end.getIndex();
        Integer step=0;
        Boolean stop=false;
        step = visitedVertex[origin][dest];
        
        System.out.println("Floyd: ("+origin+","+dest+")");
        System.out.println("primer paso "+step);
        do{
            //step = visitedVertex[step][dest];
            //System.out.println("paso "+step);
            stop=true;
        }
        while(step!=dest&&!stop);
    }

}
