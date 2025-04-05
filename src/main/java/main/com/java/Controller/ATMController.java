package main.com.java.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import main.com.java.Entity.ATMUser;
import main.com.java.Service.ATMService;
import main.com.java.Utility.SendMessageMail;

@Controller
public class ATMController {

    @Autowired
    private ATMService atmService;
    
  


    // Helper Method for Session Check
    private boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    // Display Registration Page
    @GetMapping("/register")
    public String viewRegistrationPage(Model model) {
        model.addAttribute("user", new ATMUser());
        return "register";
    }

    // Handle Registration Data
    @PostMapping("/register_data")
    public String registerUser(@ModelAttribute("user") ATMUser user, Model model) {
        // Check if email already exists
        if (atmService.isEmailExists(user.getEmail())) {
            model.addAttribute("error", "Email already used. Please try with a different email.");
            return "register";
        }

        // Proceed with registration if email is unique
        ATMUser registeredUser = atmService.registerUser(user);
        if (registeredUser != null) {
            model.addAttribute("message", "Registration successful. Check your email for your card number.");
            return "login";
        } else {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "register";
        }
    }


    // Show Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Handle Login with Session Management
    @Controller
    public class LoginController {

        @Autowired
        private ATMService atmService;

        @PostMapping("/login_data")
        public String loginUser(@RequestParam String email, @RequestParam String pin, Model model, HttpSession session) {
            try {
                ATMUser user = atmService.loginUser(email, pin);

                if (user != null) {
                    session.setAttribute("user", user); // Store user in session
                    return "redirect:/dashboard";
                } else {
                    model.addAttribute("error", "Invalid email or PIN. Please register first if you don't have an account.");
                    return "login"; // Return to login.jsp
                }
            } catch (Exception e) {
                model.addAttribute("error", "An error occurred: " + e.getMessage());
                e.printStackTrace();
                return "login"; // Return to login.jsp in case of exception
            }
        }
    }


    // Dashboard
    @GetMapping("/dashboard")
    public String showDashboardPage(HttpSession session, Model model) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/login?error=Unauthorized access. Please log in first.";
        }
        ATMUser user = (ATMUser) session.getAttribute("user");
        model.addAttribute("user", user);
        return "dashboard";
    }
 

    // Withdraw Cash
    @PostMapping("/withdraw")
    public String withdrawCash(@RequestParam double amount, HttpSession session, Model model) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/login?error=Unauthorized access. Please log in first.";
        }
        ATMUser user = (ATMUser) session.getAttribute("user");
        String message = atmService.withdrawCash(user.getEmail(), amount);
        model.addAttribute("message", message);
        return "withdraw";
    }

    // Deposit Cash
    @PostMapping("/deposit")
    public String depositCash(@RequestParam double amount, HttpSession session, Model model) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/login?error=Unauthorized access. Please log in first.";
        }
        ATMUser user = (ATMUser) session.getAttribute("user");
        String message = atmService.depositCash(user.getEmail(), amount);
        model.addAttribute("message", message);
        return "deposit";
    }

    // Check Balance
    @GetMapping("/balance")
    public String checkBalance(HttpSession session, Model model) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/login?error=Unauthorized access. Please log in first.";
        }
        ATMUser user = (ATMUser) session.getAttribute("user");
        String balance = atmService.checkBalance(user.getEmail());
        model.addAttribute("balance", balance);
        return "balance";
    }

    // Mini Statement
    @GetMapping("/statement")
    public String getMiniStatement(HttpSession session, Model model) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/login?error=Unauthorized access. Please log in first.";
        }
        ATMUser user = (ATMUser) session.getAttribute("user");
        model.addAttribute("statement", atmService.getMiniStatement(user.getEmail()));
        return "statement";
    }

    // Logout and Invalidate Session

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
        return "redirect:/login?message=You have successfully logged out.";
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate(); // Clear the session
        }
        return "redirect:/login?message=You have successfully logged out.";
    }
    
    
    // Show forgot PIN page
    @GetMapping("/forgotPin")
    public String showForgotPinPage() {
        return "forgotPin";
    }

    
    
    
    @Autowired
    private SendMessageMail sendMessageMail;
    
    // Send Reset PIN Email
    
    @PostMapping("/forgotPin")
    public String sendResetEmail(@RequestParam("email") String email, Model model) {
        ATMUser user = atmService.getUserByEmail(email);

        if (user == null) {
            model.addAttribute("error", "Email not found.");
            return "forgotPin";
        }

        String token = atmService.generateResetToken(email);
        String resetLink = "http://localhost:8080/confirm-reset?email=" + email;
        
        // Send the email
        sendMessageMail.sendMail(email, "PIN Reset Request", "Click here to reset your PIN: " + resetLink);
        System.out.println("Reset link sent to: " + email);

        model.addAttribute("message", "A PIN reset link has been sent to your email.");
        return "forgotPin";
    }


    // Show Reset PIN Page
    @GetMapping("/resetPin")
    public String showResetPinPage(@RequestParam("token") String token, Model model) {
        String email = atmService.validateResetToken(token);
        if (email == null) {
            model.addAttribute("error", "Invalid or expired token.");
            return "error";
        }
        model.addAttribute("email", email);
        return "resetPin";
    }

    // Reset PIN
    @PostMapping("/resetPin")
    public String resetPin(@RequestParam("email") String email, @RequestParam("newPin") String newPin, Model model) {
        if (atmService.updatePin(email, newPin)) {
            model.addAttribute("message", "PIN successfully reset. Please login.");
            return "login";
        }
        model.addAttribute("error", "Failed to reset PIN. Try again.");
        return "resetPin";
    }
    
    @GetMapping("/confirm-reset")
    public String showResetPin(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return "resetPin"; // Ensure resetPin.jsp exists in your views
    }

    
    
}

