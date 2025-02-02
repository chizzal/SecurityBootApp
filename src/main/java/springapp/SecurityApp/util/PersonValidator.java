package springapp.SecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import springapp.SecurityApp.models.Person;
import springapp.SecurityApp.services.PersonDetailsService;

@Component
public class PersonValidator implements Validator {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (this.personDetailsService.isPersonExist(person.getUsername())) {
            errors.rejectValue("username", "",
                    "Человек с таким именем пользователя уже существует");
        }
    }
}
