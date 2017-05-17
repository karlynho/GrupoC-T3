/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.megusta;

import BeanPrincipal.BeanPrincipal;
import ControlVistaHome.ControlHome;
import com.uma.diariosur.modelo.Evento;
import com.uma.diariosur.modelo.Megusta;
import com.uma.diariosur.modelo.Usuario;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Carmen
 */
@Named(value = "Mismegusta")
@SessionScoped

public class Mismegusta implements Serializable{

    /**
     * Creates a new instance of Mismegusta
     */
    
    @Inject
    private BeanPrincipal bp;
    @Inject
    private ControlHome ch;

    public BeanPrincipal getBp() {
        return bp;
    }

    public void setBp(BeanPrincipal bp) {
        this.bp = bp;
    }

    public ControlHome getCh() {
        return ch;
    }

    public void setCh(ControlHome ch) {
        this.ch = ch;
    }
  
   
    
    public String noMegusta(Megusta e){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento eliminado de Mis Me gusta" , "Evento eliminado de Mis Me gusta");
        FacesContext.getCurrentInstance().addMessage(null, message);
        bp.eliminarMegusta(e);
        
       
       
       return null;
        
        
    }
  
    
   
    
    public Mismegusta() {
  
    }
    
}
