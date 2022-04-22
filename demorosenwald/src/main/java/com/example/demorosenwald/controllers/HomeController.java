package com.example.demorosenwald.controllers;

import com.example.demorosenwald.service.RosenwaldSchoolDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final RosenwaldSchoolDataService rosenwaldSchoolDataService;

    public HomeController(RosenwaldSchoolDataService rosenwaldSchoolDataService) {
        this.rosenwaldSchoolDataService = rosenwaldSchoolDataService;
    }

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("schools", rosenwaldSchoolDataService.getHomeSchoolData());
        return "index";
    }
    @RequestMapping("/all_schools")
    public String all_schools(){
        return "all_schools";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/school")
    public String school(){
        return "school";
    }


}
