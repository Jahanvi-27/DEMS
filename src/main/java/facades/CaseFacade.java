/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.Cases;
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
public class CaseFacade {
    
     @PersistenceContext(unitName="mypu")
    private EntityManager em;

    public void save(Cases c){

        em.persist(c);

    }

    public void edit(Cases c){

        em.merge(c);

    }

    public void delete(Cases c){

        em.remove(em.merge(c));

    }

    public List<Cases> findAll(){

        return em.createQuery(
        "SELECT c FROM Cases c",
        Cases.class)
        .getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void remove(Cases c) {
        em.remove(em.merge(c));
    }

    public Cases find(Integer selectedCaseId) {
        return em.find(Cases.class,selectedCaseId);
    }
    
    public long countAll() {
    return em.createQuery("SELECT COUNT(c) FROM Cases c", Long.class)
            .getSingleResult();
}

public long countByStatus(String status) {
    return em.createQuery("SELECT COUNT(c) FROM Cases c WHERE c.status = :status", Long.class)
            .setParameter("status", status)
            .getSingleResult();
}
    
    
}
