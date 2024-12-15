package com.project.trainreservation.repository;

import com.project.trainreservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    
    List<ReservationEntity> findByUser_UserId(Long userId);

    
    List<ReservationEntity> findByTrain_TrainId(Long trainId);

    
    List<ReservationEntity> findByTravelDate(LocalDate travelDate);

    
    List<ReservationEntity> findByUser_UserIdAndTrain_TrainId(Long userId, Long trainId);
}
