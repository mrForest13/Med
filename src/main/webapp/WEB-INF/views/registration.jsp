<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">

	<div class="row">
		<div class="col-md-6 col-md-offset-3">

			<h2>Rejestracja</h2>
			<form method="POST">
				<div class="form-group">
					<label for="imie">Imie:</label> <input type="text" name="firstName"
						class="form-control" id="imie">
				</div>
				<div class="form-group">
					<label for="nazwisko">Nazwisko:</label> <input type="text"
						name="lastName" class="form-control" id="nazwisko">
				</div>
				<div class="form-group">
					<label for="email">Adres e-mail:</label> <input type="email"
						name="email" class="form-control" id="email">
				</div>
				<div class="form-group">
					<label for="phone">Nr telefonu:</label> <input type="tel"
						name="phone" class="form-control" id="phone">
				</div>
				<div class="form-group">
					<label for="date">Data urodzenia:</label> <input type="date"
						name="birthDate" class="form-control" id="date">
				</div>
				<div class="form-group">
					<label for="pesel">Pesel:</label> <input type="text" name="pesel"
						class="form-control" id="pesel">
				</div>

				<fieldset class="form-group">
					<div class="form-check">
						<label class="form-check-label"> <input type="radio"
							class="form-check-input" name="gender" id="optionsRadios1"
							value="M" checked> Mezczynza
						</label> <label class="form-check-label"> <input type="radio"
							class="form-check-input" name="gender" id="optionsRadios2"
							value="K"> Kobieta
						</label>
					</div>
				</fieldset>


				<div class="form-group">
					<label for="login">Nazwa uzytkownika:</label> <input type="text"
						name="login" class="form-control" id="login">
				</div>
				<div class="form-group">
					<label for="password">Haslo:</label> <input type="password"
						name="password" class="form-control" id="password">
				</div>
				<div class="form-group">
					<label for="password">Powtorz haslo:</label> <input type="password"
						name="password2" class="form-control" id="password">
				</div>

				<button type="submit" class="btn btn-primary">Zarejestruj</button>

			</form>

		</div>
	</div>

</div>

