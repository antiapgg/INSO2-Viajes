/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.GruposFacadeLocal;
import com.unileon.EJB.TipoViajeFacadeLocal;
import com.unileon.modelo.Grupos;
import com.unileon.modelo.TipoViaje;
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
public class LyFTiposController implements Serializable {
    
    @EJB
    private TipoViajeFacadeLocal tiposEJB;
    private List<TipoViaje> listaTipos;
    
    private TipoViaje tipoSel;
    private String url;
    
    @PostConstruct
    void init(){
        System.out.println("ENTRO EN LYFTIPOSCONTROLLER");
        tipoSel = new TipoViaje();
        listaTipos = showTipos();
    }
    
    public void buscar(TipoViaje tipos){
        //TODO Buscar viajes relacionados con el grupo indicado y mostrarlos
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(tipos == null){
                ec.redirect("LyFTipos.softwareII.xhtml" + "?faces-redirect=true");
            } else {
                System.out.println("\n\nTIPO: " + tipos.getNombre());
                ec.redirect("ListarT.softwareII.xhtml?tipoId=" + tipos.getIdTipoViaje());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<TipoViaje> showTipos(){
        listaTipos = tiposEJB.findAll();
        return listaTipos;
    }

    public TipoViajeFacadeLocal getTiposEJB() {
        return tiposEJB;
    }

    public void setTiposEJB(TipoViajeFacadeLocal tiposEJB) {
        this.tiposEJB = tiposEJB;
    }

    public List<TipoViaje> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(List<TipoViaje> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public TipoViaje getTipo() {
        return tipoSel;
    }

    public void setTipo(TipoViaje tipo) {
        this.tipoSel = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   


    
    
}
