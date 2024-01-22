package com.example.service1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/service1")
public class Controller {
    private List<String> messagearr = new ArrayList<>();
    @PostMapping("/addMessage")
    public ResponseEntity<String> addMessage(@RequestBody String message) {
        messagearr.add(message);
        return ResponseEntity.ok("Message added to Service 1: " + message);
    }

    @GetMapping("/getMessages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messagearr);
    }

    @PutMapping("/updateMessage/{index}")
    public ResponseEntity<String> updateMessage(@PathVariable int index, @RequestBody String updatedMessage) {
        if (index >= 0 && index < messagearr.size()) {
            messagearr.set(index, updatedMessage);
            return ResponseEntity.ok("Message at index " + index + " updated in Service 1: " + updatedMessage);
        } else {
            return ResponseEntity.badRequest().body("Invalid index for updating message in Service 1");
        }
    }

    @DeleteMapping("/deleteMessage/{index}")
    public ResponseEntity<String> deleteMessage(@PathVariable int index) {
        if (index >= 0 && index < messagearr.size()) {
            String deletedMessage = messagearr.remove(index);
            return ResponseEntity.ok("Message at index " + index + " deleted in Service 1: " + deletedMessage);
        } else {
            return ResponseEntity.badRequest().body("Invalid index for deleting message in Service 1");
        }
    }
}
