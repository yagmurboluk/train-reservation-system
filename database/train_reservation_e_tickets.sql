CREATE TABLE e_tickets (
    e_ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_id BIGINT NOT NULL,
    issue_date DATETIME NOT NULL,
    qr_code VARCHAR(255) NOT NULL UNIQUE,
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id) ON DELETE CASCADE
);
