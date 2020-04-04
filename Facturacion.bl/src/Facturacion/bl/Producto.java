/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.bl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Sinoé Adalid Tovar
 */

@Entity
@Table(name="Producto")
public class Producto {
    private SimpleIntegerProperty codigo;
    private SimpleStringProperty descripcion;
    private SimpleDoubleProperty precio;
    private SimpleIntegerProperty existencia;
    private SimpleObjectProperty categoria;
    private SimpleObjectProperty verImagen;
    private byte[] imagen;

   
    
    
    
    public Producto (){
        codigo = new SimpleIntegerProperty();
        descripcion = new SimpleStringProperty();
        categoria = new SimpleObjectProperty();
        precio = new SimpleDoubleProperty();
        existencia = new SimpleIntegerProperty();
        verImagen = new SimpleObjectProperty();
        imagen = "0".getBytes();
    }

    //Metodos
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getCodigo() {
        return codigo.get();
    }

    public void setCodigo(Integer codigo) {
        this.codigo.set(codigo);
    }
    
    public SimpleIntegerProperty codigoProperty(){
        return codigo;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    
    public SimpleStringProperty descripcionProperty(){
        return descripcion;
    }

    public SimpleDoubleProperty precioProperty() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }
    
    public Double getPrecio(){
        return precio.get();
    }

    public SimpleIntegerProperty existenciaProperty() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia.set(existencia);
    }
    
    public Integer getExistencia(){
        return existencia.get();
    }
    
    public SimpleObjectProperty categoriaProperty() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria.set(categoria);
    }
    
    @ManyToOne
    @JoinColumn(name="categoriaId", nullable=false)
    public Categoria getCategoria(){
        return (Categoria)categoria.get();
    }
    
    //Imagen convertida en bytes se guarda en la base de datos.
    @Lob
    @Column(name = "imagen", columnDefinition = "MEDIUMBLOB")
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    //Retornando la imagen convertida en arreglo de Bytes que se almacena en la base de datos.
    @Transient
    public Image getVerImagen(){
        Image img = new Image(new ByteArrayInputStream(imagen));
        return img;
    }
    
    //Retorna la descripción de la categoría para obtenerla en el resporte de productos.
    @Transient
    public String getdescripcionCategoria(){
        return getCategoria().getDescripcion();
    }
    
    //Retorna la imagen del producto para obtenerla en reporte de productos.
    @Transient
    public InputStream getFoto(){
        return new ByteArrayInputStream(imagen);
    }
    
    public void setVerImagen(Image image){
        if (image == null) {
        setImagen("0".getBytes());
        verImagen.set(image);
        return;
        }
        
        BufferedImage Image = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        //Sin importar la extensión de la imagen, se guarda con extensión PNG.
        try {
            ImageIO.write(Image, "png", stream);
            byte[] bytes = stream.toByteArray();
            stream.close();
            setImagen(bytes);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        verImagen.set(image);
    }
    
    public SimpleObjectProperty verImagenProperty(){
        return verImagen;
    }
    
    
     private Set<FacturaDetalle> facturaDetalle;
 
    @OneToMany(mappedBy="producto")
    public Set<FacturaDetalle> getFacturaDetalle() {
        return facturaDetalle;
    }

    public void setFacturaDetalle(Set<FacturaDetalle> facturaDetalle) {
        this.facturaDetalle = facturaDetalle;
    }
}
