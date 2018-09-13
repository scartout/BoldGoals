package pl.scartout.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.User;
import pl.scartout.repo.UserRepo;
import pl.scartout.service.UserService;
	 
@Controller
public class UserController {
	
	private UserService userService;
	private UserRepo userRepo;
    
    @Autowired
    public void setUserService(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }
    
    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("user", new User());
        return "login";
    }
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute @Valid User user,
			BindingResult bindResult) {
		if(bindResult.hasErrors())
			return "login";
		else {
			if (userRepo.findByUsername(user.getUsername())==null) {
				userService.addWithDefaultRole(user);
				return "thankyou";
			}
			else return "login";
			
		}
	}
	
}