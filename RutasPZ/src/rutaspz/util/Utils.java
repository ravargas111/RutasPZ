/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.util;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import rutaspz.RutasPZ;

/**
 *
 * @author robri
 */
public class Utils {
    private static Utils INSTANCE = null;
    private File directory;
    
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
    
    public void createIcon(Pane parent,Double x,Double y,FontAwesomeIcon icon){
        FontAwesomeIconView i = new FontAwesomeIconView(icon);
        i.setX(x);
        i.setY(y);
        parent.getChildren().add(i);
    }
    
    public FontAwesomeIconView createIcon(Pane parent,FontAwesomeIcon icon){
        FontAwesomeIconView i = new FontAwesomeIconView(icon);
        i.setVisible(false);
        parent.getChildren().add(i);
        return i;
    }
    
    public void putIcon(FontAwesomeIconView icon,Double x,Double y){
        icon.setX(x);
        icon.setY(y);
        icon.setVisible(true);
    }
    
    public void quitIcon(FontAwesomeIconView icon){
        icon.setX(0);
        icon.setY(0);
        icon.setVisible(false);
    }
    
    public void quitObject(Pane parent,Node node){
        if(parent.getChildren().contains(node))
            parent.getChildren().remove(node);
    }
    
    /**
     * abre el directorio y si no existe lo crea
     */
    private void openDirectory(){
        directory = new File(System.getProperty("user.home") + "\\Documents\\CanchasPZ\\Reportes");
            if (!directory.exists()){
                directory.mkdirs();
            }//crea directorio
    }
    
    /**
     * averigua si el archivo de la ruta definida ya existía para abrirlo y si no existe,lo crea
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void openFile(String url) throws FileNotFoundException, IOException{
        File file = new File(directory+"\\" + url);
        //ver el tipo de file que queremos
        //verifica si ya existía o tiene que crear uno nuevo
        if (file.isFile() && file.exists()) {
                FileInputStream is = new FileInputStream(file);
                    
                     
		} else {
                    
                    
		}
    }
    
    private void saveFile(String url){
        try{
            //antes crea directorio
            //luego se basa en ese directorio + url para intentar guardar
            FileOutputStream file = new FileOutputStream(directory+"\\" + url);
            //workbook.write(file);//escribe con la url a partir del directorio
            
            Mensaje msj = new Mensaje();
            msj.show(Alert.AlertType.INFORMATION, "Archivo guardado con éxito", "");
            showFile(directory + "\\" + url);
        } catch(IOException ex){
            Mensaje msj = new Mensaje();
            msj.show(Alert.AlertType.INFORMATION, "error guardando archivo", "");
            System.out.println("Ha ocurrido un error generando el reporte de ganancias,\nintentalo mas tarde");
        }
    }
    
    /**
     * hace que windows abra el archivo
     * @param dir 
     */
    private void showFile(String dir){
        try{ 
            File path = new File (dir);
            Desktop.getDesktop().open(path);
     
        }catch(IOException e){
            System.out.println("error ruta");
        }
    }
}
