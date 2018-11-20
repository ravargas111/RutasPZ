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
    
    /**
     * invoca todas las funciones para cargar los datos del grafo
     */
    public void init(){
        countNodes();
        initMatrix();
        initAyacents();
        searchAdyacents();
        //printGrafo();
    }
    
    /**
     * imprime informaación de todos los vértices
     */
    private void printGrafo(){
        System.out.println("\n\n****Información Grafo****");
        System.out.println("");
        System.out.println("\nMatriz Adyacencia");
        printMatrixA();
        System.out.println("");
        System.out.println("\nMatriz Pesos");
        printMatrixW();
        printVertices();
        System.out.println("\nTotal Nodos: "+nodes);
    }
    
    /**
     * cuenta el número de vértices
     */
    private void countNodes(){
        vertices.stream().forEach(e->{
            nodes++;
        });
    }
    
    /**
     * imprime matriz de adyacencia
     */
    private void printMatrixA(){
        System.out.println("\n\n****Matriz Adyacencia****");
        
        //System.out.println("Integer [][] mat = {");
        for (int i = 0; i < nodes; i++) {
            System.out.println("");
            for (int j = 0; j < nodes; j++) {
                //System.out.print(" "+adjacents[i][j]);
                System.out.printf("%5d", adjacents[i][j]);
            }
        }
    }
    
    /**
     * imprime matriz de pesos
     */
    private void printMatrixW(){
        System.out.println("\n\n****Matriz Pesos****");
        for (int i = 0; i < nodes; i++) {
            System.out.println("");
            for (int j = 0; j < nodes; j++) {
                //System.out.print(" "+weigths[i][j]);
                System.out.printf("%10.2f",weigths[i][j]);
            }
        }
    }
    
    /**
     * instancia de matrices adyacencia y pesos
     */
    private void initMatrix(){
        adjacents = new Integer[nodes][nodes];
        weigths = new Double[nodes][nodes];
        
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                adjacents[i][j]=0;
                weigths[i][j]=0.0;
            }
        }
        loadMatrix();
    }
    
    /**
     * carga la matriz inicial
     */
    private void loadMatrix(){
        //adjacents = initialMatrix();
    }
    
    /**
     * pone como adyacente una posición de la matriz de adyacencia
     * @param i
     * @param j 
     */
    public void putAdyacencia(Integer i,Integer j){
        adjacents[i][j]=1;
    }
    
    /**
     * por cada adyacente busca el peso hacia el otro para cargarlos en la matriz de pesos
     */
    private void searchAdyacents(){
        //System.out.println("\n\n\n***prueba adyacencias***");
        vertices.stream().forEach(e->{
            Integer i = e.getIndex();//a partir del nodo x,revisa todas las columnas
            //System.out.println("");
            //System.out.println("adyacencias nodo ("+i+")");
            Integer grade=0;
            for (int j = 0; j < nodes; j++) {
                if(adjacents[i][j]>0){
                   doubleWay(i,j);//selecciona el grado
                   Double w = e.distance(vertices.get(j));
                   //System.out.print("("+i+","+j+") -> ");
                   grade+=adjacents[i][j];
                   weigths[i][j] = w;//selecciona el peso
                }    
            }
            e.setGrade(grade);
            //System.out.println("B: "+i);
        });
        //printMatrixW();
    }
    
    /**
     * busca si hay doble vía y pone el grado en 2
     * @param i
     * @param j 
     */
    private void doubleWay(Integer i,Integer j){
        if(adjacents[i][j].equals(1)&&adjacents[j][i].equals(0)){
            adjacents[i][j]=2;
            adjacents[j][i]=2;
        }
    }
    
    /**
     * inicializa la matriz de adyacencias
     */
    private void initAyacents(){
        initBorderAdyacents();
        initPricipalStreetAdyacents();
        initOtherAdyacents();
    }
    
    private void initBorderAdyacents(){
        //Adyacencias vértice 0
        putAdyacencia(0,1);
        putAdyacencia(0,26);
        //Adyacencias vértice 1
        putAdyacencia(1,0);
        putAdyacencia(1,2);
        //Adyacencias vértice 2
        putAdyacencia(2,1);
        putAdyacencia(2,3);
        putAdyacencia(2,33);
        //Adyacencias vértice 3
        putAdyacencia(3,2);
        putAdyacencia(3,4);
        putAdyacencia(3,33);
        //Adyacencias vértice 4
        putAdyacencia(4,3);
        putAdyacencia(4,5);
        putAdyacencia(4,35);
        //Adyacencias vértice 5
        putAdyacencia(5,4);
        putAdyacencia(5,6);
        //Adyacencias vértice 6
        putAdyacencia(6,5);
        putAdyacencia(6,7);
        //Adyacencias vértice 7
        putAdyacencia(7,6);
        putAdyacencia(7,8);
        putAdyacencia(7,46);
        //Adyacencias vértice 8
        putAdyacencia(8,7);
        putAdyacencia(8,9);
        putAdyacencia(8,52);
        //Adyacencias vértice 9
        putAdyacencia(9,8);
        putAdyacencia(9,10);
        putAdyacencia(9,58);
        //Adyacencias vértice 10
        putAdyacencia(10,9);
        putAdyacencia(10,11);
        putAdyacencia(10,65);//antes estaba en 64
        //Adyacencias vértice 11
        putAdyacencia(11,10);
        putAdyacencia(11,12);
        putAdyacencia(11,75);//antes estaba en 74
        //Adyacencias vértice 12
        putAdyacencia(12,11);
        putAdyacencia(12,13);
        //Adyacencias vértice 13
        putAdyacencia(13,12);
        putAdyacencia(13,14);
        putAdyacencia(13,84);//antes estaba en 83
        //Adyacencias vértice 14
        putAdyacencia(14,13);
        //putAdyacencia(14,32);
        putAdyacencia(14,15);
        //Adyacencias vértice 15
        putAdyacencia(15,14);
        putAdyacencia(15,16);
        putAdyacencia(15,81);//antes estaba en 80
        //Adyacencias vértice 16
        putAdyacencia(16,15);
        putAdyacencia(16,17);
        //Adyacencias vértice 17
        putAdyacencia(17,16);
        putAdyacencia(17,18);
        putAdyacencia(17,81);//antes estaba en 81
        //Adyacencias vértice 18
        putAdyacencia(18,17);
        putAdyacencia(18,19);
        //Adyacencias vértice 19
        putAdyacencia(19,18);
        putAdyacencia(19,20);
        putAdyacencia(19,64);//antes estaba en 63
        //Adyacencias vértice 20
        putAdyacencia(20,19);
        putAdyacencia(20,21);
        //Adyacencias vértice 21
        putAdyacencia(21,20);
        putAdyacencia(21,22);
        putAdyacencia(21,51);
        //Adyacencias vértice 22
        putAdyacencia(22,21);
        putAdyacencia(22,23);
        //Adyacencias vértice 23
        putAdyacencia(23,22);
        putAdyacencia(23,24);
        putAdyacencia(23,27);
        //Adyacencias vértice 24
        putAdyacencia(24,23);
        putAdyacencia(24,25);
        //Adyacencias vértice 25
        putAdyacencia(25,24);
        putAdyacencia(25,26);
        putAdyacencia(25,37);
        //Adyacencias vértice 26
        putAdyacencia(26,25);
        putAdyacencia(26,0);
        putAdyacencia(26,34);
    }
    
    private void initPricipalStreetAdyacents(){
        //Adyacencias vértice 27
        putAdyacencia(27,43);
        putAdyacencia(27,28);
        //Adyacencias vértice 28
        putAdyacencia(28,50);
        putAdyacencia(28,51);
        putAdyacencia(28,29);
        //Adyacencias vértice 29
        putAdyacencia(29,57);
        putAdyacencia(29,30);
        //Adyacencias vértice 30
        putAdyacencia(30,31);
        putAdyacencia(30,63);//antes 62
        putAdyacencia(30,64);//antes 63
        //Adyacencias vértice 31
        putAdyacencia(31,32);
        putAdyacencia(31,69);//antes 68
        putAdyacencia(31,70);//antes 70
        //Adyacencias vértice 32
        putAdyacencia(32,78);//antes 77
        putAdyacencia(32,79);//antes 78
        putAdyacencia(32,14);
    }
    
    private void initOtherAdyacents(){
        //Adyacencias vértice 33
        putAdyacencia(33,2);
        putAdyacencia(33,3);
        putAdyacencia(33,34);
        //Adyacencias vértice 34
        putAdyacencia(34,33);
        putAdyacencia(34,35);
        putAdyacencia(34,26);
        putAdyacencia(34,36);
        //Adyacencias vértice 35
        putAdyacencia(35,4);
        putAdyacencia(35,34);
        putAdyacencia(35,39);
        //Adyacencias vértice 36
        putAdyacencia(36,34);
        putAdyacencia(36,37);
        //Adyacencias vértice 37
        putAdyacencia(37,36);
        //putAdyacencia(37,25);no tiene
        putAdyacencia(37,38);
        putAdyacencia(37,41);
        //Adyacencias vértice 38
        putAdyacencia(38,37);
        putAdyacencia(38,24);
        //Adyacencias vértice 39
        putAdyacencia(39,40);
        putAdyacencia(39,35);
        putAdyacencia(39,47);
        //Adyacencias vértice 40
        putAdyacencia(40,39);
        putAdyacencia(40,41);
        putAdyacencia(40,48);
        //Adyacencias vértice 41
        putAdyacencia(41,42);
        putAdyacencia(41,40);
        putAdyacencia(41,49);
        //Adyacencias vértice 42
        putAdyacencia(42,38);
        putAdyacencia(42,41);
        putAdyacencia(42,43);
        //Adyacencias vértice 43
        putAdyacencia(43,42);
        putAdyacencia(43,45);
        putAdyacencia(43,27);
        //Adyacencias vértice 44
        putAdyacencia(44,42);
        putAdyacencia(44,45);
        //Adyacencias vértice 45
        putAdyacencia(45,44);
        putAdyacencia(45,43);
        //Adyacencias vértice 46
        putAdyacencia(46,7);
        putAdyacencia(46,52);
        putAdyacencia(46,47);
        //Adyacencias vértice 47
        putAdyacencia(47,46);
        putAdyacencia(47,48);
        putAdyacencia(47,39);
        putAdyacencia(47,53);
        //Adyacencias vértice 48
        putAdyacencia(48,47);
        putAdyacencia(48,40);
        putAdyacencia(48,49);
        //Adyacencias vértice 49
        putAdyacencia(49,48);
        putAdyacencia(49,50);
        putAdyacencia(49,54);
        //Adyacencias vértice 50
        putAdyacencia(50,44);
        putAdyacencia(50,49);
        putAdyacencia(50,28);
        //Adyacencias vértice 51
        putAdyacencia(51,28);
        putAdyacencia(51,21);
        //Adyacencias vértice 52
        putAdyacencia(52,46);
        putAdyacencia(52,58);
        putAdyacencia(52,53);
        //Adyacencias vértice 53
        putAdyacencia(53,47);
        putAdyacencia(53,60);//antes 59
        putAdyacencia(53,54);
        //Adyacencias vértice 54
        putAdyacencia(54,61);//antes 60
        putAdyacencia(54,55);
        //Adyacencias vértice 55
        putAdyacencia(55,56);
        //Adyacencias vértice 56
        putAdyacencia(56,50);
        putAdyacencia(56,29);
        //Adyacencias vértice 57
        putAdyacencia(57,20);
        //Adyacencias vértice 58
        putAdyacencia(58,9);
        putAdyacencia(58,52);
        putAdyacencia(58,59);
        //Adyacencias vértice 59
        putAdyacencia(59,58);
        putAdyacencia(59,66);
        putAdyacencia(59,60);
        //Adyacencias vértice 60
        putAdyacencia(60,59);
        putAdyacencia(60,53);
        putAdyacencia(60,61);
        putAdyacencia(60,67);
        //Adyacencias vértice 61
        putAdyacencia(61,60);
        putAdyacencia(61,62);
        putAdyacencia(61,68);
        //Adyacencias vértice 62
        putAdyacencia(62,61);
        putAdyacencia(62,55);
        putAdyacencia(62,63);
        //Adyacencias vértice 63
        putAdyacencia(63,62);
        putAdyacencia(63,30);
        putAdyacencia(63,69);
        //Adyacencias vértice 64
        putAdyacencia(64,19);
        putAdyacencia(64,57);
        putAdyacencia(64,30);
        //Adyacencias vértice 65
        putAdyacencia(65,10);
        putAdyacencia(65,76);
        putAdyacencia(65,66);
        //Adyacencias vértice 66
        putAdyacencia(66,65);
        putAdyacencia(66,59);
        putAdyacencia(66,77);
        putAdyacencia(66,67);
        //Adyacencias vértice 67
        putAdyacencia(67,66);
        putAdyacencia(67,68);
        putAdyacencia(67,60);
        putAdyacencia(67,83);
        //Adyacencias vértice 68
        putAdyacencia(68,67);
        //putAdyacencia(68,61);calle una vía
        putAdyacencia(68,73);
        //Adyacencias vértice 69
        putAdyacencia(69,63);
        putAdyacencia(69,72);
        putAdyacencia(69,31);
        //Adyacencias vértice 70
        putAdyacencia(70,64);
        putAdyacencia(70,31);
        //Adyacencias vértice 71
        putAdyacencia(71,72);
        putAdyacencia(71,74);
        //Adyacencias vértice 72
        putAdyacencia(72,71);
        putAdyacencia(72,69);
        putAdyacencia(72,78);
        //Adyacencias vértice 73
        putAdyacencia(73,74);
        //putAdyacencia(73,68);calle una vía
        putAdyacencia(73,80);
        //Adyacencias vértice 74
        putAdyacencia(74,71);
        putAdyacencia(74,73);
        //Adyacencias vértice 75
        putAdyacencia(75,11);
        //Adyacencias vértice 76
        putAdyacencia(76,65);
        //Adyacencias vértice 77
        putAdyacencia(77,66);
        //Adyacencias vértice 78
        putAdyacencia(78,72);
        putAdyacencia(78,84);
        putAdyacencia(78,32);
        //Adyacencias vértice 79
        putAdyacencia(79,70);
        putAdyacencia(79,32);
        //Adyacencias vértice 80
        //putAdyacencia(80,73);calle una vía
        //Adyacencias vértice 81
        putAdyacencia(81,79);
        putAdyacencia(81,17);
        //Adyacencias vértice 82
        putAdyacencia(82,83);
        //Adyacencias vértice 83
        putAdyacencia(83,67);
        putAdyacencia(83,82);
        putAdyacencia(83,84);
        //Adyacencias vértice 84
        putAdyacencia(84,83);
        putAdyacencia(84,78);
        putAdyacencia(84,13);
        
        //todo preguntar por vértices
        //75,76,77,82
        //**80 (no hay vía para arriba)
    }
    
    private void printVertices(){
        System.out.println("\n\nLista de vértices");
        vertices.stream().forEach(v->VertexUtils.getInstance().printVertexInfo(v));
    }
    
    public void printVertexAdyacents(Vertex v){
        Integer i = v.getIndex();
        for(int j =0;j<nodes;j++){
            if(adjacents[i][j]>0){
                System.out.print("("+j+") -> ");
            }
        }
    }
    
    /**
     * obtiene una nueva matriz de pesos actualizada con los cambios de estado en los vértices
     * @return 
     */
    public Double[][] updateWeights(){
        //nueva matriz para siempre tener la original (no sé ni para qué XD pero por aquello)
        Double[][] weigthsAux = new Double[nodes][nodes];
        
        //inicializa nueva matriz
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                weigthsAux[i][j]=0.0;
            }
        }
        
        //por cada
        vertices.stream().forEach(e->{
            Integer i = e.getIndex();//a partir del nodo x,revisa todas las columnas
            for (int j = 0; j < nodes; j++) {//recorre las columnas de i(índice stream)
                if(adjacents[i][j]>0){//si tiene adyacente en esa posición i/j
                    Integer state = vertices.get(j).getState();//obtiene estado del adyacente
                    Double w = e.distance(vertices.get(j));//calcula la distancia entre el vértice base y el adyacente
                    w*=state;//producto dependiendo del estado (1=normal,2(Moderado),3(Lento))    
                    weigthsAux[i][j] = w;//selecciona el peso
                }    
            }
        });
        return weigthsAux;
    }
}
