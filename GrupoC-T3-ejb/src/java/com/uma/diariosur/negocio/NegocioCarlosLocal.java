/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Formulario;
import com.uma.diariosur.entidades.Imagen;
import com.uma.diariosur.entidades.Periodista;
import com.uma.diariosur.entidades.Usuario;
import com.uma.diariosur.entidades.Valoracion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Carlos
 */
@Local
public interface NegocioCarlosLocal {
    
    List<Formulario> listarFormulario();
    List<Evento> listarEvento();
    List<Usuario> listarUsuario();
    void rechazarFormulario(Formulario f);
    void validarFormulario(Integer id, Periodista periodista);
    void crearFormulario(Formulario f);
    void crearImagen(Imagen img);
    void crearUsuario(Usuario u);
    void crearEvento(Evento f);
    void actualizarUsuario(Usuario u);
    void eliminarEvento(Evento e);
    void eliminarValoracion(Valoracion v);
}
