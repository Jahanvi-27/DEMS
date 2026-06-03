/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import Entity.AuditLogs;
import Entity.Cases;
import Entity.Evidence;
import Entity.EvidenceCategories;
import Entity.Users;
import facades.AuditLogFacade;
import facades.CaseFacade;
import facades.EvidenceCategoryFacade;
import facades.EvidenceFacade;
import facades.UserFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dhwani
 */
@Named(value = "evidenceBean")
@SessionScoped
public class EvidenceBean implements Serializable {

    @EJB
    private EvidenceFacade evidenceFacade;

    @EJB
    private CaseFacade caseFacade;

    @EJB
    private EvidenceCategoryFacade categoryFacade;

    @EJB
    private UserFacade userFacade;

    @EJB
    private AuditLogFacade auditLogFacade;

    private Evidence evidence = new Evidence();

    private Integer selectedCaseId;
    private Integer selectedCategoryId;

    public List<Cases> getCases() {
        return caseFacade.findAll();
    }

    public List<EvidenceCategories> getCategories() {
        return categoryFacade.findAll();
    }

    public List<Evidence> getEvidenceList() {
        return evidenceFacade.findAll();
    }

    public String saveEvidence() {

        Cases c = caseFacade.find(selectedCaseId);

        EvidenceCategories cat =
                categoryFacade.find(selectedCategoryId);

        Users adminUser =
                userFacade.find(1);

        evidence.setCaseId(c);
        evidence.setCategoryId(cat);
        evidence.setUploadedBy(adminUser);
        evidence.setStatus("Pending");

        evidenceFacade.save(evidence);

        AuditLogs log = new AuditLogs();

        log.setUserId(adminUser);
        log.setActionType("UPLOAD_EVIDENCE");
        log.setDescription(
                "Uploaded Evidence : "
                + evidence.getEvidenceName()
        );

        auditLogFacade.save(log);

        evidence = new Evidence();

        return "evidence.xhtml?faces-redirect=true";
    }

    // getters setters
}