package by.taskManager.task.service;

import by.taskManager.common.APIResponse;
import by.taskManager.person.service.PersonService;
import by.taskManager.task.Task;
import by.taskManager.task.repository.TaskRepository;
import by.taskManager.taskLog.TaskLog;
import by.taskManager.taskLog.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    PersonService personService;

    @Autowired
    TaskLogService taskLogService;

    @Transactional
    public APIResponse create(Task task) {
        if (personService.getOne(task.getPerson().getId()) != null) {
            taskRepository.save(task);
            return new APIResponse(true, "New task created!", null);
        }
        return new APIResponse(false, "Cannot find user with id " + task.getPerson().getId(), null);
    }

    @Transactional
    public Task getOne(Integer id) {
        return taskRepository.findByIdAndDeleteDateNull(id);
    }

    @Transactional
    public List<Task> getAll() {
        return taskRepository.findByDeleteDateNull();
    }

    @Transactional
    public List<Task> getAllTasksForPersonId(Integer id) {
        return taskRepository.findByPersonIdAndDeleteDateNull(id);
    }

    @Transactional
    public APIResponse update(Integer id, Task task) {
        if (taskRepository.findByIdAndDeleteDateNull(id) != null) {
            task.setId(id);
            taskRepository.save(task);
            return new APIResponse(true, "Existing task updated!", null);
        }
        return new APIResponse(false, "Task with id " + id + " wasn't found", null);
    }

    @Transactional
    public APIResponse updateStatus(Integer id, TaskLog taskLog) {
        Task task = taskRepository.findByIdAndDeleteDateNull(id);
        if (task != null) {
            task.setStatus(taskLog.getTask().getStatus());
            taskLog.setTask(task);
            taskRepository.save(task);
            taskLogService.create(taskLog);
            return new APIResponse(true, "Existing task status updated!", null);
        }
        return new APIResponse(false, "Task with id " + id + " wasn't found", null);
    }

    @Transactional
    public APIResponse delete(Integer id) {
        Task task = taskRepository.findByIdAndDeleteDateNull(id);
        if (task != null) {
            task.setDeleteDate(LocalDateTime.now());
            taskRepository.save(task);
            return new APIResponse(true, "Existing task deleted!", null);
        }
        return new APIResponse(false, "Task with id " + id + " wasn't found", null);
    }

}