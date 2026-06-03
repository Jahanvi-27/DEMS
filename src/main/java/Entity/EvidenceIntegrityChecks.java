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
@Table(name = "evidence_integrity_checks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvidenceIntegrityChecks.findAll", query = "SELECT e FROM EvidenceIntegrityChecks e"),
    @NamedQuery(name = "EvidenceIntegrityChecks.findByCheckId", query = "SELECT e FROM EvidenceIntegrityChecks e WHERE e.checkId = :checkId"),
    @NamedQuery(name = "EvidenceIntegrityChecks.findByOriginalHash", query = "SELECT e FROM EvidenceIntegrityChecks e WHERE e.originalHash = :originalHash"),
    @NamedQuery(name = "EvidenceIntegrityChecks.findByCurrentHash", query = "SELECT e FROM EvidenceIntegrityChecks e WHERE e.currentHash = :currentHash"),
    @NamedQuery(name = "EvidenceIntegrityChecks.findByVerificationStatus", query = "SELECT e FROM EvidenceIntegrityChecks e WHERE e.verificationStatus = :verificationStatus"),
    @NamedQuery(name = "EvidenceIntegrityChecks.findByCheckedDate", query = "SELECT e FROM EvidenceIntegrityChecks e WHERE e.checkedDate = :checkedDate")})
public class EvidenceIntegrityChecks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "check_id")
    private Integer checkId;
    @Size(max = 255)
    @Column(name = "original_hash")
    private String originalHash;
    @Size(max = 255)
    @Column(name = "current_hash")
    private String currentHash;
    @Size(max = 8)
    @Column(name = "verification_status")
    private String verificationStatus;
    @Column(name = "checked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkedDate;
    @JoinColumn(name = "evidence_id", referencedColumnName = "evidence_id")
    @ManyToOne(optional = false)
    private Evidence evidenceId;
    @JoinColumn(name = "checked_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users checkedBy;

    public EvidenceIntegrityChecks() {
    }

    public EvidenceIntegrityChecks(Integer checkId) {
        this.checkId = checkId;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public String getOriginalHash() {
        return originalHash;
    }

    public void setOriginalHash(String originalHash) {
        this.originalHash = originalHash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public Date getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(Date checkedDate) {
        this.checkedDate = checkedDate;
    }

    public Evidence getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Evidence evidenceId) {
        this.evidenceId = evidenceId;
    }

    public Users getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(Users checkedBy) {
        this.checkedBy = checkedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (checkId != null ? checkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvidenceIntegrityChecks)) {
            return false;
        }
        EvidenceIntegrityChecks other = (EvidenceIntegrityChecks) object;
        if ((this.checkId == null && other.checkId != null) || (this.checkId != null && !this.checkId.equals(other.checkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EvidenceIntegrityChecks[ checkId=" + checkId + " ]";
    }
    
}
