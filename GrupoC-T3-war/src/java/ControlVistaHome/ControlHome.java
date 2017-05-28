
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistaHome;

import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Periodista;
import com.uma.diariosur.entidades.Usuario;
import com.uma.diariosur.entidades.Valoracion;
import com.uma.diariosur.negocio.NegocioCarlosLocal;
import com.uma.diariosur.negocio.NegocioSteven;
import com.uma.diariosur.negocio.NegocioStevenLocal;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author steven
 */
@Named(value = "controlHome")
@SessionScoped


public class ControlHome implements Serializable{

  
    private Usuario usuario;
    private Periodista periodista;
    private String evento;
    private String ubicacion;
    private String categoria;
    private Date fecha;
    private String stringVacio;
    private Date   fechaVacia;
    private String busqueda;
    private String busquedaVacia;
    private List<Evento> listaEventosVacia;
    private List<Evento> eventos;
    private List<Evento> eventosFiltrados;
    private List<Evento> eventosValidos;
    private Evento eventoV;

    
    
    public Evento getEventoV() {
        return eventoV;
    }

    public void setEventoV(Evento eventoV) {
        this.eventoV = eventoV;
    }

    public List<Evento> getEventosFiltrados() {
        return eventosFiltrados;
    }

    public void setEventosFiltrados(List<Evento> eventosFiltrados) {
        this.eventosFiltrados = eventosFiltrados;
    }

    public NegocioStevenLocal getNs() {
        return ns;
    }

    public void setNs(NegocioStevenLocal ns) {
        this.ns = ns;
    }
    
    
    
    @EJB
    private NegocioStevenLocal ns;
    @EJB
    private NegocioCarlosLocal nc;

    public NegocioCarlosLocal getNc() {
        return nc;
    }

    public void setNc(NegocioCarlosLocal nc) {
        this.nc = nc;
    }
    
     public List<Evento> getEventos() {
         eventos = ns.listarEventos();
        return eventos;
        
        
    }

    
    

    public String getBusquedaVacia() {
        return busquedaVacia;
    }

    public void setBusquedaVacia(String busquedaVacia) {
        this.busquedaVacia = busquedaVacia;
    }

    
    
    
    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    
    
    public String buscar(){
        if(busqueda == null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "El campo de búsqueda está vacio");
            FacesContext.getCurrentInstance().addMessage("menu:bus", message);
        }
        List<Evento> ev = new ArrayList();
        List<Evento> todoEventos = new ArrayList();
        
        todoEventos = ns.listarEventos();
        
        int i=0;
        Evento event = new Evento();
        while(i<todoEventos.size()){
            event = todoEventos.get(i);
            if(event.getNombre().toUpperCase().contains(busqueda.toUpperCase())){
                ev.add(event);
               
            }
            i++;
        }
        if(!ev.isEmpty()){
            this.eventosFiltrados = ev;
            return "PaginaHome.xhtml";
        }else{
            
            this.eventosFiltrados=ns.listarEventos();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "No se han encontrado coincidencias.");
            FacesContext.getCurrentInstance().addMessage("menu:bus", message);
           
            return null;
        }      
    }
    
    
    
    
    public String getStringVavio() {
        return stringVacio;
    }

    public void setStringVavio(String stringVavio) {
        this.stringVacio = stringVavio;
    }

    public Date getFechaVacia() {
        return fechaVacia;
    }

    public void setFechaVacia(Date fechaVacia) {
        this.fechaVacia = fechaVacia;
    }

   


   
    
    
    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setPeriodista(Periodista periodista){
        this.periodista = periodista;
    }
    
    public Periodista getPeriodista(){
        return periodista;
    }
    
    public String home(){
       return "PaginaHome";

    }
    
    public String login(){
        if(this.usuario == null){
            if(this.periodista == null){
                //No hay usuario, por tanto redirigimos a pagina Login
                return "Login.xhtml";
            }else{
                return "PaginaHome.xhtml";
            }
        }else{
                return "PaginaHome.xhtml";
        }
    }
    
    public String registro(){
        return "Registro.xhtml";
    }
    public String logout(){
        //Cerramos la sesion
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        periodista = null;
        usuario = null;
        return "Login.xhtml";
    }
    
    public String perfil(){
        return "Ajustes.xhtml";
    }
    

    public String rehacer(){
        this.ubicacion = stringVacio;
        this.categoria = stringVacio;
        this.fecha     = fechaVacia;
        this.busqueda = busquedaVacia;
        this.eventosFiltrados = listaEventosVacia;
        return "PaginaHome.xhtml";
    }

 
    public String getStringVacio() {
        return stringVacio;
    }

    public void setStringVacio(String stringVacio) {
        this.stringVacio = stringVacio;
    }

    public List<Evento> getListaEventosVacia() {
        return listaEventosVacia;
    }

    public void setListaEventosVacia(List<Evento> listaEventosVacia) {
        this.listaEventosVacia = listaEventosVacia;
    }

    public String verEvento(Evento e){   
       int i = 0;
       int j= 0;
      
       Evento ev = new Evento();
        ev=e;
        this.eventoV=e;
        List<Evento> validos = new ArrayList<Evento>();
        List<Evento> Novalidos = new ArrayList<Evento>();
       for (Evento ee : ns.listarEventos()) {
           if(ee.getCategoria().equals(ev.getCategoria()) && !(ee.getNombre().equals(ev.getNombre())) ){
               validos.add(ee);
               i++;
               
           }else{
               
               if(!(ee.getNombre().equals(ev.getNombre()))){
                   Novalidos.add(ee);
               }
               
           
               
           }
           
       }
    
       //Para que en Recomendados siempre tenga al menos 7
       while(i<7 && !Novalidos.isEmpty()){
           validos.add(Novalidos.get(0));
           Novalidos.remove(0);
           
       }
       
       eventosValidos = validos;
       return "vistaEvento.xhtml";
    }

    public List<Evento> getEventosValidos() {
        return eventosValidos;
    }

    public void setEventosValidos(List<Evento> eventosValidos) {
        this.eventosValidos = eventosValidos;
    }
    
      public Integer media(){  
      if((eventoV.getValoraciones() != null) && (eventoV.getValoraciones().size()>0)){
            
        int i= 0;
        Iterator<Valoracion> it = eventoV.getValoraciones().iterator();
        Valoracion val = new Valoracion();
        while(it.hasNext()){
            val = it.next();
            i =  (i+ val.getPuntuacion());
        }
        return i / eventoV.getValoraciones().size();
        }else{
            return 0;
        }
        
    }


    
    public String RevisarEvento(){
        return "formularios.xhtml";
    }
    
    public String accederEvento(){
        return "rellenar_formulario.xhtml";
    }

    public String accederMismegusta(){
        return "Megusta.xhtml";
    }
    
    public String borrarEvento(Evento e){
        
        nc.eliminarEvento(e);
        return null;
    }
    
    
    /**
     * Creates a new instance of ControlHome
     */
    public ControlHome() {
       

    }
  
    

}