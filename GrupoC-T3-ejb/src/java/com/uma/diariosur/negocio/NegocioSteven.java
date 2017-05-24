/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Imagen;
import com.uma.diariosur.entidades.Valoracion;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Steven
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class NegocioSteven implements NegocioStevenLocal {


    @PersistenceContext(name = "GrupoC-T3-ejbPU")
    private  EntityManager em;
    
    @Resource
    private SessionContext sessionContext;
    
    
    
    @Override
    public List<Evento> listarEventos(){
         TypedQuery<Evento> query=em.createNamedQuery("lista.Eventos", Evento.class);
        return query.getResultList();
    }

    @Override
    public List<Valoracion> listarValoraciones() {
          TypedQuery<Valoracion> query=em.createNamedQuery("lista.Valoracion", Valoracion.class);
          return query.getResultList();
    }

    @Override
    public void insertarValoracion(Valoracion var) {
        UserTransaction userTxn = sessionContext.getUserTransaction();
        
        try{
            userTxn.begin();
            em.persist(var);
            userTxn.commit();
            

        } catch(Throwable e){
            try {
           //     userTxn.rollback(); //-- Include this in try-catch 
            } catch (IllegalStateException ex) {
                Logger.getLogger(NegocioSteven.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(NegocioSteven.class.getName()).log(Level.SEVERE, null, ex);
            
            } 
  }
    }
    
    
    
}
