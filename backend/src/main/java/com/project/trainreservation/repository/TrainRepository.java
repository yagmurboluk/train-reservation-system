package com.project.trainreservation.repository;

import com.project.trainreservation.entity.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<TrainEntity, Long> {

    
    List<TrainEntity> findByDepartureStation(String departureStation);

    
    List<TrainEntity> findByCapacityGreaterThanEqual(int capacity);

   
    List<TrainEntity> findByDepartureStationAndArrivalStation(String departureStation, String arrivalStation);

   
    List<TrainEntity> findByDepartureStationOrderByDepartureTimeAsc(String departureStation);

    
    List<TrainEntity> findByDepartureStationAndDepartureTimeAfter(String departureStation, LocalDateTime departureTime);
}
