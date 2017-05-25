
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.evento;
import BeanPrincipal.BeanPrincipal;
import ControlVistaHome.ControlHome;
import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Megusta;
import com.uma.diariosur.entidades.Usuario;
import com.uma.diariosur.entidades.Valoracion;
import com.uma.diariosur.negocio.NegocioCarmenLocal;
import com.uma.diariosur.negocio.NegocioCarlosLocal;
import com.uma.diariosur.negocio.NegocioCarmenLocal;
import com.uma.diariosur.negocio.NegocioStevenLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.hibernate.Hibernate;
import org.primefaces.event.RateEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
/**
 *
 * @author steven
 */
@Named(value = "pruebaBean")
@SessionScoped
public class PruebaBean implements Serializable{
    @Inject
    private BeanPrincipal ctreve;
    @Inject
    private ControlHome ctrh;
    private List<Valoracion> val;
    private Integer rating2; 
    private Integer ratinguser;
    private String text;
    private MapModel geoModel;
    private String centerGeoMap = "41.850033, -87.6500523";
    
    @EJB
    private NegocioCarmenLocal ncar; 
    @EJB
    private NegocioStevenLocal ns;
   
    public MapModel getGeoModel() {
        return geoModel;
    }

    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }
    
    

    public BeanPrincipal getCtreve() {
        return ctreve;
    }

    public void setCtreve(BeanPrincipal ctreve) {
        this.ctreve = ctreve;
    }

    public List<Valoracion> getVal() {
        return val;
    }

    public void setVal(List<Valoracion> val) {
        this.val = val;
    }

    public ControlHome getCtrh() {
        return ctrh;
    }

    public void setCtrh(ControlHome ctrh) {
        this.ctrh = ctrh;
    }
    
    
    public List<Valoracion> consultaV(Evento e){
       List<Valoracion> valoraciones = ns.listarValoraciones();
       List<Valoracion> val= new ArrayList();
       
       
       for(Valoracion v:valoraciones){
           if(v.getEvento().getId()==e.getId()){
               val.add(v);
           }
       }
       
       return val;
    }
    
    public List<Valoracion> comentarios() {
        val = new ArrayList<>();
        val = consultaV(ctrh.getEventoV()); 
        return val;
    }

    public String guardarComentario() {
        
        if(this.ratinguser==null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "Por favor introduzca una puntuacion");
            FacesContext.getCurrentInstance().addMessage("val:p", message);
            return null;
        }
        
        if(text.isEmpty()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "Por favor introduzca un texto");
            FacesContext.getCurrentInstance().addMessage("val:c", message);
            return null;
        }
        
        
        
        Valoracion var = new Valoracion();
        var.setComentario(text);
        var.setEvento(ctrh.getEventoV());
        var.setPuntuacion(ratinguser);
        var.setUsuario(ctrh.getUsuario());
        ns.insertarValoracion(var);
        
        this.text=null;
        ratinguser=null; /// para despues de actualizar resetear atributos de val
        
        return null;
    }

     
    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    
 
    public Integer getRating2() {
        return rating2;
    }
 
    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }

    public Integer getRatinguser() {
        return ratinguser;
    }

    public void setRatinguser(Integer ratinguser) {
        this.ratinguser = ratinguser;
    }
    
   
     
 
    public String getText() {
        return text;
    }
 
    public void setText(String text) {
        this.text = text;
        
    }
    
    public String MeGusta(Evento eve){
        
        boolean encontrado = false;
        for(Megusta m: ctrh.getUsuario().getMegusta()){
            if(m.getUsuario().getNick().equals(ctrh.getUsuario().getNick())){
                if(m.getEvento().getId() == eve.getId()){
                     encontrado=true;
                 }
            }
            
        }
  
      if(encontrado){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso" , "Este evento ya lo añadiste a Mis MeGusta");
            FacesContext.getCurrentInstance().addMessage("pm:bm", message);
            return null;
      }  
        
      else{
          Megusta me = new Megusta();
          
          me.setEvento(eve);
          me.setUsuario(ctrh.getUsuario());

          
          ncar.crearMegusta(me);

          
          FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "" , "Añadido evento a mis MeGusta");
          FacesContext.getCurrentInstance().addMessage("pm:bm", message);
          return "vistaEvento.xhtml";
     }   
       
    }
    
    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
         
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
             
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), ctrh.getEventoV().getNombre()));
            }
        }
    }
    
    
    
    
    
    
    
    
    
    public boolean precio(Evento e){
        if(e.getPrecio()>0){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Creates a new instance of ControlHome
     */
    public PruebaBean() {
        geoModel = new DefaultMapModel();
    }
    
}

