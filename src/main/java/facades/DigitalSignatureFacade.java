/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.DigitalSignatures;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Dhwani
 */
@Stateless
@LocalBean
public class DigitalSignatureFacade {

    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public void save(DigitalSignatures obj) {
        em.persist(obj);
    }

    public void edit(DigitalSignatures obj) {
        em.merge(obj);
    }

    public DigitalSignatures find(Object id) {
        return em.find(DigitalSignatures.class, id);
    }
}