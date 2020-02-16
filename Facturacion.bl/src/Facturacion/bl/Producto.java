/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.bl;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Sinoé Adalid Tovar
 */
public class Producto {
    private SimpleIntegerProperty codigo;
    private SimpleStringProperty descripcion;
    
    public Producto (){
        codigo = new SimpleIntegerProperty();
        descripcion = new SimpleStringProperty();
    }

    //Metodos
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
}
