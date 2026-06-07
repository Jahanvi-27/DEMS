/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

import Entity.*;
import facades.*;
import jakarta.ejb.Stateless;
import jakarta.faces.context.FacesContext;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.UUID;

/**
 *
 * @author Dhwani
 */
@Stateless
public class DigitalSignatureBean  {
    
    @EJB
    private DigitalSignatureFacade digitalSignaturesFacade;

    @EJB
    private EvidenceFacade evidenceFacade;

    @EJB
    private UserFacade userFacade;
    
    public void createSignature(Evidence e) {

    Users user = (Users) FacesContext.getCurrentInstance()
        .getExternalContext()
        .getSessionMap()
        .get("user");

    DigitalSignatures sig = new DigitalSignatures();

    sig.setUserId(user);

    sig.setCertificateNumber(
        "CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()
    );

    sig.setSignatureFile(generateSignatureHash(e, user));

    sig.setIssueDate(new java.util.Date());

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.YEAR, 1);
    sig.setExpiryDate(cal.getTime());
    sig.setCreatedAt(new java.util.Date());

    digitalSignaturesFacade.save(sig);
}
    
    private String generateSignatureHash(Evidence e, Users u) {

    try {
        String data =
                e.getEvidenceCode()
                + e.getSha256Hash()
                + u.getUserId()
                + System.currentTimeMillis();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(data.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();

    } catch (Exception ex) {
        return null;
    }
}
    
   
    public void signAndLockEvidence(Integer evidenceId, Integer userId) {

        Evidence e = evidenceFacade.find(evidenceId);
        Users user = userFacade.find(userId);

        if (e == null) {
            throw new RuntimeException("Evidence not found");
        }

        if (!"Verified".equals(e.getStatus())) {
            throw new RuntimeException("Only Verified evidence can be signed");
        }

        // CREATE SIGNATURE
        DigitalSignatures sig = new DigitalSignatures();
        sig.setUserId(user);

        sig.setCertificateNumber("CERT-" + UUID.randomUUID().toString().substring(0, 8));

        sig.setSignatureFile(generateHash(e, user));
        sig.setIssueDate(new java.util.Date());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        sig.setExpiryDate(cal.getTime());

        digitalSignaturesFacade.save(sig);

        // UPDATE STATUS
        e.setStatus("Analyzed");
        evidenceFacade.edit(e);
    }

    private String generateHash(Evidence e, Users u) {
        try {
            String data = e.getEvidenceCode() + u.getUserId() + System.currentTimeMillis();

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();

        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Creates a new instance of DigitalSignatureBean
     */
    public DigitalSignatureBean() {
    }
    
}
