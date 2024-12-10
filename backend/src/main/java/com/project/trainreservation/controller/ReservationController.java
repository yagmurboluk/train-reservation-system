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

    // Dependency Injection
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Yeni bir rezervasyon oluşturur.
     *
     * @param reservationDTO Kullanıcıdan alınan rezervasyon verileri.
     * @return Oluşturulan rezervasyonun DTO'su.
     */
    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(createdReservation);
    }

    /**
     * Tüm rezervasyonları getirir.
     *
     * @return Tüm rezervasyonların DTO listesi.
     */
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    /**
     * ID'ye göre belirli bir rezervasyonu getirir.
     *
     * @param reservationId Rezervasyonun ID'si.
     * @return Bulunan rezervasyonun DTO'su.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Long reservationId) {
        ReservationDTO reservation = reservationService.getReservationById(reservationId);
        return ResponseEntity.ok(reservation);
    }

    /**
     * Belirli bir kullanıcıya ait rezervasyonları getirir.
     *
     * @param userId Kullanıcının ID'si.
     * @return Kullanıcıya ait rezervasyonların DTO listesi.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByUserId(@PathVariable("userId") Long userId) {
        List<ReservationDTO> reservations = reservationService.getReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    /**
     * Rezervasyonu günceller.
     *
     * @param reservationId Güncellenecek rezervasyonun ID'si.
     * @param reservationDTO Güncel bilgiler.
     * @return Güncellenmiş rezervasyonun DTO'su.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(
            @PathVariable("id") Long reservationId,
            @Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO updatedReservation = reservationService.updateReservation(reservationId, reservationDTO);
        return ResponseEntity.ok(updatedReservation);
    }

    /**
     * Rezervasyonu siler.
     *
     * @param reservationId Silinecek rezervasyonun ID'si.
     * @return Başarılı silme mesajı.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok("Reservation deleted successfully.");
    }
}
