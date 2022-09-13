/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.modelo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author antiapgg
 */

@Named
@ViewScoped

public class PlantillaController implements Serializable{
 
   public void verificarYMostrar(){
       try {
           Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
           if (us == null) {
               FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
           }
       } catch (IOException e) {
           //
       }
   } 
}
