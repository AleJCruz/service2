package com.example.service2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/service2")
public class Controller {
    private List<String> tasks = new ArrayList<>();

    @PostMapping("/addTask")
    public ResponseEntity<String> addTask(@RequestBody String task) {
        tasks.add(task);
        return ResponseEntity.ok("Task added to Service 2: " + task);
    }

    @GetMapping("/getTasks")
    public ResponseEntity<List<String>> getTasks() {
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/updateTask/{index}")
    public ResponseEntity<String> updateTask(@PathVariable int index, @RequestBody String updatedTask) {
        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, updatedTask);
            return ResponseEntity.ok("Task at index " + index + " updated in Service 2: " + updatedTask);
        } else {
            return ResponseEntity.badRequest().body("Invalid index for updating task in Service 2");
        }
    }

    @DeleteMapping("/deleteTask/{index}")
    public ResponseEntity<String> deleteTask(@PathVariable int index) {
        if (index >= 0 && index < tasks.size()) {
            String deletedTask = tasks.remove(index);
            return ResponseEntity.ok("Task at index " + index + " deleted in Service 2: " + deletedTask);
        } else {
            return ResponseEntity.badRequest().body("Invalid index for deleting task in Service 2");
        }
    }
}
