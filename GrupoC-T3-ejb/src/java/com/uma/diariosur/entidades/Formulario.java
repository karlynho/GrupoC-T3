/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Carlos
 */
@NamedQueries({
    
    @NamedQuery(name="lista.formularios",
                query="SELECT f FROM Formulario f "),
    
        
})
@Entity
public class Formulario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha_subida;
    private String nombre;
    private String descripcion;
    private String categoria;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha_inicio;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha_fin;
    private Double precio;
    private String ubicacion;
    
    @OneToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="Imagen_ID",referencedColumnName = "id_m")
    private Imagen im_id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Usuario usuario;
    

    public Imagen getIm_id() {
        return im_id;
    }

    public void setIm_id(Imagen im_id) {
        this.im_id = im_id;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    
    public Formulario() {
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Date getFecha_subida() {
        return fecha_subida;
    }

    public void setFecha_subida(Date fecha_subida) {
        this.fecha_subida = fecha_subida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
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
        if (!(object instanceof Formulario)) {
            return false;
        }
        Formulario other = (Formulario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  nombre;
    }
    

}

