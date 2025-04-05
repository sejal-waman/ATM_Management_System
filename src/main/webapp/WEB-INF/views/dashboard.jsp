<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.com.java.Entity.ATMUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    ATMUser user = (ATMUser) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("/login?error=Please log in first.");
        return;
    }
%>

<!-- Set Locale and Load Messages -->
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale.language : 'en'}" />
<fmt:setBundle basename="messages/messages" />

<html>
<head>
    <title><fmt:message key="dashboard.title"/></title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <!-- Chatbot Icon and Box (Top Right Side) -->
    <div class="chatbot-icon" onclick="toggleChatbot()">
        <img src="images/chatbot-logo.png" alt="Chatbot" />
    </div>

    <div id="chatbot-box" class="chatbot-box" style="display:none;">
        <h3>Chat with Our Chatbot</h3>
        <form id="chatbotForm">
            <label>Ask a Question:</label>
            <input type="text" id="question" name="question" required>
            <button type="button" onclick="askChatbot()">Ask</button>
        </form>
        <p id="chatbotResponse"></p>
    </div>

    <!-- Dashboard Container -->
    <div class="dashboard-container">
        <!-- Language Selection Form -->
        <form action="/changeLanguage" method="get">
            <label><fmt:message key="select.language"/></label>
            <select name="lang" onchange="this.form.submit()">
                <option value="en" ${sessionScope.locale.language == 'en' ? 'selected' : ''}>English</option>
                <option value="hi" ${sessionScope.locale.language == 'hi' ? 'selected' : ''}>हिंदी</option>
                <option value="mr" ${sessionScope.locale.language == 'mr' ? 'selected' : ''}>मराठी</option>
            </select>
        </form>

        <!-- Welcome Message -->
        <h2><fmt:message key="welcome.message"/>, <%= user.getName() %>!</h2>
        <p><fmt:message key="card.number.label"/> <strong><%= user.getCardNumber() %></strong></p>

        <!-- Options Section -->
        <div class="options">
            <form action="/withdraw" method="post">
                <label><fmt:message key="withdraw.label"/></label>
                <input type="number" name="amount" required>
                <input type="hidden" name="email" value="<%= user.getEmail() %>">
                <button type="submit"><fmt:message key="withdraw.button"/></button>
            </form>

            <form action="/deposit" method="post">
                <label><fmt:message key="deposit.label"/></label>
                <input type="number" name="amount" required>
                <input type="hidden" name="email" value="<%= user.getEmail() %>">
                <button type="submit"><fmt:message key="deposit.button"/></button>
            </form>

            <form action="/balance" method="get">
                <input type="hidden" name="email" value="<%= user.getEmail() %>">
                <button type="submit"><fmt:message key="check.balance.button"/></button>
            </form>

            <form action="/statement" method="get">
                <input type="hidden" name="email" value="<%= user.getEmail() %>">
                <button type="submit"><fmt:message key="mini.statement.button"/></button>
            </form>

            <form action="/pdf/pdf_statement" method="get">
                <input type="hidden" name="email" value="<%= user.getEmail() %>">
                <button type="submit"><fmt:message key="download.pdf.button"/></button>
            </form>

            <form action="/logout" method="post">
                <button type="submit"><fmt:message key="logout.button"/></button>
            </form>
        </div>

        <!-- Contact Support Section -->
        <div class="support-section">
            <h3>Contact Customer Support</h3>
            <form action="/submitCustomerSupport" method="post">
                <input type="hidden" name="email" value="<%= user.getEmail() %>">
                <label>Subject:</label>
                <input type="text" name="subject" required>
                <label>Description:</label>
                <textarea name="description" required></textarea>
                <button type="submit">Submit Support Request</button>
            </form>
        </div>
    </div>

    <!-- JavaScript for Chatbot -->
    <script>
        function toggleChatbot() {
            const chatBox = document.getElementById("chatbot-box");
            chatBox.style.display = chatBox.style.display === "none" ? "block" : "none";
        }

        function askChatbot() {
            const question = document.getElementById("question").value;
            fetch('/chatbot/ask?question=' + encodeURIComponent(question), {
                method: 'POST'
            })
            .then(response => response.text())
            .then(data => {
                document.getElementById("chatbotResponse").innerText = data;
            })
            .catch(error => {
                console.error('Error:', error);
                document.getElementById("chatbotResponse").innerText = "Sorry, something went wrong.";
            });
        }
    </script>

</body>
</html>
