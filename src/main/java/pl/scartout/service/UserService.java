package pl.scartout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.scartout.model.Role;
import pl.scartout.model.User;
import pl.scartout.repo.RoleRepo;
import pl.scartout.repo.UserRepo;

@Service
public class UserService {

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final String DEFAULT_ROLE = "user";
	private UserRepo userRepo;
	private RoleRepo roleRepo;

	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Autowired
	public void setRoleRepo(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}

	public void addWithDefaultRole(User user) {
		Role defaultRole = roleRepo.findByRole(DEFAULT_ROLE);
		user.setRole(defaultRole);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}
	
}