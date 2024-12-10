package com.project.trainreservation.controller;

import com.project.trainreservation.dto.ETicketDTO;
import com.project.trainreservation.dto.TicketIdRequestDTO; // Yeni DTO
import com.project.trainreservation.service.ETicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/etickets")
public class ETicketController {

    @Autowired
    private ETicketService eTicketService;

    /**
     * E-Ticket oluşturur.
     */
    @PostMapping
    public ResponseEntity<ETicketDTO> createETicket(@RequestBody TicketIdRequestDTO request) {
        ETicketDTO eTicketDTO = eTicketService.createETicket(request.getTicketId());
        return ResponseEntity.ok(eTicketDTO);
    }

    /**
     * QR kod ile E-Ticket getirir.
     */
    @GetMapping("/qr/{qrCode}")
    public ResponseEntity<ETicketDTO> getETicketByQrCode(@PathVariable String qrCode) {
        ETicketDTO eTicketDTO = eTicketService.getETicketByQrCode(qrCode);
        return ResponseEntity.ok(eTicketDTO);
    }
    
    /**
     * Ticket ID ile E-Ticket getirir.
     */
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<ETicketDTO> getETicketByTicketId(@PathVariable Long ticketId) {
        ETicketDTO eTicketDTO = eTicketService.getETicketByTicketId(ticketId);
        return ResponseEntity.ok(eTicketDTO);
    }
}
