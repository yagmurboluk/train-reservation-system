package com.project.trainreservation.repository;

import com.project.trainreservation.entity.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<TrainEntity, Long> {

    /**
     * Kalkış istasyonuna göre trenleri getirir.
     *
     * @param departureStation Kalkış istasyonu.
     * @return Uygun trenlerin listesi.
     */
    List<TrainEntity> findByDepartureStation(String departureStation);

    /**
     * Kapasiteye göre trenleri getirir.
     *
     * @param capacity Minimum kapasite.
     * @return Kapasiteye uygun trenlerin listesi.
     */
    List<TrainEntity> findByCapacityGreaterThanEqual(int capacity);

    /**
     * Kalkış ve varış istasyonlarına göre trenleri getirir.
     *
     * @param departureStation Kalkış istasyonu.
     * @param arrivalStation Varış istasyonu.
     * @return Uygun trenlerin listesi.
     */
    List<TrainEntity> findByDepartureStationAndArrivalStation(String departureStation, String arrivalStation);

    /**
     * Kalkış zamanına göre trenleri sıralar.
     *
     * @param departureStation Kalkış istasyonu.
     * @return Kalkış zamanına göre sıralanmış trenlerin listesi.
     */
    List<TrainEntity> findByDepartureStationOrderByDepartureTimeAsc(String departureStation);

    /**
     * Belirli bir tarihte kalkacak trenleri getirir.
     *
     * @param departureStation Kalkış istasyonu.
     * @param departureTime Kalkış zamanı.
     * @return Belirli kalkış tarihindeki trenlerin listesi.
     */
    List<TrainEntity> findByDepartureStationAndDepartureTimeAfter(String departureStation, LocalDateTime departureTime);
}
