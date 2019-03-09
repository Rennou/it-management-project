<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../script-include.jsp" />
<title>Project Resources List</title>
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
				<a class="btn btn-primary" href="/resources?form" role="button">Nouvelle Ressource</a>
				<br />
				<br />
				<c:if test="${empty resources}">
					La liste des ressources est vide!
				</c:if>

				<c:if test="${not empty resources}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Nom</th>
								<th scope="col">Prénom</th>
								<th scope="col">Rôle</th>
								<th scope="col">Compte crée</th>
								<!--  <th scope="col">Compte crée</th>-->
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resources}" var="resource">
								<tr>
									<td>${resource.username}</td>
									<td>${resource.lastName}</td>
									<td>${resource.firstName}</td>
									<td>${resource.function}</td>
									<td>${resource.accountCreated ? 'Oui' : 'Non'}</td>
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