package com.project.trainreservation.controller;

import com.project.trainreservation.dto.TicketDTO;
import com.project.trainreservation.enums.BookingStatus;
import com.project.trainreservation.response.ErrorResponse;
import com.project.trainreservation.service.TicketService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

   
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    
    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody TicketDTO ticketDTO) {
        if (ticketDTO.getUserId() == null || ticketDTO.getTrainId() == null) {
            return ResponseEntity.badRequest().body("UserId ve TrainId gerekli!");
        }
        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }

    
    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable("id") Long ticketId) {
        TicketDTO ticket = ticketService.getTicketById(ticketId);
        return ResponseEntity.ok(ticket);
    }

   
    @PutMapping("/{id}/status")
    public ResponseEntity<TicketDTO> updateTicketStatus(
            @PathVariable("id") Long ticketId,
            @RequestParam("status") BookingStatus status) {
        TicketDTO updatedTicket = ticketService.updateTicketStatus(ticketId, status);
        return ResponseEntity.ok(updatedTicket);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("id") Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok("Ticket deleted successfully.");
    }

    
    @GetMapping("/filter")
    public ResponseEntity<List<TicketDTO>> getFilteredTickets(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String date,
            @RequestParam int passengers) {

       
        List<TicketDTO> filteredTickets = ticketService.filterTickets(from, to, date, passengers);

        return ResponseEntity.ok(filteredTickets);
    }

}
