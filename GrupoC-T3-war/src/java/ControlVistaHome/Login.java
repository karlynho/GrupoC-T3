
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistaHome;


import BeanPrincipal.BeanPrincipal;
import com.uma.diariosur.entidades.Periodista;
import com.uma.diariosur.entidades.Usuario;
import java.util.List;
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
           usuarios = bnp.getUsuarios();
           periodistas = bnp.getPeriodistas();
           encontrado = false;
           int tam = usuarios.size();
           int tam2= periodistas.size();
           int i = 0;
           int j = 0;
        
        if(this.usuario.isEmpty() || this.contrasenia.isEmpty()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "Rellene los dos campos por favor");
            FacesContext.getCurrentInstance().addMessage("login:pass", message);
            return null;
        }
           
           
        //comprobamos primero si es usuario normal
        while(i<tam && !encontrado){
            //recorremos la lista buscando al usuario
            if(usuarios.get(i).getNick().equals(this.usuario)){
                encontrado = true;
                
                if(usuarios.get(i).getPassword().equals(this.contrasenia)){
                    // usuario y contraseña correcto
                    ctrlhome.setUsuario(usuarios.get(i));
                  
                }else{
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "Usuario-Contraseña incorrecto");
                    FacesContext.getCurrentInstance().addMessage("login:pass", message);
                    return null;
            }
                
            }
            
            i++;
        }
        
        
        
        this.periodista=this.usuario;
        //comprobamos si es periodista
        while(j<tam2 && !encontrado){
            //recorremos la lista buscando al usuario
            if(periodistas.get(j).getNombre().equals(this.periodista)){
                encontrado = true; 
                
                if(periodistas.get(j).getPassword().equals(this.contrasenia)){
                    // usuario y contraseña correcto
                   ctrlhome.setPeriodista(periodistas.get(j));
                  
            }  else{
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "Usuario-Contraseña incorrecto");
                    FacesContext.getCurrentInstance().addMessage("login:pass", message);
                    return null;
                }
            }
            
            j++;
        }
        
        
        
        if(!encontrado){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "El nombre de usuario no existe");
            FacesContext.getCurrentInstance().addMessage("login:usuario", message);
            return null;
        }else{
            return ctrlhome.home();
        }    
    }
    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

*/