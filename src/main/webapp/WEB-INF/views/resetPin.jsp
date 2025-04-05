<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset PIN</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Reset Your PIN</h2>
    <form action="/resetPin" method="post">
        <input type="hidden" name="email" value="${email}">
        <label>New PIN (4 Digits):</label>
        <input type="password" name="newPin" pattern="[0-9]{4}" required>
        <button type="submit">Reset PIN</button>
    </form>
    <p>${message}</p>
    <p>${error}</p>
</body>
</html>
