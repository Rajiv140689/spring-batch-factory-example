package com.spring_batch_example.springbatchexample1.controller;

import com.spring_batch_example.springbatchexample1.Dao.InputPayload;
import com.spring_batch_example.springbatchexample1.Dao.User;
import com.spring_batch_example.springbatchexample1.Dao.UserRepository;
import com.spring_batch_example.springbatchexample1.service.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class GenericController {
    @Autowired
    public ServiceRequest serviceRequests;

    @PostMapping("/save")
    public void saveUser(User user) {
        System.out.println("inputPayload: "+user.toString());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
//        // Implement
//    }

    @PostMapping
    public void receiveInput(@Validated @RequestBody InputPayload inputPayload) {
        System.out.println("inputPayload: "+inputPayload.toString());
        serviceRequests.routeRequest(inputPayload);
    }
}
