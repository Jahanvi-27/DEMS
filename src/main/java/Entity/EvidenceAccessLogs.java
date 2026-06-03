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
@Table(name = "evidence_access_logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvidenceAccessLogs.findAll", query = "SELECT e FROM EvidenceAccessLogs e"),
    @NamedQuery(name = "EvidenceAccessLogs.findByAccessId", query = "SELECT e FROM EvidenceAccessLogs e WHERE e.accessId = :accessId"),
    @NamedQuery(name = "EvidenceAccessLogs.findByAccessType", query = "SELECT e FROM EvidenceAccessLogs e WHERE e.accessType = :accessType"),
    @NamedQuery(name = "EvidenceAccessLogs.findByAccessTime", query = "SELECT e FROM EvidenceAccessLogs e WHERE e.accessTime = :accessTime"),
    @NamedQuery(name = "EvidenceAccessLogs.findByIpAddress", query = "SELECT e FROM EvidenceAccessLogs e WHERE e.ipAddress = :ipAddress")})
public class EvidenceAccessLogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "access_id")
    private Integer accessId;
    @Size(max = 8)
    @Column(name = "access_type")
    private String accessType;
    @Column(name = "access_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accessTime;
    @Size(max = 50)
    @Column(name = "ip_address")
    private String ipAddress;
    @Lob
    @Size(max = 65535)
    @Column(name = "device_info")
    private String deviceInfo;
    @JoinColumn(name = "evidence_id", referencedColumnName = "evidence_id")
    @ManyToOne(optional = false)
    private Evidence evidenceId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public EvidenceAccessLogs() {
    }

    public EvidenceAccessLogs(Integer accessId) {
        this.accessId = accessId;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public Evidence getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Evidence evidenceId) {
        this.evidenceId = evidenceId;
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
        hash += (accessId != null ? accessId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvidenceAccessLogs)) {
            return false;
        }
        EvidenceAccessLogs other = (EvidenceAccessLogs) object;
        if ((this.accessId == null && other.accessId != null) || (this.accessId != null && !this.accessId.equals(other.accessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EvidenceAccessLogs[ accessId=" + accessId + " ]";
    }
    
}
