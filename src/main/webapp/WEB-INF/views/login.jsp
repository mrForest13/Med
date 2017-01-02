<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="container">

	<div class="col-md-4"></div>
	<div class="col-md-4">

		<h1>Logowanie</h1>

		<form method="POST">


			<div class="form-group">
				<label for="login">Login:</label> <input type="text" name="login"
					class="form-control" id="login">
			</div>
			<div class="form-group">
				<label for="password">Haslo:</label> <input type="password"
					name="password" class="form-control" id="password">
			</div>

			<div class="form-group">
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3">
						<input type="submit" name="login-submit" id="login-submit"
							tabindex="4" class="form-control btn btn-primary" value="Log In">
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="row">
					<div class="col-lg-12">
						<div class="text-center">
							<a href="${contextPath}/registration" tabindex="5"
								class="new user">Nowy uzytkownik? Zarejestruj sie.</a>
						</div>
					</div>
				</div>
			</div>


		</form>

	</div>
	<div class="col-md-4"></div>

</div>
