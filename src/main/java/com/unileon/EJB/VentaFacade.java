/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Destino;
import com.unileon.modelo.Usuario;
import com.unileon.modelo.Venta;
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
public class VentaFacade extends AbstractFacade<Venta> implements VentaFacadeLocal {

    @PersistenceContext(unitName = "viajesVI")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }

    @Override
    public List<Venta> consultarVentasUs() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        String consulta = "FROM Venta v WHERE v.Usuario=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", usuario);
        System.out.println("QUERY " + query);
        List<Venta> resultado = query.getResultList();
        System.out.println(resultado);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
        return resultado;
    }
    
}
