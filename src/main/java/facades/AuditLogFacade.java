/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.AuditLogs;
import Entity.Users;
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
public class AuditLogFacade {

    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public void save(AuditLogs log){
        em.persist(log);
    }
    
     public List<AuditLogs> findAll() {
    return em.createQuery(
        "SELECT a FROM AuditLogs a ORDER BY a.timestamp DESC",
        AuditLogs.class
    ).getResultList();
}

    public List<AuditLogs> findByUser(Users user) {
    return em.createQuery(
        "SELECT a FROM AuditLogs a WHERE a.userId = :u",
        AuditLogs.class
    )
    .setParameter("u", user)
    .getResultList();
}
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
