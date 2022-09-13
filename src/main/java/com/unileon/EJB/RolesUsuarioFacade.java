/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.RolesUsuario;
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
public class RolesUsuarioFacade extends AbstractFacade<RolesUsuario> implements RolesUsuarioFacadeLocal {

    @PersistenceContext(unitName = "viajesVI")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public RolesUsuario buscarRolPorTipoUsuario(String tipo){
        RolesUsuario rol = new RolesUsuario();
        String consulta;
        if(tipo == null){
            tipo = "Comprador";
        }
        try{
            System.out.println(tipo);
            consulta = "FROM RolesUsuario r WHERE r.TipoUsuario=:param1";
            Query query = em.createQuery(consulta);
            query.setParameter("param1", tipo);
            System.out.println(query);
            List<RolesUsuario> resultado = query.getResultList();
            System.out.println(resultado);
            
            if(resultado.isEmpty()){
                rol = null;
            }
            else{
                rol = resultado.get(0);
            }
        
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("RolesUsuario", rol);
        
        }catch(Exception e){
            System.out.println("Tipo de usuario no encontrado en la base de datos. " + e.getMessage());
        }
        return rol;
    }

    public RolesUsuarioFacade() {
        super(RolesUsuario.class);
    }
    
}
