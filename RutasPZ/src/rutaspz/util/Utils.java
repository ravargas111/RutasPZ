/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.util;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import rutaspz.RutasPZ;

/**
 *
 * @author robri
 */
public class Utils {
    private static Utils INSTANCE = null;

    public Utils() {
    }
    
    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AppContext.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Utils();
                }
            }
        }
    }

    public static Utils getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void createHamburgerTransition(JFXHamburger btn,JFXDrawer drawer,String view){
        HamburgerBackArrowBasicTransition hmbTransition = new HamburgerBackArrowBasicTransition(btn);
        hmbTransition.setRate(-1);
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
            hmbTransition.setRate(hmbTransition.getRate()*-1);
            hmbTransition.play();
            
            if(drawer.isOpened()){
                drawer.close();
            }
            else{
                drawer.open();
            }
        });
        
        try {
            VBox box = FXMLLoader.load(RutasPZ.class.getResource("view/"+view+".fxml"));
            drawer.setSidePane(box);
        }
        catch (IOException ex) {
            System.out.println("Error cargando el menu\nError:" + ex);
        }
    }
    
    public void createCloseDrawerEvent(JFXDrawer drawer,Node node){
        try{
            node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
                if(drawer.isOpened()){
                drawer.close();
            }
            });
        }
        catch(Exception e){
            System.out.println("error tratando de generar evento cerrado");
        }
    }
}
