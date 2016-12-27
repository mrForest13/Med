<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>Spittr</title>
</head>
<body>
	<h1>Rejestracja</h1>
	<form method="POST">
		Imie: <input type="text" name="firstName" /><br /> 
		Nazwisko: <input type="text" name="lastName" /><br /> 
		Adres e-mail: <input type="text" name="email" /><br /> 
		Nr telefonu: <input type="text" name="phone" /><br /> 
		Data urodzenia: <input type="text" name="birthDate" /><br />
		Pesel: <input type="text" name="pesel" /><br />  
		<input type="radio" name="gender" value="M" checked> Mezczynza
  		<input type="radio" name="gender" value="K"> Kobieta<br>
		
		Nazwa uzytkownika: <input type="text" name="login" /><br/>
		Haslo: <input type="password" name="password" /><br/>
		Powtorz haslo: <input type="password" name="password2" /><br/>
		
		<input type="submit" value="Zarejestruj" />
	</form>
</body>
</html>