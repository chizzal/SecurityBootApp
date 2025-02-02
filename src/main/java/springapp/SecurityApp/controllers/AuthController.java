package springapp.SecurityApp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springapp.SecurityApp.models.Person;
import springapp.SecurityApp.services.RegistrationService;
import springapp.SecurityApp.util.PersonValidator;

@Controller
@RequestMapping({"/auth"})
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;

    public AuthController(PersonValidator personValidator, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }

    @GetMapping({"/login"})
    public String loginPage(Model model, HttpServletRequest request) {
        String errorCode = request.getParameter("error");
        if (errorCode != null) {
            model.addAttribute("errorCode", errorCode);
        }

        return "auth/login";
    }

    @GetMapping({"/registration"})
    public String regPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping({"/registration"})
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        this.personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        } else {
            this.registrationService.register(person);
            return "redirect:/auth/login";
        }
    }
}
