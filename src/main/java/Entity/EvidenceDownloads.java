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
@Table(name = "evidence_downloads")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvidenceDownloads.findAll", query = "SELECT e FROM EvidenceDownloads e"),
    @NamedQuery(name = "EvidenceDownloads.findByDownloadId", query = "SELECT e FROM EvidenceDownloads e WHERE e.downloadId = :downloadId"),
    @NamedQuery(name = "EvidenceDownloads.findByDownloadDate", query = "SELECT e FROM EvidenceDownloads e WHERE e.downloadDate = :downloadDate"),
    @NamedQuery(name = "EvidenceDownloads.findByIpAddress", query = "SELECT e FROM EvidenceDownloads e WHERE e.ipAddress = :ipAddress"),
    @NamedQuery(name = "EvidenceDownloads.findByStatus", query = "SELECT e FROM EvidenceDownloads e WHERE e.status = :status")})
public class EvidenceDownloads implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "download_id")
    private Integer downloadId;
    @Column(name = "download_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date downloadDate;
    @Size(max = 50)
    @Column(name = "ip_address")
    private String ipAddress;
    @Lob
    @Size(max = 65535)
    @Column(name = "device_info")
    private String deviceInfo;
    @Lob
    @Size(max = 65535)
    @Column(name = "download_reason")
    private String downloadReason;
    @Size(max = 7)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "evidence_id", referencedColumnName = "evidence_id")
    @ManyToOne(optional = false)
    private Evidence evidenceId;
    @JoinColumn(name = "downloaded_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users downloadedBy;

    public EvidenceDownloads() {
    }

    public EvidenceDownloads(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public Integer getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
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

    public String getDownloadReason() {
        return downloadReason;
    }

    public void setDownloadReason(String downloadReason) {
        this.downloadReason = downloadReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Evidence getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Evidence evidenceId) {
        this.evidenceId = evidenceId;
    }

    public Users getDownloadedBy() {
        return downloadedBy;
    }

    public void setDownloadedBy(Users downloadedBy) {
        this.downloadedBy = downloadedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (downloadId != null ? downloadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvidenceDownloads)) {
            return false;
        }
        EvidenceDownloads other = (EvidenceDownloads) object;
        if ((this.downloadId == null && other.downloadId != null) || (this.downloadId != null && !this.downloadId.equals(other.downloadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EvidenceDownloads[ downloadId=" + downloadId + " ]";
    }
    
}
