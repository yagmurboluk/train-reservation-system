package com.project.trainreservation.controller;

import com.project.trainreservation.dto.UserDTO;
import com.project.trainreservation.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Yeni bir kullanıcı oluşturur.
     *
     * @param userDTO Kullanıcıdan alınan bilgiler.
     * @return Oluşturulan kullanıcının DTO'su.
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    /**
     * Tüm kullanıcıları getirir.
     *
     * @return Tüm kullanıcıların DTO listesi.
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * ID'ye göre belirli bir kullanıcıyı getirir.
     *
     * @param userId Kullanıcının ID'si.
     * @return Bulunan kullanıcının DTO'su.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    /**
     * Kullanıcı bilgilerini günceller.
     *
     * @param userId Güncellenecek kullanıcının ID'si.
     * @param userDTO Güncel bilgiler.
     * @return Güncellenmiş kullanıcının DTO'su.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Kullanıcıyı siler.
     *
     * @param userId Silinecek kullanıcının ID'si.
     * @return Başarılı silme mesajı.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }

    /**
     * Kullanıcı giriş yapar.
     *
     * @param email Kullanıcının email adresi.
     * @param password Kullanıcının düz metin şifresi.
     * @return Giriş başarılıysa mesaj döner, aksi takdirde hata döner.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        if (userService.login(email, password)) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.status(400).body("Invalid email or password.");
    }

}
