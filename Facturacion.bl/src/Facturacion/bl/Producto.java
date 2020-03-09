/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.bl;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Sinoé Adalid Tovar
 */

@Entity
@Table(name="Producto")
public class Producto {
    private SimpleIntegerProperty codigo;
    private SimpleStringProperty descripcion;
    private SimpleDoubleProperty precio;
    private SimpleIntegerProperty existencia;
    private SimpleObjectProperty categoria;
    
    
    public Producto (){
        codigo = new SimpleIntegerProperty();
        descripcion = new SimpleStringProperty();
        categoria = new SimpleObjectProperty();
        precio = new SimpleDoubleProperty();
        existencia = new SimpleIntegerProperty();
    }

    //Metodos
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getCodigo() {
        return codigo.get();
    }

    public void setCodigo(Integer codigo) {
        this.codigo.set(codigo);
    }
    
    public SimpleIntegerProperty codigoProperty(){
        return codigo;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    
    public SimpleStringProperty descripcionProperty(){
        return descripcion;
    }

    public SimpleDoubleProperty precioProperty() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }
    
    public Double getPrecio(){
        return precio.get();
    }

    public SimpleIntegerProperty existenciaProperty() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia.set(existencia);
    }
    
    public Integer getExistencia(){
        return existencia.get();
    }
    
    public SimpleObjectProperty categoriaProperty() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria.set(categoria);
    }
    
    @ManyToOne
    @JoinColumn(name="categoriaId", nullable=false)
    public Categoria getCategoria(){
        return (Categoria)categoria.get();
    }

    
}
