/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.DestinoFacadeLocal;
import com.unileon.EJB.GruposFacadeLocal;
import com.unileon.modelo.Destino;
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
public class ListarController implements Serializable {
    
    @EJB
    private DestinoFacadeLocal destinosEJB;
    private List<Destino> listaDestinos;
    
    private Destino destino;
    private String url;
    
    @PostConstruct
    void init(){
       
        System.out.println("ENTRO EN LYFDESTINOSCONTROLLER");
        destino =  new Destino();
        listaDestinos = showDestinos();
    }
    
    public void buscar(Destino destino){
      
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(destino == null){
                ec.redirect("Listar.softwareII.xhtml");
            } else {
                ec.redirect("DestinoSelected.softwareII.xhtml?destinoId=" + destino.getIdDestino());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void editarDestino(Destino destino){
        
         ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(destino == null){
                ec.redirect("ModificarDestino.softwareII.xhtml");
            } else {
                ec.redirect("EditarDestino.softwareII.xhtml?destinoId=" + destino.getIdDestino());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Destino> showDestinos(){
        listaDestinos = destinosEJB.findAll();
        return listaDestinos;
    }

    public DestinoFacadeLocal getDestinosEJB() {
        return destinosEJB;
    }

    public void setDestinosEJB(DestinoFacadeLocal destinosEJB) {
        this.destinosEJB = destinosEJB;
    }

    public List<Destino> getListaDestinos() {
        return listaDestinos;
    }

    public void setListaDestinos(List<Destino> listaDestinos) {
        this.listaDestinos = listaDestinos;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
