CREATE TABLE seats (
    seat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_number INT NOT NULL,
    reserved BOOLEAN DEFAULT FALSE,
    train_id BIGINT NOT NULL,
    FOREIGN KEY (train_id) REFERENCES trains(train_id) ON DELETE CASCADE
);
