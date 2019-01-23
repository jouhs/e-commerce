package fr.jouhs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.jouhs.entities.AppUser;
import fr.jouhs.service.AccountService;
import lombok.Data;

@RestController
public class UserController {
	@Autowired
	private AccountService accountService;
	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm userForm) {
		return accountService.saveUser(userForm.getUsername(), userForm.getPassword(), 
				userForm.getConfirmedPassword()); 
	}
}
@Data
class UserForm{
	private String username;
	private String password;
	private String confirmedPassword;
}
