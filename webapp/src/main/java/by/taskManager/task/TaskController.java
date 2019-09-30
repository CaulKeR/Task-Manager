package by.taskManager.task;

import by.taskManager.common.APIResponse;
import by.taskManager.task.service.TaskService;
import by.taskManager.taskLog.TaskLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public APIResponse create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @GetMapping
    public String getAllTasks() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(taskService.getAll());
    }

    @GetMapping("{id}")
    public String getTask(@PathVariable int id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(taskService.getOne(id));
    }

    @PutMapping("{id}/status")
    public APIResponse updateStatus(@PathVariable int id, @RequestBody TaskLog taskLog) {
        return taskService.updateStatus(id, taskLog);
    }

}