
package com.uma.diariosur.formularios;


import ControlVistaHome.ControlHome;
import com.uma.diariosur.entidades.Formulario;
import com.uma.diariosur.negocio.NegocioCarlosLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
 
  
@SessionScoped
@Named(value = "dataScrollerView")
public class DataScrollerView implements Serializable {
    @EJB
    private NegocioCarlosLocal nc;
    @Inject
    private ControlHome ctrlhome;
    private List<Formulario> forms;

    public List<Formulario> getForms() {
        forms = nc.listarFormulario();
        return forms;
    }

    public NegocioCarlosLocal getNc() {
        return nc;
    }

    public void setNc(NegocioCarlosLocal nc) {
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

    
    public String home(){
        return "PaginaHome.xhtml";
    }
    
    public String validar(Formulario f){
        
      nc.validarFormulario(f.getId(), ctrlhome.getPeriodista());
       
       return "formularios.xhtml"; 
    }
    
    public String rechazar(Formulario f){
        
        nc.rechazarFormulario(f);
        return "formularios.xhtml"; 
    }
    
}
