package fr.jouhs.service;

import fr.jouhs.entities.AppRole;
import fr.jouhs.entities.AppUser;

public interface AccountService {
	public AppUser saveUser(String username, String password, String confirmedPassword);
	public AppRole save(AppRole role);
	public AppUser loadUserByUsername(String username);
	public void addRoleToUser(String username, String roleName);

}
