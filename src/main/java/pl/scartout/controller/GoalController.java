package pl.scartout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GoalController {
    
    @RequestMapping("/goal")
    public String goal() {
        return "goal";
    }
    
    @RequestMapping("/goals")
    public String goals() {
        return "goals";
    }

}