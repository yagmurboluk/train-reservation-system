package com.project.trainreservation.dto;

public class TicketIdRequestDTO {
    private Long ticketId;

    // Constructor
    public TicketIdRequestDTO() {
    }

    public TicketIdRequestDTO(Long ticketId) {
        this.ticketId = ticketId;
    }

    // Getter ve Setter
    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "TicketIdRequestDTO{" +
                "ticketId=" + ticketId +
                '}';
    }
}
