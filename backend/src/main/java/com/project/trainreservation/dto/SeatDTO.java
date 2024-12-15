package com.project.trainreservation.dto;

public class SeatDTO {
    private Long seatId;
    private int seatNumber;
    private boolean reserved;
    private Long trainId; 

   
    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    @Override
    public String toString() {
        return "SeatDTO{" +
                "seatId=" + seatId +
                ", seatNumber=" + seatNumber +
                ", reserved=" + reserved +
                ", trainId=" + trainId +
                '}';
    }
}
