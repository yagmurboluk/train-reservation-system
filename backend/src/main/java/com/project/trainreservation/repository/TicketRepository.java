package com.project.trainreservation.repository;

import com.project.trainreservation.entity.TicketEntity;
import com.project.trainreservation.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

   
    List<TicketEntity> findByUser_UserId(Long userId);

    
    List<TicketEntity> findByTrain_TrainId(Long trainId);

    
    List<TicketEntity> findByStatus(BookingStatus status);

    
    List<TicketEntity> findByUser_UserIdAndTrain_TrainId(Long userId, Long trainId);

    
    List<TicketEntity> findByTravelDate(LocalDate travelDate);

    
    List<TicketEntity> findByUser_UserIdAndTravelDate(Long userId, LocalDate travelDate);
    
    
    @Query("SELECT t FROM TicketEntity t WHERE " +
            "t.train.departureStation = :from AND " +
            "t.train.arrivalStation = :to AND " +
            "t.travelDate = :date AND " +
            "t.train.capacity >= :passengers")
    List<TicketEntity> findFilteredTickets(@Param("from") String from,
                                           @Param("to") String to,
                                           @Param("date") LocalDate date,
                                           @Param("passengers") int passengers);
}
