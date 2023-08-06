package com.example.IntoToSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService<PasswordEncoder> {
    private final UserRepository userRepository;
    private boolean isUserDtoValid(User userDto) {
        if (userDto == null) {
            return false;
        }
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            return false;
        }
        if (userDto.getEmail() == null || !userDto.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
            return false;
        }

        return true; 
    }

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
//    @Autowired
//    public UserService(UserRepository userRepository) throws RegistrationException{
//        this.userRepository = userRepository;
//		this.passwordEncoder = null;
//    }
    public void validateUserDto(User userDto) throws RegistrationException {
        // Implement your validation logic here
        if (userDto == null || 
            userDto.getUsername() == null || userDto.getUsername().isEmpty() ||
            userDto.getEmail() == null || userDto.getEmail().isEmpty() ||
            userDto.getPassword() == null || userDto.getPassword().isEmpty() ||
            userDto.getFullName() == null || userDto.getFullName().isEmpty() ||
            userDto.getAge() <= 0 ||
            userDto.getGender() == null || userDto.getGender().isEmpty()) {
            throw new RegistrationException("Invalid user data. Please provide all required fields.");
        }
    }

    public User registerUser(User userDto) throws RegistrationException {
        if (!isUserDtoValid(userDto)) {
            throw new RegistrationException("Invalid user data. Please provide all required fields.");
        }

        // Check if username is already taken
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RegistrationException("Username already exists. Please choose a different username.");
        }

        // Check if email is already registered
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RegistrationException("Email is already registered. Please use a different email address.");
        }

        // Create a new user entity
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(((org.springframework.security.crypto.password.PasswordEncoder) passwordEncoder).encode(userDto.getPassword())); 
        newUser.setFullName(userDto.getFullName());
        newUser.setAge(userDto.getAge());
        newUser.setGender(userDto.getGender());

        // Save the user to the database
        return userRepository.save(newUser);
    }
}
