/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Dhwani
 */
@Named(value = "dashboardBean")
@RequestScoped
public class DashboardBean {
    
    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    private Long totalUsers;
    private Long totalCases;
    private Long totalEvidence;
    private Long openCases;
    private Long closedCases;

    @PostConstruct
    public void init() {

        totalUsers =
        (Long) em.createQuery(
        "SELECT COUNT(u) FROM Users u")
        .getSingleResult();

        totalCases =
        (Long) em.createQuery(
        "SELECT COUNT(c) FROM Cases c")
        .getSingleResult();

        totalEvidence =
        (Long) em.createQuery(
        "SELECT COUNT(e) FROM Evidence e")
        .getSingleResult();

        openCases =
        (Long) em.createQuery(
        "SELECT COUNT(c) FROM Cases c WHERE c.status='Open'")
        .getSingleResult();

        closedCases =
        (Long) em.createQuery(
        "SELECT COUNT(c) FROM Cases c WHERE c.status='Closed'")
        .getSingleResult();
    }

    public Long getTotalUsers() { return totalUsers; }
    public Long getTotalCases() { return totalCases; }
    public Long getTotalEvidence() { return totalEvidence; }
    public Long getOpenCases() { return openCases; }
    public Long getClosedCases() { return closedCases; }

    /**
     * Creates a new instance of DashboardBean
     */
    public DashboardBean() {
    }
    
}
