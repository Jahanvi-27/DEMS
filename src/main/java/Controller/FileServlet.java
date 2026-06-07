package Controller;

import Entity.*;
import facades.*;

import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;

@WebServlet("/file")
public class FileServlet extends HttpServlet {

    private final String STORAGE = "D:/139_701_A2/DEMS/evidence/";

    @EJB EvidenceFacade evidenceFacade;
    @EJB EvidenceIntegrityFacade integrityFacade;
    @EJB ChainOfCustodyFacade chainOfCustodyFacade;
    @EJB private ActivityLogFacade activityLogFacade;

   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {

    int id = Integer.parseInt(req.getParameter("id"));

    Evidence e = evidenceFacade.find(id);

    if (e == null) {
        resp.getWriter().write("Evidence not found");
        return;
    }

    File file = new File(STORAGE + e.getFilePath());

    if (!file.exists()) {
        resp.getWriter().write("File not found");
        return;
    }

    HttpSession session = req.getSession(false);
    Users user = (Users) session.getAttribute("user");

    try {

        // 1. integrity + audit
        verifyIntegrity(e, user);
        logAccess(e, user, "VIEW_EVIDENCE");
        logAccess(e, user, "DOWNLOAD_EVIDENCE");
        
        ActivityLogs viewLog = new ActivityLogs();
        viewLog.setUserId(user);
        viewLog.setActivityType("VIEW_EVIDENCE");
        viewLog.setActivityDescription("Evidence viewed: " + e.getEvidenceName());
        viewLog.setModuleName("EVIDENCE");
        viewLog.setActivityTime(new java.util.Date());

        HttpServletRequest request =
                (HttpServletRequest) req;

        viewLog.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(viewLog);
        
        ActivityLogs log = new ActivityLogs();
        log.setUserId(user);
        log.setActivityType("DOWNLOAD_EVIDENCE");
        log.setActivityDescription("Evidence Download: " + e.getEvidenceName());
            log.setModuleName("EVIDENCE");
        log.setActivityTime(new java.util.Date());
        
        log.setIpAddress(request.getRemoteAddr());

        activityLogFacade.create(log);

        String password = e.getEvidenceCode();

        String fileName = file.getName().toLowerCase();

        // =========================
        // PDF → ENCRYPT + DOWNLOAD
        // =========================
        if (fileName.endsWith(".pdf")) {

            PDDocument document = PDDocument.load(file);

            AccessPermission ap = new AccessPermission();

            StandardProtectionPolicy spp =
                    new StandardProtectionPolicy(password, password, ap);

            spp.setEncryptionKeyLength(128);
            spp.setPermissions(ap);

            document.protect(spp);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            document.close();

            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition",
                    "inline; filename=\"" + file.getName() + "\"");

            resp.getOutputStream().write(baos.toByteArray());
            return;
        }

        // =========================
        // IMAGE / VIDEO → DIRECT VIEW
        // =========================
//        if (fileName.endsWith(".jpg") ||
//            fileName.endsWith(".png") ||
//            fileName.endsWith(".jpeg") ||
//            fileName.endsWith(".mp4") ||
//            fileName.endsWith(".webm")) {
//
//            resp.setContentType(e.getFileType());
//            resp.setHeader("Content-Disposition",
//                    "inline; filename=\"" + file.getName() + "\"");
//
//            Files.copy(file.toPath(), resp.getOutputStream());
//            return;
//        }

        // =========================
        // OTHER FILES → ZIP DOWNLOAD
        // =========================
        File zipTemp = File.createTempFile("evidence_", ".zip");

        ZipFile zipFile = new ZipFile(zipTemp, password.toCharArray());

        ZipParameters params = new ZipParameters();
        params.setEncryptFiles(true);
        params.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);

        zipFile.addFile(file, params);

        resp.setContentType("application/zip");
        resp.setHeader("Content-Disposition",
                "attachment; filename=\"" + file.getName() + ".zip\"");

        Files.copy(zipTemp.toPath(), resp.getOutputStream());
        zipTemp.delete();

        return;

    } catch (Exception ex) {
        ex.printStackTrace();
        resp.getWriter().write("Error loading file");
    }
}

    // ================= LOG =================
    private void logAccess(Evidence e, Users user, String action) {

        ChainOfCustody c = new ChainOfCustody();
        c.setEvidenceId(e);
        c.setFromUser(user);
        c.setToUser(user);
        c.setTransferReason(action);
        c.setTransferDate(new java.util.Date());

        String signature = e.getEvidenceCode()
                + user.getUserId()
                + System.currentTimeMillis();

        c.setDigitalSignature(signature);

        chainOfCustodyFacade.save(c);
    }

    // ================= INTEGRITY =================
    private void verifyIntegrity(Evidence e, Users user) {

        try {
            File file = new File(STORAGE + e.getFilePath());
            if (!file.exists()) return;

            byte[] bytes = Files.readAllBytes(file.toPath());

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(bytes);

            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            String currentHash = sb.toString();

            String status = currentHash.equals(e.getSha256Hash())
                    ? "Verified"
                    : "Tampered";

            EvidenceIntegrityChecks check = new EvidenceIntegrityChecks();
            check.setEvidenceId(e);
            check.setOriginalHash(e.getSha256Hash());
            check.setCurrentHash(currentHash);
            check.setVerificationStatus(status);
            check.setCheckedBy(user);
            check.setCheckedDate(new java.util.Date());

            integrityFacade.save(check);

            if ("Verified".equals(status)) {
                e.setStatus("Verified");
                evidenceFacade.edit(e);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}