package pl.scartout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Item;
import pl.scartout.repo.ItemRepo;

@RestController
@RequestMapping("/api/summary")
public class SummaryRestController {
	
	private ItemRepo itemRepo;
	 
    @Autowired
    public SummaryRestController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getItemsSummary(Model model) {
	  	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  	List<Item> items = itemRepo.itemByUsername(userDetails.getUsername());
	    return ResponseEntity.ok(items);
	}
    
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemRepo.findOne(id);
        return ResponseEntity.ok(item);
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