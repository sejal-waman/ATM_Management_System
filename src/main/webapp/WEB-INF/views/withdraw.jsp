<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.com.java.Entity.ATMUser" %>
<%
    ATMUser user = (ATMUser) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp?error=Please log in first.");
        return;
    }
%>

<html>
<head>
    <title>Withdrawal Status</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Withdrawal Status</h2>
        <% 
            String message = (String) request.getAttribute("message");
            String error = (String) request.getAttribute("error");
            if (message != null) {
        %>
            <p style="color: green;"><%= message %></p>
        <% } else if (error != null) { %>
            <p style="color: red;"><%= error %></p>
        <% } %>

        <a href="/dashboard">Back to Dashboard</a>
    </div>
</body>
</html>
