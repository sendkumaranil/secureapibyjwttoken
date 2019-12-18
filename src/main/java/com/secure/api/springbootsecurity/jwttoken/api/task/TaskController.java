package com.secure.api.springbootsecurity.jwttoken.api.task;

import com.secure.api.springbootsecurity.jwttoken.entites.Task;
import com.secure.api.springbootsecurity.jwttoken.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody Task newTask){
        Task createdTask=taskRepository.save(newTask);
        return new ResponseEntity<>("Task Created with Task Id:"+createdTask.getId(),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(){
        List<Task> tasks=new ArrayList<>();
        Iterable<Task> itr=taskRepository.findAll();

        if(itr==null || !itr.iterator().hasNext()){
            return new ResponseEntity("Task Not Found",HttpStatus.NOT_FOUND);
        }
        itr.forEach(task -> tasks.add(task));
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editTask(@PathVariable("id") Long taskId,@RequestBody Task task){
        Task existingTask=taskRepository.findById(taskId).get();
        if(existingTask==null){
            return new ResponseEntity<>("Task Not Found",HttpStatus.NOT_FOUND);
        }
        existingTask.setDescription(task.getDescription());
        taskRepository.save(existingTask);

        return new ResponseEntity<>("Task Updated",HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId){
        taskRepository.deleteById(taskId);

        return new ResponseEntity<>("Task Deleted", HttpStatus.NOT_FOUND);
    }
}
