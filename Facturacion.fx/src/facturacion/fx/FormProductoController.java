/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import Facturacion.bl.Producto;
import Facturacion.bl.ProductoServicio;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sinoé Adalid Tovar
 */
public class FormProductoController implements Initializable {
    
    //Creacion de variables para que se enlace con el formulario FXML. 
    @FXML
    private TableView tableView;
    
    @FXML
    private TableColumn<Producto, Integer> colCod;
    
    @FXML
    private TableColumn<Producto, String> colDescripcion;
    
  
    
    //Permite ver los cambios cuando ocurren a la lista.
    ObservableList<Producto> data;
    
    //Creando atributo.
    ProductoServicio servicio;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        servicio = new ProductoServicio();
        
        colCod.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        
        cargarDatos();
    }    
    
    public void agregarProducto() throws IOException{
        Producto agregarProducto = new Producto();
        abrirVentanaModal(agregarProducto, "Agregar Producto");
    }
    
    public void guardar(Producto producto){
        servicio.guardar(producto);
        cargarDatos();
        
    }
    
    private void abrirVentanaModal(Producto producto, String titulo) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarEditarProducto.fxml"));
        Parent root = (Parent) loader.load();
        
        AgregarEditarProductoController controller = loader.getController();
        controller.setController(this);
        controller.setProducto(producto);
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(titulo);
        stage.show();
    }

    private void cargarDatos() {
        data = FXCollections.observableArrayList(servicio.obtenerProductos());
        tableView.setItems(data);
        tableView.refresh();
    }
    
    
}


