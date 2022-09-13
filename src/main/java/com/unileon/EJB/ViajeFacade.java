/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Usuario;
import com.unileon.modelo.Venta;
import com.unileon.modelo.Viaje;
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
public class ViajeFacade extends AbstractFacade<Viaje> implements ViajeFacadeLocal {

    @PersistenceContext(unitName = "viajesVI")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViajeFacade() {
        super(Viaje.class);
    }

    @Override
    public List<Viaje> consultarViajesUs() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
/*        
        String consulta = "FROM Venta v WHERE v.Usuario=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", usuario);
        System.out.println("QUERY " + query);
        List<Venta> resultadoVenta = query.getResultList(); */

        String consulta2 = "FROM Viaje v WHERE v.Persona=:param1 and v.vendido=:param2";
        Query query2 = em.createQuery(consulta2);
        query2.setParameter("param1", usuario.getPersona());
        query2.setParameter("param2", 0);
        
        List<Viaje> resultado = query2.getResultList();
        /*    for(int i = 0; i < resultado.size(); i++){
                for(int j = 0; j < resultadoVenta.size(); j++){
                    if(resultadoVenta.get(j).getViaje().getIdViajes() == resultado.get(i).getIdViajes()){
                        resultado.remove(resultadoVenta.get(i));
                        j = resultadoVenta.size();
                    }
                }
            }
        System.out.println(resultado);*/
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
        return resultado;
    }
    
}
