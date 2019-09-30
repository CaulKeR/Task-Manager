package by.taskManager.taskLog.service;

import by.taskManager.common.APIResponse;
import by.taskManager.taskLog.TaskLog;
import by.taskManager.taskLog.repository.TaskLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskLogService {

    @Autowired
    TaskLogRepository taskLogRepository;

    @Transactional
    public APIResponse create(TaskLog taskLog) {
        taskLogRepository.save(taskLog);
        return new APIResponse(true, "New task_log created!", null);
    }

    @Transactional
    public TaskLog getOne(Integer id) {
        return taskLogRepository.getOne(Long.valueOf(id));
    }

    @Transactional
    public List<TaskLog> getAll() {
        return taskLogRepository.findAll();
    }

    @Transactional
    public APIResponse update(Integer id, TaskLog taskLog) {
        if (taskLogRepository.getOne(Long.valueOf(id)) != null) {
            taskLog.setId(id);
            taskLogRepository.save(taskLog);
            return new APIResponse(true, "Existing task_log updated!", null);
        }
        return new APIResponse(false, "Task_log with id " + id + " wasn't found", null);
    }

}