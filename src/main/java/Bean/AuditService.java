/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package Bean;

import Entity.AuditLogs;
import Entity.Users;
import facades.AuditLogFacade;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;

/**
 *
 * @author Dhwani
 */
@Stateless
@LocalBean
public class AuditService {
    
     @EJB
    private AuditLogFacade auditLogFacade;

    public void log(Users user, String action, String description) {

        AuditLogs log = new AuditLogs();

        log.setUserId(user);
        log.setActionType(action);
        log.setDescription(description);
        log.setTimestamp(new java.util.Date());

        auditLogFacade.save(log);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
