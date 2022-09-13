/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author antiapgg
 */
@Entity
@Table(name="menus")
public class Menus  implements Serializable {
       
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int IdMenu;
    
    @Column (name="Nombre")
    private String Nombre;
    
    @Column (name="Tipo")
    private String Tipo;
    
    @Column (name="Estado")
    private int estado;
    
    @Column (name="url")
    private String url;
    
    @JoinColumn (name="IdMenu_Menu")
    @ManyToOne
    private Menus IdMenu_Menu;
    
    @JoinColumn (name="rolesUsuario_idRolUs")
    @ManyToOne
    private RolesUsuario rolesUsuario_idRolUs;

       
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public int getIdMenu() {
        return IdMenu;
    }

    public void setIdMenu(int IdMenu) {
        this.IdMenu = IdMenu;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Menus getIdMenu_Menu() {
        return IdMenu_Menu;
    }

    public void setIdMenu_Menu(Menus IdMenu_Menu) {
        this.IdMenu_Menu = IdMenu_Menu;
    }

    public RolesUsuario getRolesUsuario() {
        return rolesUsuario_idRolUs;
    }

    public void setRolesUsuario(RolesUsuario rolesUsuario) {
        this.rolesUsuario_idRolUs = rolesUsuario;
    }

    
    /*************************
     *   HASHCODE & EQUALS   *
     *************************/
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.IdMenu;
        hash = 59 * hash + Objects.hashCode(this.Nombre);
        hash = 59 * hash + Objects.hashCode(this.Tipo);
        hash = 59 * hash + this.estado;
        hash = 59 * hash + Objects.hashCode(this.url);
        hash = 59 * hash + Objects.hashCode(this.IdMenu_Menu);
        hash = 59 * hash + Objects.hashCode(this.rolesUsuario_idRolUs);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menus other = (Menus) obj;
        if (this.IdMenu != other.IdMenu) {
            return false;
        }
        if (this.Tipo != other.Tipo) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.IdMenu_Menu, other.IdMenu_Menu)) {
            return false;
        }
        if (!Objects.equals(this.rolesUsuario_idRolUs, other.rolesUsuario_idRolUs)) {
            return false;
        }
        return true;
    }
    
    
    
}
