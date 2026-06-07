/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

import Entity.*;
import facades.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Dhwani
 */
@Named("dashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {

    @EJB private UserFacade userFacade;
    @EJB private CaseFacade caseFacade;
    @EJB private EvidenceFacade evidenceFacade;
    @EJB
    private ActivityLogFacade activityLogFacade;

    public List<ActivityLogs> getAllLogs() {
        return activityLogFacade.findAll();
    }

    // =========================
    // ACTIVITY TYPES COUNT
    // =========================
    public Map<String, Long> getActivityTypeStats() {

        Map<String, Long> map = new HashMap<>();

        for (ActivityLogs log : getAllLogs()) {
            String type = log.getActivityType();
            map.put(type, map.getOrDefault(type, 0L) + 1);
        }
        return map;
    }

    // =========================
    // MODULE WISE STATS
    // =========================
    public Map<String, Long> getModuleStats() {

        Map<String, Long> map = new HashMap<>();

        for (ActivityLogs log : getAllLogs()) {
            String module = log.getModuleName();
            map.put(module, map.getOrDefault(module, 0L) + 1);
        }
        return map;
    }

    // =========================
    // DAILY ACTIVITY
    // =========================
    public Map<String, Long> getDailyStats() {

        Map<String, Long> map = new TreeMap<>();

        for (ActivityLogs log : getAllLogs()) {

            String date = log.getActivityTime().toString().substring(0, 10);

            map.put(date, map.getOrDefault(date, 0L) + 1);
        }
        return map;
    }
    
    public List<ActivityLogs> getRecentActivities() {
    return activityLogFacade.findRecent(10);
}

    // ================= USERS =================
    public long getTotalUsers() {
        return userFacade.countAll();
    }

    // ================= CASES =================
    public long getTotalCases() {
        return caseFacade.countAll();
    }

    public long getOpenCases() {
        return caseFacade.countByStatus("Open");
    }

    public long getClosedCases() {
        return caseFacade.countByStatus("Closed");
    }

    // ================= EVIDENCE =================
    public long getTotalEvidence() {
        return evidenceFacade.countAll();
    }

    public long getPendingEvidence() {
        return evidenceFacade.countByStatus("Pending");
    }

    public long getVerifiedEvidence() {
        return evidenceFacade.countByStatus("Verified");
    }

    public long getAnalyzedEvidence() {
        return evidenceFacade.countByStatus("Analyzed");
    }

    public long getArchivedEvidence() {
        return evidenceFacade.countByStatus("Archived");
    }
}