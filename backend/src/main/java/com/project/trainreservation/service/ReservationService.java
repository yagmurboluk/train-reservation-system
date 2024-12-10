package com.project.trainreservation.service;

import com.project.trainreservation.dto.ReservationDTO;
import com.project.trainreservation.entity.ReservationEntity;
import com.project.trainreservation.entity.TrainEntity;
import com.project.trainreservation.entity.UserEntity;
import com.project.trainreservation.repository.ReservationRepository;
import com.project.trainreservation.repository.TrainRepository;
import com.project.trainreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainRepository trainRepository;

    /**
     * ReservationDTO'dan ReservationEntity'ye dönüştürme.
     */
    private ReservationEntity convertToEntity(ReservationDTO reservationDTO) {
        ReservationEntity reservation = new ReservationEntity();

        UserEntity user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + reservationDTO.getUserId()));
        TrainEntity train = trainRepository.findById(reservationDTO.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found with ID: " + reservationDTO.getTrainId()));

        reservation.setUser(user);
        reservation.setTrain(train);
        reservation.setTravelDate(reservationDTO.getTravelDate());
        reservation.setSeatNumber(reservationDTO.getSeatNumber());
        reservation.setTicketType(reservationDTO.getTicketType());
        reservation.setClassType(reservationDTO.getClassType());

        return reservation;
    }

    /**
     * ReservationEntity'den ReservationDTO'ya dönüştürme.
     */
    private ReservationDTO convertToDTO(ReservationEntity reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setReservationId(reservation.getReservationId());
        dto.setUserId(reservation.getUser().getUserId());
        dto.setTrainId(reservation.getTrain().getTrainId());
        dto.setTravelDate(reservation.getTravelDate());
        dto.setSeatNumber(reservation.getSeatNumber());
        dto.setTicketType(reservation.getTicketType());
        dto.setClassType(reservation.getClassType());
        return dto;
    }

    /**
     * Yeni bir rezervasyon oluşturur.
     */
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        ReservationEntity reservation = convertToEntity(reservationDTO);
        ReservationEntity savedReservation = reservationRepository.save(reservation);
        return convertToDTO(savedReservation);
    }

    /**
     * Tüm rezervasyonları getirir.
     */
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Belirli bir rezervasyonu ID'ye göre getirir.
     */
    public ReservationDTO getReservationById(Long reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + reservationId));
        return convertToDTO(reservation);
    }

    /**
     * Belirli bir kullanıcıya ait rezervasyonları getirir.
     */
    public List<ReservationDTO> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUser_UserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Belirli bir trene ait rezervasyonları getirir.
     */
    public List<ReservationDTO> getReservationsByTrainId(Long trainId) {
        return reservationRepository.findByTrain_TrainId(trainId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Belirli bir seyahat tarihindeki rezervasyonları getirir.
     */
    public List<ReservationDTO> getReservationsByTravelDate(LocalDate travelDate) {
        return reservationRepository.findByTravelDate(travelDate).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Rezervasyonu günceller.
     */
    public ReservationDTO updateReservation(Long reservationId, ReservationDTO reservationDTO) {
        ReservationEntity existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + reservationId));

        existingReservation.setTravelDate(reservationDTO.getTravelDate());
        existingReservation.setSeatNumber(reservationDTO.getSeatNumber());
        existingReservation.setTicketType(reservationDTO.getTicketType());
        existingReservation.setClassType(reservationDTO.getClassType());

        ReservationEntity updatedReservation = reservationRepository.save(existingReservation);
        return convertToDTO(updatedReservation);
    }

    /**
     * Rezervasyonu siler.
     */
    public void deleteReservation(Long reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + reservationId));
        reservationRepository.delete(reservation);
    }
}
