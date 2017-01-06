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
			<li><a href="${contextPath}/login">Logowanie</a></li>
			<li><a href="${contextPath}/registration">Rejestracja</a></li>
		</ul>
	</div>
</nav>