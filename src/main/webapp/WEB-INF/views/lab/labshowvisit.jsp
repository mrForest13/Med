<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">


	<div class="row">
		<div class="col-md-6 col-md-offset-4">

			<form class="form-inline" method="POST"
				action="${contextPath}/med-1/lab/patient">
				<div class="form-group">
					<label for="email">Pesel:</label> <input type="text"
						class="form-control" name="pesel" id="pesel">
				</div>
				<button type="submit" class="btn btn-primary">Szukaj</button>
			</form>

		</div>
	</div>

	<table class="table table-striped">
		<caption>Badania</caption>
		<tr>
			<th>Data wizyty</th>
			<th>Lekarz</th>
			<th>Rodzaj</th>
			<th></th>
		</tr>

		<c:forEach var="lab" items="${labList}">
			<tr>
				<td>${lab.getVisit().visitDateFrom}</td>
				<td>${lab.getVisit().getDoctor().firstName}
					${lab.getVisit().getDoctor().lastName}</td>
				<td>${lab.getVisit().getVisitType().visitType}</td>
				<td><a class="btn btn-primary"
					href="${contextPath}/med-1/lab/patient/${lab.getId()}">dodaj wyniki</a></td>
			</tr>
		</c:forEach>
	</table>

</div>