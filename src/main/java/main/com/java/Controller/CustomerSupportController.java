package main.com.java.Controller;



import main.com.java.Entity.CustomerSupport;
import main.com.java.Repository.CustomerSupportRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerSupportController 
{

    @Autowired
    private CustomerSupportRepository customerSupportRepository;
    @PostMapping("/submitCustomerSupport")
    public String submitSupport(@RequestParam String email, 
                                 @RequestParam String subject, 
                                 @RequestParam String description) 
    {
        CustomerSupport support = new CustomerSupport();
        support.setUserEmail(email);
        support.setSubject(subject);
        support.setDescription(description);
        customerSupportRepository.save(support);
        return "Support submitted successfully!";
    }
}
