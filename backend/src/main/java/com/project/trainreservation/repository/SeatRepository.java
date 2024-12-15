package com.project.trainreservation.repository;

import com.project.trainreservation.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    List<SeatEntity> findByTrain_TrainId(Long trainId);

    
    List<SeatEntity> findByTrain_TrainIdAndReservedFalse(Long trainId);

    
    List<SeatEntity> findByTrain_TrainIdAndReservedTrue(Long trainId);

    
    SeatEntity findByTrain_TrainIdAndSeatNumber(Long trainId, int seatNumber);
}
