package pl.scartout.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.scartout.model.Goal;
import pl.scartout.model.User;
import pl.scartout.repo.GoalRepo;
import pl.scartout.repo.ItemRepo;
import pl.scartout.repo.UserRepo;

@RestController
@RequestMapping("/api/goals")
public class GoalRestController {
	
	private GoalRepo goalRepo;
	private UserRepo userRepo;
	private ItemRepo itemRepo;
	 
    @Autowired
    public GoalRestController(GoalRepo goalRepo, UserRepo userRepo, ItemRepo itemRepo) {
        this.goalRepo = goalRepo;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
    }
       
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Goal>> allGoals() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Long userId = userRepo.idByUsername(userDetails.getUsername());
    	List<Goal> goals = goalRepo.findAllByUserId(userId);
        return ResponseEntity.ok(goals);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goal> getGoalById(@PathVariable Long id) {
        Goal goal = goalRepo.findOne(id);
        return ResponseEntity.ok(goal);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveGoal(@RequestBody Goal goal) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	User user = userRepo.findByUsername(userDetails.getUsername());
    	goal.setUser(user);
        Goal save = goalRepo.save(goal);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(location).body(save);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody int deleteGoal(@PathVariable("id") Long goalId) {
    	Goal managedGoal = goalRepo.getById(goalId);
        managedGoal.getItems().clear();
        itemRepo.deleteItems(goalId);
    	goalRepo.deleteGoal(goalId);
    	System.out.println("xx"+goalId);
		return 1;
    }

}