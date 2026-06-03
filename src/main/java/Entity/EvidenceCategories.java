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
@Table(name = "evidence_categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvidenceCategories.findAll", query = "SELECT e FROM EvidenceCategories e"),
    @NamedQuery(name = "EvidenceCategories.findByCategoryId", query = "SELECT e FROM EvidenceCategories e WHERE e.categoryId = :categoryId"),
    @NamedQuery(name = "EvidenceCategories.findByCategoryName", query = "SELECT e FROM EvidenceCategories e WHERE e.categoryName = :categoryName"),
    @NamedQuery(name = "EvidenceCategories.findByCreatedAt", query = "SELECT e FROM EvidenceCategories e WHERE e.createdAt = :createdAt")})
public class EvidenceCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<Evidence> evidenceCollection;

    public EvidenceCategories() {
    }

    public EvidenceCategories(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public EvidenceCategories(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @XmlTransient
    public Collection<Evidence> getEvidenceCollection() {
        return evidenceCollection;
    }

    public void setEvidenceCollection(Collection<Evidence> evidenceCollection) {
        this.evidenceCollection = evidenceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvidenceCategories)) {
            return false;
        }
        EvidenceCategories other = (EvidenceCategories) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EvidenceCategories[ categoryId=" + categoryId + " ]";
    }
    
}
