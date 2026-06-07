/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.ActivityLogs;
import Entity.AuditLogs;
import Entity.CaseAssignments;
import Entity.Cases;
import Entity.ChainOfCustody;
import Entity.Evidence;
import Entity.Users;
import facades.ActivityLogFacade;
import facades.AuditLogFacade;
import facades.CaseFacade;
import facades.ChainOfCustodyFacade;
import facades.EvidenceFacade;
import facades.UserFacade;
import facades.caseAssignmentsFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     
     @EJB private ChainOfCustodyFacade chainOfCustodyFacade;
     
     @EJB
private EvidenceFacade evidenceFacade;
     
     @EJB
private caseAssignmentsFacade caseAssignmentsFacade;
     
     @Inject private AccessControlBean accessControlBean;
     
     @EJB private ActivityLogFacade activityLogFacade;
     

    private Cases currentCase = new Cases();
    
    private Map<Integer, String> assignedUserMap = new HashMap<>();

    public Map<Integer, String> getAssignedUserMap() {
        return assignedUserMap;
    }

    public Cases getCurrentCase() {
        return currentCase;
    }

    public void setCurrentCase(Cases currentCase) {
        this.currentCase = currentCase;
    }
    
    public List<Users> getUsers() {
    return userFacade.findAll();
}

    public List<Cases> getCases() {
        return caseFacade.findAll();
    }
    
    private Integer selectedUserId;

public Integer getSelectedUserId() {
    return selectedUserId;
}

public void setSelectedUserId(Integer selectedUserId) {
    this.selectedUserId = selectedUserId;
}
    
    private Cases selectedCase;

public Cases getSelectedCase() {
    return selectedCase;
}

public void setSelectedCase(Cases selectedCase) {
    this.selectedCase = selectedCase;
}

@PostConstruct
public void init() {
    for (Cases c : caseFacade.findAll()) {
        CaseAssignments ca = caseAssignmentsFacade.findByCaseId(c.getCaseId());

        if (ca != null) {
            assignedUserMap.put(c.getCaseId(),
                    ca.getUserId().getFullName());
        } else {
            assignedUserMap.put(c.getCaseId(), "None");
        }
    }
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
        
        log.setTimestamp(new java.util.Date());

        auditLogFacade.save(log);
        
        //2.1 ACTIVITY LOG
        ActivityLogs l = new ActivityLogs();
        l.setUserId(adminUser);
        l.setActivityType("UPDATE_CASE");
        l.setActivityDescription("Case : " + currentCase.getTitle());
        l.setModuleName("CASE");
        l.setActivityTime(new java.util.Date());
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        l.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(l);

        return "cases.xhtml?faces-redirect=true";

    } catch (Exception e) {

        e.printStackTrace();

        return null;
    }
}

    public void deleteCase(Cases c){
        
        if (!accessControlBean.hasPermission("DELETE_CASE")) {
    throw new RuntimeException("Not allowed");
}

        Users user = (Users) FacesContext.getCurrentInstance()
        .getExternalContext()
        .getSessionMap()
        .get("user");

        AuditLogs log = new AuditLogs();

        log.setUserId(user);

        log.setActionType("DELETE_CASE");

        log.setDescription(
            "Deleted Case : " +
            c.getCaseNumber()
        );
        
        log.setTimestamp(new java.util.Date());

        auditLogFacade.save(log);

        caseFacade.remove(c);
        
        //2.1 ACTIVITY LOG
        ActivityLogs l = new ActivityLogs();
        l.setUserId(user);
        l.setActivityType("DELETE_CASE");
        l.setActivityDescription("Case : " + currentCase.getTitle());
            l.setModuleName("CASE");
        l.setActivityTime(new java.util.Date());
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        l.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(l);
    }

    public String saveCase() {

    try {

        currentCase.setStatus("Open");

        Users user = (Users) FacesContext.getCurrentInstance()
        .getExternalContext()
        .getSessionMap()
        .get("user");

        currentCase.setCreatedBy(user);

        caseFacade.save(currentCase);
        
        AuditLogs log = new AuditLogs();

        log.setUserId(user);

        log.setActionType("CREATE_CASE");

        log.setDescription(
            "Created Case : " +
            currentCase.getCaseNumber()
        );
        
        log.setTimestamp(new java.util.Date());

        auditLogFacade.save(log);
        
        //2.1 ACTIVITY LOG
        ActivityLogs l = new ActivityLogs();
        l.setUserId(user);
        l.setActivityType("CREATE_CASE");
        l.setActivityDescription("Case : " + currentCase.getTitle());
            l.setModuleName("CASE");
        l.setActivityTime(new java.util.Date());
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        l.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(l);

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
    
   public String assignCase(Integer caseId, Integer userId) {

    try {
        
        CaseAssignments existing = caseAssignmentsFacade.findByCaseId(caseId);

        if (existing != null) {
            throw new RuntimeException("Case already assigned!");
}

        Cases c = caseFacade.find(caseId);
        Users u = userFacade.find(userId);
        Users user = (Users) FacesContext.getCurrentInstance()
        .getExternalContext()
        .getSessionMap()
        .get("user");

        if (c == null || u == null || user == null) {
            return null;
        }

        // 1. SAVE CASE ASSIGNMENT
        CaseAssignments ca = new CaseAssignments();
        ca.setCaseId(c);
        ca.setUserId(u);
        ca.setAssignedBy(user);
        c.setStatus("Under Investigation");
        caseFacade.edit(c);
        ca.setAssignedDate(new java.util.Date());
        
        assignedUserMap.put(c.getCaseId(), u.getFullName());

        caseAssignmentsFacade.save(ca);

        // 2. AUDIT LOG
        AuditLogs log = new AuditLogs();
        log.setUserId(user);
        log.setActionType("CASE_ASSIGNMENT");
        log.setDescription("Case " + c.getCaseNumber() + " assigned to " + u.getFullName());
        log.setTimestamp(new java.util.Date());

        auditLogFacade.save(log);
        
        //2.1 ACTIVITY LOG
       

        // 3. AUTO LINK EVIDENCE (IMPORTANT FORENSIC STEP)
        linkExistingEvidenceToUser(c, u);
        
         ActivityLogs l = new ActivityLogs();
        l.setUserId(user);
        l.setActivityType("ASSIGN_CASE");
        l.setActivityDescription("Case : " + c.getTitle() + " Assigned to : " + ca.getUserId().getFullName());
            l.setModuleName("CASE");
        l.setActivityTime(new java.util.Date());
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        l.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(l);
        

        return "cases.xhtml?faces-redirect=true";

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
 
   
  public String assignedUserName(Cases c) {
    CaseAssignments ca = caseAssignmentsFacade.findByCaseId(c.getCaseId());

    if (ca == null || ca.getUserId() == null) {
        return "None";
    }

    return ca.getUserId().getFullName();
}
   
  public void linkExistingEvidenceToUser(Cases c, Users u) {

    List<Evidence> evidenceList = evidenceFacade.findByCase(c.getCaseId());

    Users admin = userFacade.find(1);

    for (Evidence e : evidenceList) {

        // chain of custody entry
        ChainOfCustody coc = new ChainOfCustody();

        coc.setEvidenceId(e);
        coc.setFromUser(admin);
        coc.setToUser(u);
        coc.setTransferReason("CASE_ASSIGNMENT_AUTO_LINK");
        coc.setTransferDate(new java.util.Date());

        String signature = e.getEvidenceCode()
                + admin.getUserId()
                + u.getUserId()
                + System.currentTimeMillis();

        coc.setDigitalSignature(signature);

        chainOfCustodyFacade.save(coc);
    }
}
  
  public boolean isUnassigned(Cases c) {
    CaseAssignments ca = caseAssignmentsFacade.findByCaseId(c.getCaseId());
    return ca == null;
}
    
    
}
