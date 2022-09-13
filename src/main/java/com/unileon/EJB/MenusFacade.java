/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Menus;
import com.unileon.modelo.RolesUsuario;
import com.unileon.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author antiapgg
 */
@Stateless
public class MenusFacade extends AbstractFacade<Menus> implements MenusFacadeLocal {

    @PersistenceContext(unitName = "viajesVI")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenusFacade() {
        super(Menus.class);
    }

    @Override
    public List<Menus> obtenerMenusUsuario(RolesUsuario us) {

        String consulta;
        List<Menus> resultado;
        try{
            
            consulta = "FROM Menus m WHERE m.rolesUsuario_idRolUs=:param2";
            Query query = em.createQuery(consulta);
            query.setParameter("param2", us);
            resultado = query.getResultList();
            return resultado;
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Menus", rol);
        
        }catch(Exception e){
            System.out.println("Tipo de usuario no encontrado en la base de datos. " + e.getMessage());
        }
        return null;
    }
    
}
