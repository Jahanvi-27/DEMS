/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.AuditLogs;
import facades.AuditLogFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dhwani
 */
@Named(value = "auditBean")
@SessionScoped
public class AuditBean implements Serializable {
    
    

    @EJB
    private AuditLogFacade auditLogFacade;
    
     @Inject private AccessControlBean accessControlBean;

    public List<AuditLogs> getLogs() {
        
        if (!accessControlBean.hasPermission("VIEW_AUDIT_LOGS")) {
    return null;
}
        return auditLogFacade.findAll();
    }
    /**
     * Creates a new instance of AuditBean
     */
    public AuditBean() {
    }
    
}
