package springapp.SecurityApp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springapp.SecurityApp.models.Person;
import springapp.SecurityApp.repositories.PeopleRepository;

@Service
public class AdminService {
    private final PeopleRepository peopleRepository;

    public AdminService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void lockPerson(Person person) {
        person.setLocked(true);
        System.out.println("User " + person.getUsername() + " is locked");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void unlockPerson(Person person) {
        person.setLocked(false);
        System.out.println("User " + person.getUsername() + " is unlocked");
    }
}
