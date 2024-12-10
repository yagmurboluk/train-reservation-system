package com.project.trainreservation.controller;

import com.project.trainreservation.dto.SeatDTO;
import com.project.trainreservation.dto.TrainDTO;
import com.project.trainreservation.service.TrainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping
    public ResponseEntity<TrainDTO> createTrain(@RequestBody TrainDTO trainDTO) {
        TrainDTO createdTrain = trainService.createTrain(trainDTO);
        return ResponseEntity.ok(createdTrain);
    }

    @GetMapping
    public ResponseEntity<List<TrainDTO>> getAllTrains() {
        List<TrainDTO> trains = trainService.getAllTrains();
        return ResponseEntity.ok(trains);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainDTO> getTrainById(@PathVariable("id") Long trainId) {
        TrainDTO train = trainService.getTrainById(trainId);
        return ResponseEntity.ok(train);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainDTO> updateTrain(
            @PathVariable("id") Long trainId,
            @RequestBody TrainDTO trainDTO) {
        TrainDTO updatedTrain = trainService.updateTrain(trainId, trainDTO);
        return ResponseEntity.ok(updatedTrain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrain(@PathVariable("id") Long trainId) {
        trainService.deleteTrain(trainId);
        return ResponseEntity.ok("Train deleted successfully.");
    }

    @GetMapping("/{id}/seats")
    public ResponseEntity<List<SeatDTO>> getSeatsByTrainId(@PathVariable("id") Long trainId) {
        List<SeatDTO> seats = trainService.getSeatsByTrainId(trainId);
        return ResponseEntity.ok(seats);
    }
}
