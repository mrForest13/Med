<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
		Data urodzenia: <input type="text" name="birthData" /><br />
		Pesel: <input type="text" name="pesel" /><br />  
		<input type="radio" name="gender" value="M" checked> Mezczynza
  		<input type="radio" name="gender" value="K"> Kobieta<br>
		
		Nazwa uzytkownika: <input type="text" name="username" /><br/>
		Haslo: <input type="password" name="password1" /><br/>
		Powtorz haslo: <input type="password" name="password2" /><br/>
		
		<input type="submit" value="Zarejestruj" />
	</form>
</body>
</html>