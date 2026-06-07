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

    public void save(Evidence e) {
        em.persist(e);
    }

    public void edit(Evidence e) {
        em.merge(e);
    }

    public void remove(Evidence e) {
        em.remove(em.merge(e));
    }

    public Evidence find(Integer id) {
        return em.find(Evidence.class, id);
    }

    public List<Evidence> findAll() {
        return em.createNamedQuery("Evidence.findAll", Evidence.class)
                .getResultList();
    }
    
    public List<Evidence> findByCase(Integer caseId) {
    return em.createQuery(
        "SELECT e FROM Evidence e WHERE e.caseId.caseId = :cid",
        Evidence.class
    )
    .setParameter("cid", caseId)
    .getResultList();
}
    
    public Evidence findByEvidenceCode(String code) {
    try {
        return em.createQuery(
            "SELECT e FROM Evidence e WHERE e.evidenceCode = :code",
            Evidence.class
        )
        .setParameter("code", code)
        .getSingleResult();

    } catch (Exception e) {
        return null;
    }
}
    
    public void flush() {
    em.flush();
}
    
    public long countAll() {
    return em.createQuery("SELECT COUNT(e) FROM Evidence e", Long.class)
            .getSingleResult();
}

public long countByStatus(String status) {
    return em.createQuery("SELECT COUNT(e) FROM Evidence e WHERE e.status = :status", Long.class)
            .setParameter("status", status)
            .getSingleResult();
}
    
    public List<Object[]> evidenceByType() {
    return em.createQuery(
        "SELECT e.fileType, COUNT(e) FROM Evidence e GROUP BY e.fileType"
    ).getResultList();
}
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
