/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

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
public class RolePermissionFacade {
    
    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    
    public boolean hasPermission(Integer roleId, String permissionName) {

        Long count = em.createQuery(
                "SELECT COUNT(rp) FROM RolePermissions rp " +
                "WHERE rp.roleId.roleId = :roleId " +
                "AND rp.permissionId.permissionName = :perm",
                Long.class
        )
        .setParameter("roleId", roleId)
        .setParameter("perm", permissionName)
        .getSingleResult();

        return count > 0;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
