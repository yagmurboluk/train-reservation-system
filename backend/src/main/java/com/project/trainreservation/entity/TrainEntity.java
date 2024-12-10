package com.project.trainreservation.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trains")
public class TrainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    private Long trainId;

    @Column(name = "train_name", nullable = false, length = 255)
    private String trainName;

    @Column(name = "departure_station", nullable = false, length = 255) // departure_city yerine departure_station kullanıldı
    private String departureStation;

    @Column(name = "arrival_station", nullable = false, length = 255) // arrival_city yerine arrival_station kullanıldı
    private String arrivalStation;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    // SeatEntity ile ilişki
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatEntity> seats = new ArrayList<>();

    // Getter ve Setter'lar
    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getDepartureStation() { // departure_city yerine departure_station
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() { // arrival_city yerine arrival_station
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<SeatEntity> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatEntity> seats) {
        this.seats = seats;
    }
}
