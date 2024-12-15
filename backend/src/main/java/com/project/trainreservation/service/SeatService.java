package com.project.trainreservation.service;

import com.project.trainreservation.dto.SeatDTO;
import com.project.trainreservation.entity.SeatEntity;
import com.project.trainreservation.entity.TrainEntity;
import com.project.trainreservation.repository.SeatRepository;
import com.project.trainreservation.repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final TrainRepository trainRepository;

    public SeatService(SeatRepository seatRepository, TrainRepository trainRepository) {
        this.seatRepository = seatRepository;
        this.trainRepository = trainRepository;
    }

    
    public List<SeatDTO> getSeatsByTrainId(Long trainId) {
        List<SeatEntity> seats = seatRepository.findByTrain_TrainId(trainId);
        if (seats.isEmpty()) {
            
            TrainEntity train = trainRepository.findById(trainId)
                    .orElseThrow(() -> new RuntimeException("Train not found with ID: " + trainId));
            
            for (int i = 1; i <= train.getCapacity(); i++) {
                SeatEntity seat = new SeatEntity();
                seat.setTrain(train);
                seat.setSeatNumber(i);
                seat.setReserved(false);
                seatRepository.save(seat);
            }
            
            seats = seatRepository.findByTrain_TrainId(trainId);
        }
        return seats.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


   
    public List<SeatDTO> getAvailableSeatsByTrainId(Long trainId) {
        return seatRepository.findByTrain_TrainIdAndReservedFalse(trainId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

   
    public List<SeatDTO> getReservedSeatsByTrainId(Long trainId) {
        return seatRepository.findByTrain_TrainIdAndReservedTrue(trainId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    
    public SeatDTO getSeatByTrainIdAndSeatNumber(Long trainId, int seatNumber) {
        SeatEntity seat = seatRepository.findByTrain_TrainIdAndSeatNumber(trainId, seatNumber);
        return convertToDTO(seat);
    }

    
    public SeatDTO reserveSeat(Long seatId) {
        SeatEntity seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with ID: " + seatId));
        if (seat.isReserved()) {
            throw new RuntimeException("Seat is already reserved.");
        }
        seat.setReserved(true);
        SeatEntity updatedSeat = seatRepository.save(seat);
        return convertToDTO(updatedSeat);
    }

   
    public SeatDTO cancelSeatReservation(Long seatId) {
        SeatEntity seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with ID: " + seatId));
        if (!seat.isReserved()) {
            throw new RuntimeException("Seat is not reserved.");
        }
        seat.setReserved(false);
        SeatEntity updatedSeat = seatRepository.save(seat);
        return convertToDTO(updatedSeat);
    }

    
    private SeatDTO convertToDTO(SeatEntity seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(seat.getSeatId());
        seatDTO.setSeatNumber(seat.getSeatNumber());
        seatDTO.setReserved(seat.isReserved());
        return seatDTO;
    }
    
    
}
