package main.com.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.com.java.Entity.ATMUser;

@Repository
public interface ATMRepository extends JpaRepository<ATMUser, Long>
{
    ATMUser findByEmail(String email); // Method to check if email exists
    boolean existsByEmail(String email); // Method to check if email exists

	
}
