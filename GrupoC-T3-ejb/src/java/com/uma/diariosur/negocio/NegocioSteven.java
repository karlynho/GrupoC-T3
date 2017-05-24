/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Imagen;
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
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Steven
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class NegocioSteven implements NegocioStevenLocal {


    @PersistenceContext(name = "GrupoC-T3-ejbPU")
    private  EntityManager em;
    
    
    
    
    @Override
    public List<Evento> listarEventos(){
         TypedQuery<Evento> query=em.createNamedQuery("lista.Eventos", Evento.class);
        return query.getResultList();
    }
    
    
    
}
