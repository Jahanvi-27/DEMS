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
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Dhwani
 */
@Entity
@Table(name = "case_assignments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaseAssignments.findAll", query = "SELECT c FROM CaseAssignments c"),
    @NamedQuery(name = "CaseAssignments.findByAssignmentId", query = "SELECT c FROM CaseAssignments c WHERE c.assignmentId = :assignmentId"),
    @NamedQuery(name = "CaseAssignments.findByAssignedDate", query = "SELECT c FROM CaseAssignments c WHERE c.assignedDate = :assignedDate")})
public class CaseAssignments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "assignment_id")
    private Integer assignmentId;
    @Column(name = "assigned_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedDate;
    @JoinColumn(name = "case_id", referencedColumnName = "case_id")
    @ManyToOne(optional = false)
    private Cases caseId;
    @JoinColumn(name = "assigned_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users assignedBy;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public CaseAssignments() {
    }

    public CaseAssignments(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Cases getCaseId() {
        return caseId;
    }

    public void setCaseId(Cases caseId) {
        this.caseId = caseId;
    }

    public Users getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Users assignedBy) {
        this.assignedBy = assignedBy;
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
        hash += (assignmentId != null ? assignmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaseAssignments)) {
            return false;
        }
        CaseAssignments other = (CaseAssignments) object;
        if ((this.assignmentId == null && other.assignmentId != null) || (this.assignmentId != null && !this.assignmentId.equals(other.assignmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CaseAssignments[ assignmentId=" + assignmentId + " ]";
    }
    
}
