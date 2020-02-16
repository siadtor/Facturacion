/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.bl;

import java.util.ArrayList;

/**
 *
 * @author Sinoé Adalid Tovar
 */
public class ProductoServicio {
    //Declaracion de la clase ArrayList, permite almacenar elemneto de forma 
    //dinamica.
    private final ArrayList<Producto> listadeProductos;

    //Constructor sin parametros
    public ProductoServicio() {
        listadeProductos = new ArrayList<>();
        
        almacenarProductos();
        
    }

    //Metodo que obtiene la lista de productos.
    public ArrayList<Producto> obtenerProductos(){
        return listadeProductos;    
    }
    
    public void guardar(Producto producto){
        if (producto.getCodigo().equals(0)){
            Integer codigo = obtenerSiguienteCodigo();
            producto.setCodigo(codigo);
            listadeProductos.add(producto);
        }
    }
    
    //Metodo para guardar los datos del producto
    private void almacenarProductos() {
        Producto p1 = new Producto();
        p1.setCodigo(0);
        p1.setDescripcion("SONY VAIO");
        
        Producto p2 = new Producto();
        p2.setCodigo(1);
        p2.setDescripcion("COMPAQ");
        
        listadeProductos.add(p1);
        listadeProductos.add(p2);
    }

    private Integer obtenerSiguienteCodigo() {
        Integer maxCodigo = 1;
        for(Producto producto: listadeProductos){
            if (producto.getCodigo() >= maxCodigo)
                maxCodigo = producto.getCodigo() + 1;
        }
        return maxCodigo;
    }
    
}
