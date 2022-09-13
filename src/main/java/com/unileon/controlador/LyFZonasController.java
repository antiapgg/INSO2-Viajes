/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.GruposFacadeLocal;
import com.unileon.EJB.TipoViajeFacadeLocal;
import com.unileon.EJB.ZonaFacadeLocal;
import com.unileon.modelo.Grupos;
import com.unileon.modelo.TipoViaje;
import com.unileon.modelo.Zona;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author antiapgg
 */
@Named
@ViewScoped
public class LyFZonasController implements Serializable {
    
    @EJB
    private ZonaFacadeLocal zonasEJB;
    private List<Zona> listaZonas;
    
    private Zona zonaSel;
    private String url;
    
    @PostConstruct
    void init(){
        System.out.println("ENTRO EN LYF ZONAS CONTROLLER");
        zonaSel = new Zona();
        listaZonas = showTipos();
    }
    
    public void buscar(Zona zonas){
        //TODO Buscar viajes relacionados con el grupo indicado y mostrarlos
       ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(zonas == null){
                ec.redirect("LyFZonas.softwareII.xhtml" + "?faces-redirect=true");
            } else {
                System.out.println("\n\nZONA: " + zonas.getNombre());
                ec.redirect("ListarZ.softwareII.xhtml?zonaId=" + zonas.getIdZona());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Zona> showTipos(){
        listaZonas = zonasEJB.findAll();
        return listaZonas;
    }

    public ZonaFacadeLocal getZonasEJB() {
        return zonasEJB;
    }

    public void setZonasEJB(ZonaFacadeLocal zonasEJB) {
        this.zonasEJB = zonasEJB;
    }

    public List<Zona> getListaZonas() {
        return listaZonas;
    }

    public void setListaZonas(List<Zona> listaZonas) {
        this.listaZonas = listaZonas;
    }

    public Zona getZona() {
        return zonaSel;
    }

    public void setZona(Zona zona) {
        this.zonaSel = zona;
    }

    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   


    
    
}
