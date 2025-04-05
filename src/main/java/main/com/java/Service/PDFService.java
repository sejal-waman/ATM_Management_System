package main.com.java.Service;

import main.com.java.Entity.ATMUser;
import main.com.java.Repository.ATMRepository;

import main.com.java.Utils.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDFService {

    @Autowired
    private ATMRepository atmUserRepository;

    // Method to generate a statement PDF for a specific user by email and return the byte[] content
    public byte[] generateTransactionStatement(String email) {
        // Fetch the user from the database using the email
        ATMUser user = atmUserRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        // Fetch transactions from the user's data (assuming it is stored in the 'transactions' field)
        List<String> transactions = user.getTransactions();

        // Call the PDF generator to create the PDF and return it as byte[]
        return PDFGenerator.generateStatementPDF(
                "Transaction Statement",
                user.getName(),
                user.getCardNumber(),
                transactions,
                user.getBalance()  // Pass the balance to the PDF generator
        );
    }


}
