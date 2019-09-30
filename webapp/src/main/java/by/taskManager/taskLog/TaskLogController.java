package by.taskManager.taskLog;

import by.taskManager.taskLog.service.TaskLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tasks_logs")
public class TaskLogController {

    @Autowired
    TaskLogService taskLogService;

    @GetMapping
    public String getAllTasksLogs() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(taskLogService.getAll());
    }

}