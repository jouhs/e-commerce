package fr.jouhs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jouhs.dao.AppRoleRepository;
import fr.jouhs.dao.AppUserRepository;
import fr.jouhs.entities.AppRole;
import fr.jouhs.entities.AppUser;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	private AppUserRepository appUserRepository;
	private AppRoleRepository appRoleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, 
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.appUserRepository = appUserRepository;
		this.appRoleRepository = appRoleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public AppUser saveUser(String username, String password, String confirmedPassword) {
		AppUser user = appUserRepository.findByUsername(username);
		if(user!=null)
			throw new RuntimeException("User already exists");
		if(!password.equals(confirmedPassword))
			throw new RuntimeException("Please confirm your password");
		AppUser appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setActived(true);
		appUser.setPassword(this.bCryptPasswordEncoder.encode(password));
		appUserRepository.save(appUser);
		addRoleToUser(username, "USER");
		return appUser;
	}

	public AppRole save(AppRole role) {
		return appRoleRepository.save(role);
	}

	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}

	public void addRoleToUser(String username, String roleName) {
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findByRoleName(roleName);
		appUser.getRoles().add(appRole);				

	}

}
