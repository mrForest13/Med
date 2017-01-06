<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<table class="table table-striped">
		<caption>Dodaj recepte</caption>
		<tr>
			<th>Data wystawienia</th>
			<th>Lekarz Wystawiajacy</th>
			<th>Leki</th>
			<th/>
		</tr>

		<c:forEach var="prescription" items="${prescriptionList}">
			<tr>
				<td>${prescription.dateOfIssue}</td>
				<td>${prescription.getDoctor().firstName}
					${prescription.getDoctor().lastName}</td>
				<td><c:forEach var="medicament"
						items="${prescription.getMedicamentList()}">
					${medicament.lekName} , 
				</c:forEach></td>
				<td><a class="btn btn-primary" href="">Pobierz pdf</a></td>
			</tr>
		</c:forEach>
	</table>
	<table class="table table-striped">
		<caption>Recepty</caption>
		<tr>
			<th>Data wystawienia</th>
			<th>Lekarz Wystawiajacy</th>
			<th>Leki</th>
			<th/>
		</tr>

		<c:forEach var="prescription" items="${prescriptionList}">
			<tr>
				<td>${prescription.dateOfIssue}</td>
				<td>${prescription.getDoctor().firstName}
					${prescription.getDoctor().lastName}</td>
				<td><c:forEach var="medicament"
						items="${prescription.getMedicamentList()}">
					${medicament.lekName} , 
				</c:forEach></td>
				<td><a class="btn btn-primary" href="">Pobierz pdf</a></td>
			</tr>
		</c:forEach>
	</table>

</div>