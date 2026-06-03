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
@Table(name = "digital_signatures")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigitalSignatures.findAll", query = "SELECT d FROM DigitalSignatures d"),
    @NamedQuery(name = "DigitalSignatures.findBySignatureId", query = "SELECT d FROM DigitalSignatures d WHERE d.signatureId = :signatureId"),
    @NamedQuery(name = "DigitalSignatures.findByCertificateNumber", query = "SELECT d FROM DigitalSignatures d WHERE d.certificateNumber = :certificateNumber"),
    @NamedQuery(name = "DigitalSignatures.findBySignatureFile", query = "SELECT d FROM DigitalSignatures d WHERE d.signatureFile = :signatureFile"),
    @NamedQuery(name = "DigitalSignatures.findByIssueDate", query = "SELECT d FROM DigitalSignatures d WHERE d.issueDate = :issueDate"),
    @NamedQuery(name = "DigitalSignatures.findByExpiryDate", query = "SELECT d FROM DigitalSignatures d WHERE d.expiryDate = :expiryDate"),
    @NamedQuery(name = "DigitalSignatures.findByCreatedAt", query = "SELECT d FROM DigitalSignatures d WHERE d.createdAt = :createdAt")})
public class DigitalSignatures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "signature_id")
    private Integer signatureId;
    @Size(max = 100)
    @Column(name = "certificate_number")
    private String certificateNumber;
    @Size(max = 255)
    @Column(name = "signature_file")
    private String signatureFile;
    @Column(name = "issue_date")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public DigitalSignatures() {
    }

    public DigitalSignatures(Integer signatureId) {
        this.signatureId = signatureId;
    }

    public Integer getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(Integer signatureId) {
        this.signatureId = signatureId;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getSignatureFile() {
        return signatureFile;
    }

    public void setSignatureFile(String signatureFile) {
        this.signatureFile = signatureFile;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        hash += (signatureId != null ? signatureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DigitalSignatures)) {
            return false;
        }
        DigitalSignatures other = (DigitalSignatures) object;
        if ((this.signatureId == null && other.signatureId != null) || (this.signatureId != null && !this.signatureId.equals(other.signatureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DigitalSignatures[ signatureId=" + signatureId + " ]";
    }
    
}
