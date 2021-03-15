package main;

import main.model.TaskRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    private static final Logger MARKLOGGER = LogManager.getLogger(TaskController.class);
    private static final Marker VARS = MarkerManager.getMarker("VARS");

    @GetMapping("/tasks/")
    public List<Task> listTasks() {

        Iterable<Task> takIterable = taskRepository.findAll();
        List<Task> taskList = new LinkedList<>();
        // try to find duplicates:
        findDuplicates(taskList);
        
        takIterable.forEach(taskList::add);
        return taskList;

    }

    @PostMapping("/tasks/")
    public ResponseEntity addTask(Task task) {
        Task newTask = taskRepository.save(task);
        return new ResponseEntity(newTask.getId(), HttpStatus.CREATED);
    }


    @GetMapping("/tasks/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(taskOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity delTask(@PathVariable int id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.deleteById(id);
        return new ResponseEntity(true, HttpStatus.OK);
    }


    @DeleteMapping("/tasks/all")
    public ResponseEntity clearList() {
        taskRepository.deleteAll();
        return new ResponseEntity(true, HttpStatus.OK);

    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity modifyTask(@PathVariable int id, String name, String description) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setDescription(description);
            task.setName(name);
            taskRepository.save(task);
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public <T> Set<T> findDuplicates(Collection<T> collection) {
        Set<T> elements = new HashSet<>();
        return collection.stream()
                .filter(e -> !elements.add(e))
                .collect(Collectors.toSet());
    }

}
