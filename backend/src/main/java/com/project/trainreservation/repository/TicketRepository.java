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

    /**
     * Kullanıcı ID'ye göre biletleri bulur.
     * @param userId Kullanıcı ID.
     * @return Kullanıcının bilet listesi.
     */
    List<TicketEntity> findByUser_UserId(Long userId);

    /**
     * Tren ID'ye göre biletleri bulur.
     * @param trainId Tren ID.
     * @return Trene ait bilet listesi.
     */
    List<TicketEntity> findByTrain_TrainId(Long trainId);

    /**
     * Rezervasyon durumuna göre biletleri bulur.
     * @param status Rezervasyon durumu (BOOKED, CANCELLED, PENDING).
     * @return Belirli bir durumdaki biletlerin listesi.
     */
    List<TicketEntity> findByStatus(BookingStatus status);

    /**
     * Belirli bir kullanıcı ve tren kombinasyonu için biletleri bulur.
     * @param userId Kullanıcı ID.
     * @param trainId Tren ID.
     * @return Kullanıcı ve tren bilgisine göre bilet listesi.
     */
    List<TicketEntity> findByUser_UserIdAndTrain_TrainId(Long userId, Long trainId);

    /**
     * Belirli bir seyahat tarihi için biletleri bulur.
     * @param travelDate Seyahat tarihi.
     * @return Seyahat tarihine göre biletlerin listesi.
     */
    List<TicketEntity> findByTravelDate(LocalDate travelDate);

    /**
     * Belirli bir kullanıcı ve seyahat tarihi için biletleri bulur.
     * @param userId Kullanıcı ID.
     * @param travelDate Seyahat tarihi.
     * @return Kullanıcı ve seyahat tarihine göre biletlerin listesi.
     */
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
