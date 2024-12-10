package com.project.trainreservation.repository;

import com.project.trainreservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    /**
     * Belirli bir kullanıcının tüm rezervasyonlarını getirir.
     *
     * @param userId Kullanıcının ID'si.
     * @return Kullanıcının tüm rezervasyonlarının listesi.
     */
    List<ReservationEntity> findByUser_UserId(Long userId);

    /**
     * Belirli bir tren için rezervasyonları getirir.
     *
     * @param trainId Trenin ID'si.
     * @return Belirli bir tren için yapılan rezervasyonların listesi.
     */
    List<ReservationEntity> findByTrain_TrainId(Long trainId);

    /**
     * Belirli bir tarihte yapılan rezervasyonları getirir.
     *
     * @param travelDate Seyahat tarihi.
     * @return Belirtilen tarihteki rezervasyonların listesi.
     */
    List<ReservationEntity> findByTravelDate(LocalDate travelDate);

    /**
     * Belirli bir kullanıcı ve tren için rezervasyonları getirir.
     *
     * @param userId Kullanıcının ID'si.
     * @param trainId Trenin ID'si.
     * @return Kullanıcı ve tren eşleşmesi olan rezervasyonların listesi.
     */
    List<ReservationEntity> findByUser_UserIdAndTrain_TrainId(Long userId, Long trainId);
}
