 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import Facturacion.bl.Producto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

/**
 * FXML Controller class
 *
 * @author Sinoé Adalid Tovar
 */
public class AgregarEditarProductoController implements Initializable {
    
    @FXML
    Button btnCancelar;
    
    @FXML
    TextField txtCodigo;
    
    @FXML
    TextField txtDescripcion;
    
    private FormProductoController controller;
    private Producto producto;
    
    public void setController(FormProductoController controller){
        this.controller = controller;
    }
    
    public void setProducto(Producto producto){
        this.producto = producto;
    
        
        txtCodigo.textProperty().bindBidirectional(producto.codigoProperty(), new NumberStringConverter());
        txtDescripcion.textProperty().bindBidirectional(producto.descripcionProperty());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void aceptar(){
        controller.guardar(producto);
        cerrar();
    }
    
    public void cancelar(){
        cerrar();
    }

    private void cerrar() {
       Stage stage = (Stage)btnCancelar.getScene().getWindow();
       stage.close();
    }
}
