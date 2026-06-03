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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByFullName", query = "SELECT u FROM Users u WHERE u.fullName = :fullName"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPasswordHash", query = "SELECT u FROM Users u WHERE u.passwordHash = :passwordHash"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status"),
    @NamedQuery(name = "Users.findByLastLogin", query = "SELECT u FROM Users u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt"),
    @NamedQuery(name = "Users.findByIsDeleted", query = "SELECT u FROM Users u WHERE u.isDeleted = :isDeleted")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "full_name")
    private String fullName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password_hash")
    private String passwordHash;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    @Size(max = 9)
    @Column(name = "status")
    private String status;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<DigitalSignatures> digitalSignaturesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uploadedBy")
    private Collection<Evidence> evidenceCollection;
    @OneToMany(mappedBy = "fromUser")
    private Collection<ChainOfCustody> chainOfCustodyCollection;
    @OneToMany(mappedBy = "toUser")
    private Collection<ChainOfCustody> chainOfCustodyCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<AuditLogs> auditLogsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<LoginHistory> loginHistoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "downloadedBy")
    private Collection<EvidenceDownloads> evidenceDownloadsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignedTo")
    private Collection<Tasks> tasksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<Cases> casesCollection;
    @OneToMany(mappedBy = "uploadedBy")
    private Collection<EvidenceVersions> evidenceVersionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "investigatorId")
    private Collection<InvestigationNotes> investigationNotesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<ActivityLogs> activityLogsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignedBy")
    private Collection<CaseAssignments> caseAssignmentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<CaseAssignments> caseAssignmentsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<PasswordResetTokens> passwordResetTokensCollection;
    @OneToMany(mappedBy = "checkedBy")
    private Collection<EvidenceIntegrityChecks> evidenceIntegrityChecksCollection;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne(optional = false)
    private Roles roleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "generatedBy")
    private Collection<GeneratedReports> generatedReportsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analystId")
    private Collection<ForensicReports> forensicReportsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<OtpVerification> otpVerificationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Notifications> notificationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<EvidenceAccessLogs> evidenceAccessLogsCollection;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String fullName, String email, String passwordHash) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @XmlTransient
    public Collection<DigitalSignatures> getDigitalSignaturesCollection() {
        return digitalSignaturesCollection;
    }

    public void setDigitalSignaturesCollection(Collection<DigitalSignatures> digitalSignaturesCollection) {
        this.digitalSignaturesCollection = digitalSignaturesCollection;
    }

    @XmlTransient
    public Collection<Evidence> getEvidenceCollection() {
        return evidenceCollection;
    }

    public void setEvidenceCollection(Collection<Evidence> evidenceCollection) {
        this.evidenceCollection = evidenceCollection;
    }

    @XmlTransient
    public Collection<ChainOfCustody> getChainOfCustodyCollection() {
        return chainOfCustodyCollection;
    }

    public void setChainOfCustodyCollection(Collection<ChainOfCustody> chainOfCustodyCollection) {
        this.chainOfCustodyCollection = chainOfCustodyCollection;
    }

    @XmlTransient
    public Collection<ChainOfCustody> getChainOfCustodyCollection1() {
        return chainOfCustodyCollection1;
    }

    public void setChainOfCustodyCollection1(Collection<ChainOfCustody> chainOfCustodyCollection1) {
        this.chainOfCustodyCollection1 = chainOfCustodyCollection1;
    }

    @XmlTransient
    public Collection<AuditLogs> getAuditLogsCollection() {
        return auditLogsCollection;
    }

    public void setAuditLogsCollection(Collection<AuditLogs> auditLogsCollection) {
        this.auditLogsCollection = auditLogsCollection;
    }

    @XmlTransient
    public Collection<LoginHistory> getLoginHistoryCollection() {
        return loginHistoryCollection;
    }

    public void setLoginHistoryCollection(Collection<LoginHistory> loginHistoryCollection) {
        this.loginHistoryCollection = loginHistoryCollection;
    }

    @XmlTransient
    public Collection<EvidenceDownloads> getEvidenceDownloadsCollection() {
        return evidenceDownloadsCollection;
    }

    public void setEvidenceDownloadsCollection(Collection<EvidenceDownloads> evidenceDownloadsCollection) {
        this.evidenceDownloadsCollection = evidenceDownloadsCollection;
    }

    @XmlTransient
    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    @XmlTransient
    public Collection<Cases> getCasesCollection() {
        return casesCollection;
    }

    public void setCasesCollection(Collection<Cases> casesCollection) {
        this.casesCollection = casesCollection;
    }

    @XmlTransient
    public Collection<EvidenceVersions> getEvidenceVersionsCollection() {
        return evidenceVersionsCollection;
    }

    public void setEvidenceVersionsCollection(Collection<EvidenceVersions> evidenceVersionsCollection) {
        this.evidenceVersionsCollection = evidenceVersionsCollection;
    }

    @XmlTransient
    public Collection<InvestigationNotes> getInvestigationNotesCollection() {
        return investigationNotesCollection;
    }

    public void setInvestigationNotesCollection(Collection<InvestigationNotes> investigationNotesCollection) {
        this.investigationNotesCollection = investigationNotesCollection;
    }

    @XmlTransient
    public Collection<ActivityLogs> getActivityLogsCollection() {
        return activityLogsCollection;
    }

    public void setActivityLogsCollection(Collection<ActivityLogs> activityLogsCollection) {
        this.activityLogsCollection = activityLogsCollection;
    }

    @XmlTransient
    public Collection<CaseAssignments> getCaseAssignmentsCollection() {
        return caseAssignmentsCollection;
    }

    public void setCaseAssignmentsCollection(Collection<CaseAssignments> caseAssignmentsCollection) {
        this.caseAssignmentsCollection = caseAssignmentsCollection;
    }

    @XmlTransient
    public Collection<CaseAssignments> getCaseAssignmentsCollection1() {
        return caseAssignmentsCollection1;
    }

    public void setCaseAssignmentsCollection1(Collection<CaseAssignments> caseAssignmentsCollection1) {
        this.caseAssignmentsCollection1 = caseAssignmentsCollection1;
    }

    @XmlTransient
    public Collection<PasswordResetTokens> getPasswordResetTokensCollection() {
        return passwordResetTokensCollection;
    }

    public void setPasswordResetTokensCollection(Collection<PasswordResetTokens> passwordResetTokensCollection) {
        this.passwordResetTokensCollection = passwordResetTokensCollection;
    }

    @XmlTransient
    public Collection<EvidenceIntegrityChecks> getEvidenceIntegrityChecksCollection() {
        return evidenceIntegrityChecksCollection;
    }

    public void setEvidenceIntegrityChecksCollection(Collection<EvidenceIntegrityChecks> evidenceIntegrityChecksCollection) {
        this.evidenceIntegrityChecksCollection = evidenceIntegrityChecksCollection;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public Collection<GeneratedReports> getGeneratedReportsCollection() {
        return generatedReportsCollection;
    }

    public void setGeneratedReportsCollection(Collection<GeneratedReports> generatedReportsCollection) {
        this.generatedReportsCollection = generatedReportsCollection;
    }

    @XmlTransient
    public Collection<ForensicReports> getForensicReportsCollection() {
        return forensicReportsCollection;
    }

    public void setForensicReportsCollection(Collection<ForensicReports> forensicReportsCollection) {
        this.forensicReportsCollection = forensicReportsCollection;
    }

    @XmlTransient
    public Collection<OtpVerification> getOtpVerificationCollection() {
        return otpVerificationCollection;
    }

    public void setOtpVerificationCollection(Collection<OtpVerification> otpVerificationCollection) {
        this.otpVerificationCollection = otpVerificationCollection;
    }

    @XmlTransient
    public Collection<Notifications> getNotificationsCollection() {
        return notificationsCollection;
    }

    public void setNotificationsCollection(Collection<Notifications> notificationsCollection) {
        this.notificationsCollection = notificationsCollection;
    }

    @XmlTransient
    public Collection<EvidenceAccessLogs> getEvidenceAccessLogsCollection() {
        return evidenceAccessLogsCollection;
    }

    public void setEvidenceAccessLogsCollection(Collection<EvidenceAccessLogs> evidenceAccessLogsCollection) {
        this.evidenceAccessLogsCollection = evidenceAccessLogsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Users[ userId=" + userId + " ]";
    }
    
}
