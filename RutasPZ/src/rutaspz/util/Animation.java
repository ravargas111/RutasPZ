package rutaspz.util;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import rutaspz.Vertex;

/**
 * El siguiente single tone permite modelar la animaciones de manera
 * que se ejecuten únicamente desde esta clase y darle más modularidad al
 * software.
 * @author Shinku
 */


public class Animation {
    //Instancia de si mismo
    private static final Animation SINGLETONE = new Animation();
    
    //Constructor básico
    private Animation(){
        //Carga los datos básicos
    }
    
    //Getter de la instancia, es la única forma de acceso al SINGLETONE
    public static Animation getInstance(){
        return SINGLETONE;
    }
    
    public void animarTexto(Node texto,String animación){
        /**
         * contiene las animaciones utilizadas en textos
         * @param texto nodo de texto al cual se le aplica la animación
         * @param animación nombre de animación a aplicar.
         * @author Rodrigo Vargas
         */
        switch (animación){
            case "escala":
                animaciónEscala(texto);
                break;
            default:
                break;
        }
    }

    public void animarImagen(Node imagen,String animación){
        /**
         * contiene las animaciones utilizadas en textos
         * @param imagen nodo de imagen al cual se le aplica la animación
         * @param animación nombre de animación a aplicar
         * @author Rodrigo Vargas
         */
        switch (animación){
            case "desvanecimiento":
                animaciónDesvanecimiento(imagen);
                break;
            case "escala":
                animaciónEscala(imagen);
                break;
            case "horizontal":
                animaciónHorizontal(imagen);
                break;
            case "vertical":
                animaciónVertical(imagen);
            default:
                break;    
        }
        
    } 
    
    public void animaciónDesvanecimiento(Node nodo){
        FadeTransition Ft = new FadeTransition(Duration.millis(5000), nodo);
        Ft.setFromValue(1.0);
        Ft.setToValue(0.0);
        Ft.play();
    }
    
    public void animaciónEscala(Node nodo){
        //Animación de escala
        ScaleTransition St = new ScaleTransition(Duration.millis(3000), nodo);
        //Se debe indicar la escala inicial y la final en los ejes X,Y y Z,
        //segun se desee.
        St.setFromX(1.0);
        St.setFromY(1.0);
        
        St.setToX(0.9);
        St.setToY(0.9);
        //El auto reverse indica si la animación se realiza en dos sentidos
        St.setAutoReverse(true);
        /*
        El Cyrcle count, indica la cantidad de veces que se repita la animación
        En el caso de estar el auto reverse activado, se toma cada ciclo como
        una animación independiente
        */
        St.setCycleCount(INDEFINITE);
        //Inicia la animación
        St.play();
    }
    
    public void aumentarTamaño(Node nodo){
        //Animación de escala
        ScaleTransition St = new ScaleTransition(Duration.millis(700), nodo);
        //Se debe indicar la escala inicial y la final en los ejes X,Y y Z,
        //segun se desee.
        St.setFromX(1.0);
        St.setFromY(1.0);
        
        St.setToX(1.18);
        St.setToY(1.18);
        //El auto reverse indica si la animación se realiza en dos sentidos
        St.setAutoReverse(true);
        /*
        El Cyrcle count, indica la cantidad de veces que se repita la animación
        En el caso de estar el auto reverse activado, se toma cada ciclo como
        una animación independiente
        */
        //Inicia la animación
        St.play();
    }
    
    public void disminuirTamaño(Node nodo){
        //Animación de escala
        ScaleTransition St = new ScaleTransition(Duration.millis(700), nodo);
        //Se debe indicar la escala inicial y la final en los ejes X,Y y Z,
        //segun se desee.
        St.setFromX(1.18);
        St.setFromY(1.18);
        
        St.setToX(1.0);
        St.setToY(1.0);
        //El auto reverse indica si la animación se realiza en dos sentidos
        St.setAutoReverse(true);
        /*
        El Cyrcle count, indica la cantidad de veces que se repita la animación
        En el caso de estar el auto reverse activado, se toma cada ciclo como
        una animación independiente
        */
        //Inicia la animación
        St.play();
    }
    
    public void animaciónHorizontal(Node nodo){
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), nodo);
        tt.setByX(30f);//Especifica el valor incrementado de la coordenada X de parada, desde el comienzo, de este TranslateTransition
        tt.setCycleCount(INDEFINITE);
        tt.setAutoReverse(true);
 
        tt.play();
    }
    
    public void animaciónVertical(Node nodo){
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), nodo);
        tt.setByY(30f);//Especifica el valor incrementado de la coordenada X de parada, desde el comienzo, de este TranslateTransition
        tt.setCycleCount(INDEFINITE);
        tt.setAutoReverse(true);
 
        tt.play();
    }
    
    public void animaciónRotar(Node nodo,Double ánguloD){
        RotateTransition rotar=new RotateTransition(Duration.seconds(3), nodo);
        rotar.setFromAngle(0.0);
        rotar.setToAngle(ánguloD);
        rotar.setAutoReverse(true);
        rotar.setCycleCount(INDEFINITE);
        rotar.play();
    }
    
    public void animaciónBoomerang(Node nodo){
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), nodo);
        tt.setByX(30f);//Especifica el valor incrementado de la coordenada X de parada, desde el comienzo, de este TranslateTransition
        tt.setCycleCount(INDEFINITE);
        tt.setAutoReverse(true);
 
        RotateTransition rotar=new RotateTransition(Duration.seconds(3), nodo);
        rotar.setFromAngle(0.0);
        rotar.setToAngle(360.0);
        rotar.setAutoReverse(true);
        rotar.setCycleCount(INDEFINITE);
        
        ParallelTransition paralelo = new ParallelTransition();
        paralelo.getChildren().addAll(
           tt,rotar
        );
        
        paralelo.play();
    }
    
    public void animaciónCartaD(Node nodo,Image nuevaImg){
        ScaleTransition disminuir = new ScaleTransition(Duration.millis(1000), nodo);
        RotateTransition rotarDism=new RotateTransition(Duration.seconds(1), nodo);
  
        disminuir.setFromX(1.0);
        disminuir.setFromY(1.0);
        disminuir.setToX(0.0);
        disminuir.setToY(0.5);
        disminuir.setFromZ(1.0);
        disminuir.setToZ(0.0);
 
        disminuir.setAutoReverse(true);
        
        rotarDism.setFromAngle(0.0);
        rotarDism.setToAngle(180.0);
        //rotarDism.setAutoReverse(true);
        
        ParallelTransition paraleloDismim = new ParallelTransition();
        paraleloDismim.getChildren().addAll(rotarDism,disminuir);
        
        //paraleloDismim.setOnFinished(value);
        paraleloDismim.setOnFinished(new EventHandler<ActionEvent>(){
 
            @Override
            public void handle(ActionEvent arg0) {
                ((ImageView)nodo).setImage(nuevaImg);
            }
        });
        
        paraleloDismim.play();     
    }
         
    public void animaciónCartaA(Node nodo){
        ScaleTransition aumentar = new ScaleTransition(Duration.millis(700), nodo);
        RotateTransition rotarAum=new RotateTransition(Duration.seconds(0.7), nodo);
        
        rotarAum.setFromAngle(180.0);
        rotarAum.setToAngle(0.0);
        //rotarAum.setAutoReverse(true);
        
        aumentar.setFromX(0.0);
        aumentar.setFromY(0.5);
        aumentar.setToX(1.0);
        aumentar.setToY(1.0);
        aumentar.setFromZ(0.0);
        aumentar.setToZ(1.0);
        aumentar.setAutoReverse(true);
        
        ParallelTransition paraleloAumen = new ParallelTransition();
        paraleloAumen.getChildren().addAll(rotarAum,aumentar);
        paraleloAumen.setDelay(Duration.seconds(1.0));
        paraleloAumen.play();
    } 
    
    public void OcultaCarta(Node nodo,Image nuevaImg){
        ScaleTransition disminuir = new ScaleTransition(Duration.millis(700), nodo);
        RotateTransition rotarDism=new RotateTransition(Duration.seconds(0.7), nodo);
  
        disminuir.setFromX(1.0);
        disminuir.setFromY(1.0);
        disminuir.setToX(0.0);
        disminuir.setToY(0.5);
        disminuir.setFromZ(1.0);
        disminuir.setToZ(0.0);
 
        disminuir.setAutoReverse(true);
        
        rotarDism.setFromAngle(0.0);
        rotarDism.setToAngle(180.0);
        
        ParallelTransition paraleloDismim = new ParallelTransition();
        paraleloDismim.getChildren().addAll(rotarDism,disminuir);
        
        paraleloDismim.setOnFinished(new EventHandler<ActionEvent>(){
 
            @Override
            public void handle(ActionEvent arg0) {
                ((ImageView)nodo).setImage(nuevaImg);
                animaciónCartaA(nodo);
            }
        });
        paraleloDismim.setDelay(Duration.seconds(3));
        paraleloDismim.play();     
    }
    
    public void voltearCarta(Node nodo,Image nuevaImg){
        ScaleTransition disminuir = new ScaleTransition(Duration.millis(700), nodo);
        RotateTransition rotarDism=new RotateTransition(Duration.seconds(0.7), nodo);
  
        disminuir.setFromX(1.0);
        disminuir.setFromY(1.0);
        disminuir.setToX(0.0);
        disminuir.setToY(0.5);
        disminuir.setFromZ(1.0);
        disminuir.setToZ(0.0);
 
        disminuir.setAutoReverse(true);
        
        rotarDism.setFromAngle(0.0);
        rotarDism.setToAngle(180.0);
        
        ParallelTransition paraleloDismim = new ParallelTransition();
        paraleloDismim.getChildren().addAll(rotarDism,disminuir);
        
        paraleloDismim.setOnFinished(new EventHandler<ActionEvent>(){
 
            @Override
            public void handle(ActionEvent arg0) {
                ((ImageView)nodo).setImage(nuevaImg);
                animaciónCartaA(nodo);
            }
        });
        paraleloDismim.play(); 
    }
    
    public void visibilidadMenu(Node menu,Boolean mostrar,Node button){
        button.setDisable(true);
        TranslateTransition visibilidad = new TranslateTransition(Duration.millis(1000), menu);
        if(mostrar)
        visibilidad.setByX(150f);
        else
            visibilidad.setByX(-150f);
        visibilidad.play();
        visibilidad.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                button.setDisable(false);
            }
        
        });
    }
    
    public void desplazarVertical(Node nodo,Float desplazamiento,Boolean dirección){
        TranslateTransition visibilidad = new TranslateTransition(Duration.millis(1000), nodo);
        if(dirección)
        visibilidad.setByY(desplazamiento);
        else
            visibilidad.setByY(-(desplazamiento));
        visibilidad.play();
    }
    
    public void desvanecimiento(Node nodo,Boolean mostrar){
        if(mostrar){
            FadeTransition desvanecer = new FadeTransition(Duration.millis(2000), nodo);
            nodo.setVisible(true);
            desvanecer.setFromValue(0.1);
            desvanecer.setToValue(1.0);
            desvanecer.play();
            }
        else{
            FadeTransition desvanecer = new FadeTransition(Duration.millis(1000), nodo);
            desvanecer.setFromValue(1.0);
            desvanecer.setToValue(0.1);
            desvanecer.setOnFinished(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    nodo.setVisible(false);
                }
                
            });
            desvanecer.play();
        }
    }
    
    public void desplazar(Node nodo, Double fromX ,Double toX ,Double fromY,Double toY){
        TranslateTransition desplazamiento = new TranslateTransition(Duration.millis(1000), nodo);
        desplazamiento.setFromX(fromX);
        desplazamiento.setToX(toX);
        desplazamiento.setFromY(fromY);
        desplazamiento.setToY(toY);
        /*desplazamiento.setOnFinished(e->{
                ((FontAwesomeIconView)nodo).setX(toX);
                ((FontAwesomeIconView)nodo).setY(toY);
        });*/
        desplazamiento.play();
    }
    
    public void desplazar(Node nodo, Double[] n1 ,Double[] n2){
        TranslateTransition desplazamiento = new TranslateTransition(Duration.millis(1000), nodo);
        desplazamiento.setFromX(n1[0]);
        desplazamiento.setFromY(n1[1]);
        desplazamiento.setToX(n2[0]);
        desplazamiento.setToY(n2[1]);
        desplazamiento.play();
    }
    
    public void desplazarSecuencia(Node nodo,ArrayList<Double[]> movimientos){
        //obtiene vértices
        FontAwesomeIconView auxNodo=(FontAwesomeIconView) nodo;
        Double[] n1 = movimientos.get(0);
        Double[] n2 = movimientos.get(1);
        //auxNodo.setX(auxNodo.getX()+n1[0]);
        //auxNodo.setY(auxNodo.getY()+n1[1]);
        movimientos.remove(0);
        //crea transición
        TranslateTransition desplazamiento = new TranslateTransition(Duration.millis(1000), nodo);
        desplazamiento.setFromX(n1[0]);
        desplazamiento.setFromY(n1[1]);
        desplazamiento.setToX(n2[0]);
        desplazamiento.setToY(n2[1]);
        //si queda más de un movimiento lo invoca al finalizar
        desplazamiento.setOnFinished(e->{
            System.out.println("("+((FontAwesomeIconView)nodo).getX()+","+((FontAwesomeIconView)nodo).getY()+")");
            //System.out.println("("+n1[0]+","+n1[1]+") "+" ("+n2[0]+","+n2[1]+")"); 
            if(movimientos.size()>1)
            desplazarSecuencia( nodo, movimientos);
            });  
        
        //System.out.println("("+n1[0]+","+n1[1]+") "+" ("+n2[0]+","+n2[1]+")");        
        desplazamiento.play(); 
        
    }
    
    public void desplazarListaMovs(Node nodo,ArrayList<Point2D> movimientos){
        try{
        //quitar esto en caso de no ocuparlo
        ArrayList<Line> pathLines = (ArrayList<Line>) AppContext.getInstance().get("lines2"); 
        
        //obtiene vértices
        FontAwesomeIconView auxNodo=(FontAwesomeIconView) nodo;
        Point2D n1 = movimientos.get(0);
        Point2D n2 = movimientos.get(1);
        //auxNodo.setX(auxNodo.getX()+n1[0]);
        //auxNodo.setY(auxNodo.getY()+n1[1]);
        movimientos.remove(0);
        //crea transición
        TranslateTransition desplazamiento = new TranslateTransition(Duration.millis(1000), nodo);
        desplazamiento.setFromX(n1.getX());
        desplazamiento.setFromY(n1.getY());
        desplazamiento.setToX(n2.getX());
        desplazamiento.setToY(n2.getY());
        
        //si queda más de un movimiento lo invoca al finalizar
        desplazamiento.setOnFinished(e->{
            //imprimir info
            //Points2DUtils.getInstance().printPoints(n1,n2);
            //System.out.println("("+n1[0]+","+n1[1]+") "+" ("+n2[0]+","+n2[1]+")");
            
            //todo quitar en caso de usarla en otra app
            pathLines.add(VertexUtils.getInstance().drawLine(n1, n2,VertexUtils.COLOR.YELLOW));
            
            if(movimientos.size()>1)
            desplazarListaMovs( nodo, movimientos);
            });  
        
        //System.out.println("("+n1[0]+","+n1[1]+") "+" ("+n2[0]+","+n2[1]+")");        
        desplazamiento.play(); 
        }
        catch(NullPointerException e){
            System.out.println("error trasladando en ruta,puntero nulo");
        }
        catch(IndexOutOfBoundsException i){
            System.out.println("error trasladando ruta,un solo vértice de ruta");
        }
    }
    
    /**
     * desplaza un nodo a partir de una lista de vértices (usada por el carro)
     * @param nodo
     * @param movimientos 
     */
    public void desplazarListaMovsV(Node nodo,ArrayList<Vertex> movimientos){
        //System.out.println("Vertices a mover size:"+movimientos.size());
        try{
        //quitar esto en caso de no ocuparlo
        ArrayList<Line> pathLines = (ArrayList<Line>) AppContext.getInstance().get("lines2");
        
        //obtiene vértices
        //FontAwesomeIconView auxNodo=(FontAwesomeIconView) nodo;
        Vertex n1 = movimientos.get(0);
        Vertex n2 = movimientos.get(1);
        //auxNodo.setX(auxNodo.getX()+n1[0]);
        //auxNodo.setY(auxNodo.getY()+n1[1]);
        movimientos.remove(0);//quita el vértice de la lista de movimientos
        //crea transición
        TranslateTransition desplazamiento = new TranslateTransition(Duration.millis(1000), nodo);
        desplazamiento.setFromX(n1.getX());
        desplazamiento.setFromY(n1.getY());
        desplazamiento.setToX(n2.getX());
        desplazamiento.setToY(n2.getY());
        
        //si queda más de un movimiento lo invoca al finalizar
        desplazamiento.setOnFinished(e->{
            //imprimir info
            //Points2DUtils.getInstance().printPoints(n1,n2);
            //System.out.println("("+n1[0]+","+n1[1]+") "+" ("+n2[0]+","+n2[1]+")");
            
            //todo quitar en caso de usarla en otra app
            pathLines.add(VertexUtils.getInstance().drawFollowedLine(n1, n2,VertexUtils.COLOR.YELLOW));
            nodo.toFront();
            if(movimientos.size()>1)
                desplazarListaMovsV( nodo, movimientos);
            });  
        
        //System.out.println("("+n1[0]+","+n1[1]+") "+" ("+n2[0]+","+n2[1]+")");        
        desplazamiento.play(); 
        }
        catch(NullPointerException e){
            System.out.println("error trasladando en ruta,puntero nulo");
        }
        catch(IndexOutOfBoundsException i){
            System.out.println("error trasladando ruta,un solo vértice de ruta");
        }
    }
    
}
