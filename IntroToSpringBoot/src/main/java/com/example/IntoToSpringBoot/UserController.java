package com.example.IntoToSpringBoot;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ExecutorService userService;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User userDto) throws RegistrationException {
        ResponseEntity<?> registeredUser = ((UserController) userService).registerUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED)
		    .body(new ApiResponse("success", "User successfully registered!", registeredUser));
    }
    
    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody AuthReq authRequest) {
		return null;
       
    }
    
}
