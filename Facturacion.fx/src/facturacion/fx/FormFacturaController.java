/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import Facturacion.bl.Categoria;
import Facturacion.bl.CategoriaServicio;
import Facturacion.bl.Factura;
import Facturacion.bl.FacturaDetalle;
import Facturacion.bl.FacturaServicio;
import Facturacion.bl.Producto;
import Facturacion.bl.ProductoServicio;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author darkg
 */
public class FormFacturaController implements Initializable {
    @FXML
    TableView tableView;
    
    @FXML
    TabPane tabPaneProducts;
    
    @FXML
    TableColumn<Producto, String> colDescripcion;
    
    @FXML
    TableColumn<Producto, Double> colPrecio;
    
    @FXML
    TableColumn colEliminar;
    
    @FXML
    Label lblTotal;
    
    @FXML
    Label lblImpuesto;
    
    Factura nuevaFactura;
    
    ObservableList<Producto> data;
    ProductoServicio servicioProductos;
    CategoriaServicio servicioCategoria;
    FacturaServicio servicioFactura;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        servicioCategoria= new CategoriaServicio();
        servicioProductos= new ProductoServicio();
        servicioFactura= new FacturaServicio();
        
       colPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
       colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
       
       definirColumnaBorrar();
       
        data = FXCollections.observableArrayList();
        tableView.setItems(data);
        tableView.refresh();
        
        crearTabs();
        nuevaFactura();
        
    } 
    
     public void nuevaFactura(){
          nuevaFactura=new Factura();
          data.clear();
          tableView.refresh();
          calcularFactura();
      }  
      
      public void guardar(){
          servicioFactura.guardar(nuevaFactura);
          Alert alerta= new Alert(Alert.AlertType.INFORMATION, "Factura Guardada");
          alerta.showAndWait();
          
          nuevaFactura();
      }
   
        
        
        private void definirColumnaBorrar() {
       colEliminar.setCellFactory(param -> new TableCell<String, String>(){
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
                        data.remove(producto);
                        tableView.refresh();
                        calcularFactura();
           });
           setGraphic(btn);
           setText(null);
              
           }
           }

           
       });
      }

    private void crearTabs() {
        ArrayList<Categoria> categorias= servicioCategoria.obtenerCategoria();
        ArrayList<Producto> productos= servicioProductos.obtenerProductos();
        
        for(Categoria categoria: categorias){
            TilePane tilePane= new TilePane();
            tilePane.setPadding(new Insets(10,10,10,10));
            tilePane.setHgap(10);
            tilePane.setVgap(10);
            
            List<Producto> productosPorCategoria = productos.stream().filter(p ->
                    Objects.equals(p.getCategoria().getId(), categoria.getId())).collect(Collectors.toList());
         
            for(Producto producto: productosPorCategoria){
                VBox vbox= new VBox();
                Label lblDescripcion= new Label();
                Label lblPrecio= new Label();
                lblDescripcion.setText(producto.getDescripcion());
                lblPrecio.setText(producto.getPrecio().toString());
                ImageView imageView= new ImageView(producto.getVerImagen());
                imageView.setUserData(producto);
                imageView.setFitWidth(50);
                imageView.setPreserveRatio(true);
                
                vbox.getChildren().add(imageView);
                vbox.getChildren().add(lblDescripcion);
                vbox.getChildren().add(lblPrecio);
                vbox.setAlignment(Pos.CENTER);
                      
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        Object object= event.getSource();
                        ImageView image= (ImageView)object;
                        Producto userdata=(Producto) image.getUserData();
                        
                        agregarProducto(userdata);
                    }                                       
                });
                tilePane.getChildren().add(vbox);
            }
            Tab tab= new Tab(categoria.getDescripcion(), tilePane);
            tabPaneProducts.getTabs().add(tab);
        }
    }
    
      private void agregarProducto(Producto userdata) {
                        data.add(userdata);
                        calcularFactura();
                    } 
      
      private void calcularFactura() {     
            Double total=0.00;
            Double impuesto; 
            nuevaFactura.getFacturaDetalle().clear();
                for(Producto producto: data){
                    FacturaDetalle detalle= new FacturaDetalle();
                    detalle.setFactura(nuevaFactura);
                    detalle.setProducto(producto);
                    detalle.setCantidad(1);
                    detalle.setPrecio(producto.getPrecio());
                    
                    nuevaFactura.getFacturaDetalle().add(detalle);
                    total+=producto.getPrecio();                   
                }
                impuesto= total*0.15;
                nuevaFactura.setTotal(total);
                nuevaFactura.setImpuesto(impuesto);
                
                lblImpuesto.setText("IMPUESTO: "+formatoMoneda(impuesto));
                lblTotal.setText("TOTAL: "+formatoMoneda(total));
                
               }
      private String formatoMoneda(double valor){
          DecimalFormat df= new DecimalFormat("#,##0.00");
          return df.format(valor);
      }
     

    
     }

