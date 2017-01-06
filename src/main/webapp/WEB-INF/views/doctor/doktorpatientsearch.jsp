<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">


	<div class="row">
		<div class="col-md-6 col-md-offset-4">

			<form class="form-inline" method="POST"
				action="${contextPath}/med-1/service/patient">
				<div class="form-group">
					<label for="email">Pesel:</label> <input type="text"
						class="form-control" name="pesel" id="pesel">
				</div>
				<button type="submit" class="btn btn-primary">Szukaj</button>
			</form>

		</div>
	</div>

	<table class="table table-striped">
		<caption>Umowione Wizyty / Badania</caption>
		<tr>
			<th>Data wizyty</th>
			<th>Lekarz</th>
			<th>Rodzaj</th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach var="visit" items="${visitList}">
			<tr>
				<td>${visit.visitDateFrom}</td>
				<td>${visit.getDoctor().firstName}
					${visit.getDoctor().lastName}</td>
				<td>${visit.getVisitType().visitType}</td>
				<td><a class="btn btn-primary"
					href="${contextPath}/med-1/doctor/cancel/${visit.getId()}">wybierz</a></td>
			</tr>
		</c:forEach>
	</table>

	<c:if test="${not empty pesel}">

		<br/><br/><br/>
	
		<div class="row">
			<div class="col-md-8 col-md-offset-2">

				<form class="form-inline" method="POST"
					action="${contextPath}/med-1/user/visit">
					<div class="form-group">
						<select class="form-control" name="visitType" id="sel1">
							<option value="" selected>-- Rodzaj Wizyty Lub Badania
								--</option>
							<c:forEach var="visitType" items="${visitTypeList}">
								<option>${visitType.visitType}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<select class="form-control" name="lastName" id="sel2">
							<option value="" selected>-- Nazwisko Lekarza --</option>
							<c:forEach var="doctor" items="${doctorList}">
								<option>${doctor.lastName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<input type="date" name="date" class="form-control" id="date">
					</div>
					<button type="submit" class="btn btn-primary">Szukaj</button>
				</form>
			</div>
		</div>
	
	</c:if>

</div>