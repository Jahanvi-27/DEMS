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
@Table(name = "login_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginHistory.findAll", query = "SELECT l FROM LoginHistory l"),
    @NamedQuery(name = "LoginHistory.findByLoginId", query = "SELECT l FROM LoginHistory l WHERE l.loginId = :loginId"),
    @NamedQuery(name = "LoginHistory.findByIpAddress", query = "SELECT l FROM LoginHistory l WHERE l.ipAddress = :ipAddress"),
    @NamedQuery(name = "LoginHistory.findByLoginTime", query = "SELECT l FROM LoginHistory l WHERE l.loginTime = :loginTime"),
    @NamedQuery(name = "LoginHistory.findByLogoutTime", query = "SELECT l FROM LoginHistory l WHERE l.logoutTime = :logoutTime")})
public class LoginHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "login_id")
    private Integer loginId;
    @Size(max = 50)
    @Column(name = "ip_address")
    private String ipAddress;
    @Lob
    @Size(max = 65535)
    @Column(name = "device_info")
    private String deviceInfo;
    @Column(name = "login_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;
    @Column(name = "logout_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutTime;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public LoginHistory() {
    }

    public LoginHistory(Integer loginId) {
        this.loginId = loginId;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
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

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
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
        hash += (loginId != null ? loginId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginHistory)) {
            return false;
        }
        LoginHistory other = (LoginHistory) object;
        if ((this.loginId == null && other.loginId != null) || (this.loginId != null && !this.loginId.equals(other.loginId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.LoginHistory[ loginId=" + loginId + " ]";
    }
    
}
