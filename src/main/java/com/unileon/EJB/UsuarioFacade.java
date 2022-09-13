/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "viajesVI")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario consultarUsuario(Usuario us){
        
        Usuario usDevuelto = new Usuario();
        
        String consulta = "FROM Usuario u WHERE u.User=:param1 and u.Password=:param2";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", us.getUser());
        query.setParameter("param2", us.getPassword());
        
        List<Usuario> resultado = query.getResultList();
        
        if(resultado.isEmpty()){
            usDevuelto = null;
        }
        else{
            usDevuelto = resultado.get(0);
        }
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usDevuelto);
        
        return usDevuelto;
    }
    
}
