package com.project.trainreservation.service;

import com.project.trainreservation.dto.TicketDTO;
import com.project.trainreservation.entity.TicketEntity;
import com.project.trainreservation.entity.TrainEntity;
import com.project.trainreservation.entity.UserEntity;
import com.project.trainreservation.enums.BookingStatus;
import com.project.trainreservation.repository.TicketRepository;
import com.project.trainreservation.repository.TrainRepository;
import com.project.trainreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainRepository trainRepository;

    /**
     * Yeni bir bilet oluşturur.
     */
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        // Kullanıcıyı veritabanından getir
        UserEntity user = userRepository.findById(ticketDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + ticketDTO.getUserId()));

        // Treni veritabanından getir
        TrainEntity train = trainRepository.findById(ticketDTO.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found with ID: " + ticketDTO.getTrainId()));

        // Eğer bookingDate null ise bugünün tarihini ata
        if (ticketDTO.getBookingDate() == null) {
            ticketDTO.setBookingDate(LocalDate.now());
        }

        // TicketEntity oluştur ve verileri ayarla
        TicketEntity ticket = new TicketEntity();
        ticket.setUser(user);
        ticket.setTrain(train);
        ticket.setBookingDate(ticketDTO.getBookingDate());
        ticket.setTravelDate(ticketDTO.getTravelDate());
        ticket.setSeatNumber(ticketDTO.getSeatNumber());
        ticket.setStatus(ticketDTO.getStatus());

        // Tren bilgilerini biletin kalkış ve varış noktalarına ayarla
        ticket.setFrom(train.getDepartureStation());
        ticket.setTo(train.getArrivalStation());

        // Bileti kaydet
        TicketEntity savedTicket = ticketRepository.save(ticket);

        // Kaydedilen bileti DTO'ya dönüştür ve döndür
        return convertToDTO(savedTicket);
    }

    /**
     * Tüm biletleri getirir.
     */
    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * ID'ye göre belirli bir bilet getirir.
     */
    public TicketDTO getTicketById(Long ticketId) {
        TicketEntity ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));
        return convertToDTO(ticket);
    }

    /**
     * Bir biletin durumunu günceller.
     */
    public TicketDTO updateTicketStatus(Long ticketId, BookingStatus status) {
        TicketEntity ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));

        ticket.setStatus(status); // Durumu güncelle
        TicketEntity updatedTicket = ticketRepository.save(ticket);

        return convertToDTO(updatedTicket);
    }

    /**
     * Bir bileti siler.
     */
    public void deleteTicket(Long ticketId) {
        TicketEntity ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));
        ticketRepository.delete(ticket);
    }

    /**
     * Filtreleme yaparak biletleri getirir.
     */
    public List<TicketDTO> filterTickets(String from, String to, String date, int passengers) {
        List<TicketEntity> filteredTickets = ticketRepository.findFilteredTickets(from, to, LocalDate.parse(date), passengers);
        return filteredTickets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * TicketEntity'den TicketDTO'ya dönüştürme işlemi.
     */
    private TicketDTO convertToDTO(TicketEntity ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setTicketId(ticket.getTicketId());
        dto.setUserId(ticket.getUser().getUserId());
        dto.setTrainId(ticket.getTrain().getTrainId());
        dto.setFrom(ticket.getFrom());
        dto.setTo(ticket.getTo());
        dto.setBookingDate(ticket.getBookingDate());
        dto.setTravelDate(ticket.getTravelDate());
        dto.setSeatNumber(ticket.getSeatNumber());
        dto.setStatus(ticket.getStatus());
        return dto;
    }
}
