CREATE TABLE tickets (
    ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    train_id BIGINT NOT NULL,
    from_station VARCHAR(255) NOT NULL,
    to_station VARCHAR(255) NOT NULL,
    booking_date DATE NULL,
    travel_date DATE NOT NULL,
    seat_number INT NOT NULL,
    status ENUM('BOOKED', 'CANCELLED', 'PENDING') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (train_id) REFERENCES trains(train_id) ON DELETE CASCADE
);
