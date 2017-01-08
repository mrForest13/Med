<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">


	<div class="row">
		<div class="col-md-6 col-md-offset-4">

			<form class="form-inline" method="POST"
				action="${contextPath}/med-1/doktor/patient">
				<div class="form-group">
					<label for="email">Pesel:</label> <input type="text"
						class="form-control" name="pesel" id="pesel">
				</div>
				<button type="submit" class="btn btn-primary">Szukaj</button>
			</form>

		</div>
	</div>

	<c:if test="${not empty user.firstName}">

		<br />
		<br />
		<br />

		<div class="row">
			<div class="col-md-6 col-md-offset-4">

				<h1>Dane Pacjenta:</h1>
				<br />
				<form method="POST">
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Imie:</label>
						<div class="col-sm-4">
							<p class="form-control-static mb-0">${user.firstName}</p>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Nazwisko:</label>
						<div class="col-sm-4">
							<p class="form-control-static mb-0">${user.lastName}</p>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">pesel:</label>
						<div class="col-sm-4">
							<p class="form-control-static mb-0">${user.pesel}</p>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Data urodzenia:</label>
						<div class="col-sm-4">
							<p class="form-control-static mb-0">${user.birthDate}</p>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Adres e-mail:</label>
						<div class="col-sm-4">
							<p class="form-control-static mb-0">${user.email}</p>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Telefon:</label>
						<div class="col-sm-4">
							<p class="form-control-static mb-0">${user.phone}</p>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm-8 col-form-label">
							<a class="btn btn-primary"
								href="${contextPath}/med-1/doktor/patient/${user.getId()}/visit">Wizyty / Badania</a>
							<a class="btn btn-primary"
								href="${contextPath}/med-1/doktor/patient/${user.getId()}/referal">skierowania</a>
							<a class="btn btn-primary"
								href="${contextPath}/med-1/doktor/patient/${user.getId()}/referal">Recepty</a>
						</div>
					</div>

				</form>
			</div>
		</div>
	</c:if>
</div>