/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author antiapgg
 */

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int idUsuario;
    
    @Column (name="User")
    private String User;
    
    @Column (name="Password")
    private String Password;
    
    @Column (name="Correo")
    private String Correo;

    @JoinColumn (name="rolesUsuario_idRolUs")
    @ManyToOne
    private RolesUsuario roles;
    
    @JoinColumn (name="persona_idPersona")
    @OneToOne (cascade=CascadeType.PERSIST)
    private Persona persona;
         
    public Usuario(){
        
    }
    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public RolesUsuario getRoles() {
        return roles;
    }

    public void setRoles(RolesUsuario roles) {
        this.roles = roles;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
             
    
    /*************************
     *   HASHCODE & EQUALS   *
     *************************/
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idUsuario;
        hash = 37 * hash + Objects.hashCode(this.User);
        hash = 37 * hash + Objects.hashCode(this.Password);
        hash = 37 * hash + Objects.hashCode(this.Correo);
        hash = 37 * hash + Objects.hashCode(this.roles);
        hash = 37 * hash + Objects.hashCode(this.persona);
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
        final Usuario other = (Usuario) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (!Objects.equals(this.User, other.User)) {
            return false;
        }
        if (!Objects.equals(this.Password, other.Password)) {
            return false;
        }
        if (!Objects.equals(this.Correo, other.Correo)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        if (!Objects.equals(this.persona, other.persona)) {
            return false;
        }
        return true;
    }
    
    
}
