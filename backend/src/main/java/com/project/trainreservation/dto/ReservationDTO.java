package com.project.trainreservation.dto;

import java.time.LocalDate;

public class ReservationDTO {

    private Long reservationId;
    private Long userId;
    private Long trainId;
    private LocalDate travelDate; // Tarih alanı LocalDate olarak tanımlandı
    private int seatNumber;
    private String ticketType; // Bilet türü (Tek Yön, Gidiş-Dönüş vb.)
    private String classType; // Sınıf türü (Ekonomi, Business, First Class)

    // Getter ve Setter
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "reservationId=" + reservationId +
                ", userId=" + userId +
                ", trainId=" + trainId +
                ", travelDate=" + travelDate +
                ", seatNumber=" + seatNumber +
                ", ticketType='" + ticketType + '\'' +
                ", classType='" + classType + '\'' +
                '}';
    }
}
