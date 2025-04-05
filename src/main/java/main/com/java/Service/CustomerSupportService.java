package main.com.java.Service;


import main.com.java.Entity.CustomerSupport;
import main.com.java.Repository.CustomerSupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerSupportService 
{

    @Autowired
    private CustomerSupportRepository customerSupportRepository;

    public void saveSupportRequest(CustomerSupport customerSupport) 
    {
        customerSupportRepository.save(customerSupport);
    }
}
