package com.customer.rewards.controller;

import com.customer.rewards.entity.User;
import com.customer.rewards.exception.ResourceNotFoundException;
import com.customer.rewards.model.UserDto;
import com.customer.rewards.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(
        name = "User CRUD operation controller"
)
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(
            summary = "Fetch all users",
            description = "Retrieve all the user records from backend"
    )
    @GetMapping("/all")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.listAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Fetch single user",
            description = "Retrieve single user records by id from backend"
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> listUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        if(user == null){
            throw new ResourceNotFoundException("User", "id", id);
        }
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Store new user",
            description = "Store single new user records to backend"
    )
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
       return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit single user",
            description = "Fetch and Edit user record and store in backend"
    )
    @PostMapping("/edit/{id}")
    public ResponseEntity<User> editUserForm(@PathVariable Integer id, @RequestBody UserDto model)  {

        try {
            User user = userService.getUserById(id);
            if(user == null){
                throw new ResourceNotFoundException("User", "id", id);
            }
            user.setEmail(model.getEmail());
            user.setFirstname(model.getFirstname());
            user.setLastname(model.getLastname());
            return ResponseEntity.ok(userService.saveUser(user));
        } catch (ResourceNotFoundException e) {
           throw new ResourceNotFoundException("User", "id", id);
        }
    }

    @Operation(
            summary = "Delete single user",
            description = "Delete single user records by id from backend"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserForm(@PathVariable Integer id)  {

        try {
            User user = userService.getUserById(id);
            if(user == null){
                return ResponseEntity.ok("User not found");
            }
            userService.deleteUserById(id);
           return ResponseEntity.ok("User deleted successfully");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }

}
