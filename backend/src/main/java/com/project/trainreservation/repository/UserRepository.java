package com.project.trainreservation.repository;

import com.project.trainreservation.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

   
    UserEntity findByEmail(String email);
    
    
    
    
}
