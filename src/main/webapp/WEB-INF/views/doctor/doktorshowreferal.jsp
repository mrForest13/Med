<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="container">

	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-8">

			<form class="form-inline" method="POST">
				<div class="form-group">
					<label for="email">Rodzaj wizyty:</label> <select
						class="form-control" name="visitType" id="sel1">
						<c:forEach var="visitType" items="${visitTypeList}">
							<option>${visitType.visitType}</option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" class="btn btn-primary">dodaj</button>
				<a class="btn btn-primary"
					href="${contextPath}/doktor/patient?pesel=${pesel}">powrot</a>
			</form>
		</div>

		<div class="col-sm-3"></div>
	</div>

	<br />
	<br />

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