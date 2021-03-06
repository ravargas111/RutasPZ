
package rutaspz.util;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AppContext {

    private static AppContext INSTANCE = null;
    private static HashMap<String, Object> context = new HashMap<>();
    private static Stage mainStage = new Stage();
    private static Integer modoJuego; 
    
    private AppContext() {
        cargarPropiedades();
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AppContext.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppContext();
                }
            }
        }
    }

    public static AppContext getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }
    
    private void cargarPropiedades(){
//        try {
//            FileInputStream configFile;
//            configFile = new FileInputStream("config/properties.ini");
//            Properties appProperties = new Properties();
//            appProperties.load(configFile);
//            configFile.close();
////            if (appProperties.getProperty("propiedades.rutalog") != null) {
////                this.set("rutalog",appProperties.getProperty("propiedades.rutalog"));
////            }
//            if (appProperties.getProperty("propiedades.resturl") != null) {
//                this.set("resturl",appProperties.getProperty("propiedades.resturl"));
//            }
//        } catch (IOException io) {
//            System.out.println("Archivo de configuración no encontrado.");
//        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public Object get(String parameter){
        
        Object object = context.get(parameter);  
        return object;
    }

    public void set(String nombre, Object valor) {
        context.put(nombre, valor);
    }

    public void delete(String parameter) {
        context.put(parameter, null);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        AppContext.mainStage = mainStage;
    }

    public static Scene getMainScene() {
        return mainStage.getScene();
    }

    public static Integer getModoJuego() {
        return modoJuego;
    }

    public static void setModoJuego(Integer modoJuego) {
        AppContext.modoJuego = modoJuego;
    }

    public String set(String scramble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
