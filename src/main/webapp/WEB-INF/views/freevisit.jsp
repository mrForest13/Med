<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container">

		<table class="table table-striped">
			<tr>
				<th>Data wizyty</th>
				<th>Lekarz</th>
				<th>Rodzaj</th>
				<th></th>
			</tr>

			<c:forEach var="visit" items="${visitList}">
				<tr>
					<td>${visit.visitDateFrom}</td>
					<td>${visit.getDoctor().firstName}
						${visit.getDoctor().lastName}</td>
					<td>${visit.getVisitType().visitType}</td>
					<td><a class="btn btn-success" href="toDo">rezerwuj</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>