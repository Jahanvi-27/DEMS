/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.ActivityLogs;
import Entity.LoginHistory;
import Entity.Users;
import facades.ActivityLogFacade;
import facades.LoginHistoryFacade;
import facades.UserFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 *
 * @author Dhwani
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
     private String email;
    private String password;
    private Users loggedUser;

    public Users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }

    @EJB
    private UserFacade userFacade;
    
    @EJB
    private LoginHistoryFacade loginHistoryFacade;
    
    @EJB
    private ActivityLogFacade activityLogFacade;

    public String login() {

    Users user = userFacade.login(email);

    if (user != null &&
        user.getPasswordHash().equals(password)) {

        loggedUser = user;

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .put("user", user);

        // =========================
        // 🔥 ADD LOGIN HISTORY HERE
        // =========================
        LoginHistory lh = new LoginHistory();

        lh.setUserId(user);
        lh.setLoginTime(new java.util.Date());

        // ✅ IP ADDRESS FIX
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();

        lh.setIpAddress(request.getRemoteAddr());

        // Device info
        lh.setDeviceInfo(
            FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestHeaderMap()
                .get("User-Agent")
        );

        loginHistoryFacade.create(lh);
        
        // =========================
        // ACTIVITY LOG ENTRY
        // =========================

        ActivityLogs log = new ActivityLogs();
        log.setUserId(user);
        log.setActivityType("LOGIN");
        log.setActivityDescription("User logged in successfully");
        log.setModuleName("AUTH");
        log.setActivityTime(new java.util.Date());

        

        

        log.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(log);

        return "dashboard.xhtml?faces-redirect=true";
    }

    return null;
}
    
    public String logout() {

    Users user = (Users) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSessionMap()
            .get("user");

    if (user != null) {
        
        // =========================
        // ACTIVITY LOG - LOGOUT
        // =========================

        ActivityLogs log = new ActivityLogs();
        log.setUserId(user);
        log.setActivityType("LOGOUT");
        log.setActivityDescription("User logged out");
        log.setModuleName("AUTH");
        log.setActivityTime(new java.util.Date());

        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();

        log.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(log);

        LoginHistory last = loginHistoryFacade.findLastLogin(user.getUserId());

        if (last != null) {
            last.setLogoutTime(new java.util.Date());
            loginHistoryFacade.edit(last);
        }
    }

    loggedUser = null;
    
    

    FacesContext.getCurrentInstance()
            .getExternalContext()
            .invalidateSession();

    return "login.xhtml?faces-redirect=true";
}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
}
