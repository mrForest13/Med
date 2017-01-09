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
					<label for="email">Lek:</label> <select class="form-control"
						name="medicament" id="sel1">
						<c:forEach var="medicament" items="${medicamentList}">
							<option value="${medicament.id}">${medicament.lekName}</option>
						</c:forEach>
					</select>
				</div>
				<c:if test="${empty checkbox}">
					<div class="checkbox">
						<label><input type="checkbox" value="true" name="add">
							Dodatkowe prawa</label>
					</div>
				</c:if>
				<button type="submit" class="btn btn-primary">dodaj</button>
				<a class="btn btn-primary"
					href="${contextPath}/doktor/patient?pesel=${pesel}">zatwierdz</a>
			</form>
		</div>

		<div class="col-sm-3"></div>
	</div>

	<br /> <br />

	<table class="table table-striped">
		<caption>Recepty</caption>
		<tr>
			<th>Data wystawienia</th>
			<th>Lekarz Wystawiajacy</th>
			<th>Leki</th>
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
			</tr>
		</c:forEach>
	</table>

</div>