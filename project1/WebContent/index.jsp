<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<h3>Enter your registration data:</h3>
	<form action="registration" method ="post">
		<label for="firstName">First name:</label><br> <input name="firstName"><br><br>
		<label for="lastName">Last name:</label><br> <input name="lastName"><br><br>
		<label for="email">Email:</label> <br><input name="email"> <br><br>
		<label for="password">Password:</label> <br><input type="password" name="password"><br><br><br>
		<input type="submit" value="Registration"><br><br>
	</form>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>