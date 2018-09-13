package pl.scartout.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.scartout.model.User;
import pl.scartout.model.Role;
import pl.scartout.repo.UserRepo;

public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepo userRepo;
	
	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("User not found");
		org.springframework.security.core.userdetails.User userDetails = 
				new org.springframework.security.core.userdetails.User(
						user.getUsername(), 
						user.getPassword(), 
						convertAuthorities(user.getRole()));
		return userDetails;
	}
	
	private Set<GrantedAuthority> convertAuthorities(Role role) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(role.getRole()));
		return authorities;
	}
	
}