package pl.scartout.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.scartout.model.Goal;
import pl.scartout.model.Item;
import pl.scartout.repo.GoalRepo;
import pl.scartout.repo.ItemRepo;

@RestController
@RequestMapping("/api/items")
public class ItemRestController {
	
	private ItemRepo itemRepo;
	private GoalRepo goalRepo;
	 
    @Autowired
    public ItemRestController(ItemRepo itemRepo, GoalRepo goalRepo) {
        this.itemRepo = itemRepo;
        this.goalRepo = goalRepo;
    }
       
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> allItems(Model model, HttpSession session) {
    	Long goalId = (Long) session.getAttribute("goalId");
    	List<Item> items = itemRepo.findAllByGoalId(goalId);
        return ResponseEntity.ok(items);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemRepo.findOne(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveItem(@RequestBody Item item,
			HttpSession session) {
    	Long goalId = (Long) session.getAttribute("goalId");
    	Goal goal = goalRepo.getById(goalId);
    	item.setGoal(goal);
        Item save = itemRepo.save(item);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(location).body(save);
    }
   
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody int deleteItem(@PathVariable("id") Integer id) {
    	itemRepo.deleteItem(id);
    	return 1;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public @ResponseBody int updateItem(@PathVariable("id") Integer id) {
    	itemRepo.updateItem(id);
    	return 1;
    }


}