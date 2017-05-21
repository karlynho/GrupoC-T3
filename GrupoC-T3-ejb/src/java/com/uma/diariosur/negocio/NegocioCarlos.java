/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Formulario;
import com.uma.diariosur.entidades.Imagen;
import com.uma.diariosur.entidades.Megusta;
import com.uma.diariosur.entidades.Periodista;
import com.uma.diariosur.entidades.Usuario;
import com.uma.diariosur.entidades.Valoracion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Carlos
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class NegocioCarlos implements NegocioCarlosLocal {

    @PersistenceContext(name = "GrupoC-T3-ejbPU")
    private EntityManager em;
    @Resource
    private SessionContext sessionContext;
    List<Formulario> formularios;
    
    @Override
    public List<Formulario> listarFormulario() {
        formularios = new ArrayList<>();
        
        Query q = em.createQuery("SELECT f from Formulario f");
        formularios = q.getResultList();
        System.out.println("seee "+ formularios.size());
        return formularios;
    }

   @Override
    public void crearFormulario(Formulario f) {
       UserTransaction userTxn = sessionContext.getUserTransaction();
        
        try{
            userTxn.begin();
            em.persist(f);
            userTxn.commit();

        } catch(Throwable e){
            try {
                //userTxn.rollback(); //-- Include this in try-catch 
            } catch (IllegalStateException ex) {
                Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
            }
  }
        
        
    }

    @Override
    public void rechazarFormulario(Integer id) {
        Formulario f = em.find(Formulario.class, id);
        if (f==null){
            System.out.println("Ese formulario no esta en la BD");
        }
        else{
            Imagen img = em.find(Imagen.class, f.getImagen_ID().getId());
            em.remove(f);
            em.remove(img);
        }
    }

    @Override
    public void validarFormulario(Integer id, Periodista periodista) {
        Formulario f = em.find(Formulario.class, id);
        if (f==null){
            System.out.println("Ese formulario no esta en la BD");
        }
        else{
            Evento e = new Evento();
            e.setNombre(f.getNombre());
            e.setDescripcion(f.getDescripcion());
            e.setCategoria(f.getCategoria());
            e.setPrecio(f.getPrecio());
            e.setUbicacion(f.getUbicacion());
            e.setFecha_inicio(f.getFecha_inicio());
            e.setFecha_final(f.getFecha_subida());
            e.setImagen(f.getImagen_ID());
            e.setPeriodista(periodista);
            List<Valoracion> v_vacia = new ArrayList();
            e.setValoraciones(v_vacia);
            List<Megusta> m_gusta = new ArrayList();
            e.setMeGusta(m_gusta);
            em.persist(e);
           
            Imagen img = em.find(Imagen.class, f.getImagen_ID().getId());
            img.setEvento(e);
            em.merge(img);
        }
        
    }

    @Override
    public void crearImagen(Imagen img) {
        UserTransaction userTxn = sessionContext.getUserTransaction();
        
        try{
            userTxn.begin();
            em.persist(img);
            
            userTxn.commit();

        } catch(Throwable e){
            try {
                userTxn.rollback(); //-- Include this in try-catch 
            } catch (IllegalStateException ex) {
                Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
            }
  }
        
        
    }

    @Override
    public void crearUsuario(Usuario u) {
        UserTransaction userTxn = sessionContext.getUserTransaction();
        
        try{
            userTxn.begin();
            em.persist(u);
            userTxn.commit();

        } catch(Throwable e){
            try {
                userTxn.rollback(); //-- Include this in try-catch 
            } catch (IllegalStateException ex) {
                Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
} 
        

    
    
    
}
