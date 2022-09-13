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
@Table(name="grupos")
public class Grupos  implements Serializable {
       
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int idGrupos;
    
    @Column (name="Nombre")
    private String Nombre;
    
    @Column (name="NumParticipantesMin")
    private int NumParticipantesMin;
    
    @Column (name="NumParticipantesMax")
    private int NumParticipantesMax;

    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public int getIdGrupos() {
        return idGrupos;
    }

    public void setIdGrupos(int idGrupos) {
        this.idGrupos = idGrupos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getNumParticipantesMin() {
        return NumParticipantesMin;
    }

    public void setNumParticipantesMin(int NumParticipantesMin) {
        this.NumParticipantesMin = NumParticipantesMin;
    }

    public int getNumParticipantesMax() {
        return NumParticipantesMax;
    }

    public void setNumParticipantesMax(int NumParticipantesMax) {
        this.NumParticipantesMax = NumParticipantesMax;
    }

        
    /*************************
     *   HASHCODE & EQUALS   *
     *************************/
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.idGrupos;
        hash = 11 * hash + Objects.hashCode(this.Nombre);
        hash = 11 * hash + this.NumParticipantesMin;
        hash = 11 * hash + this.NumParticipantesMax;
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
        final Grupos other = (Grupos) obj;
        if (this.idGrupos != other.idGrupos) {
            return false;
        }
        if (this.NumParticipantesMin != other.NumParticipantesMin) {
            return false;
        }
        if (this.NumParticipantesMax != other.NumParticipantesMax) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        return true;
    }
    
}
