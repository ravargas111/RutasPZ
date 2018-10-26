/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutaspz.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import rutaspz.RutasPZ;

/**
 *
 * @author robri
 */
public class Utils {
    private static Utils INSTANCE = null;
    private File directory;
    private static final String localBasePath = "data\\";
    private static final String localSavedFilesPath = localBasePath + "saved files\\";
    
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
    
    /**
     * pone un ícono en una posición del parent
     * @param icon
     * @param x
     * @param y 
     */
    public void putIcon(FontAwesomeIconView icon,Double x,Double y){
        icon.setX(x);
        icon.setY(y);
        icon.setVisible(true);
    }
    
    /**
     * quita un ícono de la lista de hijos de un parent
     * @param icon 
     */
    public void quitIcon(FontAwesomeIconView icon){
        icon.setX(0);
        icon.setY(0);
        icon.setVisible(false);
    }
    
    /**
     * quita un nodo de la lista de hijos de un parent
     * @param parent
     * @param node 
     */
    public void quitObject(Pane parent,Node node){
        if(parent.getChildren().contains(node))
            parent.getChildren().remove(node);
    }
    
    /**
     * pone un nodo de la lista de hijos de un parent
     * @param parent
     * @param node 
     */
    public void putObject(Pane parent,Node node){
        if(!parent.getChildren().contains(node))
            parent.getChildren().add(node);
    }
    
    /**
     * le crea un popUp a un al darle click sobre un nodo 
     * @param node nodo sobre el cual crear popup
     * @param content contenido a agregar en el popUp
     */
    public void createPopUp(Node node){
        JFXPopup popUp = new JFXPopup();
        VBox vbox = popUpContent(node);
        node.setOnMouseClicked(e->{
            //System.out.println("mostrando pop");
            popUp.show(node, e.getScreenX(), e.getScreenY());
        });
        
        popUp.setPopupContent(vbox);
    }
    
    private VBox popUpContent(Node node){
        VBox vbox=new VBox();
        JFXButton b1 = new JFXButton("asdasd");
        vbox.getChildren().add(b1);
        return vbox;
    }
    
    /**
     * le crea un popUp a un al mouse event previo 
     * @param node nodo sobre el cual crear popup
     * @param content contenido a agregar en el popUp
     */
    public void createPopUp(Node node,Node content,MouseEvent e){
        JFXPopup popUp = new JFXPopup();
        popUp.show(node,JFXPopup.PopupVPosition.TOP,JFXPopup.PopupHPosition.LEFT,e.getX(),e.getY());
        popUp.setPopupContent((Region) content);
    }
    
    /**
     * crea o abre un directorio en la computadora del usuario
     * @param urlDir dirección url del directorio a crear o buscar (separados por "\\")
     */
    private void verifyExternalDirectory(String urlDir){
        directory = new File(System.getProperty("user.home") + urlDir);
            if (!directory.exists()){
                directory.mkdirs();
            }//crea directorio
    }
    
    /**
     * averigua si el archivo de la ruta definida + el directorio ya existía para abrirlo y si no existe,lo crea
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void loadExternalFile(String url) throws FileNotFoundException, IOException{
        File file = new File(directory+"\\" + url);
        //ver el tipo de file que queremos
        //verifica si ya existía o tiene que crear uno nuevo
        if (file.isFile() && file.exists()) {
                FileInputStream is = new FileInputStream(file);
                    
                     
		} else {
                    
                    
		}
    }
    
    private void saveExternalFile(String url){
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
    
    private static void verifyLocalDirectory(){
        File directory = new File(localSavedFilesPath);
        if (!directory.exists()){
            directory.mkdirs();
        }
    }
    
    public static boolean existLocalFile(String name){
        File file = new File(localSavedFilesPath + name);
        return file.exists();
    }
    
    public static boolean saveLocalFile(Object p){
        boolean resultado = false;
        ObjectOutputStream oos = null;
        try{
            verifyLocalDirectory();
            File file = new File(localSavedFilesPath + AppContext.getInstance().get("user"));
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(p);
            resultado = true;
            System.out.println("archivo guardado exitosamente");
            //mostrarMensaje("Guardar partida","Partida guardada exitosamente");
        }catch(FileNotFoundException ex){
            resultado = false;
            System.out.println("Ha ocurrido un error generando archivo, intentalo mas tarde\nError: " + ex);
        }catch(IOException ex){
            resultado = false;
            System.out.println("Ha ocurrido un error archivo, intentalo mas tarde\nError: " + ex);
        } finally {
            if(oos != null){
                try {
                    oos.close();
                } catch(IOException ex){

                }
            }
        }
        return resultado;
    }
    
    public static Object loadLocalFile(String name){
        Object p = null;
        if(existLocalFile(name)){
            ObjectInputStream ois = null;
            try{
                File file = new File(localSavedFilesPath + name);
                ois = new ObjectInputStream(new FileInputStream(file));
                p = ois.readObject();
            } catch (ClassCastException | IOException | ClassNotFoundException ex) {
                System.out.println("Ha ocurrido un error cargando el archivo local\nError: " + ex);
            } finally {
                if(ois != null){
                    try {
                        ois.close();
                    } catch(IOException ex){

                    }
                }
            }
        }
        return p;
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
    
    private void saveLocalFile(){
        
    }
    
    private void openLocalFile(){
        
    }
    
}
