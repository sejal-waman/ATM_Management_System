package main.com.java.Repository;

import main.com.java.Entity.CustomerSupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSupportRepository extends JpaRepository<CustomerSupport, Long>
{
	
}
