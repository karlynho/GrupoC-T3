/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


/**
 *
 * @author Carmen
 */
@Entity
public class Periodista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 
    private String nombre;
    private String apellidos;
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_nacimiento;
    private String password;
    private String ambito;

    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Periodista")
    private List<Evento> eventos;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Periodista")
    private List<Formulario> formulario;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Periodista() {
        
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Formulario> getFormulario() {
        return formulario;
    }

    public void setFormulario(List<Formulario> formulario) {
        this.formulario = formulario;
    }
    
    
     public Periodista (String nombre, String apellidos, Integer id, String email, String contrasenia){
        this.apellidos=apellidos;
        this.id=id;
        this.nombre=nombre;
        this.email=email;
        this.password=contrasenia;
        
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
        if (!(object instanceof Periodista)) {
            return false;
        }
        Periodista other = (Periodista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   
    @Override
    public String toString() {
        return nombre;
    }
    
}
