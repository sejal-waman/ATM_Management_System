package main.com.java;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "main.com.java")
public class AtmProjectApplication 
{
    public static void main(String[] args) 
    {
        SpringApplication.run(AtmProjectApplication.class, args);
        System.out.println("ATM MACHINE SYSTEM");
    }
}
