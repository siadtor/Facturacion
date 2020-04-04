/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.fx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author Sinoé Adalid Tovar
 */
public class FormReporteProductosController implements Initializable {
                   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
        
    public void generarReporte() throws ParseException{
      VistaProductoReporte vistaReporteP = new VistaProductoReporte();
        try {
            vistaReporteP.vistaReporte();
        } catch (JRException ex) {
            Logger.getLogger(FormReporteProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   
}
