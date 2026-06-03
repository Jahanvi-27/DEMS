/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.EvidenceCategories;
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
public class EvidenceCategoryFacade {
    
     @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public List<EvidenceCategories> findAll(){

        return em.createNamedQuery(
            "EvidenceCategories.findAll",
            EvidenceCategories.class
        ).getResultList();
    }

    public EvidenceCategories find(Integer id){

        return em.find(
            EvidenceCategories.class,
            id
        );
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
