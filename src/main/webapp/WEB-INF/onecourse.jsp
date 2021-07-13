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
	<h1>${course.name}</h1>
	Instructor: ${course.instructor}<br>
	Sign Ups: <c:set var="count" value="0"/>
		<c:forEach items="${course.users}" var="user">
			<c:set var="count" value="${count + 1}"/>
		</c:forEach>
		${count} / ${course.capacity}
	
	<table>
	<thead>
	<tr>
	<th>Name</th>
	<th>Sign Up Date</th>
	<th>Action</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${course.users}" var="user">
	<tr>
		<td>${user.name }</td>
		<td>${user.createdAt }</td>
		<td>
		<c:if test="${myId == user.id}">
			<form:form action="/coursesapi/users/${course.id}" method="delete" modelAttribute="coursedelete">
			<input type="submit" value="Remove">
			</form:form>
		</c:if>
		</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<br>
	<a href="/courses/edit/${course.id}"><button>Edit</button></a>
	<form:form action="/coursesapi/${course.id}" method="delete">
	<input type="submit" value="Delete">
	</form:form>
	
    </div>
    
</body>
</html>