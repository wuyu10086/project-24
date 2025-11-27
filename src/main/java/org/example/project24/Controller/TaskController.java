package com.taskmanager.controller;

import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;
import com.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String list(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Task> tasks = taskService.getUserTasks(user.getId());
        model.addAttribute("tasks", tasks);
        model.addAttribute("user", user);
        return "task/list";
    }

    @PostMapping("/add")
    public String add(Task task, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            task.setUserId(user.getId());
            taskService.createTask(task);
        }
        return "redirect:/tasks";
    }

    @PostMapping("/update")
    public String update(Task task) {
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}