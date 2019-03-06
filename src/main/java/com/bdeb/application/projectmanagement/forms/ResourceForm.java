package com.bdeb.application.projectmanagement.forms;

import com.bdeb.application.projectmanagement.model.Resource;

public class ResourceForm {

	private Resource resource;
	private String role;
	private String email;
	private String phone;
	private String password;
	private String passwordConfirm;
	
	

	public ResourceForm() {
		super();
		resource = new Resource();
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	
	public String valider() {
		if(!password.equals(passwordConfirm)) {
			return "Erreur de validation des données: Mot de passe et confirm mot de passe ne sont pas identiques";
		}
		if("NONE".equals(role)) {
			return "Erreur de validation des données: Veuiller selectionner la fonction";
		}
		return null;
	}

}
