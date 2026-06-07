package Bean;

import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;
import java.math.BigInteger;

import Entity.*;
import facades.*;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named(value = "evidenceBean")
@SessionScoped
public class EvidenceBean implements Serializable {

    @EJB private EvidenceFacade evidenceFacade;
    @EJB private EvidenceVersionsFacade evidenceVersionsFacade;
    @EJB private ChainOfCustodyFacade chainOfCustodyFacade;
    @EJB private CaseFacade caseFacade;
    @EJB private EvidenceCategoryFacade categoryFacade;
    @EJB private UserFacade userFacade;
    @EJB private AuditLogFacade auditLogFacade;
    @EJB private DigitalSignatureBean digitalSignatureBean;
    @EJB private AuditService auditService;
     @Inject private AccessControlBean accessControlBean;
     @EJB private ActivityLogFacade activityLogFacade;

    private Evidence evidence = new Evidence();
    private Integer selectedCaseId;
    private Integer selectedCategoryId;
    private transient Part file;

    // ---------- GETTERS ----------
    public Evidence getEvidence() { return evidence; }
    public Integer getSelectedCaseId() { return selectedCaseId; }
    public Integer getSelectedCategoryId() { return selectedCategoryId; }
    public Part getFile() { return file; }

    public void setEvidence(Evidence e) { this.evidence = e; }
    public void setSelectedCaseId(Integer id) { this.selectedCaseId = id; }
    public void setSelectedCategoryId(Integer id) { this.selectedCategoryId = id; }
    public void setFile(Part file) { this.file = file; }

    // ---------- LIST ----------
    public java.util.List<Evidence> getEvidenceList() {
        return evidenceFacade.findAll();
    }

    public java.util.List<Cases> getCases() {
        return caseFacade.findAll();
    }

    public java.util.List<EvidenceCategories> getCategories() {
        return categoryFacade.findAll();
    }

    // ---------- UTIL ----------
    private String generateCode() {
        return "EV-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }

    private String sha256(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data);
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    // ---------- SAVE ----------
    
    public String saveEvidence() {

    try {

        if (file == null) {
            throw new RuntimeException("File not received");
        }
        
        if (!accessControlBean.hasPermission("UPLOAD_EVIDENCE")) {
    return "accessDenied.xhtml";
}

        Cases c = caseFacade.find(selectedCaseId);
        EvidenceCategories cat = categoryFacade.find(selectedCategoryId);
        Users user = userFacade.find(1);

        String code = generateCode();

        byte[] bytes = file.getInputStream().readAllBytes();

        String folder = "D:/139_701_A2/DEMS/evidence/";
        Files.createDirectories(Paths.get(folder));

        String fileName = code + "_" + file.getSubmittedFileName();
        String fullPath = folder + fileName;

        Files.write(Paths.get(fullPath), bytes);

        Evidence e = new Evidence();

        e.setEvidenceName(evidence.getEvidenceName());
        e.setEvidenceCode(code);
        e.setCaseId(c);
        e.setCategoryId(cat);
        e.setUploadedBy(user);

        e.setFilePath(fileName);
        e.setFileType(file.getContentType());
        e.setFileSize(BigInteger.valueOf(file.getSize()));
        e.setSha256Hash(sha256(bytes));
        e.setUploadDate(new java.util.Date());
        e.setUpdatedAt(new java.util.Date());
        e.setStatus("Pending");

        // 🔥 STEP 1: SAVE FIRST
        evidenceFacade.save(e);

        // 🔥 IMPORTANT: FLUSH TO GET ID
        evidenceFacade.flush(); // <-- if not present, add it (see below)

        // 🔥 STEP 2: NOW SAFE
        logChain(e, user, user, "INITIAL_UPLOAD");
        
        auditService.log(user, "UPLOAD_EVIDENCE",
        "Evidence uploaded: " + code);

        evidence = new Evidence();
        
        ActivityLogs log = new ActivityLogs();
        log.setUserId(user);
        log.setActivityType("UPLOAD_EVIDENCE");
        log.setActivityDescription("Evidence uploaded: " + code);
            log.setModuleName("EVIDENCE");
        log.setActivityTime(new java.util.Date());
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        log.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(log);

        return "evidence.xhtml?faces-redirect=true";

    } catch (Exception ex) {
        ex.printStackTrace();
        return null;
    }
}
    
//    public String saveEvidence() {
//
//        try {
//            
//            if (file == null) {
//            throw new RuntimeException("File not received");
//        }
//            
//            Cases c = caseFacade.find(selectedCaseId);
//            EvidenceCategories cat = categoryFacade.find(selectedCategoryId);
//            Users user = userFacade.find(1);
//
//            String code = generateCode();
//
//            InputStream input = file.getInputStream();
//            byte[] bytes = input.readAllBytes();
//
//            String folder = "D:/139_701_A2/DEMS/evidence/";
//            Files.createDirectories(Paths.get(folder));
//            
//            String fileName = code + "_" + file.getSubmittedFileName();
//            String fullPath = folder + fileName;
//            
//            Files.write(Paths.get(fullPath), bytes);
//
//            evidence.setEvidenceCode(code);
//            evidence.setCaseId(c);
//            evidence.setCategoryId(cat);
//            evidence.setUploadedBy(user);
//            logChain(evidence, user, user, "INITIAL_UPLOAD");
//
//            evidence.setFilePath(fileName); // IMPORTANT (web path)
//            evidence.setFileType(file.getContentType());
//            evidence.setFileSize(BigInteger.valueOf(file.getSize()));
//
//            evidence.setSha256Hash(sha256(bytes));
//            evidence.setUploadDate(new java.util.Date());
//            evidence.setUpdatedAt(new java.util.Date());
//            evidence.setStatus("Pending");
//
//            evidenceFacade.save(evidence);
//            
////            // CREATE VERSION
////            int versionNo = evidenceVersionsFacade.getLastVersion(evidence.getEvidenceId()) + 1;
////
////            EvidenceVersions v = new EvidenceVersions();
////
////            v.setEvidenceId(evidence);
////            v.setVersionNo(versionNo);
////            v.setFilePath(fileName);
////            v.setHashValue(sha256(bytes));
////            v.setUploadedBy(user);
////            v.setUploadDate(new java.util.Date());
////
////            evidenceVersionsFacade.save(v);
//
//                digitalSignatureBean.createSignature(evidence);
//
//            evidence = new Evidence();
//
//            return "evidence.xhtml?faces-redirect=true";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    
public boolean isAdmin() {

    Users user = (Users) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSessionMap()
            .get("user");

    return user != null 
            && user.getRoleId() != null 
            && "ADMIN".equalsIgnoreCase(user.getRoleId().getRoleName());
}

        public void updateStatus(Evidence e, String status) {
            
            Users user = (Users) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .get("user");

            auditService.log(user, "STATUS_CHANGE",
                    "Evidence " + e.getEvidenceCode() + " -> " + status);
            
            e.setStatus(status);
            
            ActivityLogs log = new ActivityLogs();
        log.setUserId(user);
        log.setActivityType("EVIDENCE_STATUS_UPDATED");
        log.setActivityDescription("Evidence : " + e.getEvidenceName() + "Status Chaged to : " + status);
            log.setModuleName("EVIDENCE");
        log.setActivityTime(new java.util.Date());
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        log.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(log);
        
            evidenceFacade.edit(e);
        }

    // ---------- SEARCH BY CODE ----------
    private String searchCode;
    private Evidence searchedEvidence;

    public String getSearchCode() { return searchCode; }
    public void setSearchCode(String searchCode) { this.searchCode = searchCode; }

    public Evidence getSearchedEvidence() { return searchedEvidence; }

    public String searchEvidence() {
        searchedEvidence = evidenceFacade.findByEvidenceCode(searchCode);
        
        return "evidenceView.xhtml?faces-redirect=true";
    }
    
    public void logChain(Evidence e,
                     Users from,
                     Users to,
                     String reason) {

    ChainOfCustody c = new ChainOfCustody();

    c.setEvidenceId(e);
    c.setFromUser(from);
    c.setToUser(to);
    c.setTransferReason(reason);
    c.setTransferDate(new java.util.Date());

    // simple forensic signature
    String signature = e.getEvidenceCode()
            + from.getUserId()
            + to.getUserId()
            + System.currentTimeMillis();

    c.setDigitalSignature(signature);

    chainOfCustodyFacade.save(c);
}
    
    public String signEvidence(Evidence e) {

    Users user = (Users) FacesContext.getCurrentInstance()
        .getExternalContext()
        .getSessionMap()
        .get("user");

    digitalSignatureBean.signAndLockEvidence(
        e.getEvidenceId(),
        user.getUserId()
    );
    
    auditService.log(user, "SIGN_EVIDENCE",
        "Evidence signed: " + e.getEvidenceCode());
    
    ActivityLogs log = new ActivityLogs();
        log.setUserId(user);
        log.setActivityType("SIGN_EVIDENCE");
        log.setActivityDescription("Evidence Signed: " + e.getEvidenceCode());
            log.setModuleName("EVIDENCE");
        log.setActivityTime(new java.util.Date());
        HttpServletRequest request =
            (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();
        log.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(log);

    return "evidence.xhtml?faces-redirect=true";
}
    
    
}