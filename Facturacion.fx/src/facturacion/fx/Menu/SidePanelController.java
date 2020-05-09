package facturacion.fx.Menu;

import Facturacion.bl.Usuario;
import com.jfoenix.controls.JFXListView;
import facturacion.fx.FacturacionFx;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SidePanelController implements Initializable {

    @FXML
    JFXListView listView;
    
    private AbrirFormularioCallback callback;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        Usuario usuarioAutenticado= FacturacionFx.getUsuarioAutenticado();
        String role=usuarioAutenticado.getRole().toLowerCase();
        switch(role){
            case "administrador":
            listView.getItems().add(new Label("Inicio"));
            listView.getItems().add(new Label("Productos"));
            listView.getItems().add(new Label("Clientes"));
            listView.getItems().add(new Label("Facturas"));
            listView.getItems().add(new Label("Reporte de Productos"));
            listView.getItems().add(new Label("Reporte de Facturas"));   
            break;
            
            case "cajero":
            listView.getItems().add(new Label("Facturas"));
            listView.getItems().add(new Label("Reporte de Productos"));
            listView.getItems().add(new Label("Reporte de Facturas"));
            break;
            
            case "inventario":
            listView.getItems().add(new Label("Productos"));
            listView.getItems().add(new Label("Reporte de Productos"));
                
        }
            /*listView.getItems().add(new Label("Productos"));
            listView.getItems().add(new Label("Clientes"));
            listView.getItems().add(new Label("Facturas"));
            listView.getItems().add(new Label("Reporte de Productos"));
            listView.getItems().add(new Label("Reporte de Facturas")); */ 
            listView.getItems().add(new Label("Cerrar Sesion"));
            listView.getItems().add(new Label("Salir"));   
        
        listView.setOnMouseClicked(event -> {
            Label label = (Label) listView.getSelectionModel().getSelectedItem();
            
            if (label.getText().equals("Salir")) {
                System.exit(0);
            } else {
                callback.abrirFormulario(label.getText());    
            }           
        });
    }

    public void setCallback(AbrirFormularioCallback callback) {
        this.callback = callback;
    }
}
