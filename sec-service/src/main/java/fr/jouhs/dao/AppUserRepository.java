package fr.jouhs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.jouhs.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findByUsername(String username);
}
