<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">

	<table class="table table-striped">
		<caption>Dostepne Skierowania</caption>
		<tr>
			<th>Data Wystawienia</th>
			<th>Typ Wizyty / Badania</th>
			<th>Lekarz</th>
		</tr>

		<c:forEach var="referal" items="${referalListN}">
			<tr>
				<td>${referal.dateOfissue}</td>
				<td>${referal.getVisitType().visitType}</td>
				<td>${referal.getDoctor().firstName}
					${referal.getDoctor().lastName}</td>
			</tr>
		</c:forEach>
	</table>

	<table class="table table-striped">
		<caption>Wykorzystane Skierowania</caption>
		<tr>
			<th>Data Wystawienia</th>
			<th>Typ Wizyty / Badania</th>
			<th>Lekarz</th>
		</tr>

		<c:forEach var="referal" items="${referalListY}">
			<tr>
				<td>${referal.dateOfissue}</td>
				<td>${referal.getVisitType().visitType}</td>
				<td>${referal.getDoctor().firstName}
					${referal.getDoctor().lastName}</td>
			</tr>
		</c:forEach>
	</table>

</div>