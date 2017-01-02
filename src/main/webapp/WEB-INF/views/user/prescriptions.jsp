<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">

	<table class="table table-striped">
		<caption>Moje Recepty</caption>
		<tr>
			<th>Data wystawienia</th>
			<th>Lekarz Wystawiajacy</th>
			<th></th>
		</tr>

		<c:forEach var="prescription" items="${prescriptionList}">
			<tr>
				<td>${prescription.dateOfIssue}</td>
				<td>${prescription.getDoctor().firstName}
					${prescription.getDoctor().lastName}</td>
				<td><a class="btn btn-primary" href="${contextPath}/med-1/user/book/${visit.getId()}"> Zobacz Leki</a></td>
			</tr>
		</c:forEach>
	</table>

</div>