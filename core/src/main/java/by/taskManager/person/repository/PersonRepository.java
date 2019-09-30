package by.taskManager.person.repository;

import by.taskManager.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByDeleteDateNull();

    Person findByIdAndDeleteDateNull(Integer id);

}