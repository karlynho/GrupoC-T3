/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Periodista;
import com.uma.diariosur.entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author patati
 */


@Stateless
public class NegocioPablo implements NegocioPabloLocal {

    @PersistenceContext(name = "GrupoC-T3-ejbPU")
    private EntityManager em;
    
    public Usuario buscarUsuario(String usuarioIntroducido,String contraseniaIntroducido){
        
        Usuario user= em.find(Usuario.class, usuarioIntroducido);
        
        if(user==null){
            return null;
        }else{
            if(compararContrasenia(contraseniaIntroducido, user.getPassword())){
                return user;
            }else{
                return null;
            }
        }
        
    }
    
    public Periodista buscarPeriodista(String usuarioIntroducido,String contraseniaIntroducido){
        
        Periodista peri= em.find(Periodista.class, usuarioIntroducido);
        
        if(peri==null){
            return null;
        }else{
            if(compararContrasenia(contraseniaIntroducido, peri.getPassword())){
                return peri;
            }else{
                return null;
            }
        }
        
    }
    
   public boolean compararContrasenia(String cont,String contraseniaIntroducida){
       boolean res=false;
       
       if(cont.equals(contraseniaIntroducida)){
           res=true;
       }
    return res;
   }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
