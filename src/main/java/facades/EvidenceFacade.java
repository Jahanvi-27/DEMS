/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.Evidence;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Dhwani
 */
@Stateless
@LocalBean
public class EvidenceFacade {

   
    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public void save(Evidence e){
        em.persist(e);
    }

    public void edit(Evidence e){
        em.merge(e);
    }

    public void remove(Evidence e){
        em.remove(em.merge(e));
    }

    public List<Evidence> findAll(){
        return em.createNamedQuery(
            "Evidence.findAll",
            Evidence.class
        ).getResultList();
    }

    public Evidence find(Integer id){
        return em.find(Evidence.class,id);
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
