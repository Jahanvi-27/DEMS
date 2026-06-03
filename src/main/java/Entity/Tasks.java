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
@Table(name = "tasks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t"),
    @NamedQuery(name = "Tasks.findByTaskId", query = "SELECT t FROM Tasks t WHERE t.taskId = :taskId"),
    @NamedQuery(name = "Tasks.findByTaskTitle", query = "SELECT t FROM Tasks t WHERE t.taskTitle = :taskTitle"),
    @NamedQuery(name = "Tasks.findByDeadline", query = "SELECT t FROM Tasks t WHERE t.deadline = :deadline"),
    @NamedQuery(name = "Tasks.findByStatus", query = "SELECT t FROM Tasks t WHERE t.status = :status"),
    @NamedQuery(name = "Tasks.findByCreatedAt", query = "SELECT t FROM Tasks t WHERE t.createdAt = :createdAt"),
    @NamedQuery(name = "Tasks.findByUpdatedAt", query = "SELECT t FROM Tasks t WHERE t.updatedAt = :updatedAt")})
public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "task_id")
    private Integer taskId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "task_title")
    private String taskTitle;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Size(max = 11)
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "case_id", referencedColumnName = "case_id")
    @ManyToOne(optional = false)
    private Cases caseId;
    @JoinColumn(name = "assigned_to", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users assignedTo;

    public Tasks() {
    }

    public Tasks(Integer taskId) {
        this.taskId = taskId;
    }

    public Tasks(Integer taskId, String taskTitle) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Users getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Users assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Tasks[ taskId=" + taskId + " ]";
    }
    
}
