/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.ajustes;

import BeanPrincipal.BeanPrincipal;
import ControlVistaHome.ControlHome;
import com.uma.diariosur.entidades.Usuario;
import java.io.Serializable;
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
    private BeanPrincipal bp;
    @Inject
    private ControlHome ch;
    private Usuario usuario;
    private String contraseña;
    private String contraseñanueva;
    private String contraseñanueva1;
    private String emailnuevo;

    public String getEmailnuevo() {
        return emailnuevo;
    }

    public void setEmailnuevo(String emailnuevo) {
        this.emailnuevo = emailnuevo;
    }
    
    

    public ControlHome getCh() {
        return ch;
    }
  
   public Usuario User(){
       usuario = bp.user();
       return usuario;
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
        
        if(!emailnuevo.isEmpty() && (contraseña.isEmpty() && contraseñanueva.isEmpty() && contraseñanueva1.isEmpty())){
            bp.cambio(emailnuevo);
            return "PaginaHome.xhtml";
        }else if(contraseña.isEmpty() || contraseñanueva.isEmpty() || contraseñanueva1.isEmpty()){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Los campos están vacíos"));
            return null;
             
        }else if(!contraseña.equals(ch.getUsuario().getPassword())){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La contraseña introducida no es la correcta"));
             return null;
        }else if(!contraseñanueva.equals(contraseñanueva1)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Las contraseñas no coinciden"));
            return null;
        }else{
            if(emailnuevo.isEmpty()){
                   bp.intercambiar(contraseñanueva);
                   return "PaginaHome.xhtml"; 
            }else{
                   bp.cambio(emailnuevo);
                   bp.intercambiar(contraseñanueva);
                   return "PaginaHome.xhtml"; 
        }
          
       
           
        }
         
       
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
