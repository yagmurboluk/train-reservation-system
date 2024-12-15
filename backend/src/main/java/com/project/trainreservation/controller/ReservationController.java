package com.project.trainreservation.controller;

import com.project.trainreservation.dto.ReservationDTO;
import com.project.trainreservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    
    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(createdReservation);
    }

    
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Long reservationId) {
        ReservationDTO reservation = reservationService.getReservationById(reservationId);
        return ResponseEntity.ok(reservation);
    }

   
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByUserId(@PathVariable("userId") Long userId) {
        List<ReservationDTO> reservations = reservationService.getReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(
            @PathVariable("id") Long reservationId,
            @Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO updatedReservation = reservationService.updateReservation(reservationId, reservationDTO);
        return ResponseEntity.ok(updatedReservation);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok("Reservation deleted successfully.");
    }
}
