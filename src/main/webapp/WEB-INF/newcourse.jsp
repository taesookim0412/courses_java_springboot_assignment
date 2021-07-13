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
	<h3>Create a new course</h3>
	<form:errors path="course.*"/>
	<form:form action="/coursesapi" method="post" modelAttribute="course">
	<form:label path="name">Name:</form:label>
	<form:input path="name"/><br>
	<form:label path="instructor">Instructor:</form:label>
	<form:input path="instructor"/><br>
	<form:label path="capacity">Capacity:</form:label>
	<form:input path="capacity"/><br>
	<input type="submit" value="Create">
	</form:form>
	
    </div>
    
</body>
</html>