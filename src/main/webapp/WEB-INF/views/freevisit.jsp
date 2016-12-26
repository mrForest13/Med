<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
<form:select path="visit">
	<form:option value="NONE" label="--- Rodzaj wizyty lub badania ---"/>
    <form:options items="${visitList}" itemLabel="visitType" />
</form:select>
</tr>
<c:forEach var="listValue" items="${visitList}">
		<tr>
		<td>${listValue.visitType}</td>
		</tr>
</c:forEach>
</table>
</body>
</html>