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

    /**
     * Bir trene ait tüm koltukları getirir.
     *
     * @param trainId Trenin ID'si.
     * @return Trene ait koltukların DTO listesi.
     */
    public List<SeatDTO> getSeatsByTrainId(Long trainId) {
        List<SeatEntity> seats = seatRepository.findByTrain_TrainId(trainId);
        if (seats.isEmpty()) {
            // Tren için koltuk yoksa, dinamik olarak koltuk oluştur ve veritabanına kaydet
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


    /**
     * Bir trene ait rezerve edilmemiş koltukları getirir.
     *
     * @param trainId Trenin ID'si.
     * @return Rezerve edilmemiş koltukların DTO listesi.
     */
    public List<SeatDTO> getAvailableSeatsByTrainId(Long trainId) {
        return seatRepository.findByTrain_TrainIdAndReservedFalse(trainId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Bir trene ait rezerve edilmiş koltukları getirir.
     *
     * @param trainId Trenin ID'si.
     * @return Rezerve edilmiş koltukların DTO listesi.
     */
    public List<SeatDTO> getReservedSeatsByTrainId(Long trainId) {
        return seatRepository.findByTrain_TrainIdAndReservedTrue(trainId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Belirli bir koltuk numarasına göre koltuk bilgisini getirir.
     *
     * @param trainId Trenin ID'si.
     * @param seatNumber Koltuk numarası.
     * @return Koltuk bilgisi DTO olarak döner.
     */
    public SeatDTO getSeatByTrainIdAndSeatNumber(Long trainId, int seatNumber) {
        SeatEntity seat = seatRepository.findByTrain_TrainIdAndSeatNumber(trainId, seatNumber);
        return convertToDTO(seat);
    }

    /**
     * Koltuğu rezerve eder.
     *
     * @param seatId Koltuk ID'si.
     * @return Güncellenmiş koltuk bilgisi DTO olarak döner.
     */
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

    /**
     * Koltuğu rezervasyondan çıkarır.
     *
     * @param seatId Koltuk ID'si.
     * @return Güncellenmiş koltuk bilgisi DTO olarak döner.
     */
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

    /**
     * SeatEntity'den SeatDTO'ya dönüştürme.
     */
    private SeatDTO convertToDTO(SeatEntity seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(seat.getSeatId());
        seatDTO.setSeatNumber(seat.getSeatNumber());
        seatDTO.setReserved(seat.isReserved());
        return seatDTO;
    }
    
    
}
