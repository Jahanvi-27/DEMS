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
@Table(name = "generated_reports")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneratedReports.findAll", query = "SELECT g FROM GeneratedReports g"),
    @NamedQuery(name = "GeneratedReports.findByReportId", query = "SELECT g FROM GeneratedReports g WHERE g.reportId = :reportId"),
    @NamedQuery(name = "GeneratedReports.findByReportType", query = "SELECT g FROM GeneratedReports g WHERE g.reportType = :reportType"),
    @NamedQuery(name = "GeneratedReports.findByReportFile", query = "SELECT g FROM GeneratedReports g WHERE g.reportFile = :reportFile"),
    @NamedQuery(name = "GeneratedReports.findByGeneratedDate", query = "SELECT g FROM GeneratedReports g WHERE g.generatedDate = :generatedDate")})
public class GeneratedReports implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "report_id")
    private Integer reportId;
    @Size(max = 100)
    @Column(name = "report_type")
    private String reportType;
    @Size(max = 255)
    @Column(name = "report_file")
    private String reportFile;
    @Column(name = "generated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date generatedDate;
    @JoinColumn(name = "generated_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users generatedBy;

    public GeneratedReports() {
    }

    public GeneratedReports(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
    }

    public Users getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(Users generatedBy) {
        this.generatedBy = generatedBy;
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
        if (!(object instanceof GeneratedReports)) {
            return false;
        }
        GeneratedReports other = (GeneratedReports) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.GeneratedReports[ reportId=" + reportId + " ]";
    }
    
}
