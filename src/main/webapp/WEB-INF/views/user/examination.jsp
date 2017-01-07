<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">

	<table class="table">
		<tr>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="lab" items="${labList}">
			<tr>
				<td>
					<table class="table">
						<caption>Badania</caption>
						<tr>
							<th>Data wizyty</th>
							<th>Lekarz</th>
							<th>Rodzaj</th>
						</tr>


						<tr class="info">
							<td>${lab.getVisit().visitDateFrom}</td>
							<td>${lab.getVisit().getDoctor().firstName}
								${lab.getVisit().getDoctor().lastName}</td>
							<td>${lab.getVisit().getVisitType().visitType}</td>
						</tr>

					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="table table-striped">
						<tr>
							<th>Name</th>
							<th>Result</th>
							<th>Standard Positive</th>
							<th>Standard Negative</th>
							<th>Unit</th>
							<th>Description</th>
						</tr>

						<c:forEach var="sample" items="${lab.getSample()}">
							<tr>
								<td>${sample.name}</td>
								<td>${sample.result}</td>
								<td>${sample.standardPositive}</td>
								<td>${sample.standardNegative}</td>
								<td>${sample.unit}</td>
								<td>${sample.description}</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</c:forEach>
	</table>




</div>