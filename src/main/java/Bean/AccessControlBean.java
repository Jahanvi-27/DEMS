/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.Users;
import facades.RolePermissionFacade;
import facades.UserFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;

/**
 *
 * @author Dhwani
 */
@Named(value = "accessControlBean")
@SessionScoped
public class AccessControlBean implements Serializable {

    @EJB
    private RolePermissionFacade rolePermissionFacade;

    public Users getCurrentUser() {
        return (Users) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("user");
    }

    public boolean hasPermission(String permissionName) {

        Users user = getCurrentUser();

        if (user == null || user.getRoleId() == null) {
            return false;
        }

        return rolePermissionFacade.hasPermission(
                user.getRoleId().getRoleId(),
                permissionName
        );
    }
}