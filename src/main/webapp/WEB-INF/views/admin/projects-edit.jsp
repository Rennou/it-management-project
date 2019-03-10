<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../script-include.jsp" />
<title>Nouveau projet</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../main-menu.jsp" />
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-2"><jsp:include page="left-menu.jsp" /></div>
			<div class="col-sm-10">
				<form:form method="POST" action="/projects/add"
					modelAttribute="projectForm">
					<c:if test="${not empty message}">
						<span style="color: red;"><b>${message}</b></span>
					</c:if>
					<table class="editform">
						<caption style="caption-side: top;">Nouveau Projet</caption>
						<tr>
							<td colspan="2">
								<div class="form-group">
									<form:label path="project.code" for="code">Code:</form:label>
									<form:input path="project.code" maxlength="10" size="30"
										type="text" class="form-control" id="code"
										placeholder="Entrer le code" required="true" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="form-group">
									<form:label path="project.description" for="code">Description:</form:label>
									<form:textarea path="project.description" class="form-control"
										rows="3" id="description" placeholder="Entrer la description"
										required="true"></form:textarea>
								</div>
							</td>
						</tr>

						<tr>
							<td>
								<div class="form-group">
									<form:label path="project.startDate" for="startDate">Date de début:</form:label>
									<form:input path="project.startDate" 
										type="date" class="form-control" id="startDate"
										placeholder="Entrer la date du début" required="true" />
								</div>
							</td>
							
							<td>
								<div class="form-group">
									<form:label path="managerId" for="managers">Gestionnaire:</form:label>
									<form:select path="managerId" id = "managers" class="form-control">
										<form:options items="${projectForm.managers}"  itemValue = "username" itemLabel = "fullName"/>
									</form:select>
								</div>
							</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<div class="form-group">
									<form:label path="ressourceIds" for="resource">Resources du projet:</form:label>
									<form:select path="ressourceIds" id = "resource" class="form-control" multiple ="true">
										<form:options items="${projectForm.resources}" itemValue = "username" itemLabel = "nameFunction"/>
									</form:select>
								</div>
							</td>
						</tr>
						
						<tr>
							 <td colspan = "2" style = "align:center;">
								<input class="btn btn-primary" type="submit" value="Soumettre"/> &nbsp;
								<a class="btn btn-primary" href="/projects/list" role="button">Annuler</a>
							</td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>