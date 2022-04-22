package com.example.demorosenwald.controllers;

import com.example.demorosenwald.entity.SchoolData;
import com.example.demorosenwald.service.RosenwaldSchoolDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SchoolController {

    private final RosenwaldSchoolDataService rosenwaldSchoolDataService;

    public SchoolController(RosenwaldSchoolDataService rosenwaldSchoolDataService) {
        this.rosenwaldSchoolDataService = rosenwaldSchoolDataService;
    }

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("schools", rosenwaldSchoolDataService.getHomeSchoolData());
        return "index";
    }

}
