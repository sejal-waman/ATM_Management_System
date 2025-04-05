package main.com.java.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ChatbotServiceImplementation 
{


	    private static final Map<String, String> faq = new HashMap<>();
	    
	    static
	    {
	        faq.put("reset pin", "To reset your PIN, go to 'Forgot PIN' on the login page.");
	        faq.put("check balance", "You can check your balance from the dashboard by clicking 'Check Balance'.");
	        faq.put("withdraw money", "Go to the dashboard, enter the amount, and click 'Withdraw'.");
	        faq.put("deposit money", "To deposit money, go to the dashboard, enter the amount, and click 'Deposit'.");
	        faq.put("download statement", "Click on 'Download PDF Statement' from the dashboard to download your transaction statement.");
	        faq.put("contact support", "If you need assistance, fill out the 'Contact Support' form available on the dashboard.");
	        faq.put("change pin", "To change your PIN, go to the dashboard, click on 'Forgot PIN', and follow the instructions.");
	        faq.put("forgot card number", "Your card number is available on the dashboard once you log in.");
	        faq.put("transaction history", "You can view your mini statement by clicking on 'Mini Statement' on the dashboard.");
	        faq.put("log out", "Click on 'Logout' from the dashboard to log out of your account.");
	        faq.put("register account", "To register a new account, click on 'Create Account' from the login page.");
	        faq.put("recover password", "If you forgot your password, click on 'Forgot Password' on the login page to reset it.");
	        faq.put("check offers", "Currently, no offers are available. Please check the dashboard regularly for updates.");
	        faq.put("update profile", "To update your profile information, go to 'My Profile' in the dashboard.");
	        faq.put("disable card", "If you want to disable your card, please contact support using the 'Contact Support' form.");
	    }


	    public String getResponse(String query)
	    {
	        query = query.toLowerCase();
	        for (String key : faq.keySet())
	        {
	            if (query.contains(key))
	            {
	                return faq.get(key);
	            }
	        }
	        return "I'm sorry, I couldn't understand your query. Please contact support for further assistance.";
	    }
	    
	   
}



