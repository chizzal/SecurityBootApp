package springapp.SecurityApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springapp.SecurityApp.models.Person;
import springapp.SecurityApp.repositories.PeopleRepository;
import springapp.SecurityApp.services.AdminService;
import springapp.SecurityApp.services.PersonDetailsService;

@Controller
@RequestMapping({"/admin"})
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final PeopleRepository peopleRepository;
    private final AdminService adminService;
    private final PersonDetailsService personDetailsService;

    public AdminController(PeopleRepository peopleRepository, AdminService adminService, PersonDetailsService personDetailsService) {
        this.peopleRepository = peopleRepository;
        this.adminService = adminService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("people", this.personDetailsService.findAll());
        return "admin";
    }

    @PostMapping({"/lock"})
    public String lockUser(@RequestParam("userId") int userId) {
        this.adminService.lockPerson((Person)this.peopleRepository.findById(userId).get());
        return "redirect:/admin";
    }

    @PostMapping({"/unlock"})
    public String unlockUser(@RequestParam("userId") int userId) {
        this.adminService.unlockPerson((Person)this.peopleRepository.findById(userId).get());
        return "redirect:/admin";
    }
}
