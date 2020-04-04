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

/**
 *
 * @author Sinoé Adalid Tovar
 */
public class CategoriaServicio {
     
   public ArrayList<Categoria> obtenerCategoria(){
       //Abriendo sesion y contectando con la base de datos.
        Session session = HibernateUtil.getSessionFactory().openSession();
       //Iniciando la transaccion.
        Transaction tx = session.beginTransaction();
       //Haciendo la consulta.
        Criteria query = session.createCriteria(Categoria.class);
       //Trayendo una lista de categoria.
        List<Categoria> resultado = query.list();
       //Terminando la transaccion.
        tx.commit();
       //Cerrando sesion y desconectando con la base de datos.
        session.close();
       //Devolviendo el resultado de la lista de categoria.
        return new ArrayList<>(resultado);
    }
}
