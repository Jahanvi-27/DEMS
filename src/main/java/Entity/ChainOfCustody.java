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
@Table(name = "chain_of_custody")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChainOfCustody.findAll", query = "SELECT c FROM ChainOfCustody c"),
    @NamedQuery(name = "ChainOfCustody.findByCustodyId", query = "SELECT c FROM ChainOfCustody c WHERE c.custodyId = :custodyId"),
    @NamedQuery(name = "ChainOfCustody.findByTransferDate", query = "SELECT c FROM ChainOfCustody c WHERE c.transferDate = :transferDate")})
public class ChainOfCustody implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "custody_id")
    private Integer custodyId;
    @Lob
    @Size(max = 65535)
    @Column(name = "transfer_reason")
    private String transferReason;
    @Column(name = "transfer_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferDate;
    @Lob
    @Size(max = 65535)
    @Column(name = "digital_signature")
    private String digitalSignature;
    @JoinColumn(name = "evidence_id", referencedColumnName = "evidence_id")
    @ManyToOne(optional = false)
    private Evidence evidenceId;
    @JoinColumn(name = "from_user", referencedColumnName = "user_id")
    @ManyToOne
    private Users fromUser;
    @JoinColumn(name = "to_user", referencedColumnName = "user_id")
    @ManyToOne
    private Users toUser;

    public ChainOfCustody() {
    }

    public ChainOfCustody(Integer custodyId) {
        this.custodyId = custodyId;
    }

    public Integer getCustodyId() {
        return custodyId;
    }

    public void setCustodyId(Integer custodyId) {
        this.custodyId = custodyId;
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(String digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

    public Evidence getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Evidence evidenceId) {
        this.evidenceId = evidenceId;
    }

    public Users getFromUser() {
        return fromUser;
    }

    public void setFromUser(Users fromUser) {
        this.fromUser = fromUser;
    }

    public Users getToUser() {
        return toUser;
    }

    public void setToUser(Users toUser) {
        this.toUser = toUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custodyId != null ? custodyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChainOfCustody)) {
            return false;
        }
        ChainOfCustody other = (ChainOfCustody) object;
        if ((this.custodyId == null && other.custodyId != null) || (this.custodyId != null && !this.custodyId.equals(other.custodyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ChainOfCustody[ custodyId=" + custodyId + " ]";
    }
    
}
