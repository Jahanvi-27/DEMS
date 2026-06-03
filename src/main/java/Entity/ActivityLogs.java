/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Dhwani
 */
@Entity
@Table(name = "activity_logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivityLogs.findAll", query = "SELECT a FROM ActivityLogs a"),
    @NamedQuery(name = "ActivityLogs.findByActivityId", query = "SELECT a FROM ActivityLogs a WHERE a.activityId = :activityId"),
    @NamedQuery(name = "ActivityLogs.findByActivityType", query = "SELECT a FROM ActivityLogs a WHERE a.activityType = :activityType"),
    @NamedQuery(name = "ActivityLogs.findByModuleName", query = "SELECT a FROM ActivityLogs a WHERE a.moduleName = :moduleName"),
    @NamedQuery(name = "ActivityLogs.findByActivityTime", query = "SELECT a FROM ActivityLogs a WHERE a.activityTime = :activityTime"),
    @NamedQuery(name = "ActivityLogs.findByIpAddress", query = "SELECT a FROM ActivityLogs a WHERE a.ipAddress = :ipAddress")})
public class ActivityLogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "activity_id")
    private Integer activityId;
    @Size(max = 100)
    @Column(name = "activity_type")
    private String activityType;
    @Lob
    @Size(max = 65535)
    @Column(name = "activity_description")
    private String activityDescription;
    @Size(max = 100)
    @Column(name = "module_name")
    private String moduleName;
    @Column(name = "activity_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activityTime;
    @Size(max = 50)
    @Column(name = "ip_address")
    private String ipAddress;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public ActivityLogs() {
    }

    public ActivityLogs(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activityId != null ? activityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivityLogs)) {
            return false;
        }
        ActivityLogs other = (ActivityLogs) object;
        if ((this.activityId == null && other.activityId != null) || (this.activityId != null && !this.activityId.equals(other.activityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ActivityLogs[ activityId=" + activityId + " ]";
    }
    
}
