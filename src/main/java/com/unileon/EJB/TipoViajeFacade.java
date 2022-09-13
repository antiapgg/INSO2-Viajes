/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.TipoViaje;
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
public class TipoViajeFacade extends AbstractFacade<TipoViaje> implements TipoViajeFacadeLocal {

    @PersistenceContext(unitName = "viajesVI")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoViajeFacade() {
        super(TipoViaje.class);
    }
    
    @Override
    public TipoViaje buscarTipo(String nom){
        TipoViaje rol = new TipoViaje();
        String consulta;
        try{
            consulta = "FROM TipoViaje r WHERE r.Nombre=:param1";
            Query query = em.createQuery(consulta);
            query.setParameter("param1", nom);
            System.out.println(query);
            List<TipoViaje> resultado = query.getResultList();
            System.out.println(resultado);
            
            if(resultado.isEmpty()){
                rol = null;
            }
            else{
                rol = resultado.get(0);
            }
        
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("TipoViaje", rol);
        
        }catch(Exception e){
            System.out.println("TipoViaje no encontrado en la base de datos. " + e.getMessage());
        }
        return rol;
    }
}
