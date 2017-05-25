/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.megusta;

import BeanPrincipal.BeanPrincipal;
import ControlVistaHome.ControlHome;
import com.uma.diariosur.entidades.Megusta;
import com.uma.diariosur.negocio.NegocioCarmenLocal;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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
    
    @EJB
    private NegocioCarmenLocal ncar;
    
    @Inject
    private BeanPrincipal bp;
    @Inject
    private ControlHome ch;
    
    private List<Megusta> listmegusta;

    public List<Megusta> getListmegusta() {
        
        List<Megusta> lista = new ArrayList();
        listmegusta = ncar.listarMegusta();
        
        for(Megusta m: listmegusta){
            if(m.getUsuario().getNick().equals(ch.getUsuario().getNick())){
                lista.add(m);
            }
            
        }
        
        return lista;
    }
    
    
    
    public NegocioCarmenLocal getNcar() {
        return ncar;
    }

    public void setNcar(NegocioCarmenLocal ncar) {
        this.ncar = ncar;
    }
    
    
    

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
        ncar.eliminarMegusta(e);
        
       
       
       return "Megusta.xhtml";
        
        
    }
  
    
   
    
    public Mismegusta() {
  
    }
    
}
