package main;

import java.util.*;
import java.util.stream.Collectors;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

  @Autowired
  private
  TaskRepository taskRepository;

  @RequestMapping(name = "/")
  public String index(Model model) {
    Iterable<Task> allTasks = taskRepository.findAll();
    List<Task> tasks = new ArrayList<>((Collection<? extends Task>) allTasks);
    model.addAttribute("tasks", tasks);
    model.addAttribute("taskCount", tasks.size());
    return "index";
  }

  public <T> Set<T> findDuplicates(Collection<T> collection) {
    Set<T> elements = new HashSet<>();
    return collection.stream()
            .filter(e -> !elements.add(e))
            .collect(Collectors.toSet());
  }
}
