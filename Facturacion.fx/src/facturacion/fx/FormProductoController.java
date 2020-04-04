/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import Facturacion.bl.Producto;
import Facturacion.bl.ProductoServicio;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
    
    @FXML
    private TableColumn<Producto, String> colCategoria;
    
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    
    @FXML
    private TableColumn<Producto, Integer> colExistencia;
    
    @FXML
    private TableColumn colEditar;
    
    @FXML
    private TableColumn colBorrar;
    
    @FXML
    private TableColumn colImagen;
            
    @FXML
    private TextField txtBusqueda;
    
     
  
    
    //Permite ver los cambios cuando ocurren a la lista.
    ObservableList<Producto> data;
   
    //Creando atributo.
    ProductoServicio servicio;
    @FXML
    private JFXButton btnAgregar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        servicio = new ProductoServicio();
        
        colCod.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        colCategoria.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCategoria().getDescripcion()));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        colExistencia.setCellValueFactory(new PropertyValueFactory<>("Existencia"));
        
        definirColumnaEditar();
        definirColumnaBorrar();
        definirColumnaImagen();
        
        cargarDatos();
    }    
    
    @FXML
    public void agregarProducto() throws IOException{
        Producto agregarProducto = new Producto();
        abrirVentanaModal(agregarProducto, "Agregar Producto");
    }
    
    public String guardar(Producto producto){
        String resultado = servicio.guardar(producto);
        if(resultado.equals("")){
        cargarDatos();
        }
        return resultado;
    }
    
    @FXML
    public void busqueda(){
      tableView.setItems(buscadordeProducto(txtBusqueda.getText()));
        
    }
    
    ObservableList<Producto> buscadordeProducto(String temp){
        if(temp.isEmpty()||temp.equals("")){
        return data;
        }else{
        ObservableList<Producto> databuscada = FXCollections.observableArrayList();
        for (Producto data1 : data) {
        if(data1.getDescripcion().toLowerCase().contains(temp.toLowerCase())){
        databuscada.add(data1);
        }
      }
        return databuscada;
            
    }

            
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
        data= FXCollections.observableArrayList(servicio.obtenerProductos());
        tableView.setItems(data);
        tableView.refresh();
        }

    private void definirColumnaEditar() {
       colEditar.setCellFactory(param -> new TableCell<String, String>(){
           final JFXButton btn = new JFXButton("Editar");
           
           @Override
           public void updateItem(String item, boolean empty){
           super.updateItem(item, empty);
           if (empty){
               setGraphic(null);
               setText(null);
           } else {
               btn.getStyleClass().add("jfx-button-info-outline");
               btn.setOnAction(event -> {
                        tableView.getSelectionModel().select(getTableRow().getItem());
                        
                        //Viene del TableView.
                        Producto productoExistente = (Producto) getTableRow().getItem();
                        //Producto ya clonado.
                        Producto producto = servicio.clonar(productoExistente);
               try {
                   abrirVentanaModal(producto, "Editar Producto");
               } catch (IOException ex) {
                   Logger.getLogger(FormProductoController.class.getName()).log(Level.SEVERE, null, ex);
               }
           });
           setGraphic(btn);
           setText(null);
              
           }
           }
       });
    }

    private void definirColumnaBorrar() {
       colBorrar.setCellFactory(param -> new TableCell<String, String>(){
           final JFXButton btn = new JFXButton("Borrar");
           
           @Override
           public void updateItem(String item, boolean empty){
           super.updateItem(item, empty);
           if (empty){
               setGraphic(null);
               setText(null);
           } else {
               btn.getStyleClass().add("jfx-button-danger-outline");
               btn.setOnAction(event -> {
                        tableView.getSelectionModel().select(getTableRow().getItem());
                        Producto producto = (Producto) getTableRow().getItem();
                        borrar(producto);
           });
           setGraphic(btn);
           setText(null);
              
           }
           }

           
       });
    }
    
    private void borrar(Producto producto) {
        Alert alert = new Alert(AlertType.CONFIRMATION,
        "¿Está seguro que desea eliminar el producto" + producto.getDescripcion() + "?",
        ButtonType.YES, ButtonType.NO);
        
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES){
        servicio.borrar(producto);
        cargarDatos();
        }
  }

    private void definirColumnaImagen() {
        colImagen.setCellFactory(param -> new TableCell<String, String>(){
           final ImageView img = new ImageView();
           
           @Override
           public void updateItem(String item, boolean empty){
           super.updateItem(item, empty);
           if (empty){
               setGraphic(null);
               setText(null);
           } else {
                        Producto producto = (Producto) getTableRow().getItem();
                        if(producto!= null){
                        img.imageProperty().set(producto.getVerImagen());
                        img.setFitWidth(100);
                        img.setPreserveRatio(true);
                        setGraphic(img);
                        setText(null);
                        }
           }
           }
           });
          
              
           }
           
}

           
    



