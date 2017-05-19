
package com.uma.diariosur.formularios;


import ControlVistaHome.ControlHome;
import com.uma.diariosur.entidades.Formulario;
import com.uma.diariosur.negocio.NegocioCarlos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
 
  
@SessionScoped

public class DataScrollerView implements Serializable {
      
    @EJB
    private NegocioCarlos nc;
    
    @Inject
    private ControlHome ctrlhome;
    private List<Formulario> formularios;

    public NegocioCarlos getNc() {
        return nc;
    }

    public void setNc(NegocioCarlos nc) {
        this.nc = nc;
    }

    public ControlHome getCtrlhome() {
        return ctrlhome;
    }

    public void setCtrlhome(ControlHome ctrlhome) {
        this.ctrlhome = ctrlhome;
    }
  
    public DataScrollerView() {
        
    }

    public List<Formulario> getFormularios(){
       formularios = new ArrayList<>();
       formularios = nc.listarFormulario();
       return formularios;
    }
    
    public String home(){
        return "PaginaHome.xhtml";
    }
    
    public String validar(Formulario f){
        
      nc.validarFormulario(f.getId(), ctrlhome.getPeriodista());
      nc.rechazarFormulario(f.getId());
       
       return "formularios.xhtml"; 
    }
    
    public String rechazar(Formulario f){
        
        nc.rechazarFormulario(f.getId());
        return "formularios.xhtml"; 
    }
    
}
