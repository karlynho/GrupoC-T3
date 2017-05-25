/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Periodista;
import com.uma.diariosur.entidades.Usuario;
import javax.ejb.Local;

/**
 *
 * @author patati
 */
@Local
public interface NegocioPabloLocal {
    
    public boolean compararContrasenia(String cont,String contraseniaIntroducida);
    public Usuario buscarUsuario(String usuarioIntroducido,String contraseniaIntroducido);
    public Periodista buscarPeriodista(String usuarioIntroducido,String contraseniaIntroducido);

   
    
}
