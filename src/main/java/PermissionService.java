/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import Entity.Users;
import facades.UserFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author Dhwani
 */
@Named(value = "permissionService")
@ApplicationScoped
public class PermissionService {
    
    @EJB
    private UserFacade userFacade;

    public boolean hasRole(String roleName) {

        Users user = SessionUtil.getUser();

        if (user == null || user.getRoleId() == null) {
            return false;
        }

        return roleName.equalsIgnoreCase(
                user.getRoleId().getRoleName()
        );
    }

    /**
     * Creates a new instance of PermissionService
     */
    public PermissionService() {
    }
    
}
