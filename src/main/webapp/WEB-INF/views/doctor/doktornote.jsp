<%@page import="com.app.path.PathVariable"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="container">


	<div class="row">
		<div class="col-md-8 col-md-offset-2">

			<h2>Notatka</h2>
			<form method="POST">
				<div class="form-group row">
					<div class="col-sm-15">
						<textarea class="form-control" id="note" name="note" rows="8"><%= request.getAttribute("note") %></textarea>
					</div>
				</div>
				<div class="form-group row">
					<div class="offset-sm-2 col-sm-10">
						<button type="submit" class="btn btn-primary">zatwierdz</button>
						<a class="btn btn-primary"
							href="${contextPath}/doktor/patient/<%= request.getAttribute("id") %>/visit">powrot</a>
					</div>
				</div>
			</form>

		</div>
	</div>

</div>