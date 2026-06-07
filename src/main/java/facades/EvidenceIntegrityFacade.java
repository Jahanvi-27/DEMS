/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.EvidenceIntegrityChecks;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Dhwani
 */
@Stateless
public class EvidenceIntegrityFacade {

    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public void save(EvidenceIntegrityChecks e) {
        em.persist(e);
    }
}