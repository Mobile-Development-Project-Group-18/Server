package com.goshell.goshell.user;

import com.goshell.goshell.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        String result = userService.addUser(user);

        if (result.contains("User already exists")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return result.isEmpty()
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing field")
                : ResponseEntity.created(URI.create("/users/" + result)).body("User registered successfully");
    }

    //Get user's profile
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        User user = userService.getUserById(userId);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with ID: " + userId);
        }
    }

    //Get user's sell products
    @GetMapping("/products/{userId}")
    public ResponseEntity<List<Product>> getUserProducts(@PathVariable String userId) {
        List<Product> products = userService.getUserProduct(userId);
        return products.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
                : ResponseEntity.ok(products);
    }

}
