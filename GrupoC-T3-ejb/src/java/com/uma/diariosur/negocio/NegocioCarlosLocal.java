/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Formulario;
import com.uma.diariosur.entidades.Imagen;
import com.uma.diariosur.entidades.Periodista;
import com.uma.diariosur.entidades.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Carlos
 */
@Local
public interface NegocioCarlosLocal {
    
    List<Formulario> listarFormulario();
    void rechazarFormulario(Integer id);
    void validarFormulario(Integer id, Periodista periodista);
    void crearFormulario(String nombre, String descripcion, String categoria, String ubicacion,
            Double precio, Date fecha_ini, Date fecha_fin, Imagen img, Usuario u);
    void crearImagen(String enlace, String tipo);
    void crearUsuario(Usuario u);
}
