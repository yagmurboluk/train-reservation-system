# Train Reservation System

The **Train Reservation System** is a comprehensive full-stack application designed to streamline train ticket booking and reservation management. Built with modern technologies, the project aims to deliver a seamless and user-friendly experience for travelers.

---

## Features

### **1. Train Ticket Booking**
- Users can search for trains based on departure and destination stations, travel date, and the number of passengers.
- Displays available tickets dynamically, complete with randomized pricing to simulate a real-world booking experience.

### **2. Weather Information**
- Integrated with the OpenWeatherMap API to provide real-time weather updates for selected cities.
- Displays key weather details such as:
  - Temperature
  - Feels-like temperature
  - Humidity
  - Wind speed
  - A brief description of the weather (e.g., sunny, cloudy).

### **3. QR Code Ticketing**
- Each booked ticket is linked to a unique QR code for easy verification during travel.

### **4. Responsive User Interface**
- Frontend built with **React.js**, ensuring a smooth, dynamic, and responsive user interface suitable for both desktop and mobile platforms.

### **5. Database Management**
- Efficient data handling using **MySQL** to manage users, trains, tickets, and reservations.
- Ensures data consistency and allows advanced querying with the help of **Hibernate ORM**.

---

## Technologies Used

### **Backend**
- **Spring Boot**: A robust framework for backend development, simplifying dependency management.
- **Hibernate**: Facilitates easy interaction with the MySQL database through JPA.
- **Maven**: A reliable build automation tool for managing dependencies and the project lifecycle.
- **MySQL**: Securely stores user, train, and reservation data.
- **JDK 17**: The application is built and runs on Java Development Kit version 17.

### **Frontend**
- **React.js**: Ensures a dynamic and highly interactive user experience.
- **Axios**: Handles seamless HTTP requests between the frontend and backend.

### **Development Tools**
- **Eclipse IDE (2024-09)**: Utilized for backend development and integration.
- **Visual Studio Code**: Employed for developing and managing the React-based frontend.
- **Git**: Tracks and manages changes in the project codebase.
- **Postman**: Used extensively for API testing and validation.

### **Other Tools and Libraries**
- **OpenWeatherMap API**: Fetches real-time weather information for cities.
- **QRCode.react Library**: Generates QR codes for tickets within the React application.

---

## How It All Comes Together

This system simplifies ticket booking with core functionalities like:
- Searching for trains.
- Viewing weather updates for better travel planning.
- Receiving QR-coded tickets to streamline verification.

The backend ensures smooth operations with Spring Boot, Hibernate, and MySQL, while the frontend brings it all to life with a sleek React.js interface. The addition of weather updates and QR codes offers a unique and valuable feature set for users.

This project showcases modern development techniques and tools, making it an excellent foundation for any scalable and responsive web application.
