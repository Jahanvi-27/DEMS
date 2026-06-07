/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.ActivityLogs;
import Entity.Roles;
import Entity.Users;
import facades.ActivityLogFacade;
import facades.RoleFacade;
import facades.UserFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dhwani
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    
     @EJB
    private UserFacade userFacade;

    @EJB
    private RoleFacade roleFacade;
    
    @EJB private ActivityLogFacade activityLogFacade;

    private Users user = new Users();
    
    private Integer selectedRoleId;
    
    

public Integer getSelectedRoleId() {
    return selectedRoleId;
}

public void setSelectedRoleId(Integer selectedRoleId) {
    this.selectedRoleId = selectedRoleId;
}

    public List<Users> getUsers() {

        return userFacade.findAllUsers();

    }
    
    

    public List<Roles> getRoles() {

        return roleFacade.findAllRoles();

    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String saveUser() {

    Roles role = roleFacade.find(selectedRoleId);

    user.setRoleId(role);
    if(user.getStatus() == null){
        user.setStatus("Active");
    }

    userFacade.save(user);
    
    ActivityLogs l = new ActivityLogs();
        l.setUserId(user);
        l.setActivityType("CREATE_USER");
        l.setActivityDescription("User " + user.getFullName());
            l.setModuleName("USER");
        l.setActivityTime(new java.util.Date());
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        l.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(l);

    user = new Users();

    return "users.xhtml?faces-redirect=true";
}
    
    public String editUser(Users selected){

    this.user = selected;

    return "editUser.xhtml";

}

public String updateUser(){

    userFacade.update(user);
    
    ActivityLogs l = new ActivityLogs();
        l.setUserId(user);
        l.setActivityType("UPDATE_USER");
        l.setActivityDescription("User : " + user.getFullName());
            l.setModuleName("USER");
        l.setActivityTime(new java.util.Date());
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        l.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(l);

    return "users.xhtml?faces-redirect=true";

}

public void deleteUser(Users user){

    userFacade.delete(user);
    
    ActivityLogs l = new ActivityLogs();
        l.setUserId(user);
        l.setActivityType("DELETE_USER");
        l.setActivityDescription("User : " + user.getFullName());
            l.setModuleName("USER");
        l.setActivityTime(new java.util.Date());
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        l.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(l);

}


    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    
}
