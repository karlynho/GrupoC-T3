
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistaHome;


import BeanPrincipal.BeanPrincipal;
import com.uma.diariosur.entidades.Periodista;
import com.uma.diariosur.entidades.Usuario;
import com.uma.diariosur.negocio.NegocioPabloLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author steven
 */
@Named(value = "login")
@RequestScoped
public class Login {

    private String usuario;
    private String periodista;
    private String contrasenia;
    private boolean encontrado;
    @Inject 
    private ControlHome ctrlhome;
    @Inject
    private BeanPrincipal bnp;
    private List<Usuario> usuarios;
    private List<Periodista>periodistas;
    
    @EJB
    private NegocioPabloLocal np;
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
   
    public List<Periodista> getPeriodistas() {
        return periodistas;
    }

    public void setPeriodistas(List<Periodista> periodistas) {
        this.periodistas = periodistas;
    }
   
    public ControlHome getCtrhome() {
        return ctrlhome;
    }

    public ControlHome getCtrlhome() {
        return ctrlhome;
    }

    public void setCtrlhome(ControlHome ctrlhome) {
        this.ctrlhome = ctrlhome;
    }

    public BeanPrincipal getBnp() {
        return bnp;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    
    public String getPeriodista() {
        return periodista;
    }

    public void setPeriodista(String periodista) {
        this.periodista = periodista;
    }
   

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    
    
    
   
    
    
    /**
     * Creates a new instance of Login
     */
    public Login() {
    }
    
    public String autenticar(){
        
        //si no hay datos introducidos en el formulario
        if(this.usuario.isEmpty() || this.contrasenia.isEmpty()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "Rellene los dos campos por favor");
            FacesContext.getCurrentInstance().addMessage("login:pass", message);
            return null;
        }
        //buscamos al usuario
            Periodista peri = np.buscarPeriodista(this.usuario, this.contrasenia);
            Usuario user= np.buscarUsuario(this.usuario,this.contrasenia);
        //si lo encuentra le dara el control al tipo que sea
        
         if(peri!=null){
            ctrlhome.setPeriodista(peri);
        }
         
        if(user!=null){
             ctrlhome.setUsuario(user);
        }
        
    
        //si no es que los datos no coinciden 
        if(peri==null && user==null){
           
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "Credenciales incorrectas");
            FacesContext.getCurrentInstance().addMessage("login:pass", message);
            return null;
        }
        
            return ctrlhome.home();
         
            
       
               
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

*/