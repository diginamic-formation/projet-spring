package fr.diginamic.dto;

import fr.diginamic.entities.Role;

public class FilmRoleDto {

	private String actor;
	private String role;

	public FilmRoleDto(Role role) {
		this.actor = role.getPerson().getFullName();
		this.role = role.getRoleName();

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

}
