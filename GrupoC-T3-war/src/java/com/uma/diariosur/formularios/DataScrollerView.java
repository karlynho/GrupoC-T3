
package com.uma.diariosur.formularios;

import BeanPrincipal.BeanPrincipal;
import ControlVistaHome.ControlHome;
import com.uma.diariosur.modelo.Evento;
import com.uma.diariosur.modelo.Formulario;
import com.uma.diariosur.modelo.Megusta;
import com.uma.diariosur.modelo.Valoracion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
 
  
@ManagedBean
@SessionScoped

public class DataScrollerView implements Serializable {
      
    @Inject
    private BeanPrincipal bp;
    @Inject
    private ControlHome ctrlhome;

    public ControlHome getCtrlhome() {
        return ctrlhome;
    }

    public void setCtrlhome(ControlHome ctrlhome) {
        this.ctrlhome = ctrlhome;
    }
  
    public DataScrollerView() {
        
    }

    public BeanPrincipal getBp() {
        return bp;
    }

    public void setBp(BeanPrincipal bp) {
        this.bp = bp;
    }
    
    public String home() {
        return "PaginaHome.xhtml";
    }
    
    public String validar(Formulario f){
        
        Evento e = new Evento ();
        e.setNombre(f.getNombre());
        e.setDescripcion(f.getDescripcion());
        e.setCategoria(f.getCategoria());
        e.setFecha_inicio(f.getFecha_inicio());
        e.setFecha_final(f.getFecha_fin());
        e.setUbicacion(f.getUbicacion());
        e.setPrecio(f.getPrecio());
        e.setImagen(f.getImg());
        e.setPeriodista(ctrlhome.getPeriodista());
        List<Valoracion> v_vacia = new ArrayList();
        e.setValoraciones(v_vacia);
        List<Megusta> m_gusta = new ArrayList();
        e.setMeGusta(m_gusta);
        bp.addEvent(e);
        bp.eliminarForm(f);
        
      
       return "formularios.xhtml"; 
    }
    
    public String rechazar(Formulario f){
        bp.eliminarForm(f);
        return "formularios.xhtml"; 
    }
    
}
