/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.GruposFacadeLocal;
import com.unileon.modelo.Grupos;
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
public class LyFGruposController implements Serializable {
    
    @EJB
    private GruposFacadeLocal gruposEJB;
    private List<Grupos> listaGrupos;
    
    private Grupos grupoSel;
    private String url;
    
    @PostConstruct
    void init(){
        System.out.println("ENTRO EN LYF GRUPOS CONTROLLER");
        grupoSel = new Grupos();
        listaGrupos = showGrupos();
    }
    
    public void buscar(Grupos grupo){
        //TODO Buscar viajes relacionados con el grupo indicado y mostrarlos
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(grupo == null){
                ec.redirect("LyFGrupos.softwareII.xhtml" + "?faces-redirect=true");
            } else {
                System.out.println("\n\nGRUPO: " + grupo.getNombre());
                ec.redirect("ListarG.softwareII.xhtml?grupoId=" + grupo.getIdGrupos());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Grupos> showGrupos(){
        listaGrupos = gruposEJB.findAll();
        return listaGrupos;
    }

    public GruposFacadeLocal getGruposEJB() {
        return gruposEJB;
    }

    public void setGruposEJB(GruposFacadeLocal gruposEJB) {
        this.gruposEJB = gruposEJB;
    }

    public List<Grupos> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupos> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public Grupos getGrupo() {
        return grupoSel;
    }

    public void setGrupo(Grupos grupo) {
        this.grupoSel = grupo;
    }


    
    
}
