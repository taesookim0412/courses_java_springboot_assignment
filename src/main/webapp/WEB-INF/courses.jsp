<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
        <h1>Welcome, ${user.name}</h1>
        </div>
	<h3>Courses</h3>
	<table>
	<thead>
	<tr>
	<th>Course</th>
	<th>Instructor</th>
	<th>Signups</th>
	<th>Action</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${courses}" var="course">
	<tr>
	<td><a href="/courses/${course.id}">${course.name}</a></td>
	<td>${course.instructor}</td>
	<td>
	<c:set var="count" value="0"/>
		<c:forEach items="${course.users}" var="user">
			<c:set var="count" value="${count + 1}"/>	
	</c:forEach>
${count} / ${course.capacity}</td>
	<td>
	<c:set var="in" value="false"/>
	
	<c:forEach items="${course.users}" var="cUser">
		<c:if test="${cUser.id == user.id}">
			<c:set var="in" value="true"/>
		</c:if>
	</c:forEach>

	<c:if test="${count != course.capacity}">
		<c:if test="${in == 'false'}">
		<form action="/coursesapi/users/${course.id}" method="post">
		<input type="submit" value="Add">
		</form>
		
		
		</c:if>
		<c:if test="${in == 'true'}">
		Already Added
		</c:if>
	</c:if>
	<c:if test="${count == course.capacity }">
	Full
	</c:if>
	</td>
	</tr>
	</c:forEach>
	</tbody>
	
	</table>
	
	<a href="/courses/new"><button>Add a course</button></a>

    </div>
    
</body>
</html>