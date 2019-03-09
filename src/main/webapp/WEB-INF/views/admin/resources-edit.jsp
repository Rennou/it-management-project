<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../script-include.jsp" />
<title>Project Resources Edit</title>
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
				
				<form:form method="POST" action="/resources/add" modelAttribute="resourceForm">
					<c:if test="${not empty message}">
						<span style = "color:red;"><b>${message}</b></span>
					</c:if>
					<table class = "editform">
					    <caption style="caption-side: top;">Nouvelle Ressource Forme</caption>
						<tr>
							<td>
							<div class="form-group">
								<form:label path= "resource.firstName" for="firstName">Prénom:</form:label> 
								<form:input path ="resource.firstName" maxlength="50" size = "30" 
									type="text" class="form-control" id="firstName" placeholder="Entrer le prénom" required  = "true"/>
							</div>
							</td>
							<td>
							<div class="form-group">
								<form:label path= "resource.lastName" for="lastName">Nom:</form:label> 
								<form:input path ="resource.lastName" maxlength="50" size = "30"
									type="text" class="form-control" id="lastName" placeholder="Entrer le nom" required  = "true"/>
							</div>
							</td>
			
						</tr>
						
						<tr>
							<td>
							<div class="form-group">
								<form:label path= "email" for="email">Courriel:</form:label> 
								<form:input path ="email" maxlength="50" size = "30"
									type="email" class="form-control" id="email" placeholder="Entrer l'adresse émail" required  = "true"/>
							</div>
							</td>
							<td>
							<div class="form-group">
								<form:label path= "phone" for="phone">Tél.:</form:label> 
								<form:input path ="phone" maxlength="12" size = "30"
									type="tel" class="form-control" id="phone" placeholder="Entrer le numéro de téléphone" pattern = "[0-9]{3}-[0-9]{3}-[0-9]{4}" required  = "true"/>
							</div>
							</td>
			
						</tr>
						
						<tr>
							<td>
							<div class="form-group">
								<form:label path= "resource.username" for="username">Nom Utilisateur:</form:label> 
								<form:input path ="resource.username" minlength = "10" maxlength="50" size = "30"
									type="text" class="form-control" id="username" placeholder="Entrer le nom d'utilisateur" required = "true"/>
							</div>
							</td>
							<td>
							<div class="form-group">
							    <form:label path= "resource.username" for="roles">Fonction:</form:label> 
								<form:select id  = "roles" path="role" class="form-control" required  = "true">
								   <form:option value="NONE" label="Selectionner la fonction"/>
								   <form:option value="PM" label="Chef de projet"/>
								   <form:option value="DEV" label="Programmeur Analyste"/>
								   <form:option value="QAA" label="Analsyte en assurance qualitie"/>
								   <form:option value="PO" label="Proprietaire du produit"/>								   
								</form:select>
								
							</div>
							</td>
			
						</tr>
						
						<tr>
							<td>
							<div class="form-group">
								<form:label path= "password" for="password">Mots De Passe:</form:label> 
								<form:input path ="password" minlength = "6" maxlength="8" size = "30"
									type="password" class="form-control" id="password" placeholder="Entrer le mots de passe" required  = "true"/>
							</div>
							</td>
							<td>
							<div class="form-group">
								<form:label path= "passwordConfirm" for="passwordConfirm">Confirmation du Mots De Passe.:</form:label> 
								<form:input path ="passwordConfirm" minlength = "6" maxlength="8" size = "30"
									type="password" class="form-control" id="passwordConfirm" placeholder="Confirmer le mots de passe" required = "true"/>
							</div>
							</td>
			
						</tr>
						
						<tr>
							<td colspan = "2">&nbsp;</td>
						</tr>
						<tr>
							 <td colspan = "2" style = "align:center;">
								<input class="btn btn-primary" type="submit" value="Soumettre"/> &nbsp;
								<a class="btn btn-primary" href="/resources/list" role="button">Annuler</a>
							</td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>