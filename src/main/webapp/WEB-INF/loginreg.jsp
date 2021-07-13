<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
    <div id="wrapper">
        <div id="topnav">
        <h1>Welcome</h1>
        </div>
	<div id="flexbox">
            <div id="box">
		<h2>Register</h2>
		<p><form:errors path="register.*"/></p>
			<form:form action="/user/new" method="post" modelAttribute="register">
			<p>
			<form:label path="name">Name:</form:label>
			<form:input path="name"/>
			</p>
			<p>
			<form:label path="email">Email:</form:label>
			<form:input path="email"/>
			</p>
			<p>
			<form:label path="password">Password:</form:label>
			<form:password path="password"/>
			</p>
			<p>
			<form:label path="passwordConfirmation">PW Confirmation:</form:label>
			<form:password path="passwordConfirmation"/>
			</p>
			<input type="submit" value="Create">
			</form:form>
            </div>

            <div id="box">
            <h2>Login</h2>
            ${loginerror}
                <form action="/user" method="post">
                <p>
                	Email:
                	<input type="text" name="loginuser"/>
                <p>
                <p>
                	Password:
                	<input type="password" name="loginpassword"/>
                </p>
                <input type="submit" value="Login">
                </form>
                
                
            </div>

        </div>

    </div>
    
</body>
</html>