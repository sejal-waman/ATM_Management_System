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
    <title>Mini Statement</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Your Last 5 Transactions</h2>
        
        <% 
            java.util.List<String> statement = (java.util.List<String>) request.getAttribute("statement");
            if (statement == null || statement.isEmpty()) {
        %>
            <p>No transactions found.</p>
        <% } else { %>
            <ul>
                <% for (String txn : statement) { %>
                    <li><%= txn %></li>
                <% } %>
            </ul>
        <% } %>
        
        <a href="/dashboard">Back to Dashboard</a>
    </div>
</body>
</html>
