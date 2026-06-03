/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.Users;
import facades.UserFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
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

    public String login() {

        Users user = userFacade.login(email);

    if(user != null &&
       user.getPasswordHash().equals(password))
    {
        loggedUser = user;
        
        FacesContext.getCurrentInstance()
        .getExternalContext()
        .getSessionMap()
        .put("user", user);
        

        return "dashboard.xhtml?faces-redirect=true";
    }

    return null;
    }
    
    public String logout() {

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
