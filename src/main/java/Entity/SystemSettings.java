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
import jakarta.persistence.Lob;
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
@Table(name = "system_settings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemSettings.findAll", query = "SELECT s FROM SystemSettings s"),
    @NamedQuery(name = "SystemSettings.findBySettingId", query = "SELECT s FROM SystemSettings s WHERE s.settingId = :settingId"),
    @NamedQuery(name = "SystemSettings.findBySettingKey", query = "SELECT s FROM SystemSettings s WHERE s.settingKey = :settingKey"),
    @NamedQuery(name = "SystemSettings.findByUpdatedAt", query = "SELECT s FROM SystemSettings s WHERE s.updatedAt = :updatedAt")})
public class SystemSettings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "setting_id")
    private Integer settingId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "setting_key")
    private String settingKey;
    @Lob
    @Size(max = 65535)
    @Column(name = "setting_value")
    private String settingValue;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public SystemSettings() {
    }

    public SystemSettings(Integer settingId) {
        this.settingId = settingId;
    }

    public SystemSettings(Integer settingId, String settingKey) {
        this.settingId = settingId;
        this.settingKey = settingKey;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public String getSettingKey() {
        return settingKey;
    }

    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (settingId != null ? settingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemSettings)) {
            return false;
        }
        SystemSettings other = (SystemSettings) object;
        if ((this.settingId == null && other.settingId != null) || (this.settingId != null && !this.settingId.equals(other.settingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.SystemSettings[ settingId=" + settingId + " ]";
    }
    
}
