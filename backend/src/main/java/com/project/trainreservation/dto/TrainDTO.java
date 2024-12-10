package com.project.trainreservation.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TrainDTO {
    private Long trainId;
    private String trainName;
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int capacity;

    // Seat bilgilerini ekliyoruz
    private List<SeatDTO> seats;

    // Getter ve Setter

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

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
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

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "TrainDTO{" +
                "trainId=" + trainId +
                ", trainName='" + trainName + '\'' +
                ", departureStation='" + departureStation + '\'' +
                ", arrivalStation='" + arrivalStation + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", capacity=" + capacity +
                ", seats=" + seats +
                '}';
    }
}
