
package com.uma.diariosur.formularios;


import ControlVistaHome.ControlHome;
import com.uma.diariosur.entidades.Formulario;
import com.uma.diariosur.negocio.NegocioCarlos;
import com.uma.diariosur.negocio.NegocioCarlosLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
 
  
@SessionScoped

public class DataScrollerView implements Serializable {
      
    @EJB
    private NegocioCarlosLocal nc;
    
    @Inject
    private ControlHome ctrlhome;
    private List<Formulario> forms;

    public List<Formulario> getForms() {
        return forms;
    }

    public void setForms(List<Formulario> forms) {
        this.forms = forms;
    }


    public ControlHome getCtrlhome() {
        return ctrlhome;
    }

    public void setCtrlhome(ControlHome ctrlhome) {
        this.ctrlhome = ctrlhome;
    }
  
    public DataScrollerView() {
        forms = new ArrayList();
        forms= nc.listarFormulario();
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
