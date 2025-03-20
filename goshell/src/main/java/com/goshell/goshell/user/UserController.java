package com.goshell.goshell.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = userService.addUser(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        User user = userService.getUserById(userId);

        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }
}
