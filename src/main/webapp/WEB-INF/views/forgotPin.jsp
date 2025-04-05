<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot PIN</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Forgot PIN</h2>
    <form action="/forgotPin" method="post">
        <label>Email:</label>
        <input type="email" name="email" required>
        <button type="submit">Send Reset Link</button>
    </form>
    <p>${message}</p>
    <p>${error}</p>
</body>
</html>
