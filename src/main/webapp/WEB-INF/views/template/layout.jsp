<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script
	src="${pageContext.request.contextPath}/webjars/jquery/1.9.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link
	href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" />

<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<table>
		<tr>
			<c:choose>
				<c:when test="${type == 0}">
					<tiles:insertAttribute name="usermenu" />
				</c:when>
				<c:when test="${type == 1}">
					<tiles:insertAttribute name="doktormenu" />
				</c:when>
				<c:when test="${type == 2}">
					<tiles:insertAttribute name="labmenu" />
				</c:when>
				<c:when test="${type == 3}">
					<tiles:insertAttribute name="servicemenu" />
				</c:when>
				<c:when test="${type == -1}">
					<tiles:insertAttribute name="homemenu" />
				</c:when>
			</c:choose>

		</tr>
	</table>

	<tiles:insertAttribute name="body" />

	<tiles:insertAttribute name="footer" />

</body>
</html>