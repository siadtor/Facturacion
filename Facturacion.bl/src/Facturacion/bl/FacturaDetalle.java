/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.bl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author darkg
 */
@Entity
@Table(name="FacturaDetalle")
public class FacturaDetalle {
    private Integer Id;
    private Factura Factura;
    private Producto producto;
    private Integer cantidad;
    private Double precio;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }
    
    @ManyToOne
    @JoinColumn(name="facturaId", nullable=false)
    public Factura getFactura() {
        return Factura;
    }

    public void setFactura(Factura Factura) {
        this.Factura = Factura;
    }

    @ManyToOne
    @JoinColumn(name="productoId", nullable=false)
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
