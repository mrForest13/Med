<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">



	<table class="table table-striped">
		<caption>Wyniki</caption>
		<tr>
			<th>Name</th>
			<th>Result</th>
			<th>Standard Positive</th>
			<th>Standard Negative</th>
			<th>Unit</th>
			<th>Description</th>
		</tr>

		<c:forEach var="sample" items="${labList}">
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

</div>