package main.com.java.Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PDFGenerator
{

    // Update the method signature to accept balance as a parameter
    public static byte[] generateStatementPDF(String title, String name, String cardNumber, List<String> transactions, double balance) {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            // Create a PdfWriter instance
            PdfWriter.getInstance(document, byteArrayOutputStream);
            
            // Open the document to start writing content
            document.open();
            
            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titlePara = new Paragraph(title, titleFont);
            document.add(titlePara);
            
            // Add Name and Card Number
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            document.add(new Paragraph("Name: " + name, contentFont));
            document.add(new Paragraph("Card Number: " + cardNumber, contentFont));
            document.add(Chunk.NEWLINE);

            // Add Balance
            document.add(new Paragraph("Available Balance: Rs." + balance, contentFont));  // Display balance in the statement
            document.add(Chunk.NEWLINE);

            // Add Transactions Header
            Paragraph transactionsHeader = new Paragraph("Transactions:", contentFont);
            document.add(transactionsHeader);

            // Add transaction details
            if (transactions != null && !transactions.isEmpty())
            {
                for (int i = 0; i < transactions.size(); i++) {
                    document.add(new Paragraph("Transaction " + (i + 1) + ": " + transactions.get(i), contentFont));
                }
            } else {
                document.add(new Paragraph("No transactions available", contentFont));  // If no transactions, show this message
            }

            // Close the document
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }
}
