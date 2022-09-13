/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.GruposFacadeLocal;
import com.unileon.EJB.MenusFacadeLocal;
import com.unileon.EJB.RolesUsuarioFacadeLocal;
import com.unileon.modelo.Grupos;
import com.unileon.modelo.Menus;
import com.unileon.modelo.RolesUsuario;
import com.unileon.modelo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author antiapgg
 */

@Named
@ViewScoped
public class MenusController implements Serializable {
    
    @EJB
    private MenusFacadeLocal menusEJB;
    private MenuModel model;
    
    @EJB
    private RolesUsuarioFacadeLocal rolesEJB;
    
    @PostConstruct
    void init(){
        model = new DefaultMenuModel();
        List<Menus> m = menusEJB.findAll();
        this.obtenerMenus();
    }
    
    public void listarMenus(){
        menusEJB.findAll();
    }
    
    public void obtenerMenus() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        RolesUsuario rol = rolesEJB.find(us.getIdUsuario());
        List<Menus> listamenus = menusEJB.obtenerMenusUsuario(rol);
        String contextURL = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        
        for(Menus men : listamenus){
            if(men.getTipo().equals("S") && men.getIdMenu() != 9){
                if(men.getIdMenu() == 0 || men.getIdMenu() == 13){
                    DefaultMenuItem subb = DefaultMenuItem.builder().value(men.getNombre()).url(men.getUrl()).build();
                    subb.setIcon("pi pi-home");
                    if(us.getIdUsuario()==1){
                        subb.setUrl(contextURL+"/privado/administrador/inicioAdmin.xhtml?faces-redirect=true");
                    }
                    else{
                        subb.setUrl(contextURL+"/privado/comprador/inicioCompr.xhtml?faces-redirect=true");
                        System.out.println(contextURL + subb.getUrl());
                    }
                    this.model.getElements().add(subb);
                }
                else{
                    DefaultSubMenu firstSubMenu = DefaultSubMenu.builder().label(men.getNombre()).build();
                    firstSubMenu.setStyle("mousehover:#CCF575; hover: #CCF575; background-color: #5BDCF3");

                    for(Menus i : listamenus){
                        Menus submenu = i.getIdMenu_Menu();
                        if(submenu != null){
                            if(men.getIdMenu() == submenu.getIdMenu()){
                                DefaultMenuItem item = DefaultMenuItem.builder().value(i.getNombre()).url(i.getUrl()).build();
                                item.setUrl(contextURL+i.getUrl()+".xhtml?faces-redirect=true");
                                item.setStyle("mouseover:#CCF575; background-color: #5BDCF3;");
                                if(i.getIdMenu() == 7 || i.getIdMenu() == 11 || i.getIdMenu() == 24 || i.getIdMenu() == 28 || i.getIdMenu() == 36){
                                    item.setIcon("pi pi-pencil");
                                }
                                else if(i.getIdMenu() == 3 || i.getIdMenu() == 33){
                                    item.setIcon("pi pi-user-edit");
                                }
                                else if(i.getIdMenu() == 2){
                                     item.setIcon("pi pi-user-plus");
                                }
                                else if(i.getIdMenu() == 4 || i.getIdMenu() == 8 || i.getIdMenu() == 12 || i.getIdMenu() == 25 || i.getIdMenu() == 29 || i.getIdMenu() == 37){
                                    item.setIcon("pi pi-trash");
                                }
                                else if(i.getIdMenu() == 6 || i.getIdMenu() == 10 || i.getIdMenu() == 23 || i.getIdMenu() == 27 || i.getIdMenu() == 35){
                                    item.setIcon("pi pi-plus-circle");
                                }
                                else if(i.getIdMenu() == 31){
                                    item.setIcon("pi pi-map");
                                }
                                else if(i.getIdMenu() == 32){
                                    item.setIcon("pi pi-paperclip");
                                }

                                firstSubMenu.getElements().add(item);
                            }
                        }
                    }
                    firstSubMenu.setStyle("mouseover:#CCF575; background-color: #5BDCF3");
                    this.model.getElements().add(firstSubMenu);
                }
            }
            else{
                if(us.getIdUsuario() == 1 && men.getTipo().equals("S") && men.getIdMenu() == 9){
                    DefaultMenuItem item = DefaultMenuItem.builder().value(men.getNombre()).url(men.getUrl()).build();
                    item.setUrl(contextURL+men.getUrl()+".xhtml?faces-redirect=true");
                    model.getElements().add(item);
                }
            }
           /* else {
                if (men. == null  && men.getTipoUsuario().equals(us.getTipo())) {
                DefaultMenuItem item = DefaultMenuItem.builder().value(men.getNombre()).url(men.getUrl()).build();
                item.setUrl(men.getUrl());
                model.getElements().add(item);
            } */
        }
               
    }
    
    public String cerrarSesion() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //FacesContext.getCurrentInstance().getExternalContext().redirect("./../../informacionAplicacion");
        return "./../../index.xhtml";
    }
    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/ 

    public MenusFacadeLocal getMenusEJB() {
        return menusEJB;
    }

    public void setMenusEJB(MenusFacadeLocal menusEJB) {
        this.menusEJB = menusEJB;
    }
    
    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    
}
