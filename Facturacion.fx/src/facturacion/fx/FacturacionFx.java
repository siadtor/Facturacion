/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import Facturacion.bl.Usuario;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Sinoé Adalid Tovar
 */
public class FacturacionFx extends Application {
    public static Boolean isSplashLoaded = false;
    static Stage stage;
    static Usuario usuario;
    
    public static Stage getStage(){
    return stage;
    }
    
    public static Usuario getUsuarioAutenticado(){
        return usuario;
    }
    public static void setUsuarioAutenticado(Usuario usuario){
        FacturacionFx.usuario= usuario;
    }
    
    
   
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
        
        FacturacionFx.stage = stage;
        Parent root = FXMLLoader.load(getClass()
                .getResource("/facturacion/fx/FormLogin.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Ingresar al Sistema");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
