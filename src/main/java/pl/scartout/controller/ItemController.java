package pl.scartout.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
	 
@Controller
public class ItemController {
    
    @RequestMapping("/item")
    public String item(HttpSession httpSession, @RequestParam Long goalId){
    	httpSession.setAttribute("goalId", goalId);
        return "item";
    }
    
    @RequestMapping("/items")
    public String items(HttpSession httpSession, @RequestParam Long goalId){
    	httpSession.setAttribute("goalId", goalId);
        return "items";
    }
    
    @RequestMapping("/summary")
    public String summary(){
        return "summary";
    }
  
}