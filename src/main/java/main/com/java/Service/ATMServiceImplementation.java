package main.com.java.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.java.Entity.ATMUser;
import main.com.java.Repository.ATMRepository;
import main.com.java.Utility.SendMessageMail;

@Service
public class ATMServiceImplementation implements ATMService 
{
    @Autowired
    private ATMRepository atmRepository;

    @Autowired
    private SendMessageMail sendMessageMail;
    
    private static final DecimalFormat decimalFormat = new DecimalFormat("\u20B9 #,##0.00");
    
    // Register user and generate ATM card number
    @Override
    public ATMUser registerUser(ATMUser user) 
    {
        user.setCardNumber(generateCardNumber());
        user.setBalance(500.0); // Initial balance set to 500
        ATMUser savedUser = atmRepository.save(user);

        // Send email with card details
        String message = "Hello " + user.getName() + ",\n\n" +
                "Your ATM card number is: " + user.getCardNumber() +
                "\nPlease keep it safe!";
        sendMessageMail.sendMail(user.getEmail(), "ATM Registration Successful", message);

        return savedUser;
    }

    // Login validation
    @Override
    public ATMUser loginUser(String email, String pin) {
        ATMUser user = atmRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        if (!user.getPin().equals(pin)) {
            throw new RuntimeException("Invalid PIN for user: " + email);
        }

        return user;
    }

    // Generate 16-digit ATM card number
    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder("4000"); // First 4 digits are fixed

        // Generate remaining 12 digits
        for (int i = 0; i < 12; i++) {
            cardNumber.append(random.nextInt(10)); // Random digit (0-9)
        }
        return cardNumber.toString();
    }

    // Withdraw Cash
    @Override
    public String withdrawCash(String email, double amount) {
        ATMUser user = atmRepository.findByEmail(email);

        if (user == null) {
            return "User not found. Please register first.";
        }

        if (amount <= 0) {
            return "Invalid amount. Please enter a positive value.";
        }

        if (user.getBalance() < amount) {
            return "Insufficient balance.";
        }

        user.setBalance(user.getBalance() - amount);
        user.addTransaction(decimalFormat.format(amount) + " Withdrawn"); // Track Transaction
        atmRepository.save(user);

        return "Withdrawal successful. Your remaining balance is " + decimalFormat.format(user.getBalance());
    }

    // Deposit Cash
    @Override
    public String depositCash(String email, double amount) {
        ATMUser user = atmRepository.findByEmail(email);

        if (user == null) {
            return "User not found. Please register first.";
        }

        if (amount <= 0) {
            return "Invalid amount. Please enter a positive value.";
        }

        user.setBalance(user.getBalance() + amount);
        user.addTransaction(decimalFormat.format(amount) + " Deposited"); // Track Transaction
        atmRepository.save(user);

        return "Deposit successful. Your updated balance is " + decimalFormat.format(user.getBalance());
    }

    // Check Balance
    @Override
    public String checkBalance(String email) {
        ATMUser user = atmRepository.findByEmail(email);

        if (user != null) {
            return "Your current balance is " + decimalFormat.format(user.getBalance());
        }
        return "User not found.";
    }

    // Generate Mini Statement (Last 5 Transactions)
    @Override
    public List<String> getMiniStatement(String email) {
        ATMUser user = atmRepository.findByEmail(email);

        if (user == null) {
            List<String> errorList = new ArrayList<>();
            errorList.add("User not found. Please register first.");
            return errorList;
        }

        List<String> transactions = user.getTransactions();
        int size = transactions.size();
        if (size == 0) {
            transactions.add("No transactions found.");
        }
        return transactions.subList(Math.max(size - 5, 0), size); // Return the last 5 transactions
    }

    @Override
    public ATMUser getUserByEmail(String email) 
    {
        return atmRepository.findByEmail(email);
    }

    @Override
    public boolean isEmailExists(String email) 
    {
        return atmRepository.existsByEmail(email);
    }
    
    private Map<String, String> resetTokens = new HashMap<>();
    
    public String generateResetToken(String email)
    {
        String token = UUID.randomUUID().toString();
        resetTokens.put(token, email);
        return token;
    }

    public String validateResetToken(String token) 
    {
        return resetTokens.remove(token);
    }

    public boolean updatePin(String email, String newPin) 
    {
        ATMUser user = atmRepository.findByEmail(email);
        if (user != null) 
        {
            user.setPin(newPin);
            atmRepository.save(user);
            return true;
        }
        return false;
    }
}