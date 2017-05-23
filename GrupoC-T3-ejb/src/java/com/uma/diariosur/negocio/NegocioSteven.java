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
    
    
    @Override
    public List<Evento> listarEventosFiltrados(String busqueda){
       // TypedQuery<Evento> query = null;
        ResultSet query;
        ResultSetMetaData mrs;
        List<Evento> eventos = new ArrayList<Evento>();
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "123456");
            Statement comando = c.createStatement();
           
           
            
            //query = em.createNamedQuery("listaEventosFiltrados.Evento", Evento.class);
            query = comando.executeQuery("SELECT e FROM Evento e WHERE e.ubicacion = 'Estadio la Rosaleda,Malaga' ");
          
          
            while(query.next()){
                Imagen i8 = new Imagen();
                i8.setEnlace("Melendi.jpg");
                Evento e4 = new Evento();
                e4.setNombre(query.getString(2));
                e4.setCategoria(query.getString(4));
                e4.setDescripcion(query.getString(3));
                e4.setFecha_inicio(query.getDate(5));
                e4.setFecha_final(query.getDate(6));
                e4.setPeriodista_DNI(query.getString(8));
                e4.setPrecio(query.getDouble(7));
                e4.setUbicacion(query.getString(10));
                e4.setImagen(i8);
                
                eventos.add(e4);
            }
            query.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(NegocioSteven.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         // return query.getResultList();
         return eventos;
    }
}
