/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.LoginHistory;
import facades.LoginHistoryFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dhwani
 */
@Named(value = "loginHistoryBean")
@SessionScoped
public class LoginHistoryBean implements Serializable {
    
    @EJB
    private LoginHistoryFacade loginHistoryFacade;

    public List<LoginHistory> getAllLogs() {
        return loginHistoryFacade.findAll();
    }

    /**
     * Creates a new instance of LoginHistoryBean
     */
    public LoginHistoryBean() {
    }
    
}
