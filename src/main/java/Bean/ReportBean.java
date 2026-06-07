/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.*;
import facades.*;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dhwani
 */
@Named(value = "reportBean")
@SessionScoped
public class ReportBean implements Serializable {

    @EJB
    private ActivityLogFacade activityLogFacade;

    @EJB
    private LoginHistoryFacade loginHistoryFacade;

    @EJB
    private EvidenceFacade evidenceFacade;

    @EJB
    private ChainOfCustodyFacade chainOfCustodyFacade;

    @EJB
    private AuditLogFacade auditLogFacade;

    // ========================
    // DATA FOR REPORTS
    // ========================
    private List<ActivityLogs> activityLogs;
    private List<LoginHistory> loginHistory;
    private List<Evidence> evidenceList;

    public void loadAllReports() {
        activityLogs = activityLogFacade.findAll();
        loginHistory = loginHistoryFacade.findAll();
        evidenceList = evidenceFacade.findAll();
    }
    
    

    // GETTERS
    public List<ActivityLogs> getActivityLogs() { return activityLogs; }
    public List<LoginHistory> getLoginHistory() { return loginHistory; }
    public List<Evidence> getEvidenceList() { return evidenceList; }
    
    private List<Object[]> activityData;
    private List<Object[]> loginData;
    private List<Object[]> evidenceData;

    @PostConstruct
    public void init() {
        activityData = activityLogFacade.countActivitiesByType();
        loginData = loginHistoryFacade.loginCountByUser();
        evidenceData = evidenceFacade.evidenceByType();
    }

    public List<Object[]> getActivityData() {
        return activityData;
    }

    public List<Object[]> getLoginData() {
        return loginData;
    }

    public List<Object[]> getEvidenceData() {
        return evidenceData;
    }
}