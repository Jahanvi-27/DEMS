/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.ActivityLogs;
import facades.ActivityLogFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dhwani
 */
@Named(value = "activityLogBean")
@SessionScoped
public class ActivityLogBean implements Serializable {
    
     @EJB
    private ActivityLogFacade activityLogFacade;

    public List<ActivityLogs> getLatestLogs() {
        return activityLogFacade.findLatest10();
    }

    /**
     * Creates a new instance of ActivityLogBean
     */
    public ActivityLogBean() {
    }
    
}
