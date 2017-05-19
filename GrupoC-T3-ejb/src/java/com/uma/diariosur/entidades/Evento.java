/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Carmen
 */
@Entity
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String descripcion;
    private String categoria;
    @Temporal(TemporalType.DATE)
    private Date fecha_inicio;
    @Temporal(TemporalType.DATE)
    private Date fecha_final;
    private Double precio;
    private String ubicacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private List<Megusta> meGusta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private List<Valoracion> valoraciones;
    
    
    @OneToOne(optional = false)
    private Imagen imagen;
    
    @ManyToOne
    private Periodista periodista;


    public Evento (){
        
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }
    
    
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_final() {
        return fecha_final;
    }


    public Double getPrecio() {
        return precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public List<Megusta> getMeGusta() {
        return meGusta;
    }

    public void setMeGusta(List<Megusta> meGusta) {
        this.meGusta = meGusta;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }



    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.diariosur1.Evento[ id=" + id + " ]";
    }
    

     public Evento(String nombre, String descripcion, String categoria, Date fecha_inicio, Date fecha_final, Double precio, String ubicacion, Imagen i, Periodista p) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.precio = precio;
        this.ubicacion = ubicacion;

    }
     
    

    
}

