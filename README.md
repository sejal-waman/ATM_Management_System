# ATM Management System

The ATM Management System is a comprehensive solution developed using **Spring Boot**, **JSP**, **Spring Data JPA**, and **MySQL**. This system provides functionalities such as user registration, login, account management, PIN management, and transaction handling. Additionally, email notifications are implemented for secure ATM card number generation, account verification, and PIN reset functionalities.

## Features

- **User Registration**: Allows users to register by providing necessary details. Email verification is required to prevent duplicate accounts.
- **Login System**: Provides secure login functionality using username and password.
- **PIN Management**: Allows users to reset their PIN with email verification.
- **ATM Transaction**: Enables users to withdraw, deposit, and check their balance.
- **Email Notifications**: Sends email notifications for events like account verification, ATM card generation, and PIN reset.
- **Session-Based Authorization**: Ensures user security through session-based login.
- **User Role Management**: Differentiates users by roles like **Admin** and **User**.

## Technologies Used

- **Backend**: Java, Spring Boot
- **Database**: MySQL
- **Frontend**: JSP (JavaServer Pages), HTML, CSS
- **Email**: JavaMailSender (for sending emails)
- **ORM**: Spring Data JPA
- **Authentication**: Session-based authentication
- **Email Service**: SMTP email configuration stored in the database
- **Validation**: Form and field validation using Spring Boot validators

## Prerequisites

Before running the project, ensure you have the following installed:

- JDK 11 or later
- Spring Boot
- MySQL Database
- Maven (for project management)

## Setup Instructions

### 1. Clone the Repository

Clone the repository to your local machine:

```bash```
git clone https://github.com/your-username/atm-management-system.git
cd atm-management-system

### 2. Database Setup
Create a MySQL database (e.g., atm_system).

Configure the application.properties file to include your MySQL database credentials:

### properties:-
spring.datasource.url=jdbc:mysql://localhost:3306/atm_system
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

### 3. Configure Email
Set up the email configuration in your application.properties file to enable email notifications.

### properties:-
spring.mail.host=smtp.your-email-provider.com
spring.mail.port=587
spring.mail.username=your-email@example.com
spring.mail.password=your-email-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

### 4. Run the Application
You can run the application by using the following command:
mvn spring-boot:run
Alternatively, you can run the project directly from your IDE.

### 5. Access the Application
Once the application is running, open your browser and go to:

http://localhost:8080

You will be presented with the login screen where you can enter your credentials to access the system.




### *Role-Based Dashboards*
Admin: Can manage users, view transaction history, and monitor system status.

User: Can manage their own account, perform transactions, and view their transaction history.

Contributing:
If you'd like to contribute to this project, feel free to fork the repository, make improvements, and submit a pull request. All contributions are welcome!

License:
This project is licensed under the MIT License - see the LICENSE file for details.

Contact:
If you have any questions or suggestions, feel free to contact me at [sejalwaman@gmail.com].



### Explanation:
- **Setup Instructions**: Guides users through the setup process, including cloning the repo, configuring the database, and setting up email settings.
- **Technologies Used**: Lists the tech stack used for the project.
- **Project Structure**: Helps developers understand how the project is organized.
- **Role-Based Dashboards**: Provides insight into the different user roles and their privileges.
- **Contributing**: Encourages others to contribute to the project.

You can adjust it to better fit your project, such as adding more detailed descriptions or instructions!
