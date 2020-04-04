/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.bl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Sinoé Adalid Tovar
 */

//Para convertir una imagen a un arrego de bytes para guaradarlo en la base de datos.
public class FacturacionUtilidad {
    public static byte[] ConvertidorImagen(File imagen) throws FileNotFoundException, IOException{
    byte[] bytes = new byte[(int) imagen.length()];
    try (FileInputStream stream = new FileInputStream(imagen)){
        stream.read(bytes);
    }
    return bytes;
    }
    
}
