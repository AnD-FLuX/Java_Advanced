<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<h2>Enter your login and password</h2>
	<form action="login" method ="post">
	
		<label for="login">Login:</label><br> <input name="login"><br><br>
		<label for="password">Password:</label><br> <input type="password" name="password"><br><br>
		
		<input type="submit" value="LogIn">
	</form>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>