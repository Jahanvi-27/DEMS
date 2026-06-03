/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.Roles;
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
public class RoleFacade {
    
    @PersistenceContext(unitName="mypu")
    private EntityManager em;

    public List<Roles> findAllRoles(){

        return em.createQuery(
        "SELECT r FROM Roles r",
        Roles.class)
        .getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Roles find(Integer id){

    return em.find(Roles.class, id);

}
}
