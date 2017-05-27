/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.rellenarformulario;

import ControlVistaHome.ControlHome;
import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Formulario;
import com.uma.diariosur.entidades.Imagen;
import com.uma.diariosur.entidades.Megusta;
import com.uma.diariosur.entidades.Valoracion;
import com.uma.diariosur.negocio.NegocioCarlosLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
/**
 *
 * @author Carlos
 */
@Named(value = "rellenarFormulario")
@RequestScoped
public class RellenarFormulario implements Serializable{

    private String nombre;
    private String categoria;
    private String descripcion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private UploadedFile img;
    private String ubicacion;
    private Double precio;
    
    @EJB
    private NegocioCarlosLocal nc;
    
    @Inject 
    private ControlHome ctrlhome;

    private String aux_ext;
    private String img_aux;
    
    public ControlHome getCtrlhome() {
        return ctrlhome;
    }

    public void setCtrlhome(ControlHome ctrlhome) {
        this.ctrlhome = ctrlhome;
    }
    
    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }
    
    private String sacar_ext(String s){
        
        int index = s.lastIndexOf('.');
            
        if (index == -1) {
                  return "";
            } else {
                  return s.substring(index + 1);
            }
        
    }
    
    public boolean save() throws IOException {
        
        boolean res = true;
        
        if(img.getFileName().isEmpty()) {
            res = false;
        }
        else{
            String ext = sacar_ext(img.getFileName());
            String aux2 = this.nombre.concat(".");
            String filename = aux2.concat(ext);
            img_aux = filename;
            InputStream input = img.getInputstream();

            
           
            URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
            
            
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        //    String path_aux = path.substring(0, path.lastIndexOf("build"));
            
            
            
            OutputStream output = new FileOutputStream(new File(path.concat("//resources"), filename));
            aux_ext = ext;
        
        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
        }
        
        return res;
}
    
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
 
    public RellenarFormulario()  {
        
    }
    
    
    public String enviar() throws IOException{
     
        if(this.nombre.isEmpty()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "Por favor introduzca un nombre");
            FacesContext.getCurrentInstance().addMessage("myform:nombre", message);
            return null;
        }
         
         
        boolean encontrado = false;
        int i=0;
        
        while (i<nc.listarEvento().size() && !encontrado){
            if (nc.listarEvento().get(i).getNombre().equalsIgnoreCase(this.nombre)){
                encontrado = true;
            }
            i++;
        }
        
        if (encontrado){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "El evento ya esta en el sistema");
            FacesContext.getCurrentInstance().addMessage("myform:nombre", message);
            return null;
        }
        else{
            
            if (this.nombre.isEmpty()|| this.descripcion.isEmpty() || this.categoria.isEmpty() || this.fecha_inicio==null || this.fecha_fin==null || this.ubicacion.isEmpty() || this.precio==null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Faltan atributos por introducir!"));
                return null;
            }
            
            if(this.fecha_inicio.after(fecha_fin)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Introduzca fechas de inicio y fin reales (fecha inicio antes que fecha fin!!"));
                return null;
            }
            
            if(!save()){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","la imagen no se subio correctamente");
                FacesContext.getCurrentInstance().addMessage("myform:img", message);
                return null;
             }
                
             
                    // Creacion de la imagen
                    
                    Imagen im = new Imagen();
                    im.setEnlace(img_aux);
                    im.setTipo(aux_ext);
                    nc.crearImagen(im);
                    
                   
                    if(ctrlhome.getUsuario()!=null){
                        Formulario form = new Formulario();
                        form.setNombre(nombre);
                        form.setDescripcion(descripcion);
                        form.setCategoria(categoria);
                        form.setUbicacion(ubicacion);
                        form.setPrecio(precio);
                        form.setFecha_inicio(fecha_inicio);
                        form.setFecha_fin(fecha_fin);
                        form.setUsuario(ctrlhome.getUsuario());
                        form.setFecha_subida(new Date());
                        form.setIm_id(im);
                        nc.crearFormulario(form);
                        
                        
                    }
                    
                    if(ctrlhome.getPeriodista()!=null){
                        Evento ev = new Evento();
                        ev.setNombre(nombre);
                        ev.setDescripcion(descripcion);
                        ev.setCategoria(categoria);
                        ev.setUbicacion(ubicacion);
                        ev.setPrecio(precio);
                        ev.setFecha_inicio(fecha_inicio);
                        ev.setFecha_final(fecha_fin);
                        ev.setPeriodista(ctrlhome.getPeriodista());
                        
                        List<Valoracion> v_vacia = new ArrayList();
                        List<Megusta> m_gusta = new ArrayList();
                        ev.setMeGusta(m_gusta);
                        ev.setImagen(im);
                        ev.setValoraciones(v_vacia);
                        nc.crearEvento(ev);
                    }
                    
                    
           
                return "PaginaHome.xhtml";
        }
        
       
    }
   
     public String comprobar(){
         
        if(this.nombre.isEmpty()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "Por favor introduzca un nombre");
            FacesContext.getCurrentInstance().addMessage("myform:nombre", message);
            return null;
        }
         
         
        boolean encontrado = false;
        int i=0;
        
        while (i<nc.listarEvento().size() && !encontrado){
            if (nc.listarEvento().get(i).getNombre().equalsIgnoreCase(this.nombre)){
                encontrado = true;
            }
            i++;
        }
        
        if (encontrado){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "El evento ya esta en el sistema");
            FacesContext.getCurrentInstance().addMessage("myform:nombre", message);
            return null;
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "" , "El evento no esta en el sistema");
            FacesContext.getCurrentInstance().addMessage("myform:nombre", message);
            return null;
        }
        
     }
     
    public String home() {
        return "PaginaHome.xhtml";
    }
    
}
