/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dhwani
 */
@Entity
@Table(name = "cases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cases.findAll", query = "SELECT c FROM Cases c"),
    @NamedQuery(name = "Cases.findByCaseId", query = "SELECT c FROM Cases c WHERE c.caseId = :caseId"),
    @NamedQuery(name = "Cases.findByCaseNumber", query = "SELECT c FROM Cases c WHERE c.caseNumber = :caseNumber"),
    @NamedQuery(name = "Cases.findByTitle", query = "SELECT c FROM Cases c WHERE c.title = :title"),
    @NamedQuery(name = "Cases.findByPriority", query = "SELECT c FROM Cases c WHERE c.priority = :priority"),
    @NamedQuery(name = "Cases.findByStatus", query = "SELECT c FROM Cases c WHERE c.status = :status"),
    @NamedQuery(name = "Cases.findByCreatedDate", query = "SELECT c FROM Cases c WHERE c.createdDate = :createdDate"),
    @NamedQuery(name = "Cases.findByClosedDate", query = "SELECT c FROM Cases c WHERE c.closedDate = :closedDate"),
    @NamedQuery(name = "Cases.findByUpdatedAt", query = "SELECT c FROM Cases c WHERE c.updatedAt = :updatedAt")})
public class Cases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "case_id")
    private Integer caseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "case_number")
    private String caseNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 8)
    @Column(name = "priority")
    private String priority;
    @Size(max = 19)
    @Column(name = "status")
    private String status;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "closed_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedDate;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caseId")
    private Collection<Evidence> evidenceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caseId")
    private Collection<Tasks> tasksCollection;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users createdBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caseId")
    private Collection<InvestigationNotes> investigationNotesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caseId")
    private Collection<CaseAssignments> caseAssignmentsCollection;

    public Cases() {
    }

    public Cases(Integer caseId) {
        this.caseId = caseId;
    }

    public Cases(Integer caseId, String caseNumber, String title) {
        this.caseId = caseId;
        this.caseNumber = caseNumber;
        this.title = title;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    public Collection<Evidence> getEvidenceCollection() {
        return evidenceCollection;
    }

    public void setEvidenceCollection(Collection<Evidence> evidenceCollection) {
        this.evidenceCollection = evidenceCollection;
    }

    @XmlTransient
    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    @XmlTransient
    public Collection<InvestigationNotes> getInvestigationNotesCollection() {
        return investigationNotesCollection;
    }

    public void setInvestigationNotesCollection(Collection<InvestigationNotes> investigationNotesCollection) {
        this.investigationNotesCollection = investigationNotesCollection;
    }

    @XmlTransient
    public Collection<CaseAssignments> getCaseAssignmentsCollection() {
        return caseAssignmentsCollection;
    }

    public void setCaseAssignmentsCollection(Collection<CaseAssignments> caseAssignmentsCollection) {
        this.caseAssignmentsCollection = caseAssignmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (caseId != null ? caseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cases)) {
            return false;
        }
        Cases other = (Cases) object;
        if ((this.caseId == null && other.caseId != null) || (this.caseId != null && !this.caseId.equals(other.caseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Cases[ caseId=" + caseId + " ]";
    }
    
}
