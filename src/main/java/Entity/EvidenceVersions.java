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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Dhwani
 */
@Entity
@Table(name = "evidence_versions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvidenceVersions.findAll", query = "SELECT e FROM EvidenceVersions e"),
    @NamedQuery(name = "EvidenceVersions.findByVersionId", query = "SELECT e FROM EvidenceVersions e WHERE e.versionId = :versionId"),
    @NamedQuery(name = "EvidenceVersions.findByVersionNo", query = "SELECT e FROM EvidenceVersions e WHERE e.versionNo = :versionNo"),
    @NamedQuery(name = "EvidenceVersions.findByFilePath", query = "SELECT e FROM EvidenceVersions e WHERE e.filePath = :filePath"),
    @NamedQuery(name = "EvidenceVersions.findByHashValue", query = "SELECT e FROM EvidenceVersions e WHERE e.hashValue = :hashValue"),
    @NamedQuery(name = "EvidenceVersions.findByUploadDate", query = "SELECT e FROM EvidenceVersions e WHERE e.uploadDate = :uploadDate")})
public class EvidenceVersions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "version_id")
    private Integer versionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version_no")
    private int versionNo;
    @Size(max = 255)
    @Column(name = "file_path")
    private String filePath;
    @Size(max = 255)
    @Column(name = "hash_value")
    private String hashValue;
    @Column(name = "upload_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @JoinColumn(name = "evidence_id", referencedColumnName = "evidence_id")
    @ManyToOne(optional = false)
    private Evidence evidenceId;
    @JoinColumn(name = "uploaded_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users uploadedBy;

    public EvidenceVersions() {
    }

    public EvidenceVersions(Integer versionId) {
        this.versionId = versionId;
    }

    public EvidenceVersions(Integer versionId, int versionNo) {
        this.versionId = versionId;
        this.versionNo = versionNo;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Evidence getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Evidence evidenceId) {
        this.evidenceId = evidenceId;
    }

    public Users getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(Users uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (versionId != null ? versionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvidenceVersions)) {
            return false;
        }
        EvidenceVersions other = (EvidenceVersions) object;
        if ((this.versionId == null && other.versionId != null) || (this.versionId != null && !this.versionId.equals(other.versionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EvidenceVersions[ versionId=" + versionId + " ]";
    }
    
}
