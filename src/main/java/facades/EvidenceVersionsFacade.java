/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.EvidenceVersions;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Dhwani
 */
@Stateless
public class EvidenceVersionsFacade {

    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public void save(EvidenceVersions v) {
        em.persist(v);
    }

    public int getLastVersion(int evidenceId) {
        try {
            Integer v = em.createQuery(
                "SELECT MAX(v.versionNo) FROM EvidenceVersions v WHERE v.evidenceId.evidenceId = :id",
                Integer.class
            )
            .setParameter("id", evidenceId)
            .getSingleResult();

            return v == null ? 0 : v;

        } catch (Exception e) {
            return 0;
        }
    }
}