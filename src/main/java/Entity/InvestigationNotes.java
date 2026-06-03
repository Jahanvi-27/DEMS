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
@Table(name = "investigation_notes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvestigationNotes.findAll", query = "SELECT i FROM InvestigationNotes i"),
    @NamedQuery(name = "InvestigationNotes.findByNoteId", query = "SELECT i FROM InvestigationNotes i WHERE i.noteId = :noteId"),
    @NamedQuery(name = "InvestigationNotes.findByCreatedAt", query = "SELECT i FROM InvestigationNotes i WHERE i.createdAt = :createdAt"),
    @NamedQuery(name = "InvestigationNotes.findByUpdatedAt", query = "SELECT i FROM InvestigationNotes i WHERE i.updatedAt = :updatedAt")})
public class InvestigationNotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "note_id")
    private Integer noteId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "notes")
    private String notes;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "case_id", referencedColumnName = "case_id")
    @ManyToOne(optional = false)
    private Cases caseId;
    @JoinColumn(name = "investigator_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users investigatorId;

    public InvestigationNotes() {
    }

    public InvestigationNotes(Integer noteId) {
        this.noteId = noteId;
    }

    public InvestigationNotes(Integer noteId, String notes) {
        this.noteId = noteId;
        this.notes = notes;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Cases getCaseId() {
        return caseId;
    }

    public void setCaseId(Cases caseId) {
        this.caseId = caseId;
    }

    public Users getInvestigatorId() {
        return investigatorId;
    }

    public void setInvestigatorId(Users investigatorId) {
        this.investigatorId = investigatorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noteId != null ? noteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvestigationNotes)) {
            return false;
        }
        InvestigationNotes other = (InvestigationNotes) object;
        if ((this.noteId == null && other.noteId != null) || (this.noteId != null && !this.noteId.equals(other.noteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.InvestigationNotes[ noteId=" + noteId + " ]";
    }
    
}
