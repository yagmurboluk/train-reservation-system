package com.project.trainreservation.service;

import com.project.trainreservation.dto.ETicketDTO;
import com.project.trainreservation.entity.ETicketEntity;
import com.project.trainreservation.entity.TicketEntity;
import com.project.trainreservation.repository.ETicketRepository;
import com.project.trainreservation.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ETicketService {

    @Autowired
    private ETicketRepository eTicketRepository;

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * E-Ticket oluşturur.
     */
    public ETicketDTO createETicket(Long ticketId) {
        TicketEntity ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));

        if (eTicketRepository.existsByTicket_TicketId(ticketId)) {
            throw new RuntimeException("E-Ticket already exists for this ticket!");
        }

        ETicketEntity eTicket = new ETicketEntity();
        eTicket.setTicket(ticket);
        eTicket.setIssueDate(LocalDateTime.now());
        eTicket.setQrCode(UUID.randomUUID().toString());

        ETicketEntity savedETicket = eTicketRepository.save(eTicket);
        return convertToDTO(savedETicket);
    }

    /**
     * QR kodu ile E-Ticket sorgular.
     */
    public ETicketDTO getETicketByQrCode(String qrCode) {
        ETicketEntity eTicket = eTicketRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("E-Ticket not found with QR code: " + qrCode));

        return convertToDTO(eTicket);
    }

    /**
     * ETicketEntity'den ETicketDTO'ya dönüşüm yapar.
     */
    private ETicketDTO convertToDTO(ETicketEntity eTicket) {
        ETicketDTO dto = new ETicketDTO();
        dto.setETicketId(eTicket.getETicketId());
        dto.setTicketId(eTicket.getTicket().getTicketId());
        dto.setFrom(eTicket.getTicket().getFrom());
        dto.setTo(eTicket.getTicket().getTo());
        dto.setTravelDate(eTicket.getTicket().getTravelDate().atStartOfDay());
        dto.setIssueDate(eTicket.getIssueDate());
        dto.setQrCode(eTicket.getQrCode());
        return dto;
    }
    
    public ETicketDTO getETicketByTicketId(Long ticketId) {
        ETicketEntity eTicket = eTicketRepository.findByTicket_TicketId(ticketId)
                .orElseThrow(() -> new RuntimeException("E-Ticket not found for ticket ID: " + ticketId));
        return convertToDTO(eTicket);
    }

}
