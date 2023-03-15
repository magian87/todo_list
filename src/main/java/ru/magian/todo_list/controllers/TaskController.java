package ru.magian.todo_list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.magian.todo_list.dao.TaskDao;
import ru.magian.todo_list.models.Task;

import javax.validation.Valid;

@Controller
@RequestMapping("tasks")
public class TaskController {
    private final TaskDao taskDao;


    @Autowired
    public TaskController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    //read all
    @GetMapping
    public String index(Model model){
        model.addAttribute("tasks", taskDao.getAll());
        return "tasks/index";
    }

    //read one
    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model){
        model.addAttribute("task", taskDao.get(id).orElse(null));
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
    public String create(@ModelAttribute("task") Task task
                         /*BindingResult bindingResult*/){
        /*personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";*/

        taskDao.save(task);

        return "redirect:/tasks";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("task", taskDao.get(id).orElse(null));
        return "tasks/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("task") @Valid  Task task,
                         @PathVariable("id") int id,
                         BindingResult bindingResult){
        /*personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
*/
        taskDao.update(id, task);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        taskDao.delete(id);
        return "redirect:/tasks";
    }





}
