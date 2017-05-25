/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.ajustes;


import ControlVistaHome.ControlHome;
import com.uma.diariosur.entidades.Usuario;
import com.uma.diariosur.negocio.NegocioCarlosLocal;
import com.uma.diariosur.negocio.NegocioCarmenLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Carmen
 */
@Named(value = "ajustes")

@ViewScoped

public class Ajustes implements Serializable{

    /**
     * Creates a new instance of Ajustes
     */
 
    @Inject
    private ControlHome ch;
    private Usuario usuario;
    private String contraseña;
    private String contraseñanueva;
    private String contraseñanueva1;
    private String emailnuevo;
    
    
    @EJB
    private NegocioCarmenLocal ncar;
    
    @EJB
    private NegocioCarlosLocal nc;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public NegocioCarmenLocal getNcar() {
        return ncar;
    }

    public void setNcar(NegocioCarmenLocal ncar) {
        this.ncar = ncar;
    }
    
    

    public String getEmailnuevo() {
        return emailnuevo;
    }

    public void setEmailnuevo(String emailnuevo) {
        this.emailnuevo = emailnuevo;
    }
    
    

    public ControlHome getCh() {
        return ch;
    }
  
   
   
    
    

    public void setusuario(ControlHome ch) {
        this.usuario = ch.getUsuario();
    }

    

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getContraseñanueva() {
        return contraseñanueva;
    }

    public void setContraseñanueva(String contraseñanueva) {
        this.contraseñanueva = contraseñanueva;
    }

    public String getContraseñanueva1() {
        return contraseñanueva1;
    }

    public void setContraseñanueva1(String contraseñanueva1) {
        this.contraseñanueva1 = contraseñanueva1;
    }
    
    
    
    
    
    public String comprueba(){
        
        int i = 0;
        while(i<nc.listarUsuario().size() && !ch.getUsuario().getNick().equals(nc.listarUsuario().get(i).getNick())){
            i++;
        }
        
        
        
        if(!emailnuevo.isEmpty() && (contraseña.isEmpty() && contraseñanueva.isEmpty() && contraseñanueva1.isEmpty())){
            
            Usuario u2 = nc.listarUsuario().get(i);
            u2.setEmail(emailnuevo);
          
            ncar.actualizarEmail(u2);
            return "PaginaHome.xhtml";
        }else if(contraseña.isEmpty() || contraseñanueva.isEmpty() || contraseñanueva1.isEmpty()){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Los campos están vacíos"));
            return null;
             
        }else if(!contraseña.equals(nc.listarUsuario().get(i).getPassword())){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La contraseña introducida no es la correcta"));
             return null;
        }else if(!contraseñanueva.equals(contraseñanueva1)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Las contraseñas no coinciden"));
            return null;
        }else{
            if(emailnuevo.isEmpty()){
                     Usuario u1 = nc.listarUsuario().get(i);
                     u1.setPassword(contraseñanueva);
                     ncar.actualizarPassword(u1);
                   return "PaginaHome.xhtml"; 
            }else{
                     
                     Usuario u2 = nc.listarUsuario().get(i);
                     u2.setEmail(emailnuevo);
                     ncar.actualizarEmail(u2);
                     
                    
                     
                     
                     Usuario u1 = nc.listarUsuario().get(i);
                     u1.setPassword(contraseñanueva);
                     ncar.actualizarPassword(u1);
                     
                   return "PaginaHome.xhtml"; 
        }
          
       
           
        }
         
       
    }
 
    public Usuario usuarioB(){
        
        Usuario user = ncar.buscarUsuario(ch.getUsuario().getNick());
        
        return user;
    }

    public NegocioCarlosLocal getNc() {
        return nc;
    }

    public void setNc(NegocioCarlosLocal nc) {
        this.nc = nc;
    }
    
            
            
            
      public String home() {
        return "PaginaHome.xhtml";
    }
      
      
      public void cerrarSesion(){
       
          ch.logout();
      }
      
    public Ajustes() {
        
    }
    
}
