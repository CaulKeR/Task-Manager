package by.taskManager.person;

import by.taskManager.common.APIResponse;
import by.taskManager.person.service.PersonService;
import by.taskManager.task.service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    TaskService taskService;

    @PostMapping
    public APIResponse create(@RequestBody Person person) {
        return personService.create(person);
    }

    @GetMapping("{id}")
    public String getPerson(@PathVariable int id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(personService.getOne(id));
    }

    @GetMapping("{id}/tasks")
    public String getAllTasksByPersonId(@PathVariable int id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(taskService.getAllTasksForPersonId(id));
    }

    @GetMapping
    public String getAllPersons() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(personService.getAll());
    }

    @PutMapping("{id}")
    public APIResponse update(@PathVariable int id, @RequestBody Person person) {
        return personService.update(id, person);
    }

    @DeleteMapping("{id}")
    public APIResponse delete(@PathVariable int id) {
        return personService.delete(id);
    }

}