/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.AuditLogs;
import Entity.Cases;
import Entity.Users;
import facades.AuditLogFacade;
import facades.CaseFacade;
import facades.UserFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dhwani
 */
@Named(value = "caseBean")
@SessionScoped
public class CaseBean implements Serializable {
    
     @EJB
    private CaseFacade caseFacade;
     
     @EJB
private UserFacade userFacade;
     
     @EJB
private AuditLogFacade auditLogFacade;
     

    private Cases currentCase = new Cases();

    public Cases getCurrentCase() {
        return currentCase;
    }

    public void setCurrentCase(Cases currentCase) {
        this.currentCase = currentCase;
    }

    public List<Cases> getCases() {
        return caseFacade.findAll();
        
        
    }
    
    private Cases selectedCase;

public Cases getSelectedCase() {
    return selectedCase;
}

public void setSelectedCase(Cases selectedCase) {
    this.selectedCase = selectedCase;
}

public String editCase(Cases c){

    selectedCase = c;
    
    

    return "editCase.xhtml?faces-redirect=true";
}

public String updateCase() {

    try {

        caseFacade.edit(selectedCase);

        Users adminUser = userFacade.find(1);

        AuditLogs log = new AuditLogs();

        log.setUserId(adminUser);

        log.setActionType("UPDATE_CASE");

        log.setDescription(
                "Updated Case : " +
                selectedCase.getCaseNumber()
        );

        auditLogFacade.save(log);

        return "cases.xhtml?faces-redirect=true";

    } catch (Exception e) {

        e.printStackTrace();

        return null;
    }
}

    public void deleteCase(Cases c){

        Users adminUser = userFacade.find(1);

        AuditLogs log = new AuditLogs();

        log.setUserId(adminUser);

        log.setActionType("DELETE_CASE");

        log.setDescription(
            "Deleted Case : " +
            c.getCaseNumber()
        );

        auditLogFacade.save(log);

        caseFacade.remove(c);
    }

    public String saveCase() {

    try {

        currentCase.setStatus("Open");

        Users adminUser = userFacade.find(1);

        currentCase.setCreatedBy(adminUser);

        caseFacade.save(currentCase);
        
        AuditLogs log = new AuditLogs();

        log.setUserId(adminUser);

        log.setActionType("CREATE_CASE");

        log.setDescription(
            "Created Case : " +
            currentCase.getCaseNumber()
        );

        auditLogFacade.save(log);

        currentCase = new Cases();

        return "cases.xhtml?faces-redirect=true";

    } catch(Exception e) {

        e.printStackTrace();

        return null;
    }
}

    /**
     * Creates a new instance of CaseBean
     */
    public CaseBean() {
    }
    
}
