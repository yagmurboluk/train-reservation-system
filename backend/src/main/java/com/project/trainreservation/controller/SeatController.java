package com.project.trainreservation.controller;

import com.project.trainreservation.dto.SeatDTO;
import com.project.trainreservation.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    /**
     * Bir trene ait tüm koltukları getirir.
     */
    @GetMapping("/train/{trainId}")
    public ResponseEntity<List<SeatDTO>> getSeatsByTrainId(@PathVariable Long trainId) {
        List<SeatDTO> seats = seatService.getSeatsByTrainId(trainId);
        return ResponseEntity.ok(seats);
    }

    /**
     * Bir trene ait rezerve edilmemiş koltukları getirir.
     */
    @GetMapping("/train/{trainId}/available")
    public ResponseEntity<List<SeatDTO>> getAvailableSeatsByTrainId(@PathVariable Long trainId) {
        List<SeatDTO> availableSeats = seatService.getAvailableSeatsByTrainId(trainId);
        return ResponseEntity.ok(availableSeats);
    }

    /**
     * Bir trene ait rezerve edilmiş koltukları getirir.
     */
    @GetMapping("/train/{trainId}/reserved")
    public ResponseEntity<List<SeatDTO>> getReservedSeatsByTrainId(@PathVariable Long trainId) {
        List<SeatDTO> reservedSeats = seatService.getReservedSeatsByTrainId(trainId);
        return ResponseEntity.ok(reservedSeats);
    }

    /**
     * Belirli bir koltuk numarasına göre koltuk bilgisi getirir.
     */
    @GetMapping("/train/{trainId}/seat/{seatNumber}")
    public ResponseEntity<SeatDTO> getSeatByTrainIdAndSeatNumber(
            @PathVariable Long trainId,
            @PathVariable int seatNumber) {
        SeatDTO seat = seatService.getSeatByTrainIdAndSeatNumber(trainId, seatNumber);
        return ResponseEntity.ok(seat);
    }

    /**
     * Koltuğu rezerve eder.
     */
    @PutMapping("/{seatId}/reserve")
    public ResponseEntity<String> reserveSeat(@PathVariable Long seatId) {
        seatService.reserveSeat(seatId);
        return ResponseEntity.ok("Seat reserved successfully.");
    }

    /**
     * Koltuk rezervasyonunu iptal eder.
     */
    @PutMapping("/{seatId}/cancel")
    public ResponseEntity<String> cancelSeatReservation(@PathVariable Long seatId) {
        seatService.cancelSeatReservation(seatId);
        return ResponseEntity.ok("Seat reservation cancelled successfully.");
    }
}
