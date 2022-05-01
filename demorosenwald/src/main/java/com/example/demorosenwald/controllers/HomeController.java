package com.example.demorosenwald.controllers;

import com.example.demorosenwald.service.RosenwaldSchoolDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/school")
    public String school(){
        return "school";
    }


    /**
     *Controller for all school
     */

//    @RequestMapping("/all_schools")
//    public String all_schools(){
//        return "all_schools";
//    }


//    @GetMapping("/all_schools")
//    public String viewHomePage(Model model) {
//        return findPaginated(1,model);
//    }
//
//    @GetMapping("/all_schools/{pageNo}")
//    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,Model model) {
//        int pageSize = 9;
//        int totalPage = 6;
//        System.out.println(pageNo);
////        Page<SchoolData> page = rosenwaldSchoolDataService.findPaginated(pageNo, pageSize, sortField, sortDir);
////        List<SchoolData> listEmployees = page.getContent();
////
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", totalPage);
//        model.addAttribute("totalItems", pageSize);
//        //
//        model.addAttribute("schools", rosenwaldSchoolDataService.getHomeSchoolData());
//        return "all_schools";
//    }



    @RequestMapping(value = "/all_schools", method = RequestMethod.GET)
    public String allSchools(Model model, @RequestParam("page") Optional<Integer> page) {



        int currentPage = page.orElse(1);
        int pageSize = rosenwaldSchoolDataService.getAllSchoolsPage().get(currentPage).size();
        int totalPages = rosenwaldSchoolDataService.getAllSchoolsPage().size()-1;

//        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("schools", rosenwaldSchoolDataService.getAllSchoolsPage().get(currentPage));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", pageSize);
        model.addAttribute("currentPage", currentPage);


//        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "all_schools";
    }



//    @RequestMapping(value = "/allSchools", method = RequestMethod.GET)
//    public String allSchools(
//            Model model,
//            @RequestParam("page") Optional<Integer> page,
//            @RequestParam("size") Optional<Integer> size) {
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(5);
//
//        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
//
//        model.addAttribute("bookPage", bookPage);
//
//        int totalPages = bookPage.getTotalPages();
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//
//        return "allSchools.html";
//    }

}
