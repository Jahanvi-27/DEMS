/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.ChainOfCustody;
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
public class ChainOfCustodyFacade {

    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public void save(ChainOfCustody c) {
        em.persist(c);
    }

    public List<ChainOfCustody> findByEvidence(int evidenceId) {
        return em.createQuery(
            "SELECT c FROM ChainOfCustody c WHERE c.evidenceId.evidenceId = :id",
            ChainOfCustody.class
        )
        .setParameter("id", evidenceId)
        .getResultList();
    }
}
