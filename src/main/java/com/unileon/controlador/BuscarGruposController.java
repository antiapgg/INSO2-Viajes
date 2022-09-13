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
import com.unileon.modelo.Zona;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
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
public class BuscarGruposController implements Serializable {
    
    @EJB
    private DestinoFacadeLocal destinosEJB;
    private List<Destino> listaDestinos;
    
    @EJB
    private GruposFacadeLocal gruposEJB;
    private Grupos grupo;
    
    private Destino destino;
    private String url;
    
    @PostConstruct
    void init(){
       
        System.out.println("ENTRO EN BUSCAR GRUPOS CONTROLLER");
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        
        if(params.size() > 0){
            Integer grupoId = new Integer(params.get("grupoId"));
            System.out.println(grupoId);
            grupo = gruposEJB.find(grupoId);
            
            listaDestinos = showDestinos(grupo);
        }
        
    }
    
    public void buscar(Destino destino){
        //TODO Buscar viajes relacionados con el grupo indicado y mostrarlos
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
    
    public List<Destino> showDestinos(Grupos grupo){
        listaDestinos = destinosEJB.consultarGrupos(grupo);
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
