/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import Facturacion.bl.ProductoServicio;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Sinoé Adalid Tovar
 */
public class VistaProductoReporte extends JFrame{
    
    public void vistaReporte() throws JRException{
    ProductoServicio servicio = new ProductoServicio();
    
    //Ruta donde se encuentra el archivo que nos ayuda a crear el reporte.
    String file = "/facturacion/fx/ReporteProductos.jasper";
    
    //Obteniendo el archivo y enviandolo como bytes.
    InputStream stream = getClass().getResourceAsStream(file);
    
    //Pasando el archivo como bytes.
    JasperReport reporte = (JasperReport) JRLoader.loadObject(stream);
    //Llenando los datos del reporte.
    JRBeanCollectionDataSource beanColDataSource = 
            new JRBeanCollectionDataSource(servicio.obtenerProductos(), false);

    //Llenando el reporte.
    JasperPrint print = JasperFillManager.fillReport(reporte, null, beanColDataSource);
    
    //Ver el informe generado.
    JRViewer viewer = new JRViewer(print);
    viewer.setOpaque(true);
    viewer.setVisible(true);
    
    //Se agrega el reporte al JFrame.
    this.add(viewer);
    this.setSize(1000, 700);
    
    //Centrando el reporte en la pantalla.
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(dim.width/2-this.getSize().width/2, 
            dim.height/2-this.getSize().height/2);
    
    //Se muestra el reporte.
    this.setVisible(true);
    
    
    }
}
