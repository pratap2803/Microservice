package com.padmavati.microservice.microservice.controller;

import com.padmavati.microservice.microservice.entity.User;
import com.padmavati.microservice.microservice.model.UserRequest;
import com.padmavati.microservice.microservice.service.ExcelGenerator;
import com.padmavati.microservice.microservice.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExcelGenerator excelGenerator;

    @GetMapping("/get-user/{id}")
    public User userData(@PathVariable int id){
        return userService.getData(id);
    }

    @GetMapping("/get-users")
    public ResponseEntity<List<User>> userData(){
        List<User> list =  userService.getAllUsers();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <User> userList = userService.getAllUsers();
        ExcelGenerator generator = new ExcelGenerator(userList);
        generator.generateExcelFile(response);
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> createUser(@RequestBody UserRequest user) {
        String response = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
