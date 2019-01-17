package fr.jouhs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.jouhs.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	public AppRole findByRoleName(String roleName);
}
