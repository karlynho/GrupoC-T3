/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Megusta;
import javax.ejb.Local;

/**
 *
 * @author Carmen
 */
@Local
public interface NegocioCarmenLocal {

    public void crearMegusta(Megusta m);
    
}
