package com.project.trainreservation.dto;

import java.time.LocalDateTime;

public class ETicketDTO {
    private Long eTicketId;
    private Long ticketId;
    private String from;
    private String to;
    private LocalDateTime travelDate;
    private LocalDateTime issueDate;
    private String qrCode;

    // Parametresiz Constructor
    public ETicketDTO() {
    }

    // Tüm alanları içeren Constructor
    public ETicketDTO(Long eTicketId, Long ticketId, String from, String to, LocalDateTime travelDate, LocalDateTime issueDate, String qrCode) {
        this.eTicketId = eTicketId;
        this.ticketId = ticketId;
        this.from = from;
        this.to = to;
        this.travelDate = travelDate;
        this.issueDate = issueDate;
        this.qrCode = qrCode;
    }

    // Getter ve Setter'lar
    public Long getETicketId() {
        return eTicketId;
    }

    public void setETicketId(Long eTicketId) {
        this.eTicketId = eTicketId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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

    public LocalDateTime getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDateTime travelDate) {
        this.travelDate = travelDate;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        return "ETicketDTO{" +
                "eTicketId=" + eTicketId +
                ", ticketId=" + ticketId +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", travelDate=" + travelDate +
                ", issueDate=" + issueDate +
                ", qrCode='" + qrCode + '\'' +
                '}';
    }
}
