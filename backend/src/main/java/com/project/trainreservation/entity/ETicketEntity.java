package com.project.trainreservation.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "e_tickets")
public class ETicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eTicketId;

    @OneToOne
    @JoinColumn(name = "ticket_id", nullable = false, unique = true)
    private TicketEntity ticket;

    @Column(name = "issue_date", nullable = false)
    private LocalDateTime issueDate;

    @Column(name = "qr_code", nullable = false, unique = true)
    private String qrCode;

    
    public ETicketEntity() {
    }

    public ETicketEntity(Long eTicketId, TicketEntity ticket, LocalDateTime issueDate, String qrCode) {
        this.eTicketId = eTicketId;
        this.ticket = ticket;
        this.issueDate = issueDate;
        this.qrCode = qrCode;
    }

   
    public Long getETicketId() {
        return eTicketId;
    }

    public void setETicketId(Long eTicketId) {
        this.eTicketId = eTicketId;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
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
        return "ETicketEntity{" +
                "eTicketId=" + eTicketId +
                ", ticket=" + ticket +
                ", issueDate=" + issueDate +
                ", qrCode='" + qrCode + '\'' +
                '}';
    }
}
