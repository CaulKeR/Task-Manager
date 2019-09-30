package by.taskManager.person.service;

import by.taskManager.common.APIResponse;
import by.taskManager.person.Person;
import by.taskManager.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public APIResponse create(Person person) {
        personRepository.save(person);
        return new APIResponse(true, "New person created!", null);
    }

    @Transactional
    public Person getOne(Integer id) {
        return personRepository.findByIdAndDeleteDateNull(id);
    }

    @Transactional
    public List<Person> getAll() {
        return personRepository.findByDeleteDateNull();
    }

    @Transactional
    public APIResponse update(Integer id, Person person) {
        if (personRepository.findByIdAndDeleteDateNull(id) != null) {
            person.setId(id);
            personRepository.save(person);
            return new APIResponse(true, "Existing person updated!", null);
        }
        return new APIResponse(false, "Person with id " + id + " wasn't found", null);
    }

    @Transactional
    public APIResponse delete(Integer id) {
        Person person = personRepository.findByIdAndDeleteDateNull(id);
        if (person != null) {
            person.setDeleteDate(LocalDateTime.now());
            personRepository.save(person);
            return new APIResponse(true, "Existing person deleted!", null);
        }
        return new APIResponse(false, "Person with id " + id + " wasn't found", null);
    }

}