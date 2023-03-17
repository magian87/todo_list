package ru.magian.todo_list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.magian.todo_list.models.Task;
import ru.magian.todo_list.services.TaskService;

import java.util.List;


@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


   /* @GetMapping("/pagination/{offset}/{pageSize}")
     public String */

    @GetMapping("/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum){
        Page<Task> page = taskService.findTaskWithPagination(pageNum);

        List<Task> tasks = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("tasks", tasks);


        return "tasks/index";

    }



    //read all
    @GetMapping
    public String index(Model model){
        return viewPage(model, 1);
        //model.addAttribute("tasks", taskService.findAll());
        //return "tasks/index";
    }

    //read one
    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model){
        model.addAttribute("task", taskService.findOne(id));
        return "tasks/show";
    }

    //new
    @GetMapping("/new")
    //public String newPerson(){
    public String newTask(@ModelAttribute("task") Task task){
        //model.addAttribute("person", new Person());
        return "tasks/new";
    }

    @PostMapping
    public String create(@ModelAttribute("task") Task task){
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") long id){
        model.addAttribute("task", taskService.findOne(id));
        return "tasks/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("task") Task task,
                         @PathVariable("id") int id){
        taskService.update(id, task);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        taskService.delete(id);
        return "redirect:/tasks";
    }





}
