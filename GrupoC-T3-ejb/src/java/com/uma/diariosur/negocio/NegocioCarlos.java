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

import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
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
    
    
    @Override
    public List<Formulario> listarFormulario() {
        TypedQuery<Formulario> query=em.createNamedQuery("lista.formularios",Formulario.class);
        return query.getResultList();
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
    public void rechazarFormulario(Formulario f) {
    
      //  Formulario f_entity = em.find(Formulario.class, id);
        Imagen img = em.find(Imagen.class, f.getIm_id().getId());
        
       UserTransaction userTxn = sessionContext.getUserTransaction();
        try {
            userTxn.begin();
            em.remove(em.contains(f) ? f : em.merge(f));
            userTxn.commit();
        } catch (NotSupportedException ex) {
            Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           

        
            
            
        }

    @Override
    public void validarFormulario(Integer id, Periodista periodista) {
        Formulario f = em.find(Formulario.class, id);
        if (f==null){
            System.out.println("Ese formulario no esta en la BD");
        }
        else{
            
            Imagen copia = new Imagen();
            copia.setEnlace(f.getIm_id().getEnlace());
            copia.setTipo(f.getIm_id().getTipo());
            crearImagen(copia);
            
            Evento e = new Evento();
            e.setNombre(f.getNombre());
            e.setDescripcion(f.getDescripcion());
            e.setCategoria(f.getCategoria());
            e.setPrecio(f.getPrecio());
            e.setUbicacion(f.getUbicacion());
            e.setFecha_inicio(f.getFecha_inicio());
            e.setFecha_final(f.getFecha_subida());
            e.setImagen(copia);
            e.setPeriodista(periodista);
            List<Valoracion> v_vacia = new ArrayList();
            e.setValoraciones(v_vacia);
            List<Megusta> m_gusta = new ArrayList();
            e.setMeGusta(m_gusta);
            
            
            UserTransaction userTxn = sessionContext.getUserTransaction();
        
            
            try{
                userTxn.begin();
                em.remove(em.contains(f) ? f : em.merge(f));
                em.persist(e);
                userTxn.commit();

        } catch(Throwable ee){
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

    @Override
    public void crearEvento(Evento f) {
        UserTransaction userTxn = sessionContext.getUserTransaction();
        
        try{
            userTxn.begin();
            em.persist(f);
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
    public List<Evento> listarEvento() {
        TypedQuery<Evento> query=em.createNamedQuery("lista.eventos",Evento.class);
        return query.getResultList();
    }

    @Override
    public List<Usuario> listarUsuario() {
        TypedQuery<Usuario> query=em.createNamedQuery("lista.usuarios",Usuario.class);
        return query.getResultList();
    }

    @Override
    public void actualizarUsuario(Usuario u) {
        UserTransaction userTxn = sessionContext.getUserTransaction();
        
        try{
            userTxn.begin();
            em.merge(u);
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
