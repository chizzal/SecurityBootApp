package springapp.SecurityApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springapp.SecurityApp.repositories.PeopleRepository;
import springapp.SecurityApp.services.AdminService;

@Controller
public class HomeController {
    private final AdminService adminService;
    private final PeopleRepository peopleRepository;

    public HomeController(AdminService adminService, PeopleRepository peopleRepository) {
        this.adminService = adminService;
        this.peopleRepository = peopleRepository;
    }

    @GetMapping({"/home"})
    public String goHome() {
        return "home";
    }
}
