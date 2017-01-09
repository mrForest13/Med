<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">

	<div class="row">
		<div class="col-md-6 col-md-offset-3">

			<h2>Dodaj Wizyte</h2>
			<br/>
			<form method="POST">
				<div class="form-group">
					<label for="visitType">Rodzaj wizyty</label><select class="form-control" name="visitType" id="sel1">
						<c:forEach var="visitType" items="${visitTypeList}">
							<option value="${visitType.id}">${visitType.visitType}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="doctor">Lekarz</label><select class="form-control" name="doctor" id="sel2">
						<c:forEach var="doctor" items="${doctorList}">
							<option value="${doctor.id}">${doctor.firstName} ${doctor.lastName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="date">Data Wizyty:</label> <input type="date"
						name="date" class="form-control" id="date">
				</div>
				<div class="form-group">
					<label for="start">Czas Rozpoczecia:</label> <input type="time"
						name="start" class="form-control" id="start">
				</div>
				<div class="form-group">
					<label for="end">Czas Zakonczenia:</label> <input type="time"
						name="end" class="form-control" id="end">
				</div>
				<div class="form-group">
					<label for="price">Cena:</label> <input type="number" name="price"
						class="form-control" id="price">
				</div>

				<button type="submit" class="btn btn-primary">Dodaj</button>

			</form>

		</div>
	</div>

</div>

