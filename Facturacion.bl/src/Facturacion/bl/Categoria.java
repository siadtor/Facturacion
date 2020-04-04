package Facturacion.bl;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sinoé Adalid Tovar
 */

@Entity
@Table(name="Categoria")
public class Categoria {
    
    //Llave primaria. El id sera autonumerico.
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;

    public Categoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria() {
    }
    
    //Hibernate, haciendo una relacion de uno a muchos.
    @OneToMany(mappedBy="categoria")
    private Set<Producto> producto;
    
    public Set<Producto> getProducto() {
        return producto;
    }

    public void setProducto(Set<Producto> producto) {
        this.producto = producto;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
      
}
