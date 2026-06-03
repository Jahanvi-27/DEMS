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
@Table(name = "otp_verification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OtpVerification.findAll", query = "SELECT o FROM OtpVerification o"),
    @NamedQuery(name = "OtpVerification.findByOtpId", query = "SELECT o FROM OtpVerification o WHERE o.otpId = :otpId"),
    @NamedQuery(name = "OtpVerification.findByOtpCode", query = "SELECT o FROM OtpVerification o WHERE o.otpCode = :otpCode"),
    @NamedQuery(name = "OtpVerification.findByExpiryTime", query = "SELECT o FROM OtpVerification o WHERE o.expiryTime = :expiryTime"),
    @NamedQuery(name = "OtpVerification.findByIsVerified", query = "SELECT o FROM OtpVerification o WHERE o.isVerified = :isVerified"),
    @NamedQuery(name = "OtpVerification.findByCreatedAt", query = "SELECT o FROM OtpVerification o WHERE o.createdAt = :createdAt")})
public class OtpVerification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "otp_id")
    private Integer otpId;
    @Size(max = 10)
    @Column(name = "otp_code")
    private String otpCode;
    @Column(name = "expiry_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryTime;
    @Column(name = "is_verified")
    private Boolean isVerified;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public OtpVerification() {
    }

    public OtpVerification(Integer otpId) {
        this.otpId = otpId;
    }

    public Integer getOtpId() {
        return otpId;
    }

    public void setOtpId(Integer otpId) {
        this.otpId = otpId;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
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
        hash += (otpId != null ? otpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtpVerification)) {
            return false;
        }
        OtpVerification other = (OtpVerification) object;
        if ((this.otpId == null && other.otpId != null) || (this.otpId != null && !this.otpId.equals(other.otpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.OtpVerification[ otpId=" + otpId + " ]";
    }
    
}
