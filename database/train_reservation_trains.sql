CREATE TABLE trains (
    train_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    train_name VARCHAR(255) NOT NULL,
    departure_station VARCHAR(255) NOT NULL,
    arrival_station VARCHAR(255) NOT NULL,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    capacity INT NOT NULL
);

INSERT INTO trains (train_name, departure_station, arrival_station, departure_time, arrival_time, capacity)
VALUES 
('Mavi Ekspres', 'İstanbul', 'İzmir', '2024-12-12 09:00:00', '2024-12-12 19:00:00', 40);

