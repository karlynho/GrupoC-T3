/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;


import com.uma.diariosur.entidades.Megusta;
import com.uma.diariosur.entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Carmen
 */
@Local
public interface NegocioCarmenLocal {

    void crearMegusta(Megusta m);
    List<Megusta> listarMegusta();
    void eliminarMegusta(Megusta e);
    void actualizarEmail(Usuario u);
    void actualizarPassword(Usuario u);
    Usuario buscarUsuario(String nick);
    
    
}

