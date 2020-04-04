 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import Facturacion.bl.Categoria;
import Facturacion.bl.CategoriaServicio;
import Facturacion.bl.Producto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * FXML Controller class
 *
 * @author Sinoé Adalid Tovar
 */
public class AgregarEditarProductoController implements Initializable {
    
    @FXML
    JFXButton btnCancelar;
    
    @FXML
    JFXTextField txtCodigo;
    
    @FXML
    JFXTextField txtDescripcion;
    
    @FXML
    JFXComboBox cmbCategoria;
    
    @FXML
    JFXTextField txtPrecio;
    
    @FXML
    JFXTextField txtExistencia;
    
    @FXML
    ImageView imagenProducto;
    
    @FXML
    JFXButton btnCambiarImagen;
    
    @FXML
    JFXButton btnEliminarImagen;
    
    
    private FormProductoController controller;
    private Producto producto;
    private CategoriaServicio categoriaServicio;
    private ObservableList<Categoria> data;
    
    public void setController(FormProductoController controller){
        this.controller = controller;
    }
    
    public void setProducto(Producto producto){
        this.producto = producto;
         
        txtCodigo.textProperty().bindBidirectional(producto.codigoProperty(), new NumberStringConverter());
        txtDescripcion.textProperty().bindBidirectional(producto.descripcionProperty());
        cmbCategoria.valueProperty().bindBidirectional(producto.categoriaProperty());
        
        cmbCategoria.setConverter(new StringConverter<Categoria>() {
            @Override
            public String toString(Categoria categoria) {
                return categoria == null ? "" : categoria.getDescripcion();
            }

            @Override
            public Categoria fromString(String string) {
                if (data == null){
                    return null;
                }
                
                for (Categoria categoria: data){
                    if (categoria.getDescripcion().equals(string)){
                        return categoria;
                    }
                }
                 return null;
            }
        });
        
        txtPrecio.textProperty().bindBidirectional(producto.precioProperty(), new NumberStringConverter());
        txtExistencia.textProperty().bindBidirectional(producto.existenciaProperty(), new NumberStringConverter());
        imagenProducto.imageProperty().bind(producto.verImagenProperty());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       categoriaServicio = new CategoriaServicio();
       data = FXCollections.observableArrayList(categoriaServicio.obtenerCategoria());
       cmbCategoria.setItems(data);
    }    
    
    public void aceptar(){
        String resultado =  controller.guardar(producto);
        if (resultado.equals("")){
        cerrar();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Productos");
            alert.setHeaderText("Error al validar los datos");
            alert.setContentText(resultado);
            alert.showAndWait();
        }
    }
    
    public void cancelar(){
        cerrar();
    }
    
    //Para la busqueda de las imagenes con sus respectivas extensiones.
    public void cambiarImagen(){
        FileChooser escogerImagen = new FileChooser();
        FileChooser.ExtensionFilter extensiones = new FileChooser.ExtensionFilter("Imagenes", "*.jpg", "*.png");
        
        escogerImagen.getExtensionFilters().add(extensiones);
        
        File imagen = escogerImagen.showOpenDialog(null);
        
        if(imagen != null){
            Image image = new Image(imagen.toURI().toString());
            producto.setVerImagen(image);
        }
    }
    
    public void eliminarImagen(){
        producto.setVerImagen(null);
    }

    private void cerrar() {
       Stage stage = (Stage)btnCancelar.getScene().getWindow();
       stage.close();
    }
}
