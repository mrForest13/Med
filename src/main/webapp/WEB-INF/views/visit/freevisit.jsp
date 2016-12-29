<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				<td><a class="btn btn-primary" href="toDo">rezerwuj</a></td>
			</tr>
		</c:forEach>
	</table>

</div>
