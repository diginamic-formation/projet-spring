package fr.diginamic.dto;

import fr.diginamic.entities.Role;

public class FilmRoleDto {

	private int id;
	private String actor;
	private String role;
	private String referenceNumber;

	public FilmRoleDto(Role role) {
		this.id = role.getPerson().getId();
		this.actor = role.getPerson().getFullName();
		this.referenceNumber = role.getPerson().getReferenceNumber();
		this.role = role.getRoleName();

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
}
