/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Megusta;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ejb.Stateless;

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
    public void crearMegusta(Megusta m) {
        UserTransaction userTxn = sessionContext.getUserTransaction();

        try {
            userTxn.begin();
            em.persist(m);
            userTxn.commit();

        } catch (Throwable e) {
            try {
                //userTxn.rollback(); //-- Include this in try-catch 
            } catch (IllegalStateException ex) {
                Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(NegocioCarlos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
