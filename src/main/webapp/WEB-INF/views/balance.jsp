<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.com.java.Entity.ATMUser" %>
<%@ page isELIgnored="false" %>

<%
    ATMUser user = (ATMUser) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp?error=Please log in first.");
        return;
    }
%>

<html>
<head>
    <title>Check Balance</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Your Current Balance</h2>
        <p>₹ <%= request.getAttribute("balance") %></p>
        <a href="/dashboard">Back to Dashboard</a>
    </div>
</body>
</html>
