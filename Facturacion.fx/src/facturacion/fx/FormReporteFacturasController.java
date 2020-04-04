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
public class FormReporteFacturasController implements Initializable {
    
    @FXML
    JFXDatePicker datePickerFechaInicial;
    
    @FXML
    JFXDatePicker datePickerFechaFinal;
    
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializando con las fechas actuales.
         datePickerFechaInicial.setValue(LocalDate.now());
         datePickerFechaFinal.setValue(LocalDate.now());
        
    }    
    
    
    public void generarReporte() throws ParseException{
     VistaFacturaReporte vistaReporteV = new VistaFacturaReporte();
        try {
            //Importa la fecha guardada en la base de datos.
            Date fechaInicial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(getFecha(datePickerFechaInicial) + " 00:00:00");
            
            Date fechaFinal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(getFecha(datePickerFechaFinal) + " 23:59:59");
            
            vistaReporteV.vistaReporte(fechaInicial, fechaFinal);
        } catch (JRException ex) {
            Logger.getLogger(FormReporteFacturasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getFecha(JFXDatePicker datePicker){
        //Formato para taer el año, mes, dia.
        String fecha;
        fecha = Integer.toString(datePicker.getValue().getYear()) + "-" +
                Integer.toString(datePicker.getValue().getMonthValue()) + "-" +
                Integer.toString(datePicker.getValue().getDayOfMonth()); 
        
        return fecha;
    }
}
