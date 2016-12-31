<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">


	<div class="row">
		<div class="col-md-6 col-md-offset-4">

			<h1>Moje Dane:</h1>
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
					<div class="col-sm-6">
						<input type="email" name="email" class="form-control" id="email"
							placeholder="${user.email}">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-4 col-form-label">Telefon:</label>
					<div class="col-sm-6">
						<input type="tel" name="phone" class="form-control" id="phone"
							placeholder="${user.phone}">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-4 col-form-label">Login:</label>
					<div class="col-sm-6">
						<input type="text" name="login" class="form-control" id="login"
							placeholder="${user.login}">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-4 col-form-label">Stare haslo:</label>
					<div class="col-sm-6">
						<input type="password" name="oldpassword" class="form-control"
							id="oldpassword">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-4 col-form-label">Nowe haslo:</label>
					<div class="col-sm-6">
						<input type="password" name="newpassword" class="form-control"
							id="newpassword">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-md-6 col-md-offset-4">
						<button type="submit" class="btn btn-primary">Zmien Dane</button>
					</div>
				</div>

			</form>
		</div>
	</div>

</div>




