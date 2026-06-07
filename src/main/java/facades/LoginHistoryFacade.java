/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

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
public class LoginHistoryFacade {

    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    // =========================
    // CREATE
    // =========================
    public void create(LoginHistory entity) {
        em.persist(entity);
    }

    // =========================
    // UPDATE
    // =========================
    public void edit(LoginHistory entity) {
        em.merge(entity);
    }

    // =========================
    // DELETE
    // =========================
    public void remove(LoginHistory entity) {
        em.remove(em.merge(entity));
    }

    // =========================
    // FIND BY ID
    // =========================
    public LoginHistory find(Object id) {
        return em.find(LoginHistory.class, id);
    }

    // =========================
    // FIND ALL
    // =========================
    public List<LoginHistory> findAll() {
        return em.createQuery(
                "SELECT l FROM LoginHistory l",
                LoginHistory.class
        ).getResultList();
    }

    // =========================
    // GET LAST LOGIN (optional)
    // =========================
    public LoginHistory findLastLogin(Integer userId) {
        List<LoginHistory> list = em.createQuery(
                "SELECT l FROM LoginHistory l WHERE l.userId.userId = :uid ORDER BY l.loginTime DESC",
                LoginHistory.class
        )
        .setParameter("uid", userId)
        .setMaxResults(1)
        .getResultList();

        return list.isEmpty() ? null : list.get(0);
    }
    
    public List<Object[]> loginCountByUser() {
    return em.createQuery(
        "SELECT l.userId.fullName, COUNT(l) FROM LoginHistory l GROUP BY l.userId"
    ).getResultList();
}
}