<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ATM Registration</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>
    <div class="container">
        <h2>Register for ATM Account</h2>

        <!-- Display error message if email already exists -->
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <c:if test="${not empty message}">
            <p style="color: green;">${message}</p>
        </c:if>

        <form action="/register_data" method="post">
            <label>Name:</label>
            <input type="text" name="name" required>
            
            <label>Email:</label>
            <input type="email" name="email" required>
            
            <label>Date of Birth:</label>
            <input type="date" name="dob" required>
            
            <label>Phone Number:</label>
            <input type="text" name="phone" pattern="[0-9]{10}" required>
            
            <label>Address:</label>
            <textarea name="address" required></textarea>
            
            <label>Gender:</label>
            <select name="gender" required>
                <option value="">Select Gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select>
            
            <label>Account Type:</label>
            <select name="accountType" required>
                <option value="">Select Account Type</option>
                <option value="Savings">Savings</option>
                <option value="Current">Current</option>
            </select>
            
            <label>PIN (4 Digits):</label>
            <input type="password" name="pin" pattern="[0-9]{4}" required>
            
            <button type="submit">Register</button>
        </form>
        <p>Already have an account? <a href="/login">Login here</a></p>
    </div>
</body>
</html>
