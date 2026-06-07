/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.ActivityLogs;
import Entity.LoginHistory;
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
public class ActivityLogFacade {
    
     @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public void create(ActivityLogs log) {
        em.persist(log);
    }
    
    // =========================
    // FIND ALL
    // =========================
    public List<ActivityLogs> findAll() {
        return em.createQuery(
                "SELECT l FROM ActivityLogs l",
                ActivityLogs.class
        ).getResultList();
    }
    
    public List<Object[]> countActivitiesByType() {
    return em.createQuery(
        "SELECT a.activityType, COUNT(a) FROM ActivityLogs a GROUP BY a.activityType"
    ).getResultList();
}
    
    public List<ActivityLogs> findLatest10() {
    return em.createQuery(
        "SELECT a FROM ActivityLogs a ORDER BY a.activityTime DESC",
        ActivityLogs.class
    ).setMaxResults(10).getResultList();
}
    
    public List<ActivityLogs> findRecent(int limit) {
    return em.createQuery(
        "SELECT a FROM ActivityLogs a ORDER BY a.activityTime DESC",
        ActivityLogs.class)
        .setMaxResults(limit)
        .getResultList();
}

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
