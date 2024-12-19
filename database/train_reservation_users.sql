CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL DEFAULT 'defaultPassword123',
    gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL
);

INSERT INTO users (name, last_name, email, password, gender)
VALUES ('Test', 'User', 'test@example.com', 'password123', 'MALE');

