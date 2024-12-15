package com.project.trainreservation.repository;

import com.project.trainreservation.entity.ETicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ETicketRepository extends JpaRepository<ETicketEntity, Long> {
    boolean existsByTicket_TicketId(Long ticketId);

    
    Optional<ETicketEntity> findByQrCode(String qrCode);
    
    
    Optional<ETicketEntity> findByTicket_TicketId(Long ticketId); // Yeni eklenen metot
}
