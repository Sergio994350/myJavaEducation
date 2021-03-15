package main;

import java.util.*;
import java.util.stream.Collectors;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

  @Autowired
  private
  TaskRepository taskRepository;

  @GetMapping("/tasks/")
  public List<Task> list() {
    Iterable<Task> allTasks = taskRepository.findAll();
    return new ArrayList<>((Collection<? extends Task>) allTasks);

  }

  @PutMapping("/tasks/")
  public int add(Task task) {
    Task newTask = taskRepository.save(task);
    return newTask.getId();
  }

  @DeleteMapping("/tasks/{id}")
  public void delete(@PathVariable int id) {
    taskRepository.deleteById(id);
  }

  @GetMapping("/tasks/{id}")
  public ResponseEntity get(@PathVariable int id) {
    Optional<Task> task = taskRepository.findById(id);
    if (!task.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return new ResponseEntity(task.get(), HttpStatus.OK);
  }
}
