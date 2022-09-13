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
import javax.persistence.Table;

/**
 *
 * @author antiapgg
 */

@Entity
@Table(name="rolesUsuario")
public class RolesUsuario  implements Serializable {
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int idRolUs;
    
    @Column (name="TipoUsuario")
    private String TipoUsuario;

        
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public int getIdRolUs() {
        return idRolUs;
    }

    public void setIdRolUs(int idRolUs) {
        this.idRolUs = idRolUs;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }
         
    
    /*************************
     *   HASHCODE & EQUALS   *
     *************************/
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.idRolUs;
        hash = 97 * hash + Objects.hashCode(this.TipoUsuario);
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
        final RolesUsuario other = (RolesUsuario) obj;
        if (this.idRolUs != other.idRolUs) {
            return false;
        }
        if (!Objects.equals(this.TipoUsuario, other.TipoUsuario)) {
            return false;
        }
        return true;
    }
    
    
    
}
