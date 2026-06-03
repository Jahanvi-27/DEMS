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
@Table(name = "password_reset_tokens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasswordResetTokens.findAll", query = "SELECT p FROM PasswordResetTokens p"),
    @NamedQuery(name = "PasswordResetTokens.findByTokenId", query = "SELECT p FROM PasswordResetTokens p WHERE p.tokenId = :tokenId"),
    @NamedQuery(name = "PasswordResetTokens.findByToken", query = "SELECT p FROM PasswordResetTokens p WHERE p.token = :token"),
    @NamedQuery(name = "PasswordResetTokens.findByExpiryTime", query = "SELECT p FROM PasswordResetTokens p WHERE p.expiryTime = :expiryTime"),
    @NamedQuery(name = "PasswordResetTokens.findByIsUsed", query = "SELECT p FROM PasswordResetTokens p WHERE p.isUsed = :isUsed"),
    @NamedQuery(name = "PasswordResetTokens.findByCreatedAt", query = "SELECT p FROM PasswordResetTokens p WHERE p.createdAt = :createdAt")})
public class PasswordResetTokens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "token_id")
    private Integer tokenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiry_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryTime;
    @Column(name = "is_used")
    private Boolean isUsed;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public PasswordResetTokens() {
    }

    public PasswordResetTokens(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public PasswordResetTokens(Integer tokenId, String token, Date expiryTime) {
        this.tokenId = tokenId;
        this.token = token;
        this.expiryTime = expiryTime;
    }

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
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
        hash += (tokenId != null ? tokenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PasswordResetTokens)) {
            return false;
        }
        PasswordResetTokens other = (PasswordResetTokens) object;
        if ((this.tokenId == null && other.tokenId != null) || (this.tokenId != null && !this.tokenId.equals(other.tokenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PasswordResetTokens[ tokenId=" + tokenId + " ]";
    }
    
}
