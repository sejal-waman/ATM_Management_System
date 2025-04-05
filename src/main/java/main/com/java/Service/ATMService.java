package main.com.java.Service;

import java.util.List;

import main.com.java.Entity.ATMUser;

public interface ATMService 
{

	public ATMUser registerUser(ATMUser atmUser);

	public ATMUser loginUser(String email, String pin);
	
	public String withdrawCash(String email, double amount);

    public String depositCash(String email, double amount);

    public String checkBalance(String email);

    public List<String> getMiniStatement(String email);
    
    ATMUser getUserByEmail(String email);

	public boolean isEmailExists(String email);

	public boolean updatePin(String email, String newPin);

	public String validateResetToken(String token);

	public String generateResetToken(String email);

}
