/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dhwani
 */
@Entity
@Table(name = "evidence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evidence.findAll", query = "SELECT e FROM Evidence e"),
    @NamedQuery(name = "Evidence.findByEvidenceId", query = "SELECT e FROM Evidence e WHERE e.evidenceId = :evidenceId"),
    @NamedQuery(name = "Evidence.findByEvidenceName", query = "SELECT e FROM Evidence e WHERE e.evidenceName = :evidenceName"),
    @NamedQuery(name = "Evidence.findByFilePath", query = "SELECT e FROM Evidence e WHERE e.filePath = :filePath"),
    @NamedQuery(name = "Evidence.findByFileSize", query = "SELECT e FROM Evidence e WHERE e.fileSize = :fileSize"),
    @NamedQuery(name = "Evidence.findByFileType", query = "SELECT e FROM Evidence e WHERE e.fileType = :fileType"),
    @NamedQuery(name = "Evidence.findBySha256Hash", query = "SELECT e FROM Evidence e WHERE e.sha256Hash = :sha256Hash"),
    @NamedQuery(name = "Evidence.findByUploadDate", query = "SELECT e FROM Evidence e WHERE e.uploadDate = :uploadDate"),
    @NamedQuery(name = "Evidence.findByStatus", query = "SELECT e FROM Evidence e WHERE e.status = :status"),
    @NamedQuery(name = "Evidence.findByUpdatedAt", query = "SELECT e FROM Evidence e WHERE e.updatedAt = :updatedAt"),
    @NamedQuery(name = "Evidence.findByEvidenceCode", query = "SELECT e FROM Evidence e WHERE e.evidenceCode = :evidenceCode")})
public class Evidence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "evidence_id")
    private Integer evidenceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "evidence_name")
    private String evidenceName;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "file_size")
    private BigInteger fileSize;
    @Size(max = 50)
    @Column(name = "file_type")
    private String fileType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sha256_hash")
    private String sha256Hash;
    @Column(name = "upload_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Size(max = 50)
    @Column(name = "evidence_code")
    private String evidenceCode;
    @JoinColumn(name = "case_id", referencedColumnName = "case_id")
    @ManyToOne(optional = false)
    private Cases caseId;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne(optional = false)
    private EvidenceCategories categoryId;
    @JoinColumn(name = "uploaded_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users uploadedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evidenceId")
    private Collection<ChainOfCustody> chainOfCustodyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evidenceId")
    private Collection<EvidenceDownloads> evidenceDownloadsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evidenceId")
    private Collection<EvidenceVersions> evidenceVersionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evidenceId")
    private Collection<EvidenceIntegrityChecks> evidenceIntegrityChecksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evidenceId")
    private Collection<ForensicReports> forensicReportsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evidenceId")
    private Collection<EvidenceAccessLogs> evidenceAccessLogsCollection;

    public Evidence() {
    }

    public Evidence(Integer evidenceId) {
        this.evidenceId = evidenceId;
    }

    public Evidence(Integer evidenceId, String evidenceName, String filePath, String sha256Hash) {
        this.evidenceId = evidenceId;
        this.evidenceName = evidenceName;
        this.filePath = filePath;
        this.sha256Hash = sha256Hash;
    }

    public Integer getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Integer evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public void setEvidenceName(String evidenceName) {
        this.evidenceName = evidenceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public BigInteger getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigInteger fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSha256Hash() {
        return sha256Hash;
    }

    public void setSha256Hash(String sha256Hash) {
        this.sha256Hash = sha256Hash;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getEvidenceCode() {
        return evidenceCode;
    }

    public void setEvidenceCode(String evidenceCode) {
        this.evidenceCode = evidenceCode;
    }

    public Cases getCaseId() {
        return caseId;
    }

    public void setCaseId(Cases caseId) {
        this.caseId = caseId;
    }

    public EvidenceCategories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(EvidenceCategories categoryId) {
        this.categoryId = categoryId;
    }

    public Users getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(Users uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @XmlTransient
    public Collection<ChainOfCustody> getChainOfCustodyCollection() {
        return chainOfCustodyCollection;
    }

    public void setChainOfCustodyCollection(Collection<ChainOfCustody> chainOfCustodyCollection) {
        this.chainOfCustodyCollection = chainOfCustodyCollection;
    }

    @XmlTransient
    public Collection<EvidenceDownloads> getEvidenceDownloadsCollection() {
        return evidenceDownloadsCollection;
    }

    public void setEvidenceDownloadsCollection(Collection<EvidenceDownloads> evidenceDownloadsCollection) {
        this.evidenceDownloadsCollection = evidenceDownloadsCollection;
    }

    @XmlTransient
    public Collection<EvidenceVersions> getEvidenceVersionsCollection() {
        return evidenceVersionsCollection;
    }

    public void setEvidenceVersionsCollection(Collection<EvidenceVersions> evidenceVersionsCollection) {
        this.evidenceVersionsCollection = evidenceVersionsCollection;
    }

    @XmlTransient
    public Collection<EvidenceIntegrityChecks> getEvidenceIntegrityChecksCollection() {
        return evidenceIntegrityChecksCollection;
    }

    public void setEvidenceIntegrityChecksCollection(Collection<EvidenceIntegrityChecks> evidenceIntegrityChecksCollection) {
        this.evidenceIntegrityChecksCollection = evidenceIntegrityChecksCollection;
    }

    @XmlTransient
    public Collection<ForensicReports> getForensicReportsCollection() {
        return forensicReportsCollection;
    }

    public void setForensicReportsCollection(Collection<ForensicReports> forensicReportsCollection) {
        this.forensicReportsCollection = forensicReportsCollection;
    }

    @XmlTransient
    public Collection<EvidenceAccessLogs> getEvidenceAccessLogsCollection() {
        return evidenceAccessLogsCollection;
    }

    public void setEvidenceAccessLogsCollection(Collection<EvidenceAccessLogs> evidenceAccessLogsCollection) {
        this.evidenceAccessLogsCollection = evidenceAccessLogsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evidenceId != null ? evidenceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evidence)) {
            return false;
        }
        Evidence other = (Evidence) object;
        if ((this.evidenceId == null && other.evidenceId != null) || (this.evidenceId != null && !this.evidenceId.equals(other.evidenceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Evidence[ evidenceId=" + evidenceId + " ]";
    }
    
}
