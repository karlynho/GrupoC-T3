/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Valoracion;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.commons.collections4.ListUtils;

/**
 *
 * @author Steven
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class NegocioSteven implements NegocioStevenLocal {

    @PersistenceContext(name = "GrupoC-T3-ejbPU")
    private EntityManager em;

    @Resource
    private SessionContext sessionContext;

    @Override
    public List<Evento> listarEventos() {
        TypedQuery<Evento> query = em.createNamedQuery("lista.eventos", Evento.class);
        return query.getResultList();
    }

    @Override
    public List<Evento> filtrarEventos(String ubicacion, Date fecha, String categoria) {

        Query query = null;
        Query query2 = null;
        Query query3 = null;

        if (!ubicacion.isEmpty()) {
            query = em.createQuery("select e from Evento e where e.ubicacion like "+ "concat('%', concat(:Ubicacion))");
            query.setParameter("Ubicacion",ubicacion);
        }

        List<Evento> even = new ArrayList();
        even = query.getResultList();

        if (!categoria.isEmpty()) {
            query2 = em.createQuery("SELECT e FROM Evento e WHERE e.categoria = :Categoria");
            query2.setParameter("Categoria", categoria);
        }
        List<Evento> even2 = new ArrayList();
        even2 = query2.getResultList();

        
          List<Evento> newList=null;
        if(even.isEmpty() || even2.isEmpty()){
           newList = ListUtils.sum(even, even2);
        }else{
            newList = ListUtils.intersection(even, even2);
        }
        
        boolean bueno = false;
        if ((fecha != null)) {
            query3 = em.createQuery("SELECT e FROM Evento e WHERE e.fecha_inicio = :Fecha");
            query3.setParameter("Fecha", fecha);
            bueno = true;
        }
        List<Evento> even3 = new ArrayList();
        
        
        if(bueno){
              even3 = query3.getResultList();
        }
        
           List<Evento> newList2=null;
        
        if(even3.isEmpty() || newList.isEmpty())
            newList2 = ListUtils.sum(newList, even3);
        else{
            newList2 = ListUtils.intersection(newList, even3);
        }
        return newList2;

    }

    @Override
    public List<Valoracion> listarValoraciones() {
        TypedQuery<Valoracion> query = em.createNamedQuery("lista.Valoracion", Valoracion.class);
        return query.getResultList();
    }

    @Override
    public void insertarValoracion(Valoracion var) {
        UserTransaction userTxn = sessionContext.getUserTransaction();

        try {
            userTxn.begin();
            em.persist(var);
            userTxn.commit();

        } catch (Throwable e) {
            try {
                userTxn.rollback(); //-- Include this in try-catch 
            } catch (IllegalStateException ex) {
                Logger.getLogger(NegocioSteven.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(NegocioSteven.class.getName()).log(Level.SEVERE, null, ex);

            } catch (SystemException ex) {
                Logger.getLogger(NegocioSteven.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
