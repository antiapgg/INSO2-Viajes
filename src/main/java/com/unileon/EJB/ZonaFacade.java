/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.RolesUsuario;
import com.unileon.modelo.Zona;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author antiapgg
 */
@Stateless
public class ZonaFacade extends AbstractFacade<Zona> implements ZonaFacadeLocal {

    @PersistenceContext(unitName = "viajesVI")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZonaFacade() {
        super(Zona.class);
    }
    
    @Override
    public Zona buscarZona(String nom){
        Zona rol = new Zona();
        String consulta;
        try{
            consulta = "FROM Zona r WHERE r.Nombre=:param1";
            Query query = em.createQuery(consulta);
            query.setParameter("param1", nom);
            System.out.println(query);
            List<Zona> resultado = query.getResultList();
            System.out.println(resultado);
            
            if(resultado.isEmpty()){
                rol = null;
            }
            else{
                rol = resultado.get(0);
            }
        
        }catch(Exception e){
            System.out.println("Zona no encontrado en la base de datos. " + e.getMessage());
        }
        return rol;
    }
    
}
