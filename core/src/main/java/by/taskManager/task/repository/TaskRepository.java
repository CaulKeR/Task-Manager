package by.taskManager.task.repository;

import by.taskManager.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDeleteDateNull();

    Task findByIdAndDeleteDateNull(Integer id);

    List<Task> findByPersonIdAndDeleteDateNull(Integer personId);

}