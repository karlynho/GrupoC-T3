/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Valoracion;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Steven
 */
@Local
public interface NegocioStevenLocal {
     
    List<Evento> listarEventos();
    List<Valoracion> listarValoraciones();
    void insertarValoracion(Valoracion var);
    List<Evento> filtrarEventos(String ubicacion, Date fecha, String evento);
    
}
