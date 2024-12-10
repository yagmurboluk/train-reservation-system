package com.project.trainreservation.repository;

import com.project.trainreservation.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    /**
     * Bir trene ait tüm koltukları getirir.
     *
     * @param trainId Trenin ID'si.
     * @return Trene ait tüm koltukların listesi.
     */
    List<SeatEntity> findByTrain_TrainId(Long trainId);

    /**
     * Bir trene ait rezerve edilmemiş koltukları getirir.
     *
     * @param trainId Trenin ID'si.
     * @return Trene ait rezerve edilmemiş koltukların listesi.
     */
    List<SeatEntity> findByTrain_TrainIdAndReservedFalse(Long trainId);

    /**
     * Bir trene ait rezerve edilmiş koltukları getirir.
     *
     * @param trainId Trenin ID'si.
     * @return Trene ait rezerve edilmiş koltukların listesi.
     */
    List<SeatEntity> findByTrain_TrainIdAndReservedTrue(Long trainId);

    /**
     * Belirli bir koltuk numarasına göre koltuğu getirir.
     *
     * @param trainId Trenin ID'si.
     * @param seatNumber Koltuk numarası.
     * @return Belirli bir koltuğun bilgisi.
     */
    SeatEntity findByTrain_TrainIdAndSeatNumber(Long trainId, int seatNumber);
}
