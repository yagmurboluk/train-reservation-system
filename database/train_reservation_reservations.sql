CREATE TABLE reservations (
    reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    train_id BIGINT NOT NULL,
    travel_date DATE NOT NULL,
    seat_number INT NOT NULL,
    ticket_type VARCHAR(50),
    class_type VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (train_id) REFERENCES trains(train_id) ON DELETE CASCADE
);
