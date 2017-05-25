/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;


import com.uma.diariosur.entidades.Formulario;
import com.uma.diariosur.entidades.Megusta;
import com.uma.diariosur.entidades.Usuario;
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
 * @author Carmen
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class NegocioCarmen implements NegocioCarmenLocal {

    @PersistenceContext(name = "GrupoC-T3-ejbPU")
    private EntityManager em;
    @Resource
    private SessionContext sessionContext;

   
       @Override
    public List<Megusta> listarMegusta() {
        TypedQuery<Megusta> query=em.createNamedQuery("lista.megusta",Megusta.class);
        return query.getResultList();
    }
    
    
    
    
    @Override
    public void crearMegusta(Megusta m){
        
        UserTransaction userTxn = sessionContext.getUserTransaction();
        
        try{
            
            userTxn.begin();
            em.persist(m);
            userTxn.commit();
            
        }catch(Throwable e){
             try {
                //userTxn.rollback(); //-- Include this in try-catch 
            } catch (IllegalStateException ex) {
                Logger.getLogger(NegocioCarmen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(NegocioCarmen.class.getName()).log(Level.SEVERE, null, ex);
            
          
        }
        
            // Add business logic below. (Right-click in editor and choose
            // "Insert Code > Add Business Method")
    }
        
   }
    
    
    @Override
    public void eliminarMegusta(Megusta m){
        
        UserTransaction userTxn = sessionContext.getUserTransaction();
        try {
            userTxn.begin();
            em.remove(em.contains(m) ? m : em.merge(m));
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
    public void actualizarEmail(Usuario u){
        
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
    
    @Override
    public void actualizarPassword(Usuario u){
        
             UserTransaction userTxn = sessionContext.getUserTransaction();
          
        
        try{  
        
            userTxn.begin();
            em.merge(u);
            userTxn.commit();

        } catch(Throwable e){
            try {
                userTxn.rollback(); //-- Include this in try-catch 
            }    catch (IllegalStateException ex) {
                     Logger.getLogger(NegocioCarmen.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (SecurityException ex) {
                     Logger.getLogger(NegocioCarmen.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (SystemException ex) {
                     Logger.getLogger(NegocioCarmen.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
    }

    @Override
    public Usuario buscarUsuario(String nick){
        
        Usuario user = em.find(Usuario.class, nick);
        
        if(user== null){
            return null;
        }else{
            return user;
        }
    }


    
        
        
        
        
        
    }
    
    
   
