/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistaHome;

import BeanPrincipal.BeanPrincipal;
import com.uma.diariosur.entidades.Usuario;
import com.uma.diariosur.negocio.NegocioCarlosLocal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Properties;
import java.util.Random;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author Carlos
 */
@Named(value = "recuperarPass")
@RequestScoped

public class RecuperarPass {

    private String email;
    @Inject 
    private ControlHome ctrlhome;
    @Inject
    private BeanPrincipal bnp;
    private Pattern pattern;

    @EJB
    private NegocioCarlosLocal nc;
    
    
    public Pattern getPattern() {
        return pattern;
    }

     public NegocioCarlosLocal getNc() {
        return nc;
    }

    public void setNc(NegocioCarlosLocal nc) {
        this.nc = nc;
    }
    
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setBnp(BeanPrincipal bnp) {
        this.bnp = bnp;
    }
    
    
    public String recuperar(){
        
        if(this.email.isEmpty()){
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "El email no puede estar vacio");
           FacesContext.getCurrentInstance().addMessage("recuperar_pass:correo", message);
           return null;
       }else{
            pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
           Matcher mather = pattern.matcher(email);
           if (mather.find() == true) {
              
               String correoEnvia = "diariosur7@gmail.com";
        String claveCorreo = "diariosur12";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.user", correoEnvia);
        prop.put("mail.password", claveCorreo);
        
        Session ses = Session.getInstance(prop, null);
        
        try{
            
            int i=0;
            boolean encontrado=false;
            int aux = 0;
        
            while(i<nc.listarUsuario().size() && !encontrado){
            
                if(nc.listarUsuario().get(i).getEmail().equalsIgnoreCase(this.email)){
                    encontrado = true;
                    aux = i;
                }
                 i++;
            }
            
            if(!encontrado){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "El correo introducido no corresponde a ningun usuario registrado");
                FacesContext.getCurrentInstance().addMessage("recuperar_pass:reenviar", message);
                return null;
            }
            
            MimeMessage mime = new MimeMessage(ses);
            
            mime.setFrom(new InternetAddress(correoEnvia, "Recuperacion contraseña"));
            
            InternetAddress internetAddresses = new InternetAddress(this.email);
            mime.setRecipient(Message.RecipientType.TO, internetAddresses);
            
            mime.setSubject("Recuperacion contraseña");
            Random r = new Random();
            MimeBodyPart mib = new MimeBodyPart();
            
            String new_pass = "diarioSur" +r.nextInt();
            
            mib.setText("Actualmente esta es la nueva contraseña " + new_pass + " le recomendamos que en la "
                    + "sección de Ajustes cambie la contraseña");
            
           
            Multipart multi = new MimeMultipart();
            multi.addBodyPart(mib);
            
            mime.setContent(multi);
            
            Transport transport = ses.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mime, mime.getAllRecipients());
            transport.close();
            
            Usuario u = nc.listarUsuario().get(aux);
            u.setPassword(new_pass);
            nc.actualizarUsuario(u);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "" , "Correo electronico de recuperacion enviado, revise su bandeja de entrada");
            FacesContext.getCurrentInstance().addMessage("recuperar_pass:reenviar", message);
            return null;
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
               
           } else {
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" , "El email no tiene la estructura adecuada");
             FacesContext.getCurrentInstance().addMessage("recuperar_pass:correo", message);
             return null;
            }
       }
        
        return null;
    }
    
    
    public String home(){
        return "PaginaHome.xhtml";
    }
    
    /**
     * Creates a new instance of RecuperarPass
     */
    public RecuperarPass() {
    }
    
}
