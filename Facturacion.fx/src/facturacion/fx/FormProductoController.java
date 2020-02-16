/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import Facturacion.bl.Producto;
import Facturacion.bl.ProductoServicio;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        
        colCod.setCellValueFactory(new PropertyValueFactory<>("Código"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripción"));
        
        data = FXCollections.observableArrayList(servicio.ObtenerProductos());
        tableView.setItems(data);
    }    
    
}
