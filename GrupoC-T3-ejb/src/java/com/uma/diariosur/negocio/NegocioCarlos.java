/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.diariosur.negocio;

import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Formulario;
import com.uma.diariosur.entidades.Imagen;
import com.uma.diariosur.entidades.Megusta;
import com.uma.diariosur.entidades.Periodista;
import com.uma.diariosur.entidades.Usuario;
import com.uma.diariosur.entidades.Valoracion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
@Stateless
public class NegocioCarlos implements NegocioCarlosLocal {

    @PersistenceContext(name = "GrupoC-T3-ejbPU")
    private EntityManager em;
    List<Formulario> formularios;
    
    @Override
    public List<Formulario> listarFormulario() {
        formularios = new ArrayList<>();
        
        Query q = em.createQuery("SELECT f from Formulario f");
        formularios = q.getResultList();
        
        return formularios;
    }

    @Override
    public void crearFormulario(String nombre, String descripcion, String categoria, String ubicacion, Double precio, Date fecha_ini, Date fecha_fin, Imagen img, Usuario u) {
        
        Formulario form = new Formulario();
        form.setNombre(nombre);
        form.setDescripcion(descripcion);
        form.setCategoria(categoria);
        form.setPrecio(precio);
        form.setFecha_inicio(fecha_ini);
        form.setFecha_fin(fecha_fin);
        form.setImg(img);
        form.setUsuario(u);
        form.setFecha_subida(new Date());
        form.setEstado("pendiente");
       
        em.persist(form);
        
        img.setF(form);
        em.merge(img);
        
    }

    @Override
    public void rechazarFormulario(Integer id) {
        Formulario f = em.find(Formulario.class, id);
        if (f==null){
            System.out.println("Ese formulario no esta en la BD");
        }
        else{
            Imagen img = em.find(Imagen.class, f.getImg().getId());
            em.remove(f);
            em.remove(img);
        }
    }

    @Override
    public void validarFormulario(Integer id, Periodista periodista) {
        Formulario f = em.find(Formulario.class, id);
        if (f==null){
            System.out.println("Ese formulario no esta en la BD");
        }
        else{
            Evento e = new Evento();
            e.setNombre(f.getNombre());
            e.setDescripcion(f.getDescripcion());
            e.setCategoria(f.getCategoria());
            e.setPrecio(f.getPrecio());
            e.setUbicacion(f.getUbicacion());
            e.setFecha_inicio(f.getFecha_inicio());
            e.setFecha_final(f.getFecha_subida());
            e.setImagen(f.getImg());
            e.setPeriodista(periodista);
            List<Valoracion> v_vacia = new ArrayList();
            e.setValoraciones(v_vacia);
            List<Megusta> m_gusta = new ArrayList();
            e.setMeGusta(m_gusta);
            em.persist(e);
           
            Imagen img = em.find(Imagen.class, f.getImg().getId());
            img.setEvento(e);
            em.merge(img);
        }
        
    }

    @Override
    public void crearImagen(String enlace, String tipo) {
        Imagen img = new Imagen();
        img.setTipo(tipo);
        img.setEnlace(enlace);
        
        em.persist(img);
        
    }
}
