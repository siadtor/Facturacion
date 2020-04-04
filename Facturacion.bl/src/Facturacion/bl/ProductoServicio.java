/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.bl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sinoé Adalid Tovar
 */
public class ProductoServicio {
    //Metodo que obtiene la lista de productos activos.
    //nota este metodo no se esta usando todavia pero lo dejare
    //la razon es por que no hemos inicializado la variable activo en productos.
     public ArrayList<Producto> obtenerProductosActivos(){
        {
       //Abriendo sesion y contectando con la base de datos.
        Session session = HibernateUtil.getSessionFactory().openSession();
       //Iniciando la transaccion.
        Transaction tx = session.beginTransaction();
       //Haciendo la consulta.
        Criteria query = session.createCriteria(Producto.class);
        query.add(Restrictions.eq("activo", true));
       //Trayendo una lista de productos.
        List<Producto> resultado = query.list();
       //Terminando la transaccion.
        tx.commit();
       //Cerrando sesion y desconectando con la base de datos.
        session.close();
            //System.out.println("-->" + resultado);
       //Devolviendo el resultado de la lista de productos.
        return new ArrayList<>(resultado);
    }  
        
        
    }
    
    public ArrayList<Producto> obtenerProductos(){
        {
       //Abriendo sesion y contectando con la base de datos.
        Session session = HibernateUtil.getSessionFactory().openSession();
       //Iniciando la transaccion.
        Transaction tx = session.beginTransaction();
       //Haciendo la consulta.
        Criteria query = session.createCriteria(Producto.class);
       //Trayendo una lista de productos.
        List<Producto> resultado = query.list();
       //Terminando la transaccion.
        tx.commit();
       //Cerrando sesion y desconectando con la base de datos.
        session.close();
            System.out.println("-->" + resultado);
       //Devolviendo el resultado de la lista de productos.
        return new ArrayList<>(resultado);
    }  
    }
    
    public ArrayList<Producto> obtenerProductos(String busqueda){
        
       //Abriendo sesion y contectando con la base de datos.
        Session session = HibernateUtil.getSessionFactory().openSession();
       //Iniciando la transaccion.
        Transaction tx = session.beginTransaction();
       //Haciendo la consulta.
        Criteria query = session.createCriteria(Producto.class);
       //Filtro de busqueda.
        query.add(Restrictions.like("descripcion", busqueda, MatchMode.ANYWHERE));
       //Trayendo una lista de productos.
        List<Producto>resultado = query.list();
       //Terminando la transaccion.
        tx.commit();
       //Cerrando sesion y desconectando con la base de datos.
        session.close();
       //Devolviendo el resultado de la lista de productos.
        return new ArrayList<>(resultado);
     
    }
    
    public String guardar(Producto producto){
        String resultado = validandoProducto(producto);
       if (resultado.equals("")){
       //Abriendo sesion y contectando con la base de datos.
        Session session = HibernateUtil.getSessionFactory().openSession();
       //Iniciando la transaccion.
        Transaction tx = session.beginTransaction();
           
        try {
           //Guardando y actualizando los productos.
            session.saveOrUpdate(producto);
            tx.commit();
               
           } catch (Exception e) {
               tx.rollback();
               return e.getMessage();
           } finally {
            //Cerrando sesion y desconectando con la base de datos.
            session.close();
           }
       
        return "";
        }
        return resultado;
    }
    
    public void borrar(Producto producto){
         //Abriendo sesion y contectando con la base de datos.
        Session session = HibernateUtil.getSessionFactory().openSession();
       //Iniciando la transaccion.
        Transaction tx = session.beginTransaction();
           
        try {
           //Eliminando los productos.
            session.delete(producto);
            tx.commit();
               
           } catch (Exception e) {
               tx.rollback();
               System.out.println(e.getMessage());;
           } finally {
            //Cerrando sesion y desconectando con la base de datos.
            session.close();
           }
    }
    
    /*Creando un metodo para clonar los productos para retornar una nueva instancia
    que no este vinculado con el binding.*/
    public Producto clonar(Producto producto){
    
    Producto productoClonado = new Producto();
    
    productoClonado.setCodigo(producto.getCodigo());
    productoClonado.setDescripcion(producto.getDescripcion());
    productoClonado.setCategoria(producto.getCategoria());
    productoClonado.setPrecio(producto.getPrecio());
    productoClonado.setExistencia(producto.getExistencia());
    productoClonado.setVerImagen(producto.getVerImagen());
    
    return productoClonado;
    }
    
    private String validandoProducto(Producto producto) {
        if (producto.getDescripcion() == null || producto.getDescripcion().equals("")){
            return "Ingrese la descripcion";
    }
        if (producto.getCategoria() == null){
            return "Selecciona un categoria";
    }
        if (producto.getPrecio() < 0){
            return "Ingrese un precio mayor o igual que cero";
    }
        if (producto.getExistencia() < 0){
            return "Ingrese una existencia mayor que cero";
    }
    return "";
    }
}
