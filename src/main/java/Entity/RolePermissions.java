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
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Dhwani
 */
@Entity
@Table(name = "role_permissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolePermissions.findAll", query = "SELECT r FROM RolePermissions r"),
    @NamedQuery(name = "RolePermissions.findByRpId", query = "SELECT r FROM RolePermissions r WHERE r.rpId = :rpId")})
public class RolePermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rp_id")
    private Integer rpId;
    @JoinColumn(name = "permission_id", referencedColumnName = "permission_id")
    @ManyToOne(optional = false)
    private Permissions permissionId;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne(optional = false)
    private Roles roleId;

    public RolePermissions() {
    }

    public RolePermissions(Integer rpId) {
        this.rpId = rpId;
    }

    public Integer getRpId() {
        return rpId;
    }

    public void setRpId(Integer rpId) {
        this.rpId = rpId;
    }

    public Permissions getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Permissions permissionId) {
        this.permissionId = permissionId;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rpId != null ? rpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolePermissions)) {
            return false;
        }
        RolePermissions other = (RolePermissions) object;
        if ((this.rpId == null && other.rpId != null) || (this.rpId != null && !this.rpId.equals(other.rpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.RolePermissions[ rpId=" + rpId + " ]";
    }
    
}
