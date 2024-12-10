package com.project.trainreservation.service;

import com.project.trainreservation.dto.SeatDTO;
import com.project.trainreservation.dto.TrainDTO;
import com.project.trainreservation.entity.SeatEntity;
import com.project.trainreservation.entity.TrainEntity;
import com.project.trainreservation.repository.SeatRepository;
import com.project.trainreservation.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private SeatRepository seatRepository;

    private TrainEntity convertToEntity(TrainDTO trainDTO) {
        TrainEntity train = new TrainEntity();
        train.setTrainId(trainDTO.getTrainId());
        train.setTrainName(trainDTO.getTrainName());
        train.setDepartureStation(trainDTO.getDepartureStation());
        train.setArrivalStation(trainDTO.getArrivalStation());
        train.setDepartureTime(trainDTO.getDepartureTime());
        train.setArrivalTime(trainDTO.getArrivalTime());
        train.setCapacity(trainDTO.getCapacity());
        return train;
    }

    private TrainDTO convertToDTO(TrainEntity train) {
        TrainDTO dto = new TrainDTO();
        dto.setTrainId(train.getTrainId());
        dto.setTrainName(train.getTrainName());
        dto.setDepartureStation(train.getDepartureStation());
        dto.setArrivalStation(train.getArrivalStation());
        dto.setDepartureTime(train.getDepartureTime());
        dto.setArrivalTime(train.getArrivalTime());
        dto.setCapacity(train.getCapacity());
        return dto;
    }

    private SeatDTO convertSeatToDTO(SeatEntity seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(seat.getSeatId());
        seatDTO.setSeatNumber(seat.getSeatNumber());
        seatDTO.setReserved(seat.isReserved());
        seatDTO.setTrainId(seat.getTrain().getTrainId());
        return seatDTO;
    }

    public TrainDTO createTrain(TrainDTO trainDTO) {
        TrainEntity train = convertToEntity(trainDTO);
        TrainEntity savedTrain = trainRepository.save(train);

        for (int i = 1; i <= train.getCapacity(); i++) {
            SeatEntity seat = new SeatEntity();
            seat.setTrain(savedTrain);
            seat.setSeatNumber(i);
            seat.setReserved(false);
            seatRepository.save(seat);
        }

        return convertToDTO(savedTrain);
    }

    public List<TrainDTO> getAllTrains() {
        return trainRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TrainDTO getTrainById(Long trainId) {
        TrainEntity train = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found with ID: " + trainId));
        return convertToDTO(train);
    }

    public TrainDTO updateTrain(Long trainId, TrainDTO trainDTO) {
        TrainEntity existingTrain = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found with ID: " + trainId));

        existingTrain.setTrainName(trainDTO.getTrainName());
        existingTrain.setDepartureStation(trainDTO.getDepartureStation());
        existingTrain.setArrivalStation(trainDTO.getArrivalStation());
        existingTrain.setDepartureTime(trainDTO.getDepartureTime());
        existingTrain.setArrivalTime(trainDTO.getArrivalTime());
        existingTrain.setCapacity(trainDTO.getCapacity());

        TrainEntity updatedTrain = trainRepository.save(existingTrain);
        return convertToDTO(updatedTrain);
    }

    public void deleteTrain(Long trainId) {
        TrainEntity train = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found with ID: " + trainId));
        trainRepository.delete(train);
    }

    public List<SeatDTO> getSeatsByTrainId(Long trainId) {
        TrainEntity train = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found with ID: " + trainId));
        return train.getSeats().stream()
                .map(this::convertSeatToDTO)
                .collect(Collectors.toList());
    }
}
