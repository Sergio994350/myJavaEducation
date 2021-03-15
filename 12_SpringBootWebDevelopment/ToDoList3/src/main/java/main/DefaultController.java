package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class DefaultController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> taskList = new LinkedList<>();

        // try to find duplicates:
        findDuplicates(taskList);

        taskIterable.forEach(taskList::add);

        model.addAttribute("tasks", taskList);
        model.addAttribute("tasksCount", taskList.size());
        return "index";
    }

    public <T> Set<T> findDuplicates(Collection<T> collection) {
        Set<T> elements = new HashSet<>();
        return collection.stream()
                .filter(e -> !elements.add(e))
                .collect(Collectors.toSet());
    }
}
