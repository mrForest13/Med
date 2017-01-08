<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="container">

	<table class="table table-striped">
		<caption>Wizyty / Badania</caption>
		<tr>
			<th>Data wizyty</th>
			<th>Lekarz</th>
			<th>Rodzaj</th>
			<th></th>
		</tr>

		<c:forEach var="visit" items="${visitList}">
			<tr>
				<td>${visit.visitDateFrom}</td>
				<td>${visit.getPatient().firstName}
					${visit.getPatient().lastName}</td>
				<td>${visit.getVisitType().visitType}</td>
				<td><a class="btn btn-primary"
					href="${contextPath}/doktor/patient/${visit.getPatient().getId()}/note/${visit.getId()}">dodaj
						notke</a> <c:if
						test="${visit.getVisitType().visitType == 'Laboratorium'}">
						<a class="btn btn-primary"
							href="${contextPath}/doktor/patient/${visit.getPatient().getId()}/examination/${visit.getId()}">wyniki</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>

</div>
