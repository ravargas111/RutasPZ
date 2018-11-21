/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz;

import java.util.ArrayList;
import java.util.Collections;
import rutaspz.util.AppContext;

/**
 *
 * @author Chris
 */
public class Dijkstra {
    //Internal Subclass
    private class Arrivals {
        Integer prev;
        Double peso;
        Integer saltos;
        Arrivals sig;
    }
    
    //Attributes
    private Integer cantNodos;
    private Integer[] letras;
    private Integer[][] adjacents;//matriz de adyacencia
    private Double[][] weigths;
    private ArrayList<Vertex> generalVertices;

    //Constructors
    public Dijkstra() {
        
    }

    public Dijkstra(Integer cantNodos, Integer[][] adjacents, Double[][] weigths) {
        this.cantNodos = cantNodos;
        this.adjacents = adjacents;
        this.weigths = weigths;
        this.generalVertices = (ArrayList<Vertex>) AppContext.getInstance().get("generalVertices");
    }
    
    //Methods
    public void getShortestPath(Integer ini, Integer fin){
        Boolean seLlega = false;
        Boolean[] evaluados = new Boolean[cantNodos];
        for (int i = 0; i < cantNodos; i++) {
            evaluados[i] = false;
        }
        Arrivals[] caminos = new Arrivals[cantNodos];
        for (int i = 0; i < cantNodos; i++) {
            caminos[i] = null;
        }
        ArrayList<Integer> porEvaluar = new ArrayList<>();
        porEvaluar.add(ini);
        while(!porEvaluar.isEmpty()){
            Integer index = porEvaluar.get(0);
            if(!index.equals(fin)){
                if(!evaluados[index]){
                    Arrivals previusArr = getMinWeight(caminos[index]);
                    if(previusArr == null){
                        previusArr = new Arrivals();
                        previusArr.peso = new Double(0);
                        previusArr.saltos = 0;
                    }
                    for (int i = 0; i < cantNodos; i++) {
                        if(weigths[index][i] != 0.0){
                            Arrivals newAr = new Arrivals();
                            newAr.prev = index;
                            newAr.peso = previusArr.peso + weigths[index][i];
                            newAr.saltos = previusArr.saltos + 1;
                            newAr.sig = null;
                            if(caminos[i] == null){
                                caminos[i] = newAr;
                            } else {
                                insertarFinal(caminos[i], newAr);
                            }
                            if(!evaluados[index])
                                porEvaluar.add(i);
                        }
                    }
                }
            } else {
                seLlega = true;
            }
            evaluados[index] = true;
            porEvaluar.remove(0);
        }
        if(seLlega){
            ArrayList<Integer> way = new ArrayList<>();
            way.add(fin);
            Arrivals next = getMinWeight(caminos[fin]);
            Boolean bandera = true;
            while(next != null && bandera){
                way.add(next.prev);                
                if(next.prev.equals(ini)) bandera = false;
                next = getMinWeight(caminos[next.prev]);
            }
            Collections.reverse(way);
            ArrayList<Vertex> list = (ArrayList<Vertex>) AppContext.getInstance().get("selectedVertices");
            list.clear();
            way.stream().forEach(i -> {
                //llenar la lista de vertex que se retorna
                list.add(generalVertices.get(i));
            });
            //return list;
        } //else{
            //return null;
        //}
    }
    
    private Arrivals getMinWeight(Arrivals a){
        if(a!=null){
            Arrivals min = a;
            Arrivals aux = a.sig;
            while(aux!=null){
                if(aux.peso < min.peso){
                    min = aux;
                }
                aux = aux.sig;
            }
            return min;
        } else {
            return null;
        }
    }
    
    private void insertarFinal(Arrivals root, Arrivals a){
        Arrivals aux = root;
        while(aux.sig!=null){
            aux = aux.sig;
        }
        aux.sig = a;
    }
    
    private void ordenarPorSaltos(Arrivals[] vec){
        for (int i = 0; i < cantNodos-1; i++){
            for (int j = i+1; j < cantNodos-1; j++) {
                if(vec[i].saltos > vec[j].saltos){
                    Arrivals aux = vec[i];
                    vec[i] = vec[j];
                    vec[j] = aux;
                }
            }
        }
    }

    //Setters and Getters
    public Integer getCantNodos() {
        return cantNodos;
    }

    public void setCantNodos(Integer cantNodos) {
        this.cantNodos = cantNodos;
    }

    public Integer[] getLetras() {
        return letras;
    }

    public void setLetras(Integer[] letras) {
        this.letras = letras;
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
    
}
