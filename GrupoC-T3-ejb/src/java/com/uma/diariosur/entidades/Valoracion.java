/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



@NamedQueries({
     
     @NamedQuery(name="lista.Valoracion",query="SELECT v FROM Valoracion v "),
     
 })




/**
 *
 * @author Carmen
 */
@Entity
public class Valoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String comentario;
    private Integer puntuacion;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private Usuario usuario;
    
    @ManyToOne (cascade = CascadeType.MERGE)
    private Evento evento;

    public Valoracion() {
        
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setComentario(String Comentario) {
        this.comentario = Comentario;
    }

    public void setPuntuacion(Integer Puntuacion) {
        this.puntuacion = Puntuacion;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoracion)) {
            return false;
        }
        Valoracion other = (Valoracion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.diariosur1.Valoracion[ id=" + id + " ]";
    }

    public Valoracion(Integer id, String comentario, Integer puntuacion, Usuario usuario, Evento evento) {
        this.id = id;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.usuario = usuario;
        this.evento = evento;
    }
    
}
