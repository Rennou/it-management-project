<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../script-include.jsp" />
<title>Projects List</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../main-menu.jsp" />
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-2"><jsp:include page="left-menu.jsp" /></div>
			<div class="col-sm-10">
				<br /> 
				<c:if test="${empty projects}">
					La liste des projets est vide!
				</c:if>

				<c:if test="${not empty projects}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">Code</th>
								<th scope="col">Description</th>
								<th scope="col">Date de démarrage</th>
								<th scope="col">Date de Fin</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${projects}" var="project">
								<tr>
									<td>${project.code}</td>
									<td>${project.description}</td>
									<td>${project.startDate}</td>
									<td>${empty resource.endDate ? 'N/A' : resource.endDate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>

			</div>
		</div>
	</div>
</body>
</html>