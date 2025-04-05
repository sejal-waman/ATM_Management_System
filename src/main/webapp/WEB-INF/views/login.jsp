<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Login to Your Account</h2>

        <% String error = request.getParameter("error"); %>
        <% if (error != null) { %>
            <p style="color: red;"><%= error %></p>
        <% } %>

		<form action="/login_data" method="post">
		    <label>Email:</label>
		    <input type="email" name="email" required>
		    <label>PIN:</label>
		    <input type="password" name="pin" required>
		    <button type="submit">Login</button>
			
		</form>
		<p>${error}</p>
		    <p>Forgot your PIN? <a href="/forgotPin">Reset PIN</a></p>

        <p>Don't have an account? <a href="/register">Register here</a></p>
    </div>
</body>
</html>
