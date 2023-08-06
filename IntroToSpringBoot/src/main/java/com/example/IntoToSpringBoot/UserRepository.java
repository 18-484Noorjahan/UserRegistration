package com.example.IntoToSpringBoot;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    // Add other custom query methods if needed
}

