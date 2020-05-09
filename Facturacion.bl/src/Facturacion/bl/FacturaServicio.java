/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.bl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author darkg
 */
public class FacturaServicio {
    
    public void guardar(Factura Factura){

        //Abriendo sesion y contectando con la base de datos.
         Session session = HibernateUtil.getSessionFactory().openSession();
        //Iniciando la transaccion.
         Transaction tx = session.beginTransaction();

         try {
            //Guardando y actualizando los productos.
             session.saveOrUpdate(Factura);
             
             for(FacturaDetalle detalle: Factura.getFacturaDetalle()){
                 Integer id = detalle.getProducto().getCodigo();
                 
                 Criteria query= session.createCriteria(Producto.class);
                 query.add(Restrictions.eq("id", id));
                 query.setMaxResults(1);
                 
                 Producto productoExistente=(Producto) query.uniqueResult();
                        
                 Integer nuevaExistencia= productoExistente.getExistencia()-
                         detalle.getCantidad();
                 
                 productoExistente.setExistencia(nuevaExistencia);
                 
                 session.saveOrUpdate(productoExistente);
             }
             
             tx.commit();

            } catch (Exception e) {
                tx.rollback();
                System.out.println(e.getMessage());
            } finally {
             //Cerrando sesion y desconectando con la base de datos.
             session.close();
            }
       
    }

    public ArrayList<Factura> obtenerFacturas(Date fechaInicial, Date fechaFinal){
        {
       //Abriendo sesion y contectando con la base de datos.
        Session session = HibernateUtil.getSessionFactory().openSession();
       //Iniciando la transaccion.
        Transaction tx = session.beginTransaction();
       //Haciendo la consulta.
        Criteria query = session.createCriteria(Factura.class);
        //Agregando restricciones a la fecha.
        query.add(Restrictions.ge("fecha", fechaInicial));
        query.add(Restrictions.le("fecha", fechaFinal));
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
       
       //Trayendo una lista de productos.
        List<Factura> resultado = query.list();
       //Terminando la transaccion.
        tx.commit();
       //Cerrando sesion y desconectando con la base de datos.
        session.close();
            System.out.println("-->" + resultado);
       //Devolviendo el resultado de la lista de productos.
        return new ArrayList<>(resultado);
    }  
    }
    
     public ArrayList<Factura> obtenerUltimasFacturasEmitidas(){
        {
       //Abriendo sesion y contectando con la base de datos.
        Session session = HibernateUtil.getSessionFactory().openSession();
       //Iniciando la transaccion.
        Transaction tx = session.beginTransaction();
       //Haciendo la consulta.
        Criteria query = session.createCriteria(Factura.class);
        query.addOrder(Order.desc("fecha"));
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setFetchMode("FacturaDetalle", FetchMode.SELECT);
        query.setMaxResults(10);
        
       //Trayendo una lista de productos.
        List<Factura> resultado = query.list();
       //Terminando la transaccion.
        tx.commit();
       //Cerrando sesion y desconectando con la base de datos.
        session.close();
            System.out.println("-->" + resultado);
       //Devolviendo el resultado de la lista de productos.
        return new ArrayList<>(resultado);
    }  
    }
}
