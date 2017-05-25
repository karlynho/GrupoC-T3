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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@NamedQueries({
    
    @NamedQuery(name="lista.usuarios",
                query="SELECT u FROM Usuario u "),
    
        
})
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String nick;
    private String nombre;
    private String apellidos;
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_nacimiento;
    private String password;
    

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "usuario")
    private List<Valoracion> valoraciones;
    
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "usuario", orphanRemoval = true)
    private List<Formulario> formulario;
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "usuario")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Megusta> megusta;


    public Usuario() {
        
    }


    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public List<Formulario> getFormulario() {
        return formulario;
    }

    public void setFormulario(List<Formulario> formulario) {
        this.formulario = formulario;
    }

    public List<Megusta> getMegusta() {
        return megusta;
    }

    public void setMegusta(List<Megusta> megusta) {
        this.megusta = megusta;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getNick(){
        return nick;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getPassword() {
        return password;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nick != null ? nick.hashCode() : 0);
        return hash;
    }

    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.nick == null && other.nick != null) || (this.nick != null && !this.nick.equals(other.nick))) {
            return false;
        }
        return true;
    }

    
    public Usuario(String nombre, String apellidos, String nick, String email, String contrasenia){
        this.apellidos=apellidos;
        this.nick=nick;
        this.nombre=nombre;
        this.email=email;
        this.password=contrasenia;
        
    }
    
    @Override
    public String toString() {

        return nick;

    }
    
    public Usuario(String nick, String nombre, String apellidos, String email, Date fecha_nacimiento, String password){
        
        this.nick = nick;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
        this.password = password;
        
    }
    
    
}
