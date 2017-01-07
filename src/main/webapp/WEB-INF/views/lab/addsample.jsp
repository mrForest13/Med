<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">

	<div class="row">
		<div class="col-md-6 col-md-offset-3">

			<form>
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">Result</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="inputPassword3">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">Standard
						Positive</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">Standard
						Negative</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="inputPassword3">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">Unit</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3">
					</div>
				</div>
				<div class="form-group row">
					<label for="exampleTextarea" class="col-sm-2 col-form-label">Description</label>
					<div class="col-sm-10">
						<textarea class="form-control" id="exampleTextarea" rows="3"></textarea>
					</div>
				</div>
				<div class="form-group row">
					<div class="offset-sm-2 col-sm-10">
						<button type="submit" class="btn btn-primary">Dodaj</button>
						<button type="submit" class="btn btn-primary">Zatwierdz</button>
					</div>
				</div>
			</form>

		</div>


		<table class="table table-striped">
			<caption>Badania</caption>
			<tr>
				<th>Name</th>
				<th>Result</th>
				<th>Standard Positive</th>
				<th>Standard Negative</th>
				<th>Unit</th>
				<th>Description</th>
			</tr>

			<c:forEach var="sample" items="${sampleList}">
				<tr>
					<td>${sample.name}</td>
					<td>${sample.result}</td>
					<td>${sample.standardPositive}</td>
					<td>${sample.standardNegative}</td>
					<td>${sample.unit}</td>
					<td>${sample.description}</td>
				</tr>
			</c:forEach>
		</table>

	</div>

</div>
