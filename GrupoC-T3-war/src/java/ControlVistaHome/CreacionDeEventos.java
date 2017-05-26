
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistaHome;

import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.negocio.NegocioStevenLocal;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import javax.inject.Named;

/**
 *
 * @author steven
 */
@Named(value = "creacionDeEventos")
@ViewScoped

public class CreacionDeEventos implements Serializable {

    @Inject
    ControlHome ctrlhome;

    @EJB
    private NegocioStevenLocal ns;

    private List<Evento> eventosFiltrados;

    private String busqueda;

    public List<Evento> getEventosFiltrados() {
        return eventosFiltrados;
    }

    public void setEventosFiltrados(List<Evento> eventosFiltrados) {
        this.eventosFiltrados = eventosFiltrados;
    }

    public ControlHome getCtrlhome() {
        return ctrlhome;
    }

    public void setCtrlhome(ControlHome ctrlhome) {
        this.ctrlhome = ctrlhome;
    }

    public NegocioStevenLocal getNs() {
        return ns;
    }

    public void setNs(NegocioStevenLocal ns) {
        this.ns = ns;
    }
    private Date fechaVacia;

    public Date getFechaVacia() {
        return fechaVacia;
    }

    public void setFechaVacia(Date fechaVacia) {
        this.fechaVacia = fechaVacia;
    }

    public String comprobacion(String evento, String ubicacion, String categoria, Date fecha) throws ParseException {

        eventosFiltrados = new ArrayList();

        eventosFiltrados = ns.filtrarEventos(ubicacion, fecha, categoria);

        if (eventosFiltrados.isEmpty()) {
            ctrlhome.setEventosFiltrados(ctrlhome.getListaEventosVacia());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay coincidencias con el filtro adioss");
            FacesContext.getCurrentInstance().addMessage("controlHome:principal", message);
            return null;
        } else {

            ctrlhome.setEventosFiltrados(eventosFiltrados);

            return "PaginaHome.xhtml";
        }

    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    /**
     * Creates a new instance of CreacionDeEventos
     */
    public CreacionDeEventos() {
    }

}
