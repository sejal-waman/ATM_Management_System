package main.com.java.Controller;

import main.com.java.Service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf")
public class TransactionController {

    @Autowired
    private PDFService pdfService;

    // Endpoint to generate and download the transaction statement PDF for a specific user by email
    @GetMapping("/pdf_statement")
    public ResponseEntity<ByteArrayResource> generatePDF(@RequestParam String email) {
        System.out.println("Received email: " + email); // Debugging line to log email
        byte[] pdfContent = pdfService.generateTransactionStatement(email);

        if (pdfContent != null) {
            ByteArrayResource resource = new ByteArrayResource(pdfContent);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + email + "_TransactionStatement.pdf")
                    .contentLength(pdfContent.length)
                    .body(resource);
        } else {
            return ResponseEntity.badRequest().body(null);  // If PDF is null, send an error response
        }
    }

}
