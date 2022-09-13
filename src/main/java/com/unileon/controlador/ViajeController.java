/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.ViajeFacadeLocal;
import com.unileon.modelo.Usuario;
import com.unileon.modelo.Viaje;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author antiapgg
 */


@Named
@ViewScoped

public class ViajeController implements Serializable {
    
    @EJB
    private ViajeFacadeLocal viajeEJB;
    private Viaje viaje;
    
    private Usuario usuario;
    
    @PostConstruct
    void init(){
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        if(params.size() > 0){
            Integer viajeId = new Integer(params.get("viajeId"));
            System.out.println("ID VIAJE " + viajeId);
            viaje = viajeEJB.find(viajeId);
        }
    }
    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public ViajeFacadeLocal getViajeEJB() {
        return viajeEJB;
    }

    public void setViajeEJB(ViajeFacadeLocal viajeEJB) {
        this.viajeEJB = viajeEJB;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }
    
    
    
}
