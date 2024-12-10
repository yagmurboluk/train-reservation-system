package com.project.trainreservation.entity;

import com.project.trainreservation.enums.BookingStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private TrainEntity train;

    @Column(name = "from_station", nullable = false)
    private String from; // Kalkış istasyonu

    @Column(name = "to_station", nullable = false)
    private String to; // Varış istasyonu

    private LocalDate bookingDate;

    private LocalDate travelDate;

    private int seatNumber;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    // Getter ve Setter'lar
    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TrainEntity getTrain() {
        return train;
    }

    public void setTrain(TrainEntity train) {
        this.train = train;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
