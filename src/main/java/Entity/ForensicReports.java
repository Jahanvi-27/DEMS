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
@Table(name = "forensic_reports")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ForensicReports.findAll", query = "SELECT f FROM ForensicReports f"),
    @NamedQuery(name = "ForensicReports.findByReportId", query = "SELECT f FROM ForensicReports f WHERE f.reportId = :reportId"),
    @NamedQuery(name = "ForensicReports.findByReportFile", query = "SELECT f FROM ForensicReports f WHERE f.reportFile = :reportFile"),
    @NamedQuery(name = "ForensicReports.findByReportDate", query = "SELECT f FROM ForensicReports f WHERE f.reportDate = :reportDate"),
    @NamedQuery(name = "ForensicReports.findByCreatedAt", query = "SELECT f FROM ForensicReports f WHERE f.createdAt = :createdAt"),
    @NamedQuery(name = "ForensicReports.findByUpdatedAt", query = "SELECT f FROM ForensicReports f WHERE f.updatedAt = :updatedAt")})
public class ForensicReports implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "report_id")
    private Integer reportId;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "report_content")
    private String reportContent;
    @Size(max = 255)
    @Column(name = "report_file")
    private String reportFile;
    @Column(name = "report_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "evidence_id", referencedColumnName = "evidence_id")
    @ManyToOne(optional = false)
    private Evidence evidenceId;
    @JoinColumn(name = "analyst_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users analystId;

    public ForensicReports() {
    }

    public ForensicReports(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Evidence getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Evidence evidenceId) {
        this.evidenceId = evidenceId;
    }

    public Users getAnalystId() {
        return analystId;
    }

    public void setAnalystId(Users analystId) {
        this.analystId = analystId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportId != null ? reportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ForensicReports)) {
            return false;
        }
        ForensicReports other = (ForensicReports) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ForensicReports[ reportId=" + reportId + " ]";
    }
    
}
