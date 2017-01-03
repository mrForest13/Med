<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="">Przychodnia</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${contextPath}/service/search">Pokaz Pacjenta</a></li>
		</ul>
		<a class="btn btn-danger navbar-btn" href="${contextPath}/logout">Wyloguj</a>
	</div>
</nav>