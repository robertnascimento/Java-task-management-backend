package iver.robert.taskmanagement.controller;

import iver.robert.taskmanagement.task.Task;
import iver.robert.taskmanagement.task.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository){

        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> taskList(){

        return taskRepository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){

        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> atualizarTask(@PathVariable long id, @RequestBody Task task){
        if(!taskRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        task.setId(id);
        task = taskRepository.save(task);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if(!taskRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
