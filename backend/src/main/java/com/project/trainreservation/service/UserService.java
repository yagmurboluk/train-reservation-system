package com.project.trainreservation.service;

import com.project.trainreservation.dto.UserDTO;
import com.project.trainreservation.entity.UserEntity;
import com.project.trainreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   
    private UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName()); // Soyadı ekleme
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // Şifre hashlenmiyorsa düz metin olarak set ediliyor
        user.setGender(userDTO.getGender());
        return user;
    }

    /**
     * User entity'sinden UserDTO'ya dönüştürme.
     */
    private UserDTO convertToDTO(UserEntity user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName()); // Soyadı ekleme
        dto.setEmail(user.getEmail());
        dto.setGender(user.getGender());
        return dto;
    }

    
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity user = convertToEntity(userDTO);
        UserEntity savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

   
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    
    public UserDTO getUserById(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return convertToDTO(user);
    }

    
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        existingUser.setName(userDTO.getName());
        existingUser.setLastName(userDTO.getLastName()); // Soyadı güncelleme
        existingUser.setEmail(userDTO.getEmail());

        
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(userDTO.getPassword());
        }

        existingUser.setGender(userDTO.getGender());

        UserEntity updatedUser = userRepository.save(existingUser);
        return convertToDTO(updatedUser);
    }

    
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        userRepository.delete(user);
    }

    
    public boolean login(String email, String rawPassword) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        
        return rawPassword.equals(user.getPassword());
    }
}
