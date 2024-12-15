package com.project.trainreservation.dto;

public class TicketIdRequestDTO {
    private Long ticketId;

    
    public TicketIdRequestDTO() {
    }

    public TicketIdRequestDTO(Long ticketId) {
        this.ticketId = ticketId;
    }

   
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
