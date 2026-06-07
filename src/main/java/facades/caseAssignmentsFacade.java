/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.CaseAssignments;
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
public class caseAssignmentsFacade {
    
    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public void save(CaseAssignments e) {
        em.persist(e);
    }

    public CaseAssignments find(int id) {
        return em.find(CaseAssignments.class, id);
    }

    public List<CaseAssignments> findAll() {
        return em.createQuery("SELECT c FROM CaseAssignments c", CaseAssignments.class)
                .getResultList();
    }

    public List<CaseAssignments> findByUser(int userId) {
        return em.createQuery(
                "SELECT c FROM CaseAssignments c WHERE c.userId.userId = :uid",
                CaseAssignments.class)
                .setParameter("uid", userId)
                .getResultList();
    }
    
    public CaseAssignments findByCaseId(Integer caseId) {
    try {
        return em.createQuery(
                "SELECT c FROM CaseAssignments c WHERE c.caseId.caseId = :caseId ORDER BY c.assignedDate DESC",
                CaseAssignments.class)
                .setParameter("caseId", caseId)
                .setMaxResults(1)
                .getSingleResult();
    } catch (Exception e) {
        return null;
    }
}
    
    public List<CaseAssignments> findAllByCaseId(Integer caseId) {
    return em.createQuery(
        "SELECT c FROM CaseAssignments c WHERE c.caseId.caseId = :id ORDER BY c.assignedDate DESC",
        CaseAssignments.class
    )
    .setParameter("id", caseId)
    .getResultList();
}

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
